module com.mycompany.iwishserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
//    requires com.mycompany.iwishserver;

    exports com.mycompany.iwishserver.Controllers;
    opens com.mycompany.iwishserver.Controllers to javafx.fxml;

    exports com.mycompany.iwishserver;
}
