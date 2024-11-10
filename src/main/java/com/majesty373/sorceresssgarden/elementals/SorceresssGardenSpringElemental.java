package com.majesty373.sorceresssgarden.elementals;

import net.runelite.api.coords.WorldPoint;

import javax.inject.Singleton;

@Singleton
public class SorceresssGardenSpringElemental extends SorceresssGardenElemental {

    public WorldPoint[] Spring2956Corner = { new WorldPoint(2922, 5471, 0), new WorldPoint(2922, 5459, 0)};
    public WorldPoint[] Spring2957Corner = { new WorldPoint(2924, 5461, 0), new WorldPoint(2924, 5463, 0), new WorldPoint(2928, 5463, 0), new WorldPoint(2928, 5461, 0)};
    public WorldPoint[] Spring2958Corner = { new WorldPoint(2924, 5461, 0), new WorldPoint(2926, 5461, 0), new WorldPoint(2926, 5458, 0), new WorldPoint(2924, 5458, 0)};
    public WorldPoint[] Spring2961Corner = { new WorldPoint(2928, 5469, 0), new WorldPoint(2935, 5469, 0)};
    public WorldPoint[] Spring2962Corner = { new WorldPoint(2925, 5464, 0), new WorldPoint(2925, 5475, 0)};
    public WorldPoint[] Spring2963Corner = { new WorldPoint(2931, 5470, 0), new WorldPoint(2931, 5477, 0)};

    public static final int[][] Spring2956GreenZone = { {1, 2, 3, 4, 5, 6, 7, 8, 9}, {0, 1, 2, 3, 19, 20, 21, 22, 23}, {}, {}, {}, {} };
    public static final int[][] Spring2957GreenZone = { {}, {}, {4, 5, 6, 7, 8, 9}, {}, {}, {} };
    public static final int[][] Spring2958GreenZone = { {}, {}, {1, 2, 3, 4, 5}, {}, {}, {} };
    public static final int[][] Spring2961GreenZone = { {}, {}, {}, {}, {2, 3, 4, 5, 6, 7}, {0, 5, 6, 7, 12} };
    public static final int[][] Spring2962GreenZone = { {}, {}, {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, {0, 1, 2, 8, 9, 10, 11, 12, 20, 21}, {}, {} };
    public static final int[][] Spring2963GreenZone = { {}, {}, {}, {}, {2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, {2, 3, 4, 5, 6, 7, 8, 9, 10, 11} };

    public static final WorldPoint[] SPRING_RUNTILES = {
            new WorldPoint(2923, 5465, 0),
            new WorldPoint(2923, 5459, 0),
            new WorldPoint(2926, 5468, 0),
            new WorldPoint(2928, 5470, 0),
            new WorldPoint(2930, 5470, 0),
            new WorldPoint(2932, 5466, 0)
    };

    public SorceresssGardenSpringElemental(int ID) {
        this.ID = ID;
        switch (ID) {
            case 2956:
                generatePath(Spring2956Corner);
                this.greenZones = Spring2956GreenZone;
                break;
            case 2957:
                generatePath(Spring2957Corner);
                this.greenZones = Spring2957GreenZone;
                break;
            case 2958:
                generatePath(Spring2958Corner);
                this.greenZones = Spring2958GreenZone;
                break;
            case 2961:
                generatePath(Spring2961Corner);
                this.greenZones = Spring2961GreenZone;
                break;
            case 2962:
                generatePath(Spring2962Corner);
                this.greenZones = Spring2962GreenZone;
                break;
            case 2963:
                generatePath(Spring2963Corner);
                this.greenZones = Spring2963GreenZone;
                break;
            default: break;
        }
    }

}
