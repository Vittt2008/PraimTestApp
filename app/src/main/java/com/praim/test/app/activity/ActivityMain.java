package com.praim.test.app.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.praim.test.app.fragment.FragmentGame;
import com.praim.test.app.helpers.BaseActivity;
import com.praim.test.app.interfaces.IOpenLevelsActivity;
import com.praim.test.app.models.Level;

import java.util.HashMap;

public class ActivityMain extends BaseActivity implements IOpenLevelsActivity {
    public static final String LEVELS = "levels";

    @Override
    protected Fragment createFragment() {
        return new FragmentGame();
    }

    @Override
    protected String getFragmentName() {
        return "game";
    }

    @Override
    public void openLevelsActivity(HashMap<Integer, Level> levels) {
        Intent intent = new Intent(this, ActivityLevels.class);
        intent.putExtra(LEVELS, levels);
        startActivity(intent);
    }
}
