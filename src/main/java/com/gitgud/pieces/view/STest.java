package com.gitgud.pieces.view;

import com.gitgud.net.ServerClientController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class STest {
    public static Scene ceateSTest()
    {
        Group root= new Group();
        Scene testScene= new Scene(root);
        ServerClientController.getInstance().getClient().addMessage("fhfhfh");
        Text text= new Text(ServerClientController.getInstance().getServer().getLatestUnprecedentedMessage().toString());

        root.getChildren().add(text);
        return testScene;
    }
}
