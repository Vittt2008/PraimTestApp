package com.praim.test.app.helpers;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.praim.test.app.models.Level;

import java.util.ArrayList;

public class LevelLoader extends AsyncTaskLoader<ArrayList<Level>> {
    private ArrayList<Level> mLevels;

    public LevelLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ArrayList<Level> loadInBackground() {
        if (mLevels == null)
            mLevels = DownloadHelper.getInstance().downloadLevels();
        return mLevels;
    }
}
