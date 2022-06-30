package de.murmelmeister.lobby.utils;

import java.util.ArrayList;
import java.util.UUID;

public class Lists {

    private ArrayList<UUID> build;

    public Lists() {
        setBuild(new ArrayList<>());
    }

    public ArrayList<UUID> getBuild() {
        return build;
    }

    public void setBuild(ArrayList<UUID> build) {
        this.build = build;
    }
}
