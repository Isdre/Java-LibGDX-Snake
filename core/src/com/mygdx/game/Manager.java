package com.mygdx.game;

public class Manager {
    private static Manager Instance = null;
    boolean death = false;
    private Manager() {

    }

    public static Manager getInstance() {
        if (Instance == null) Instance = new Manager();

        return Instance;
    }
}
