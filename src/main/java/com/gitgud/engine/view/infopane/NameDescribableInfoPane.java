package com.gitgud.engine.view.infopane;

import com.gitgud.engine.model.gameobjects.Describable;
import com.gitgud.engine.model.gameobjects.Named;
import com.gitgud.engine.utility.Strings;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;


/**
 *
 * @param <Type>
 * @author Julius Rohe, Finn L.
 * @Owner: Finn L.
 * @Since: 30.06.2024
 * @Version: 1.1
 */
public class NameDescribableInfoPane<Type extends Named & Describable> extends InfoPane<Type>
{
    /**
     * Concat the name and the description of a {@link Named}&{@link Describable} and defaults to {@link InfoPane#InfoPane(String)}.
     *
     * @param type The {@link Named}&{@link Describable}
     */
    public NameDescribableInfoPane(Type type)
    {
        super(getString(type));
    }
    
    
    /**
     * Defaults to {@link InfoPane#InfoPane(Label, Background, Border)}.
     * @see InfoPane#InfoPane(Label, Background, Border)
     */
    protected NameDescribableInfoPane(Label label, Background background, Border border)
    {
        super(label, background, border);
    }
    
    
    /**
     * Determines the text of the {@link InfoPane}.
     *
     * @param type A Named & Describable.
     * @return The text string.
     * @param <T> The type of the Named & Describable.
     */
    private static <T extends Named & Describable> String getString(T type)
    {
        return type.name().replace(Strings.LINE_BREAK, Strings.TABULATOR) + Strings.LINE_BREAK + type.description();
    }
}
