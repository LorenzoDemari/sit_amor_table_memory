package it.emarolab.sitArmorInjected.Table;

import it.emarolab.sit.example.tableScenario.sceneElement.SpatialObject;

public interface Legs {

    class Chair_X extends SpatialObject {
        public static final String TYPE = "CHAIR_X";

        @Override
        protected String getNamePrefix() {
            return "CX";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Chair_Y extends SpatialObject {

        public static final String TYPE = "CHAIR_Y";

        @Override
        protected String getNamePrefix() {
            return "CY";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Chair_Minus_X extends SpatialObject {
        public static final String TYPE = "CHAIR_MINUS_X";

        @Override
        protected String getNamePrefix() {
            return "CmX";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Chair_Minus_Y extends SpatialObject {
        public static final String TYPE = "CHAIR_MINUS_Y";

        @Override
        protected String getNamePrefix() {
            return "CmY";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Bed_X extends SpatialObject {
        public static final String TYPE = "BED_X";

        @Override
        protected String getNamePrefix() {
            return "BX";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Bed_Y extends SpatialObject {
        public static final String TYPE = "BED_Y";

        @Override
        protected String getNamePrefix() {
            return "BY";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Bed_Minus_X extends SpatialObject {
        public static final String TYPE = "BED__MINUS_X";

        @Override
        protected String getNamePrefix() {
            return "BmX";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Bed_Minus_Y extends SpatialObject {
        public static final String TYPE = "BED_MINUS_Y";

        @Override
        protected String getNamePrefix() {
            return "BmY";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Not_X extends SpatialObject {
        public static final String TYPE = "NOT_X";

        @Override
        protected String getNamePrefix() {
            return "NX";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Not_Y extends SpatialObject {
        public static final String TYPE = "NOT_Y";

        @Override
        protected String getNamePrefix() {
            return "NY";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Not_Minus_X extends SpatialObject {
        public static final String TYPE = "NOT__MINUS_X";

        @Override
        protected String getNamePrefix() {
            return "NmX";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Not_Minus_Y extends SpatialObject {
        public static final String TYPE = "NOT_MINUS_Y";

        @Override
        protected String getNamePrefix() {
            return "NmY";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Roof_X extends SpatialObject {
        public static final String TYPE = "ROOF_X";

        @Override
        protected String getNamePrefix() {
            return "RX";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Roof_Y extends SpatialObject {
        public static final String TYPE = "ROOF_Y";

        @Override
        protected String getNamePrefix() {
            return "RmY";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Roof_Minus_X extends SpatialObject {
        public static final String TYPE = "ROOF__MINUS_X";

        @Override
        protected String getNamePrefix() {
            return "RmX";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Roof_Minus_Y extends SpatialObject {
        public static final String TYPE = "ROOF_MINUS_Y";

        @Override
        protected String getNamePrefix() {
            return "RmY";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

}
