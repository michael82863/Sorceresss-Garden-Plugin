package com.majesty373.sorceresssgarden;

import com.majesty373.sorceresssgarden.elementals.SorceresssGardenAutumnElemental;
import com.majesty373.sorceresssgarden.elementals.SorceresssGardenElemental;
import com.majesty373.sorceresssgarden.elementals.SorceresssGardenWinterElemental;
import com.majesty373.sorceresssgarden.elementals.SorceresssGardenSpringElemental;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;

@Singleton
public class SorceresssGardenOverlay extends Overlay {

	private final Client client;
	private final SorceresssGardenConfig config;
	private final SorceresssGardenElemental elemental;
	private Enum<SorceresssGarden> previousGarden;
	private static final WorldPoint LAUNCH_POINT_WINTER = new WorldPoint(2902, 5470, 0);
	private static final WorldPoint LAUNCH_POINT_SPRING = new WorldPoint(2921, 5473, 0);
	private static final WorldPoint LAUNCH_POINT_AUTUMN = new WorldPoint(2908, 5461, 0);

	@Inject
	public SorceresssGardenOverlay(Client client, SorceresssGardenConfig config, SorceresssGardenElemental elemental) {
		this.client = client;
		this.config = config;
		this.elemental = elemental;
		setPosition(OverlayPosition.DYNAMIC);
		previousGarden = config.garden();
	}

	/**
	 * This method sorts the inputted NPC array and returns it sorted from the lowest ID to the largest ID
	 * @param npcs The array of NPCs to be sorted
	 * @return NPC array that has been sorted
	 */
	public NPC[] bubbleSort(NPC[] npcs) {
		int lastPos, index;
		NPC temp;
		for (lastPos = npcs.length - 1; lastPos >= 0; lastPos--) {
			for (index = 0; index < lastPos; index++) {
				if (npcs[index].getId() > npcs[index + 1].getId()) {
					temp = npcs[index];
					npcs[index] = npcs[index + 1];
					npcs[index + 1] = temp;
				}
			}
		}
		return npcs;
	}

	private void addElementalToList(Graphics2D graphics, NPC[] npcs) {
		for (NPC npc : npcs) { // for each NPC
			elemental.addToElementalList(npc.getId(), config); // create an elemental and add it to the list if it is not on it already
			SorceresssGardenElemental elem = elemental.getElemental(npc.getId()); // then get that elemental
			if (elem != null) {
				elem.setNPC(npc); // update the NPC variable in the Elemental object. This is used to get the elemental's current location

				////////
				if (SorceresssGardenPlugin.DEBUG) {
					String text = Math.max(0, elem.getPathIndex(elem.npc.getWorldLocation())) + "";
					final Point textLocation = Perspective.getCanvasTextLocation(client, graphics, npc.getLocalLocation(), text, 0);
					if (textLocation != null) OverlayUtil.renderTextLocation(graphics, textLocation, text, Color.WHITE); // draw the path index of the elemental
					renderTile(graphics, npc.getWorldLocation(), Color.CYAN); // paint where the elemental is
				}
				////////
			}
		}

	}

	private void renderRunTiles(Graphics2D graphics, WorldPoint[] runTiles, WorldPoint launchPoint) {
		for (int i = 0; i < runTiles.length; i++) { // for each run tile
			int total = 0;
			for (SorceresssGardenElemental el : elemental.elementals) { // for each elemental
				int dist = el.insideGreenZone(i); // get how many steps until the next green zone
				if (dist > 0) {
					total += dist; // and add them up, this is the crude answer to how long until the tile turns green
				}
			}
			renderTile(graphics, launchPoint, Color.CYAN); // paint the starting tile by the gate
			final Point textLocation = Perspective.getCanvasTextLocation(client, graphics, LocalPoint.fromWorld(client.getWorldView(-1), runTiles[i]), total + "", 0);
			if (textLocation != null) OverlayUtil.renderTextLocation(graphics, textLocation, total + "", Color.WHITE); // draw the steps until the tile turns to green
			renderTile(graphics, runTiles[i], (total == 0 ? config.tilesGood() : config.tilesBad())); // paint the tile green or red
		}
	}

	/**
	 * This method renders and colors the run tiles and the start tile
	 * @param graphics Graphics2D from java.awt
	 * @return Null
	 */
	@Override
	public Dimension render(Graphics2D graphics) {
		if (previousGarden != config.garden()) {
			elemental.elementals.clear();
			previousGarden = config.garden();
		}
		switch (config.garden()) {
			case WINTER:
				addElementalToList(graphics, bubbleSort(client.getNpcs().stream().filter(SorceresssGardenElemental::isWinterElemental).toArray(NPC[]::new)));
				renderRunTiles(graphics, SorceresssGardenWinterElemental.WINTER_RUNTILES, LAUNCH_POINT_WINTER);
				break;
			case SPRING:
				addElementalToList(graphics, bubbleSort(client.getNpcs().stream().filter(SorceresssGardenElemental::isSpringElemental).toArray(NPC[]::new)));
				renderRunTiles(graphics, SorceresssGardenSpringElemental.SPRING_RUNTILES, LAUNCH_POINT_SPRING);
				break;
			case AUTUMN:
				addElementalToList(graphics, bubbleSort(client.getNpcs().stream().filter(SorceresssGardenElemental::isAutumnElemental).toArray(NPC[]::new)));
				renderRunTiles(graphics, SorceresssGardenAutumnElemental.AUTUMN_RUNTILES, LAUNCH_POINT_AUTUMN);
				break;
			case SUMMER:
				break;
		}
		return null;
	}

	/**
	 * This method takes the WorldPoint of a tile and renders a rectangle on it of the given color
	 * @param graphics Graphics2D from java.awt
	 * @param wp WorldPoint of where the tile is
	 * @param color Color to render the tile
	 */
	private void renderTile(Graphics2D graphics, WorldPoint wp, Color color) {
		LocalPoint lp = LocalPoint.fromWorld(client, wp);
		if (lp != null) {
			Polygon poly = Perspective.getCanvasTilePoly(client, lp);
			if (poly != null)
				OverlayUtil.renderPolygon(graphics, poly, color);
		}
	}

	private void renderText(Graphics2D graphics, Point point, String text, Color color) {
		OverlayUtil.renderTextLocation(graphics, point, text, color);

	}
}