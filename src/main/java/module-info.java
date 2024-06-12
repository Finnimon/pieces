module com.gitgud.pieces {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires jdk.unsupported;
    requires com.opencsv;


    opens com.gitgud.pieces to javafx.fxml;
    opens com.gitgud.utility.modification.fightAgent to com.google.gson;
    exports com.gitgud.pieces;
    exports com.gitgud.model.gameObjects to com.google.gson;
}