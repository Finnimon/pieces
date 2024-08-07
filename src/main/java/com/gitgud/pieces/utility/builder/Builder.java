package com.gitgud.pieces.utility.builder;

public interface Builder<Type>
{
    Type result();
    
    
    void reset();
    
    
    /**
     * @param type the type as int to build
     * @throws UnsuitableBuilderException if (!{@link #canBuild(int type)})
     */
    default void build(int type)
    {
        if (!canBuild(type))
        {
            throw new UnsuitableBuilderException(this, type);
        }
        
        tryBuild(type);
    }
    
    
    boolean canBuild(int type);
    
    
    /**
     * May behave erratically if {@link #canBuild(int type)} returns false.
     *
     * @param type the type as int to try to build
     * @throws NullPointerException       if {@link #canBuild(int type)} returns false.
     * @throws UnsuitableBuilderException if {@link #canBuild(int type)} returns false.
     * @throws IllegalArgumentException   if {@link #canBuild(int type)} returns false.
     */
    void tryBuild(int type);
}
