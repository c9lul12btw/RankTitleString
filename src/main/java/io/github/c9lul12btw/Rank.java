package io.github.c9lul12btw;

import java.util.Map;

public class Rank {

    public enum BuildRank {
        MASTER("",""),
        EMERALD("",""),
        DIAMOND("",""),
        GOLD("",""),
        IRON("",""),
        BRONZE("","");

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
        MANAGER("",""),
        DEVELOPER("",""),
        ADMIN("",""),
        MOD("",""),
        HELPER("",""),
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
        FIRST_RANKED("","",'b'),
        BUILD_COMP("","",'5'),
        OLD_PLAYER("","",'6'),
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


