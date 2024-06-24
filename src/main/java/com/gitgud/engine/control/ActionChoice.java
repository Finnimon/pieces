package com.gitgud.engine.control;

import com.gitgud.engine.model.action.Action;
import com.gitgud.engine.model.action.ActionAwaiterModel;
import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.GridMappable;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.view.ActionChoiceRender;
import com.gitgud.engine.view.ActionContextRender;
import com.gitgud.engine.view.HudRender;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Objects;


public abstract class ActionChoice<ActionAwaiterType extends ActionAwaitingController<ModelType, GridMappableType, RenderType>, ModelType extends ActionAwaiterModel<GridMappableType>, GridMappableType extends GridMappable, RenderType extends ActionContextRender<ModelType, GridMappableType>> implements Named, Describable
{
    private final String name;
    
    
    private final ActionAwaiterType awaiter;
    
    
    private final String description;
    
    
    private final Action<ActionAwaiterType> action;
    
    
    public ActionChoice(String name, String description, ActionAwaiterType awaiter, Action<ActionAwaiterType> action)
    {
        this.name = name;
        this.awaiter = awaiter;
        this.description = description;
        this.action = action;
    }
    
    
    public ActionChoice(String name, String description, ActionAwaiterType awaiter)
    {
        this(name, description, awaiter, null);
    }
    
    
    public static <AAType extends ActionAwaitingController<MType, GType, RType>, MType extends ActionAwaiterModel<GType>, GType extends GridMappable, RType extends ActionContextRender<MType, GType>> ActionChoice<AAType, MType, GType, RType> empty(
            String name, String description, AAType actionAwaiter)
    {
        return new ActionChoice<>(name, description, actionAwaiter, Action.empty())
        {
            @Override
            public void show(AAType actionAwaiter)
            {
                throw new UnsupportedOperationException(
                        "This should never be called for ActionChoice.empty() Choice, \n\r as it is simply meant to do nothing but advance the awaiter."); //should never be called because the action is not null
            }
        };
    }
    
    
    protected Action<ActionAwaiterType> getAction()
    {
        return action;
    }
    
    
    public void select()
    {
        ActionAwaiterType actionAwaiterType = getAwaiter();
        actionAwaiterType.getRender().getHud().clearChoices();
        
        if (action != null)
        {
            action.enAct(actionAwaiterType);
            actionAwaiterType.advance();
            return;
        }
        
        show(actionAwaiterType);
    }
    
    
    public abstract void show(ActionAwaiterType actionAwaiter);
    
    
    private ObservableList<Node> getHudChildren(ActionAwaiterType actionAwaiter)
    {
        HudRender<ModelType> render = actionAwaiter.getRender();
        return render.getHud().getChildren();
    }
    
    
    public Node getNode()
    {
        return new ActionChoiceRender(this);
    }
    
    
    @Override
    public String description()
    {
        return this.description;
    }
    
    
    @Override
    public String name()
    {
        return this.name;
    }
    
    
    public ActionAwaiterType getAwaiter()
    {
        return awaiter;
    }
    
    
    public EventHandler<MouseEvent> getMouseEventHandler()
    {
        return event ->
        {
            if (event == null)
            {
                return;
            }
            if (event.getEventType() != MouseEvent.MOUSE_CLICKED)
            {
                return;
            }
            if (event.getButton() != MouseButton.PRIMARY)
            {
                return;
            }
            
            event.consume();
            select();
        };
    }
    
    
    public EventHandler<KeyEvent> getKeyEventHandler(String character)
    {
        if (character == null)
        {
            throw new IllegalArgumentException("Character cannot be null");
        }
        
        return event ->
        {
            if (event == null)
            {
                return;
            }
            if (event.getEventType() != KeyEvent.KEY_TYPED)
            {
                return;
            }
            if (!Objects.equals(event.getCharacter(), character))
            {
                return;
            }
            
            event.consume();
            select();
        };
    }
}
