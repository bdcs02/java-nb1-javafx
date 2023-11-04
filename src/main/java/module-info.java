module grafikus.foci {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.naming;
    requires java.sql;
    requires org.hibernate.orm.core;
    opens grafikus.foci to javafx.fxml;
    opens grafikus.foci.model to javafx.base, org.hibernate.orm.core;
    opens grafikus.foci.controller to javafx.fxml; // A controllers csomag exportálása
    exports grafikus.foci;
}



