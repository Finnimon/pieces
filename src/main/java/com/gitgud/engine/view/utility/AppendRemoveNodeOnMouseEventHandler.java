package com.gitgud.engine.view.utility;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


/**
 * Mouse event handler for appending/removing nodes on the {@link Pane}
 *
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 * @see com.gitgud.engine.view.infopane.InfoPane
 */
public class AppendRemoveNodeOnMouseEventHandler implements EventHandler<MouseEvent>
{
    private final Pane parent;
    
    
    private final Node appendix;
    
    
    private final EventType<MouseEvent> addEvent;
    
    
    private final EventType<MouseEvent> removeEvent;
    
    
    private final MouseButton mouseButton;
    
    
    private AppendRemoveNodeOnMouseEventHandler(Pane parent, Node appendix)
    {
        this(parent, appendix, MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_RELEASED, MouseButton.SECONDARY);
    }
    
    
    /**
     * Creates a new AppendRemoveNodeOnMouseEventHandler for the specified values.
     *
     * @param parent      The parent {@link Pane} to add the event handler to and appendix to.
     * @param appendix    The appendix {@link Node} to append or remove.
     * @param addEvent    The EventType to add the appendix.
     * @param removeEvent The EventType to remove the appendix.
     * @param mouseButton The MouseButton to trigger the event.
     */
    public AppendRemoveNodeOnMouseEventHandler(Pane parent, Node appendix, EventType<MouseEvent> addEvent,
                                               EventType<MouseEvent> removeEvent, MouseButton mouseButton)
    {
        this.parent = parent;
        this.appendix = appendix;
        this.addEvent = addEvent;
        this.removeEvent = removeEvent;
        this.mouseButton = mouseButton;
    }
    
    
    /**
     * Defaults to {@link #add(Pane, Node, EventType, EventType, MouseButton)} with {@link MouseEvent#MOUSE_PRESSED},
     * {@link MouseEvent#MOUSE_RELEASED} and {@link MouseButton#SECONDARY}
     *
     * @see #add(Pane, Node, EventType, EventType, MouseButton).
     */
    public static void add(Pane parent, Node Appendix)
    {
        add(parent, Appendix, MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_RELEASED, MouseButton.SECONDARY);
    }
    
    
    /**
     * Factory method that adds the Eventhandler to the specified parent.
     *
     * @param parent      The parent {@link Pane} to add the event handler to and appendix to.
     * @param Appendix    The appendix to be appended or removed
     * @param addEvent    The EventType to add the appendix.
     * @param removeEvent The EventType to remove the appendix.
     * @param mouseButton The MouseButton to trigger the event.
     */
    public static void add(Pane parent, Node Appendix, EventType<MouseEvent> addEvent,
                           EventType<MouseEvent> removeEvent, MouseButton mouseButton)
    {
        EventHandler<MouseEvent> handler = new AppendRemoveNodeOnMouseEventHandler(parent,
                                                                                   Appendix,
                                                                                   addEvent,
                                                                                   removeEvent,
                                                                                   mouseButton);
        parent.addEventHandler(addEvent, handler);
        parent.addEventHandler(removeEvent, handler);
    }
    
    
    @Override
    public void handle(MouseEvent event)
    {
        if (event.getButton() != mouseButton)
        {
            return;
        }
        
        if (event.getEventType() == addEvent && !parent.getChildren().contains(appendix))
        {
            parent.toFront();
            parent.getChildren().add(appendix);
            double x = event.getX();
            double y = event.getY();
            appendix.setTranslateX(x);
            appendix.setTranslateY(y);
        }
        else if (event.getEventType() == removeEvent)
        {
            parent.getChildren().remove(appendix);
        }
        else
        {
            return;
        }
        
        event.consume();
    }
}
