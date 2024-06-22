module com.gitgud.pieces {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires jdk.unsupported;
    requires com.opencsv;
    
    
    opens com.gitgud.pieces to javafx.fxml;
    opens com.gitgud.pieces.utility.modification.fightAgent to com.google.gson;
    exports com.gitgud.pieces;
    exports com.gitgud.pieces.model.gameobjects to com.google.gson;
    exports com.gitgud.engine.model.gameobjects to com.google.gson;
    exports com.gitgud.engine.model.gameobjects.agent to com.google.gson;
    exports com.gitgud.engine.model.gameobjects.interactable to com.google.gson;
}