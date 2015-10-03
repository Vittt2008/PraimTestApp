package com.praim.test.app.activity;

import android.support.v4.app.Fragment;

import com.praim.test.app.fragment.FragmentLevels;
import com.praim.test.app.helpers.BaseActivity;
import com.praim.test.app.models.Level;

import java.util.HashMap;

public class ActivityLevels extends BaseActivity{
    @Override
    protected Fragment createFragment() {
        return FragmentLevels.newInstance((HashMap<Integer, Level>) getIntent().getSerializableExtra(ActivityMain.LEVELS));
    }

    @Override
    protected String getFragmentName() {
        return "levels";
    }
}
