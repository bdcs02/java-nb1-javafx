module grafikus.foci {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires gson;
    requires httpcore;
    requires httpclient;
    opens grafikus.foci to javafx.fxml;
    opens grafikus.foci.model to javafx.base, org.hibernate.orm.core;
    opens grafikus.foci.controller to javafx.fxml; // A controllers csomag exportálása
    opens com.oanda.v20.account to gson;
    opens com.oanda.v20.primitives to gson;
    opens com.oanda.v20.transaction to gson;
    opens com.oanda.v20.pricing to gson;
     opens com.oanda.v20.pricing_common to gson;
     opens com.oanda.v20.order to gson;
    opens com.oanda.v20.instrument to gson;
    exports grafikus.foci;
}

