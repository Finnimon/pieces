package com.gitgud.engine.view.events;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class AppendRemoveNodeOnMouseEvent implements EventHandler<MouseEvent>
{
    private final Group parent;
    
    
    private final Node appendix;
    
    
    private final EventType<MouseEvent> addEvent;
    
    
    private final EventType<MouseEvent> removeEvent;
    
    
    private final MouseButton mouseButton;
    
    
    public AppendRemoveNodeOnMouseEvent(Group parent, Node appendix, EventType<MouseEvent> addEvent,
                                        EventType<MouseEvent> removeEvent, MouseButton mouseButton)
    {
        this.parent = parent;
        this.appendix = appendix;
        this.addEvent = addEvent;
        this.removeEvent = removeEvent;
        this.mouseButton = mouseButton;
    }
    
    
    public AppendRemoveNodeOnMouseEvent(Group parent, Node appendix)
    {
        this(parent, appendix, MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_RELEASED, MouseButton.SECONDARY);
    }
    
    
    public static void add(Group parent, Node Appendix, EventType<MouseEvent> addEvent,
                           EventType<MouseEvent> removeEvent, MouseButton mouseButton)
    {
        EventHandler<MouseEvent> handler = new AppendRemoveNodeOnMouseEvent(parent,
                                                                            Appendix,
                                                                            addEvent,
                                                                            removeEvent,
                                                                            mouseButton);
        parent.addEventHandler(addEvent, handler);
        parent.addEventHandler(removeEvent, handler);
    }
    
    
    public static void add(Group parent, Node Appendix)
    {
        add(parent, Appendix, MouseEvent.MOUSE_PRESSED, MouseEvent.MOUSE_RELEASED, MouseButton.SECONDARY);
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
