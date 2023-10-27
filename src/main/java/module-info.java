module grafikus.foci {
    requires javafx.controls;
    requires javafx.fxml;


    opens grafikus.foci to javafx.fxml;
    exports grafikus.foci;
}