package com.gitgud.engine.view.infopane;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.utility.Strings;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;


public class NameDescribableInfoPane<Type extends Named & Describable> extends InfoPane<Type>
{
    public NameDescribableInfoPane(Label label, Background background, Border border)
    {
        super(label, background, border);
    }
    
    
    public NameDescribableInfoPane(Type type)
    {
        super(getString(type));
    }
    
    
    private static <T extends Named & Describable> String getString(T type)
    {
        return type.name().replace(Strings.LINE_BREAK, Strings.TABULATOR) + Strings.LINE_BREAK + type.description();
    }
}
