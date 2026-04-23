module com.example.mycalcus {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.mycalc to javafx.fxml;
    exports com.example.mycalc;

    exports com.example.mycalcus;   // 👈 ADD THIS LINE
}