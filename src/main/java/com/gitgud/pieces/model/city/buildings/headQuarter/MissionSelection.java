package com.gitgud.pieces.model.city.buildings.headQuarter;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.game.Game;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.utility.gsonSerialization.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static com.gitgud.pieces.utility.gsonSerialization.JsonParser.DOT_JSON;


/**
 * <p>Asset Locator for Mission JSON files.
 * <p>src\main\resources\com\gitgud\pieces\model\city\buildings\headQuarter\MissionSelection\TUTORIAL.json
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @since 26.07.2024
 */
enum MissionSelection implements Describable, Sprite
{
    TUTORIAL("This is the tutorial. " + "You will gain a few resources but nothing of much value."),
    FIRST("Take back the rich lands of E1. " +
          "While you may not find many soldiers to recruit here, " +
          "you will certainly gain many riches. "),
    SECOND("It is time to venture forth into the 6th row. " +
           "Many soldiers of yours remain, stationed and awaiting your return."),
    THIRD(""),
    FOURTH("Steal the White Queen's riches. " +
           "Many Artefacts and resources are scattered in her Province D1. " +
           "But be prepared, she is quite powerful and has a lot of Clergymen with her, who will aid her with powerful spells"),
    FIFTH("It is time to Attack E1 and destroy the White King's palace. " +
          "There is nothing to gain here, just blood to shed. " +
          "This Mission will be by far the hardest you have faced.");
    
    
    private final String description;
    
    
    MissionSelection(String description)
    {
        this.description = description;
    }
    
    
    /**
     * <p>Determines the asset FilePath for this mission.
     * <p>The returned path should always be a readable JSON file. If it is not, please see to it, that all needed resource files are available.
     * @return the json file path for this mission
     */
    @NotNull
    private String getJsonFilePath()
    {
        return com.gitgud.pieces.utility.Strings.SRC_MAIN_RESOURCES +
               MissionSelection.class.getName().replace(".", Strings.FILE_SEPERATOR) +
               Strings.FILE_SEPERATOR +
               this.name() +
               DOT_JSON;
    }
    
    
    /**
     * Loads the corresponding Mission JSON file into a Mission object
     *
     * @return the loaded mission
     * @Precondition: The Mission JSON file must exist and be readable
     * @Postcondition: No Exceptions will be thrown
     */
    @NotNull
    public Mission getMission()
    {
        File file = new File(getJsonFilePath());
        return JsonParser.getInstance().parseJson(file, Mission.class);
    }
    
    
    @Override
    public String description()
    {
        return description;
    }
    
    
    /**
     * Saves this mission to the {@link ActiveGameController} and starts the next Scene
     *
     * @Author: Finn L.
     * @Version: 1.0
     * @see Game.Flow#showNextScene()
     * @since 26.07.2024
     */
    public void select()
    {
        ActiveGameController.getInstance().get().setMission(getMission());
        Game.Flow.showNextScene();
    }
    
    
    @Override
    public String getSpriteFilePath()
    {
        return null;//todo for icon???
    }
}
