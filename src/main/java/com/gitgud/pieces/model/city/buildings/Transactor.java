package com.gitgud.pieces.model.city.buildings;

public interface Transactor<T>
{
    default void transactIfPossible(T t)
    {
        if (isTransactionPossible(t))
        {
            transact(t);
        }
    }
    
    
    boolean isTransactionPossible(T t);
    
    
    default void transact(T t)
    {
        deductCostFromInventory(t);
        changeValue(changeValue(t));
    }
    
    
    void deductCostFromInventory(T t);
    
    
    T changeValue(T value);
}
