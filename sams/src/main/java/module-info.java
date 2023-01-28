module com.main.sams {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.google.gson;

    opens com.main.sams to javafx.fxml;
    exports com.main.sams;

    opens allFxComponents to javafx.fxml;
    exports allFxComponents;
    exports allFxComponents.Controllers;
    opens allFxComponents.Controllers to javafx.fxml;
}