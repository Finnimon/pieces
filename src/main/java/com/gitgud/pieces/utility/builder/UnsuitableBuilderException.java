package com.gitgud.pieces.utility.builder;

public class UnsuitableBuilderException extends IllegalArgumentException
{
    public UnsuitableBuilderException(Builder<?> builder, int type)
    {
        super("Unsuitable builder:\t" + builder.getClass().getSimpleName() + ", for type:\t" + type);
    }
}
