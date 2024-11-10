package com.majesty373.sorceresssgarden.elementals;

import net.runelite.api.coords.WorldPoint;

import javax.inject.Singleton;

@Singleton
public class SorceresssGardenWinterElemental extends SorceresssGardenElemental {

    public WorldPoint[] Winter5798Corner = { new WorldPoint(2897, 5471, 0), new WorldPoint(2899, 5471, 0), new WorldPoint(2899, 5478, 0), new WorldPoint(2897, 5478, 0)};
    public WorldPoint[] Winter5799Corner = { new WorldPoint(2900, 5480, 0), new WorldPoint(2897, 5480, 0), new WorldPoint(2897, 5481, 0), new WorldPoint(2896, 5481, 0), new WorldPoint(2896, 5483, 0), new WorldPoint(2900, 5483, 0)};
    public WorldPoint[] Winter5800Corner = { new WorldPoint(2896, 5481, 0), new WorldPoint(2891, 5481, 0), new WorldPoint(2891, 5483, 0), new WorldPoint(2896, 5483, 0)};
    public WorldPoint[] Winter5801Corner = { new WorldPoint(2889, 5485, 0), new WorldPoint(2900, 5485, 0)};

    public static final int[][] Winter5798GreenZone = { {7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17}, {0, 1, 2, 10, 11, 12, 13, 14, 15, 16, 17}, {}, {}, {} };
    public static final int[][] Winter5799GreenZone = { {}, {3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, {6, 7, 8, 9, 10, 11, 12, 13}, {6, 7, 8, 9, 10, 11, 12, 13}, {} };
    public static final int[][] Winter5800GreenZone = { {}, {}, {3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, {0, 1, 2, 3, 8, 9, 10, 11, 12, 13}, {} };
    public static final int[][] Winter5801GreenZone = { {}, {}, {}, {}, {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15} };

    public static final WorldPoint[] WINTER_RUNTILES = {
            new WorldPoint(2900, 5476, 0),
            new WorldPoint(2898, 5481, 0),
            new WorldPoint(2891, 5481, 0),
            new WorldPoint(2892, 5484, 0),
            new WorldPoint(2891, 5478, 0)
    };

    public SorceresssGardenWinterElemental(int ID) {
        this.ID = ID;
        switch (ID) {
            case 5798:
                generatePath(Winter5798Corner);
                this.greenZones = Winter5798GreenZone;
                break;
            case 5799:
                generatePath(Winter5799Corner);
                this.greenZones = Winter5799GreenZone;
                break;
            case 5800:
                generatePath(Winter5800Corner);
                this.greenZones = Winter5800GreenZone;
                break;
            case 5801:
                generatePath(Winter5801Corner);
                this.greenZones = Winter5801GreenZone;
                break;
            default: break;
        }
    }

}
