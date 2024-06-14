module com.gitgud.pieces {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires jdk.unsupported;
    requires com.opencsv;


    opens com.gitgud.pieces to javafx.fxml;
    exports com.gitgud.pieces;
    exports com.gitgud.pieces.model.gameObjects to com.google.gson;
    exports com.gitgud.engine.model.gameObject to com.google.gson;
    exports com.gitgud.engine.model.gameObject.agent to com.google.gson;
    exports com.gitgud.engine.model.gameObject.interactable to com.google.gson;
}