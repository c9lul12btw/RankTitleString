package io.github.c9lul12btw;

public class Rank {

    public enum BuildRank {
        MASTER,
        ENDER,
        EMERALD,
        DIAMOND,
        GOLD,
        IRON,
        STONE,
    }

    public enum StaffRank {
        MANAGER,
        DEVELOPER,
        ADMIN,
        MOD,
        HELPER,
        NONE
    }

    public enum TitleRank {
        FIRST_RANKED,
        BUILD_COMP,
        OLD_PLAYER,
    }


    public static String getBuildRank(BuildRank rank) {
        switch (rank) {
            case MASTER: {
                return "&fMaster";
            }
            case ENDER: {
                return "&5Ender";
            }
            case EMERALD: {
                return "&2Emerald";
            }
            case DIAMOND: {
                return "&bDiamond";
            }
            case GOLD: {
                return "&6Gold";
            }
            case IRON: {
                return "&7Iron";
            }
            case STONE: {
                return "&&8Stone";
            }
        }
        return null;
    }

    public static String getStaffRank(StaffRank rank) {
        switch (rank) {
            case MANAGER: {
                return "&cManager";
            }
            case DEVELOPER: {
                return "&cDev";
            }
            case ADMIN: {
                return "&cAdmin";
            }
            case MOD: {
                return "&2Mod";
            }
            case HELPER: {
                return "&9Helper";
            }
            case NONE: {
                return null;
            }
        }
        return null;
    }

    public static String getTitleRank(TitleRank rank) {
        switch (rank) {
            case FIRST_RANKED: {
                return "&b#1 Ranked";
            }
            case BUILD_COMP: {
                return "&2Build Comp Winner";
            }
            case OLD_PLAYER: {
                return "&a1 Year";
            }
        }
        return null;
    }
}
