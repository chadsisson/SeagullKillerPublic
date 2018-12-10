package org.brk.rspeer.data.settings;

public class ScriptSettings {

    private boolean doBury;
    private int trainAttack;
    private int trainStrength;
    private int trainDefence;
    private int trainPrayer;
    private static ScriptSettings instance;

    public ScriptSettings(boolean doBury, int trainAttack, int trainStrength, int trainDefence, int trainPrayer) {
        this.doBury = doBury;
        this.trainAttack = trainAttack;
        this.trainStrength = trainStrength;
        this.trainDefence = trainDefence;
        this.trainPrayer = trainPrayer;
    }

    public static ScriptSettings getInstance() {
        if (instance == null) {
            instance = new ScriptSettings(false, 0, 0, 0, 0);
        }
        return instance;
    }

    public boolean isDoBury() {
        return doBury;
    }

    public void setDoBury(boolean doBury) {
        this.doBury = doBury;
    }

    public int getTrainAttack() {
        return trainAttack;
    }

    public void setTrainAttack(int trainAttack) {
        this.trainAttack = trainAttack;
    }

    public int getTrainStrength() {
        return trainStrength;
    }

    public void setTrainStrength(int trainStrength) {
        this.trainStrength = trainStrength;
    }

    public int getTrainDefence() {
        return trainDefence;
    }

    public void setTrainDefence(int trainDefence) {
        this.trainDefence = trainDefence;
    }

    public int getTrainPrayer() {
        return trainPrayer;
    }

    public void setTrainPrayer(int trainPrayer) {
        this.trainPrayer = trainPrayer;
    }
}
