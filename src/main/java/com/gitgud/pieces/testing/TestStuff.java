package com.gitgud.pieces.testing;

import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.control.MissionController;
import com.gitgud.pieces.model.mission.Mission;
import javafx.stage.Stage;

import static com.gitgud.pieces.testing.Missions.MISSION0;


public class TestStuff
{
    public static void main(String[] args)
    {
    }
    
    
    public static void lindigTest(Stage stage)
    {
        lindigJsonTest();
    }
    
    
    private static void lindigJsonTest()
    {
        //        ActiveGameController.testInitialize();
        //        Mission mission= MissionSelection.TUTORIAL.getMission();
        ////        Game.Saver.save();
        Game.Loader.load("TestPlayer");
        //        JsonParser.getInstance().parseIntoJsonFile(new File
        //        ("src\\main\\resources\\com\\gitgud\\pieces\\model\\city\\buildings\\headQuarters\\MissionSelection
        //        \\TUTORIAL.json"),ActiveGameController.getInstance().get().getMission());
    }
    
    
    private static void testMissionController()
    {
        Mission mission = MISSION0;
        MissionController missionController = new MissionController(mission);
        missionController.start();
    }
}
