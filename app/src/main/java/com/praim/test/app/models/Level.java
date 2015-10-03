package com.praim.test.app.models;

import java.io.Serializable;

public class Level implements Serializable{
    public int level;
    public int score;
    public int scorePerClick;

    public Level(int level, int score, int scorePerClick) {
        this.level = level;
        this.score = score;
        this.scorePerClick = scorePerClick;
    }
}
