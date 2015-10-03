package com.praim.test.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.praim.test.app.R;
import com.praim.test.app.activity.ActivityMain;
import com.praim.test.app.adapter.AdapterLevels;
import com.praim.test.app.models.Level;

import java.util.HashMap;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class FragmentLevels extends Fragment {
    private static final String LEVELS = "levels";
    private RecyclerView mRecyclerView;
    private AdapterLevels mAdapter;

    public static FragmentLevels newInstance(HashMap<Integer, Level> levels) {
        Bundle args = new Bundle();
        args.putSerializable(ActivityMain.LEVELS, levels);
        FragmentLevels fragment = new FragmentLevels();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_levels, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.rv_levels);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new SlideInUpAnimator());
        mRecyclerView.getItemAnimator().setAddDuration(500);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new AdapterLevels((HashMap<Integer, Level>) getArguments().getSerializable(ActivityMain.LEVELS));
        mRecyclerView.setAdapter(mAdapter);
    }
}
