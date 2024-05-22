module com.gitgud.pieces {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gitgud.pieces to javafx.fxml;
    exports com.gitgud.pieces;
}