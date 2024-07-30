package com.gitgud.engine.utility.modification;

import com.gitgud.engine.model.Durable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * A Modifier that allows a list of objects to be unmodified if duration is over.
 *
 * @param <T> @param <T> The type of Object to be modified and demodified
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 20.04.2024
 * @Version: 1.0
 */
public abstract class DurableModifier<T> extends Modifier<T> implements Durable
{
    /**
     * so far modified objects
     */
    private final ArrayList<T> modified = new ArrayList<>();
    
    
    @Override
    public void undo()
    {
        for (T d : modified)
        {
            demodify(d);
        }
        modified.clear();
    }
    
    
    @Override
    public T apply(@NotNull T t)
    {
        modified.add(t);
        return super.apply(t);
    }
    
    
    @Override
    public T disApply(T t)
    {
        modified.remove(t);
        return super.disApply(t);
    }
}
