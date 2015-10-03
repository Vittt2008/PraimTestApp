package com.praim.test.app.helpers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.praim.test.app.R;

public abstract class BaseActivity extends AppCompatActivity {
    protected Fragment mFragment;


    protected abstract Fragment createFragment();

    protected abstract String getFragmentName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        if (savedInstanceState == null) {
            mFragment = createFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root_container, mFragment, getFragmentName())
                    .commit();
        } else {
            mFragment = getSupportFragmentManager().findFragmentByTag(getFragmentName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

