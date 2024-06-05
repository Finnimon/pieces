package com.gitgud.utility.modification;

import java.util.Collection;


public abstract class Modifier<T>
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
    
    
    /**
     * May only demodify a clone of the original object
     *
     * @param t object to be demodified
     * @return demodified object
     */
    public abstract T demodify(T t);
}
