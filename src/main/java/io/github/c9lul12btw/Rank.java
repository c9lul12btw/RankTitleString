package io.github.c9lul12btw;

import java.util.Map;

public class Rank {

    public enum BuildRank {
        MASTER("§5Master",""),
        EMERALD("§2Emerald",""),
        DIAMOND("§bDiamond",""),
        GOLD("§6Gold",""),
        SILVER("§fSILVER",""),
        BRONZE("§3Bronze","");

        private Map<String, String> info;
        private String prefix;
        private String description;

        public String getPrefix() {
            return prefix;
        }

        public String getDescription() {
            return description;
        }

        BuildRank(String prefix, String description) {
            this.prefix = prefix;
            this.description = description;
        }
    }

    public enum StaffRank {
        MANAGER("§cManager",""),
        DEVELOPER("§cDev",""),
        ADMIN("§cAdmin",""),
        MOD("§2Mod",""),
        HELPER("§9Helper",""),
        NONE("","");

        private Map<String, String> info;
        private String prefix;
        private String description;

        public String getPrefix() {
            return prefix;
        }

        public String getDescription() {
            return description;
        }

        StaffRank(String prefix, String description) {
            this.prefix = prefix;
            this.description = description;
        }
    }

    public enum TitleRank {
        FIRST_RANKED("§b#1 Ranked","first ranked player",'b'),
        BUILD_COMP("§5Build Comp","win a build comp",'5'),
        OLD_PLAYER("§61 Year","been here ages",'6'),
        NONE("","",'7');

        private Map<String, String> info;
        private String prefix;
        private String description;
        private char colourData;

        public String getPrefix() {
            return prefix;
        }

        public String getDescription() {
            return description;
        }

        public char getColourData() { return colourData; }

        TitleRank(String prefix, String description, char colourData) {
            this.prefix = prefix;
            this.description = description;
            this.colourData = colourData;
        }
    }
}


