package com.gitgud.pieces.model.city.buildings;

public interface Transactor<T>
{
    default void transact(T t)
    {
        deductCostFromInventory(t);
        changeValue(changeValue(t));
    }
    
    
    boolean isTransactionPossible(T t);
    
    
    default void transactIfPossible(T t)
    {
        if (isTransactionPossible(t))
        {
            transact(t);
        }
    }
    
    
    void deductCostFromInventory(T t);
    
    
    T changeValue(T value);
}
