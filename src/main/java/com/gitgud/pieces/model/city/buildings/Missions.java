package com.gitgud.pieces.model.city.buildings;

import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.utility.gsonSerialization.JsonParser;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static com.gitgud.pieces.utility.gsonSerialization.JsonParser.DOT_JSON;


/**
 * <p>Asset Locator for Mission JSON files.
 * <p>All Mission JSON files are located in src\main\resources\com\gitgud\pieces\model\city\buildings\Missions
 *
 * @author Finn L.
 * @version 1.0
 * @since 26.07.2024
 */
enum Missions
{
    TUTORIAL, FIRST, SECOND, THIRD, FOURTH, FIFTH;
    
    
    /**
     * Determines the asset FilePath for this mission, using the class name
     *
     * @return the json file path for this mission
     */
    @NotNull
    private String getJsonFilePath()
    {
        return com.gitgud.pieces.utility.Strings.SRC_MAIN_RESOURCES +
               Missions.class.getName().replace(".", Strings.FILE_SEPERATOR) +
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
}
