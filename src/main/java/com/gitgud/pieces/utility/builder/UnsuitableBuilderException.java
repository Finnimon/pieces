package com.gitgud.pieces.utility.builder;

/**
 * Exception for unsuitable builders.
 *
 * @author Finn L.
 * @see Builder
 * @see Director
 */
public class UnsuitableBuilderException extends IllegalArgumentException
{
    public UnsuitableBuilderException(Builder<?> builder, int type)
    {
        super("Unsuitable builder:\t" + builder.getClass().getSimpleName() + ", for type:\t" + type);
    }
}
