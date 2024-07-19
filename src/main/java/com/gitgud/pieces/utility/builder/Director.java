package com.gitgud.pieces.utility.builder;

public interface Director<BuildType>
{
    Builder<BuildType> changeBuilder(Builder<BuildType> builder);
    
    
    BuildType make(int type);
    
    
    /**
     * @return Default {@link BuildType} of current Builder
     */
    BuildType make();
    
    
    int calculateType(BuildType buildType);
    
    
    Builder<BuildType> getSuitedBuilder(int type);
}
