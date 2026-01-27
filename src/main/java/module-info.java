module com.mycompany.iwishserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
//    requires com.mycompany.iwishserver;

    exports com.mycompany.iwishserver.Controllers;
    exports com.mycompany.iwishserver.Models;
    exports com.mycompany.iwishserver.Services;
    exports com.mycompany.iwishserver.network;
    exports com.mycompany.iwishserver.DTO;
    exports com.mycompany.iwishserver;
    opens  com.mycompany.iwishserver.Models to com.google.gson;
    opens com.mycompany.iwishserver.Controllers to javafx.fxml;
    opens com.mycompany.iwishserver.DTO to com.google.gson;

}
