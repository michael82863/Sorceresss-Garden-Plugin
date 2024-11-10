package com.majesty373.sorceresssgarden.elementals;

import com.majesty373.sorceresssgarden.SorceresssGardenConfig;
import com.majesty373.sorceresssgarden.SorceresssGardenPlugin;
import net.runelite.api.NPC;
import net.runelite.api.coords.WorldPoint;

import java.util.ArrayList;

public class SorceresssGardenElemental {

    public ArrayList<SorceresssGardenElemental> elementals = new ArrayList<SorceresssGardenElemental>();

    public int ID;
    public int cornersPast = 0;
    public int pathIndex = 0;
    public NPC npc;
    public ArrayList<WorldPoint> pathPoints = new ArrayList<WorldPoint>();
    public ArrayList<WorldPoint> corners = new ArrayList<WorldPoint>();
    protected int[][] greenZones;

    public SorceresssGardenElemental() {
    }

    protected void generatePath(WorldPoint[] corners) {
        for (int i = 0; i < corners.length; i++) {
            this.corners.add(corners[i]);
            int startX = corners[i].getX();
            int startY = corners[i].getY();
            int endX = (i == corners.length - 1) ? corners[0].getX() : corners[i + 1].getX();
            int endY = (i == corners.length - 1) ? corners[0].getY() : corners[i + 1].getY();

            // Determine the direction of movement
            int xDirection = Integer.compare(endX, startX);
            int yDirection = Integer.compare(endY, startY);

            // Add tiles from start to end
            for (int x = startX, y = startY; x != endX || y != endY; ) {
                pathPoints.add(new WorldPoint(x, y, 0));
                if (x != endX) {
                    x += xDirection;
                }
                if (y != endY) {
                    y += yDirection;
                }
            }
        }

    }

    /**
     * This method returns true if the NPC's ID is within the bounds of Spring Elementals
     * @param npc The NPC to be tested
     * @return Boolean on if the NPC is a Spring Elemental or not
     */
    public static boolean isSpringElemental(NPC npc) {
        return npc.getId() >= 2956 && npc.getId() <= 2963;
    }

    /**
     * This method returns true if the NPC's ID is within the bounds of Winter Elementals
     * @param npc The NPC to be tested
     * @return Boolean on if the NPC is a Winter Elemental or not
     */
    public static boolean isWinterElemental(NPC npc) {
        return npc.getId() >= 5796 && npc.getId() <= 5801;
    }

    /**
     * This method returns true if the NPC's ID is within the bounds of Autumn Elementals
     * @param npc The NPC to be tested
     * @return Boolean on if the NPC is an Autumn Elemental or not
     */
    public static boolean isAutumnElemental(NPC npc) {
        return npc.getId() >= 5802 && npc.getId() <= 5807;
    }

    public int getPathIndex(WorldPoint point) {
        if (cornersPast > 0) {
            for (int i = pathPoints.size() - 1; i >= 0; i--) {
                if (getIndex(point, pathPoints.get(i))) {
                    pathIndex = i;
                    return i;
                }
            }
        } else {
            for (int i = 0; i < pathPoints.size(); i++) {
                if (getIndex(point, pathPoints.get(i))) {
                    pathIndex = i;
                    return i;
                }

            }
        }
        pathIndex = 0;
        return -1;
    }

    private boolean getIndex(WorldPoint point, WorldPoint pathPoint) {
        if (point.getX() == pathPoint.getX() && point.getY() == pathPoint.getY() && point.getPlane() == pathPoint.getPlane()) {
            for (int j = 0; j < corners.size(); j++) {
                if (point.getX() == corners.get(j).getX() && point.getY() == corners.get(j).getY()) {
                    cornersPast = j;
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public int insideGreenZone(int runTile) {
        boolean help = false;

        if (greenZones == null) return 0;
        int[] array = greenZones[runTile]; // figure out what array of path indices to use
        if (array == null || array.length == 0) return 0; // no data for this NPC, so just return true

        int index = getPathIndex(npc.getWorldLocation()); // current path index of NPC
        if (index < 0) return 0; // NPC is not on the path for some reason

        for (int i = 0; i < array.length; i++) { // for each path index
            if (array[i] == index) { // the NPC is in the green zone
                int ind = index;
                int rounds = 1;
                boolean restartList = true;
                if (i + 1 >= array.length) i = array.length - 2; // * bad fix to allow the code to reset the list properly when the NPC index is the last index in the array
                for (int j = i + 1; j < array.length; j++) { // for each remaining index
                    if (SorceresssGardenPlugin.DEBUG && help) System.out.println("ID=" + ID + ", rT=" + runTile + ", In=" + index + ", ind=" + ind + ", rou=" + rounds + ", Pl=" + pathPoints.size() + ", R=" + restartList + ", i=" + i + ", j=" + j + ", arL=" + array.length + ", arr=" + array[j] + ", ind+rou=" + (ind + rounds));
                    if (array[j] > ind + rounds++) { // if the next index is above the current index plus j then there is a skipped index
                        int red = array[array.length - 1];
                        if (j - 1 >= 0) red = array[j - 1] + 1;
                        int len = 0;
                        if (!restartList) len = pathPoints.size(); // we wrapped around the list so we need to add that length
                        if (SorceresssGardenPlugin.DEBUG && help) System.out.println("return from green zone with valid answer: " + (-(len - index + red)));
                        return -(len - index + red); // the answer is the index of the next red zone tile minus the NPC current index plus the path length offset for wrapping around the list
                    }
                    if (j + 1 >= array.length) { // the array is about to be out of bounds, so restart the list if it hasn't been already.
                        if (restartList) {
                            restartList = false;
                            if (array[array.length - 1] < pathPoints.size() - 1) {
                                if (SorceresssGardenPlugin.DEBUG && help) System.out.println("return from green zone, short list, with valid answer: " + (-(array[array.length - 1] + 1 - index)));
                                return -(array[array.length - 1] + 1 - index); // the path is longer then the index of the last green zone tile, so that means that, if the first index of the array is not 0, then the next tile is a red zone tile.
                            }
                            j = -1;
                            ind = 0; // we need to change the value that is being compared to the value of 0
                            rounds = 0;
                        } else break; // list has already been reset once before, so just break the loop
                    }
                }
                if (SorceresssGardenPlugin.DEBUG) System.out.println("return from green zone with invalid answer!");
                return 0; // we wrapped around the list and also did not find the right answer. Should not reach here. NPC is in the green zone and there is no red zone on the path?
            }
        }
        if (SorceresssGardenPlugin.DEBUG && help) System.out.println("ID=" + ID + ", rT=" + runTile + ", In=" + index + ", Pl=" + pathPoints.size());
        // NPC is not in the green zone, so it must be in the red zone
        for (int arr : array) { // for every green zone index
            if (arr > index) {
                if (SorceresssGardenPlugin.DEBUG && help) System.out.println("return from red zone with valid answer: " + (arr - index));
                return arr - index; // if it is above the current NPC index then return the difference
            }
        }
        // couldn't find a green tile above the current NPC index
        // this means that the next green tile will be the first index in the array
        if (SorceresssGardenPlugin.DEBUG && help) System.out.println("return from red zone with the first valid answer: " + (pathPoints.size() - index + array[0]));
        return pathPoints.size() - index + array[0]; // the answer is the length of the path minus the current NPX index plus the index of the first green zone tile
    }

    public void setNPC(NPC npc) {
        this.npc = npc;
    }

    public void addToElementalList(int ID, SorceresssGardenConfig config) {
        if (getElemental(ID) == null) {
            switch (config.garden()) {
                case WINTER:
                    elementals.add(new SorceresssGardenWinterElemental(ID));
                    break;
                case SPRING:
                    elementals.add(new SorceresssGardenSpringElemental(ID));
                    break;
                case AUTUMN:
                    elementals.add(new SorceresssGardenAutumnElemental(ID));
                    break;
                case SUMMER:
                    break;

            }
        }
    }

    public SorceresssGardenElemental getElemental(int ID) {
        for (SorceresssGardenElemental el : elementals) {
            if (el.ID == ID) return el;
        }
        return null;
    }

}
