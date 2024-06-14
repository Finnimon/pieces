package com.gitgud.pieces.view;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Delfina
 * @version 1.0
 * @Owner: Delfina
 * @since 11.05.2024
 */

public class SMainMenue {

    public static Scene createMainMenueScene(Stage stage) {
        String mainMenueBackgroundPath = "C:/Users/delfi/Desktop/2 Semester 2024/GUI_prog/GUI_pics.jpg";
        VBox root = GuiUtils.createVBox(mainMenueBackgroundPath);
        Scene MainMenueScene = new Scene(root); //alle Knoten als Inhalt der Szene festgelegt
        stage.setScene(MainMenueScene);

        // "url('file:///C:/Users/delfi/Desktop/2_Semester_2024/GUI_prog/GUI_Grafik.jpeg');");

        Text hauptmenueText = new Text("Hauptmenü");
        hauptmenueText.setFont(Font.font("Lato", FontWeight.MEDIUM, 24)); // Schriftart und Größe festlegen
        root.getChildren().add(hauptmenueText);


        Button button = new Button();


        //erstellen der Szene mit den buttons etc
        Button[] buttonsArray = new Button[5];
        buttonsArray[0] = new Button("Fortfahren");
        buttonsArray[1] = new Button("Spiel laden");
        buttonsArray[2] = new Button("Einstellungen");
        buttonsArray[3] = new Button("Spiel beenden");
        buttonsArray[4] = new Button("Arena");

        //Größe und Schriftart für jeweils alle Buttons festlegen
        for (Button buttonS : buttonsArray) {
            GuiUtils.setButtonTrait(buttonS);
        }

        //buttonsArray[0].setOnAction(e -> stage.setScene(oldGameScene)); TODO: zurück ins alte Spiel
        buttonsArray[1].setOnAction(e -> stage.setScene(SDifficulty.createDifficultyScene(stage)));
        //buttonsArray[2].setOnAction(e -> stage.setScene(settings)) TODO: brauchen wir überhaupt Settings?
        buttonsArray[3].setOnAction(e -> stage.setScene(null));
        buttonsArray[4].setOnAction(e -> stage.setScene(SMission.createMissionScene(stage)));


        //getChildren()-Methode gibt eine Liste der direkten untergeordneten Knoten des aktuellen Knotens zurück
        root.getChildren().addAll(buttonsArray);

        return MainMenueScene;
    }
}
