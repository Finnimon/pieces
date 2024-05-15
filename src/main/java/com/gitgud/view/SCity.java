package com.gitgud.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SCity {


    public static Scene createCityScene(Stage stage)
    {
        Group root = new Group();
        Scene CityScene = new Scene(root);
        stage.setScene(CityScene);

        // Hier kannst du alles machen
        // Wichtig ist, dass du keine Atrribute zunzufügst und alle Methoden Static sind

        Button wrought = new Button("wrought");
        wrought.setOnAction(e -> startWroughtInteraction());

        Button tavern = new Button("tavern");
        tavern.setOnAction(e -> startTavernInteraction());

        Button magicAmplifier = new Button("magicAmplifier");
        magicAmplifier.setOnAction(e -> startMagicAmplifierInteraction());

        Button basisCamp = new Button("basisCamp");
        basisCamp.setOnAction(e -> startBasisCampInteraction());

        Button factionCamps = new Button("factionCamps");
        factionCamps.setOnAction(e -> startFactionCampsInteraction());

        Button trainingGrounds = new Button("trainingGrounds");
        trainingGrounds.setOnAction(e -> startTrainingGroundsInteraction());

        Button market = new Button("market");
        market.setOnAction(e -> startMarketInteraction());

        Button headquarters = new Button("headquarters");
        headquarters.setOnAction(e -> stage.setScene(SMap.createMapScene(stage)));

        root.getChildren().addAll(wrought, tavern,magicAmplifier, basisCamp, factionCamps, trainingGrounds, market, headquarters);
        return CityScene;
    }

    private static void startHeadquartersInteraction()
    {
        Stage StageHeadquarters = new Stage();
        StageHeadquarters.initModality(Modality.APPLICATION_MODAL);// Blocking input events so that no interaction occurs in the city meanwhile
        Group root = new Group();
        Scene SceneHeadquarters= new Scene(root);
        //Hier kannst du alle sachen machen, die mit den einzelnen Gebäuden zutun haben
        root.getChildren().addAll();
        StageHeadquarters.setScene(SceneHeadquarters);
    }

    private static void startMarketInteraction()
    {
        Stage StageMarket = new Stage();
        StageMarket.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene SceneMarket= new Scene(root);

        root.getChildren().addAll();
        StageMarket.setScene(SceneMarket);
    }

    private static void startTrainingGroundsInteraction()
    {
        Stage StageTrainingGrounds = new Stage();
        StageTrainingGrounds.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene SceneTrainingGrounds= new Scene(root);

        root.getChildren().addAll();
        StageTrainingGrounds.setScene(SceneTrainingGrounds);
    }

    private static void startFactionCampsInteraction()
    {
        Stage StageFactionCamps = new Stage();
        StageFactionCamps.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene SceneFactionCamps= new Scene(root);

        root.getChildren().addAll();
        StageFactionCamps.setScene(SceneFactionCamps);
    }

    private static void startBasisCampInteraction()
    {
        Stage StageBasisCamp = new Stage();
        StageBasisCamp.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene SceneBasisCamp= new Scene(root);

        root.getChildren().addAll();
        StageBasisCamp.setScene(SceneBasisCamp);
    }

    private static void startMagicAmplifierInteraction()
    {
        Stage StageMagicAmplifier = new Stage();
        StageMagicAmplifier.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene SceneMagicAmplifier= new Scene(root);

        root.getChildren().addAll();
        StageMagicAmplifier.setScene(SceneMagicAmplifier);
    }

    private static void startTavernInteraction()
    {
        Stage StageTavern = new Stage();
        StageTavern.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene SceneTavern= new Scene(root);

        root.getChildren().addAll();
        StageTavern.setScene(SceneTavern);
    }

    private static void startWroughtInteraction()
    {
        Stage StageWrought = new Stage();
        StageWrought.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene SceneWrought= new Scene(root);

        root.getChildren().addAll();
        StageWrought.setScene(SceneWrought);
    }
}
