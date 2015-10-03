package com.praim.test.app.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.praim.test.app.BR;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameModel extends BaseObservable implements Serializable {
    private int mScore;
    private int mLevel;
    private int mPreviousScorePerClick;

    private HashMap<Integer, Level> mLevels;

    public GameModel() {
        mLevels = new LinkedHashMap<Integer, Level>() {{
            put(1, new Level(1, 0, 1));
            put(2, new Level(2, 12, 2));
            put(3, new Level(3, 30, 5));
            put(4, new Level(4, 70, 7));
            put(5, new Level(5, 150, 10));
            put(6, new Level(6, 250, 12));
            put(7, new Level(7, 350, 15));
            /*put(8, new Level(8, 500, 20));
            put(9, new Level(9, 700, 24));
            put(10, new Level(10, 1000, 27));
            put(11, new Level(11, 1500, 31));
            put(12, new Level(12, 2300, 32));
            put(13, new Level(13, 3000, 35));*/
        }};

        mScore = 0;
        mLevel = 1;
    }

    public GameModel(List<Level> levels) {
        mLevels = new LinkedHashMap<Integer, Level>();
        for (Level level : levels)
            mLevels.put(level.level, level);
        mScore = 0;
        mLevel = 1;
    }

    //region GetMethods
    @Bindable
    public int getScore() {
        return mScore;
    }

    @SuppressWarnings("unused")
    @Bindable
    public int getLevel() {
        return mLevel;
    }

    @SuppressWarnings("unused")
    @Bindable
    public int getClickScore() {
        Level currentLevel = mLevels.get(mLevel);
        return currentLevel.scorePerClick;
    }

    @SuppressWarnings("unused")
    @Bindable
    public int getPreviousScorePerClick() {
        return mPreviousScorePerClick;
    }

    public HashMap<Integer, Level> getLevels() {
        return mLevels;
    }
    //endregion

    public void click() {
        Level currentLevel = mLevels.get(mLevel);
        Level nextLevel = mLevels.get(mLevel + 1);
        mPreviousScorePerClick = currentLevel.scorePerClick;
        mScore = mScore + mPreviousScorePerClick;
        if (mScore >= nextLevel.score)
            mLevel = mLevel + 1;
        notifyPropertyChanged(BR._all);
    }

    public void startNewGame() {
        mScore = 0;
        mLevel = 1;
        notifyPropertyChanged(BR._all);
    }

    public void setScore(int score) {
        mScore = score;
        mLevel = mLevels.size();
        for (Level level : mLevels.values()) {
            if (mScore < level.score) {
                mLevel = level.level - 1;
                break;
            }
        }
        notifyPropertyChanged(BR._all);
    }

    public boolean isFinished() {
        return mLevel == mLevels.size();
    }
}
