module com.ahd.embarkinginfxwithfile {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.ahd.embarkinginfxwithfile to javafx.fxml;
    exports com.ahd.embarkinginfxwithfile;
    exports com.ahd.embarkinginfxwithfile.Controller;
    opens com.ahd.embarkinginfxwithfile.Controller to javafx.fxml;
    exports com.ahd.embarkinginfxwithfile.Classes;
    opens com.ahd.embarkinginfxwithfile.Classes to javafx.fxml;
}