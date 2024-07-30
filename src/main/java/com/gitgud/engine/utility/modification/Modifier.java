package com.gitgud.engine.utility.modification;

import com.gitgud.engine.model.DisApplicable;
import com.github.ruediste.polymorphicGson.GsonPolymorph;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


@GsonPolymorph
public abstract class Modifier<T> implements DisApplicable<T>
{
    public static <T> T applyModifiers(T object, Collection<Modifier<T>> modifier)
    {
        for (Modifier<T> m : modifier)
        {
            object = m.modify(object);
        }
        
        
        return object;
    }
    
    
    /**
     * May only modify a clone of the original object
     *
     * @param t object to be modified
     * @return modified object
     */
    public abstract T modify(T t);
    
    
    @Override
    public T apply(@NotNull T t)
    {
        return modify(t);
    }
    
    
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
