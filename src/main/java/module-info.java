module com.gitgud.pieces {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires jdk.unsupported;


    opens com.gitgud.pieces to javafx.fxml;
    exports com.gitgud.pieces;
    exports com.gitgud.model.gameObjects to com.google.gson;
}