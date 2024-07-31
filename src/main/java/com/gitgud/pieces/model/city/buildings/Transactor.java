package com.gitgud.pieces.model.city.buildings;

/**
 * Classes that can change the value of something for a cost.
 *
 * @param <T>
 */
public interface Transactor<T>
{
    /**
     * Calls {@link #transact(Object)} if the transaction is possible.
     *
     * @param t The value to change upon transaction.
     */
    default void transactIfPossible(T t)
    {
        if (isTransactionPossible(t))
        {
            transact(t);
        }
    }
    
    
    /**
     * Determine whether the transaction is possible.
     *
     * @param t The value to change upon transaction.
     * @return Whether the transaction is possible.
     */
    boolean isTransactionPossible(T t);
    
    
    /**
     * Changes the value of {@code t} and deducts the cost.
     *
     * @param t The value to change upon transaction.
     */
    private void transact(T t)
    {
        deductCostFromInventory(t);
        changeValue(changeValue(t));
    }
    
    
    /**
     * Deducts the cost for changing {@code t} from the player inventory.
     * @param t The value whose changing cost is to be deducted.
     */
    void deductCostFromInventory(T t);
    
    
    /**
     * Change the value of {@code t} by adding it to the inventory or causing a reaction such as a level-up.
     * @param value The value to change.
     * @return The changed value. Can often be ignored.
     */
    T changeValue(T value);
    
    
    /**
     * Get the cost of changing {@code t}.
     * @param t The value to get the cost of changing.
     * @return The cost of changing {@code t}.
     */
    Object getCost(T t);
}
