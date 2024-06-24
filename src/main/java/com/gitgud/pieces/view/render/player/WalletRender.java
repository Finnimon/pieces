package com.gitgud.pieces.view.render.player;

import com.gitgud.engine.view.UpdatableRender;
import com.gitgud.pieces.model.player.Wallet;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class WalletRender extends Group implements UpdatableRender<Wallet>
{
    private final Wallet wallet;
    private final GridPane gridPane = new GridPane();
    
    public WalletRender(Wallet wallet)
    {
        this.wallet = wallet;
        
        render(wallet);
    }
    
    
    @Override
    public void render(Wallet data)
    {
    
    }
    
    
    @Override
    public void updateRender()
    {
    
    }
    
    
    @Override
    public Wallet getData()
    {
        return wallet;
    }
}
