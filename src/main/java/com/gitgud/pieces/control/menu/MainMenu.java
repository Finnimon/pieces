package com.gitgud.pieces.control.menu;

@Deprecated
public class MainMenu implements OptionMenu
{
    private final String name;
    
    
    private final Menu[] options;
    
    
    private MainMenu(String name, Menu[] options)
    {
        this.name = name;
        this.options = options;
    }
    
    
    public static MainMenu create()
    {
        Menu[] options = new Menu[]{
        };
        
        return new MainMenu("Main Menu", options);
    }
    
    
    @Override
    public String name()
    {
        return name;
    }
    
    
    @Override
    public Menu[] options()
    {
        return options;
    }
}
