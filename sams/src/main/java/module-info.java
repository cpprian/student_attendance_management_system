module com.main.sams {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.main.sams to javafx.fxml;
    exports com.main.sams;

    opens com.main.sams.allFxComponents to javafx.fxml;
    exports com.main.sams.allFxComponents;
    exports com.main.sams.allFxComponents.Controllers;
    opens com.main.sams.allFxComponents.Controllers to javafx.fxml;
}