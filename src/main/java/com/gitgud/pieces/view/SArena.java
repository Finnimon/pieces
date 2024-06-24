package com.gitgud.pieces.view;

import com.gitgud.net.ClientController;
import com.gitgud.net.ServerController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

    public class SArena
    {

        public static final String SERVER_ADDRESS_INPUT_INFO = "Server address seperated by . if IPv4 separated by : IPv6 if you are the client";
        public static final String SEARCH_GAME = "Search Game";
        public static final String SERVER_LOBY_INFO = "Waiting vor a game member to join";
        public static final String CLIENT_SERVER_INFO = "Waiting vor a game to be hostet";

        public static Scene createArenaScene(Stage stage)
        {

            Group root = new Group();
            Scene ArenaScene =new Scene(root);
            TextField serverTextField = new TextField(SERVER_ADDRESS_INPUT_INFO);
            CheckBox isServer = new CheckBox();
            VBox vBox =new VBox();

            Button continuoToLobby = new Button(SEARCH_GAME);

            continuoToLobby.setOnAction(e -> {
                if(serverTextField.hasProperties())
                {
                    if (isServer.isSelected())
                    {
                        stage.setScene(CreateServerLobby(stage));
                    } else
                    {
                        stage.setScene(CreateClientLobby(stage, serverTextField.getCharacters()));

                    }
                }
            });

            vBox.getChildren().addAll(serverTextField, isServer, continuoToLobby);
            root.getChildren().add(vBox);
            return ArenaScene;
        }

        private static Scene
        CreateServerLobby(Stage stage)
        {
            Group root =new Group();
            Text text = new Text(SERVER_LOBY_INFO);
            root.getChildren().add(text);
            ArenaController.InitialiseArenaAsServer();

            if(ServerController.getInstance().getServer().isConnected())
            {
                stage.setScene(STest.ceateSTest());
            }
            return new Scene(root);
        }

        private static Scene CreateClientLobby(Stage stage, CharSequence serverAddress)
        {
            String stringServerAddress = serverAddress.toString();
            Group root =new Group();
            Text text = new Text(CLIENT_SERVER_INFO);
            root.getChildren().add(text);
            ArenaController.InitialiseArenaAsClient(stringServerAddress);

            if(ClientController.getInstance().getClient().isConnected())
            {
                stage.setScene(STest.ceateSTest());
            }

            return new Scene(root);
        }
    }
