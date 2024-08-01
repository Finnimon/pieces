package com.gitgud.pieces.control.menu;

@Deprecated
public class LoadMenu extends BaseActionMenu
{
    public LoadMenu()
    {
        super("Load", x -> true, x -> showLoadScreen());
    }
    
    
    private static void showLoadScreen()
    {
    }
}
