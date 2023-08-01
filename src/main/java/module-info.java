module com.example.ticketbooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ticketbooking to javafx.fxml;
    exports com.example.ticketbooking;
}