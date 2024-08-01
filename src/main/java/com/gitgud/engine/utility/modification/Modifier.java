package com.gitgud.engine.utility.modification;

import com.gitgud.engine.model.DisApplicable;
import com.github.ruediste.polymorphicGson.GsonPolymorph;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


/**
 * Allows modification and demodification of an object
 *
 * @param <T> The type of Object to be modified and demodified
 * @author Finn L.
 * @Owner: Finn L.
 * @Since: 20.04.2024
 * @Version: 1.0
 */
@GsonPolymorph
public abstract class Modifier<T> implements DisApplicable<T>
{
    /**
     * Helper Method for applying multiple Modifiers at once.
     *
     * @param object   The object to modify,
     * @param modifier The modifiers that should be used.
     * @param <T>      The type of object to be modified.
     * @return The modified {@code object}.
     */
    public static <T> T applyModifiers(T object, Collection<Modifier<T>> modifier)
    {
        for (Modifier<T> m : modifier)
        {
            object = m.apply(object);//calls apply in case there is extra functionality.
        }
        
        return object;
    }
    
    
    @Override
    public T apply(@NotNull T t)
    {
        return modify(t);
    }
    
    
    /**
     * May only modify a clone of the original object
     *
     * @param t object to be modified
     * @return modified object
     */
    public abstract T modify(T t);
    
    
    @Override
    public T disApply(T t)
    {
        return demodify(t);
    }
    
    
    /**
     * May only demodify a clone of the original object
     *
     * @param t object to be demodified
     * @return demodified object
     */
    public abstract T demodify(T t);
}
