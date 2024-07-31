module com.gitgud.pieces {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires jdk.unsupported;
    requires com.opencsv;
    requires jeromq;
    requires org.hildan.fxgson;
    requires com.github.ruediste.polymorphicGson;
    requires org.jetbrains.annotations;
    
    
    opens com.gitgud.pieces to javafx.fxml;
    opens com.gitgud.pieces.utility.modification.fightAgent to com.google.gson;
    opens com.gitgud.engine.model.map to com.google.gson;
    opens com.gitgud.graph to com.google.gson;
    opens com.gitgud.engine.control to com.google.gson;
    opens com.gitgud.engine.view to com.google.gson;
    opens com.gitgud.pieces.model.gameobjects.agents to com.google.gson;
    opens com.gitgud.engine.model.gameobjects.agent to com.google.gson;
    opens com.gitgud.engine.model.gameobjects to com.google.gson;
    opens com.gitgud.pieces.model.player to com.google.gson;
    opens com.gitgud.pieces.model.game to com.google.gson;
    opens com.gitgud.pieces.model.fight to com.google.gson;
    opens com.gitgud.pieces.model.gameobjects to com.google.gson;
    opens com.gitgud.engine.model.gameobjects.interactable to com.google.gson;
    opens com.gitgud.pieces.model.gameobjects.interactable.collectibles to com.google.gson;
    opens com.gitgud.pieces.model.city to com.google.gson;
    opens com.gitgud.pieces.model.city.buildings to com.google.gson;
    opens com.gitgud.pieces.model.mission to com.google.gson;
    opens com.gitgud.pieces.model.gameobjects.interactable.buildings to com.google.gson;
    opens com.gitgud.pieces.model to com.google.gson;
    opens com.gitgud.engine.utility.modification to com.google.gson;
    exports com.gitgud.pieces;
    exports com.gitgud.pieces.control;
    opens com.gitgud.pieces.control to javafx.fxml;
    exports com.gitgud.pieces.view;
    opens com.gitgud.pieces.view to javafx.fxml;
    opens com.gitgud.pieces.model.city.buildings.headQuarters to com.google.gson;
    exports com.gitgud.pieces.control.game;
    opens com.gitgud.pieces.control.game to com.google.gson, javafx.fxml;
    exports com.gitgud.pieces.view.sUnused;
    opens com.gitgud.pieces.view.sUnused to javafx.fxml;
}