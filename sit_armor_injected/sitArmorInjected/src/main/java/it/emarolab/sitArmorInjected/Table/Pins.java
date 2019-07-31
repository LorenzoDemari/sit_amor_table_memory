package it.emarolab.sitArmorInjected.Table;

import it.emarolab.sit.example.simpleSpatialScenario.Point3D;
import it.emarolab.sit.example.tableScenario.sceneElement.SpatialObject;

public interface Pins {

    class Pin_1 extends SpatialObject {
        public static final String TYPE = "PIN_1";

        // todo move it outside the service and send it in the request
        public Pin_1() {
            this.setCenter(new Point3D(-0.30, -0.16, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P1";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_2 extends SpatialObject {
        public static final String TYPE = "PIN_2";

        public Pin_2() {
            this.setCenter(new Point3D(-0.05, -0.15, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P2";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_3 extends SpatialObject {
        public static final String TYPE = "PIN_3";

        public Pin_3() {
            this.setCenter(new Point3D(0.03, -0.15, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P3";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_4 extends SpatialObject {
        public static final String TYPE = "PIN_4";

        public Pin_4() {
            this.setCenter(new Point3D(0.27, -0.17, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P4";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_5 extends SpatialObject {
        public static final String TYPE = "PIN_5";

        public Pin_5() {
            this.setCenter(new Point3D(0.24, -0.02, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P5";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_6 extends SpatialObject {
        public static final String TYPE = "PIN_6";

        public Pin_6() {
            this.setCenter(new Point3D(0.24, 0.04, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P6";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_7 extends SpatialObject {
        public static final String TYPE = "PIN_7";

        public Pin_7() {
            this.setCenter(new Point3D(0.27, 0.19, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P7";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_8 extends SpatialObject {
        public static final String TYPE = "PIN_8";

        public Pin_8() {
            this.setCenter(new Point3D(0.04, 0.17, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P8";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_9 extends SpatialObject {
        public static final String TYPE = "PIN_9";


        public Pin_9() {
            this.setCenter(new Point3D(-0.04, 0.17, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P9";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_10 extends SpatialObject {
        public static final String TYPE = "PIN_10";


        public Pin_10() {
            this.setCenter(new Point3D(-0.28, 0.20, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P10";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_11 extends SpatialObject {
        public static final String TYPE = "PIN_11";


        public Pin_11() {
            this.setCenter(new Point3D(-0.24, 0.05, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P11";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

    class Pin_12 extends SpatialObject {
        public static final String TYPE = "PIN_12";


        public Pin_12() {
            this.setCenter(new Point3D(-0.25, -0.01, 0));
        }

        @Override
        protected String getNamePrefix() {
            return "P7";
        }

        @Override
        public String getTypeName() {
            return TYPE;
        }
    }

}
