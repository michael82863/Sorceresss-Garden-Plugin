package com.majesty373.sorceresssgarden.elementals;

import net.runelite.api.coords.WorldPoint;

import javax.inject.Singleton;

@Singleton
public class SorceresssGardenAutumnElemental extends SorceresssGardenElemental {

    public WorldPoint[] Autumn5802Corner = { new WorldPoint(2908, 5460, 0), new WorldPoint(2898, 5460, 0)};
    public WorldPoint[] Autumn5803Corner = { new WorldPoint(2900, 5455, 0), new WorldPoint(2900, 5450, 0)};
    public WorldPoint[] Autumn5804Corner = { new WorldPoint(2905, 5449, 0), new WorldPoint(2899, 5449, 0)};
    public WorldPoint[] Autumn5805Corner = { new WorldPoint(2903, 5451, 0), new WorldPoint(2903, 5455, 0), new WorldPoint(2905, 5455, 0), new WorldPoint(2905, 5451, 0)};
    public WorldPoint[] Autumn5806Corner = { new WorldPoint(2917, 5457, 0), new WorldPoint(2904, 5457, 0)};
    public WorldPoint[] Autumn5807Corner = { new WorldPoint(2917, 5455, 0), new WorldPoint(2908, 5455, 0)};

    public static final int[][] Autumn5802GreenZone = { {4, 5, 6, 7, 8, 9, 10}, {17, 18, 19, 20, 0}, {}, {}, {}, {}, {}, {} };
    public static final int[][] Autumn5803GreenZone = { {}, {}, {2, 3, 4}, {0, 9}, {0, 7, 8, 9}, {}, {}, {} };
    public static final int[][] Autumn5804GreenZone = { {}, {}, {}, {}, {7, 8, 9}, {}, {}, {} };
    public static final int[][] Autumn5805GreenZone = { {}, {}, {}, {}, {}, {2, 3, 4, 5, 6, 7, 8, 9}, {5, 6, 7, 8, 9, 10, 11}, {} };
    public static final int[][] Autumn5806GreenZone = { {}, {}, {}, {}, {}, {}, {0, 1, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25}, {} };
    public static final int[][] Autumn5807GreenZone = { {}, {}, {}, {}, {}, {}, {}, {0, 12, 13, 14, 15, 16, 17} };

    public static final WorldPoint[] AUTUMN_RUNTILES = {
            new WorldPoint(2904, 5459, 0),
            new WorldPoint(2901, 5455, 0),
            new WorldPoint(2899, 5453, 0),
            new WorldPoint(2901, 5451, 0),
            new WorldPoint(2903, 5450, 0),
            new WorldPoint(2902, 5453, 0),
            new WorldPoint(2908, 5456, 0),
            new WorldPoint(2913, 5453, 0)
    };

    public SorceresssGardenAutumnElemental(int ID) {
        this.ID = ID;
        switch (ID) {
            case 5802:
                generatePath(Autumn5802Corner);
                this.greenZones = Autumn5802GreenZone;
                break;
            case 5803:
                generatePath(Autumn5803Corner);
                this.greenZones = Autumn5803GreenZone;
                break;
            case 5804:
                generatePath(Autumn5804Corner);
                this.greenZones = Autumn5804GreenZone;
                break;
            case 5805:
                generatePath(Autumn5805Corner);
                this.greenZones = Autumn5805GreenZone;
                break;
            case 5806:
                generatePath(Autumn5806Corner);
                this.greenZones = Autumn5806GreenZone;
                break;
            case 5807:
                generatePath(Autumn5807Corner);
                this.greenZones = Autumn5807GreenZone;
                break;
            default: break;
        }
    }

}
