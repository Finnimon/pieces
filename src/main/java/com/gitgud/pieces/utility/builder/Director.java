package com.gitgud.pieces.utility.builder;

/**
 * Interface for builder director Pattern Director.
 *
 * @param <BuildType> The type of object to build.
 * @see Builder
 */
public interface Director<BuildType>
{
    /**
     * Sets a new builder to be used when calling {@link #make(int)}
     *
     * @param builder The builder to change
     */
    void changeBuilder(Builder<BuildType> builder);
    
    
    /**
     * Makes a new {@link BuildType}.
     *
     * @param type The type to build.
     * @return A new {@link BuildType}
     */
    BuildType make(int type);
    
    
    /**
     * @return Default {@link BuildType} of current Builder
     */
    BuildType make();
    
    
    int calculateType(BuildType buildType);
    
    
    Builder<BuildType> getSuitedBuilder(int type);
}
