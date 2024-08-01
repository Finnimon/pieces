package com.gitgud.pieces.model.city.buildings.headQuarters;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Sprite;
import com.gitgud.engine.utility.Strings;
import com.gitgud.pieces.control.ActiveGameController;
import com.gitgud.pieces.control.Game;
import com.gitgud.pieces.model.gameobjects.agents.FightAgent;
import com.gitgud.pieces.model.gameobjects.agents.PlayerAgent;
import com.gitgud.pieces.model.mission.Mission;
import com.gitgud.pieces.utility.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.concurrent.Task;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.Executors;

import static com.gitgud.pieces.utility.JsonParser.DOT_JSON;


/**
 * <p>Asset Locator for Mission JSON files.
 * <p>src\main\resources\com\gitgud\pieces\model\city\buildings\headQuarter\MissionSelection\*.json
 *
 * @author Finn L.
 * @version 1.0
 * @Owner: Finn L.
 * @see HeadQuarters
 * @since 26.07.2024
 */
public enum MissionSelection implements Describable, Sprite
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
           "But be prepared, she is quite powerful and has a lot of Clergymen with her, who will aid her with " +
           "powerful spells"),
    FIFTH("It is time to Attack E1 and destroy the White King's palace. " +
          "There is nothing to gain here, just blood to shed. " +
          "This Mission will be by far the hardest you have faced.");
    
    
    private static final String PLAYER_AGENT_JSON_MEMBER_NAME = "playerAgent";
    
    
    private static final String ACTIVE_AGENTS_JSON_MEMBER_NAME = "activeFightAgents";
    
    
    private static final String DISCARDED_AGENTS_JSON_MEMBER_NAME = "discardedFightAgents";
    
    
    /**
     * The Description of the mission.
     */
    private final String description;
    
    
    /**
     * Private Constructor for the enum.
     *
     * @param description The description of the mission.
     */
    MissionSelection(String description)
    {
        this.description = description;
    }
    
    
    @Override
    public String description()
    {
        return description;
    }
    
    
    /**
     * Saves this mission to the {@link ActiveGameController} and starts the next Scene
     * <p> Doesn't take an array of agents to ensure proper array length upon entering the mission.a
     *
     * @param firstAgent  The first agent to take into the mission.
     * @param secondAgent The second agent to take into the mission.
     * @param thirdAgent  The third agent to take into the mission.
     * @param fourthAgent The fourth agent to take into the mission.
     * @param fifthAgent  The fifth agent to take into the mission.
     * @Precondition: The ActiveGameController must be initialized. The first agent is not null so that at least one
     * agent will be selected.
     * @Postcondition: No Exceptions will be thrown.
     * @see Game.Flow#showNextScene()
     * @since 26.07.2024
     */
    public void select(@NotNull FightAgent firstAgent, FightAgent secondAgent, FightAgent thirdAgent,
                       FightAgent fourthAgent, FightAgent fifthAgent)
    {
        FightAgent[] agents = { firstAgent, secondAgent, thirdAgent, fourthAgent, fifthAgent };
        Executors.newSingleThreadExecutor().execute(selectionTask(agents));
    }
    
    
    /**
     * Creates a task for the selection of this missionSelection.
     * <p>Calls getMission().
     * <p>On success it saves the mission to the {@link ActiveGameController} and starts the next Scene.
     *
     * @return The created task.
     */
    private Task<Mission> selectionTask(FightAgent[] agents)
    {
        return new Task<>()
        {
            @Override
            protected Mission call()
            {
                return getMission(agents);
            }
            
            
            @Override
            protected void succeeded()
            {
                ActiveGameController.getInstance().get().setMission(getValue());
                Game.Flow.showNextScene();
            }
        };
    }
    
    
    /**
     * Loads the corresponding Mission JSON file into a Mission object
     *
     * @return the loaded mission
     * @Precondition: The Mission JSON file must exist and be readable
     * @Postcondition: No Exceptions will be thrown
     */
    @NotNull
    private Mission getMission(FightAgent[] agents)
    {
        Gson gson = JsonParser.getInstance().getGson();
        JsonObject jsonObject = prepareMissionJsonObject(gson, agents);
        
        return gson.fromJson(jsonObject, Mission.class);
    }
    
    
    /**
     * Gets the {@link Mission} Json Object and prepares it for the current Player and their selection of agents.
     *
     * @param gson The gson Instance to use for parsing
     * @return The {@link Mission} Json Object
     */
    @NotNull
    private JsonObject prepareMissionJsonObject(Gson gson, FightAgent[] agents)
    {
        File file = new File(getJsonFilePath());
        FileReader fileReader = JsonParser.getInstance().getFileReader(file);
        JsonObject jsonObject = com.google.gson.JsonParser.parseReader(fileReader).getAsJsonObject();
        replacePlayerAgent(gson, jsonObject);
        replaceActiveAndDiscardedAgents(gson, jsonObject, agents);
        return jsonObject;
    }
    
    
    /**
     * <p>Determines the asset FilePath for this mission.
     * <p>The returned path should always be a readable JSON file. If it is not, please see to it, that all needed
     * resource files are available.
     *
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
     * Replaces the PlayerAgent with the new one as specified in
     * * {@link com.gitgud.pieces.model.city.buildings.PlayerReborn}.
     *
     * @param gson              The gson instance.
     * @param missionJsonObject The JsonObject to edit.
     */
    private static void replacePlayerAgent(@NotNull Gson gson, @NotNull JsonObject missionJsonObject)
    {
        missionJsonObject.remove(PLAYER_AGENT_JSON_MEMBER_NAME);
        JsonElement newPlayerAgent = gson.toJsonTree(new PlayerAgent(ActiveGameController.getInstance()
                                                                                         .get()
                                                                                         .getCity()
                                                                                         .getPlayerReborn()
                                                                                         .getFaction()));
        missionJsonObject.add(PLAYER_AGENT_JSON_MEMBER_NAME, newPlayerAgent);
    }
    
    
    /**
     * Replaces the active and discarded agents with the new ones as specified.
     *
     * @param gson              The gson instance.
     * @param missionJson       The JsonObject to edit.
     * @param activeFightAgents The new active agents to set.
     */
    private void replaceActiveAndDiscardedAgents(Gson gson, JsonObject missionJson, FightAgent[] activeFightAgents)
    {
        missionJson.remove(ACTIVE_AGENTS_JSON_MEMBER_NAME);
        JsonElement newActiveAgents = gson.toJsonTree(activeFightAgents);
        missionJson.add(ACTIVE_AGENTS_JSON_MEMBER_NAME, newActiveAgents);
        missionJson.remove(DISCARDED_AGENTS_JSON_MEMBER_NAME);
        ArrayList<FightAgent> discardedAgents = new ArrayList<>();
        for (int i = 0; i < activeFightAgents.length; i++)
        {
            discardedAgents.add(null);
        }
        JsonElement newDiscardedAgents = gson.toJsonTree(discardedAgents.toArray());
        missionJson.add(DISCARDED_AGENTS_JSON_MEMBER_NAME, newDiscardedAgents);
    }
    
    
    @Override
    public @NotNull String getSpriteFilePath()
    {
        return null;//todo for icon??? No file for now
    }
}
