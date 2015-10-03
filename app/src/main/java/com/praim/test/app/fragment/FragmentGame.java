package com.praim.test.app.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.praim.test.app.R;
import com.praim.test.app.databinding.FragmentGameBinding;
import com.praim.test.app.helpers.LevelLoader;
import com.praim.test.app.helpers.PreferenceHelper;
import com.praim.test.app.interfaces.IOpenLevelsActivity;
import com.praim.test.app.models.GameModel;
import com.praim.test.app.models.Level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FragmentGame extends Fragment implements IOpenLevelsActivity, LoaderManager.LoaderCallbacks<ArrayList<Level>> {
    public static final String MODEL = "model";

    private FragmentGameBinding mBinding;
    private GameModel mModel;
    private Random mRandom;

    public FragmentGame() {
        mRandom = new Random();
    }

    //region FragmentMethods
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            mModel = (GameModel) savedInstanceState.getSerializable(MODEL);
            restoreAndBindModel();
            changeUI(false);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MODEL, mModel);
    }

    @Override
    public void onStop() {
        super.onStop();
        saveModel();
    }
    //endregion

    //region ButtonEvents
    @SuppressWarnings("unused")
    public void onScoreClick(View view) {
        mModel.click();
        animateClickScoreView();
        if (mModel.isFinished())
            showNewGameDialog();
    }

    @SuppressWarnings("unused")
    public void onQuestionClick(View view) {
        openLevelsActivity(getModel().getLevels());
    }
    //endregion

    //region LoaderMethods
    @Override
    public Loader<ArrayList<Level>> onCreateLoader(int id, Bundle args) {
        return new LevelLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Level>> loader, ArrayList<Level> data) {
        mModel = new GameModel(data);
        restoreAndBindModel();
        changeUI(true);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Level>> loader) {
    }
    //endregion

    @SuppressWarnings("unused")
    public GameModel getModel() {
        return mModel;
    }

    @Override
    public void openLevelsActivity(HashMap<Integer, Level> levels) {
        IOpenLevelsActivity activity = (IOpenLevelsActivity) getActivity();
        activity.openLevelsActivity(levels);
    }

    private void restoreAndBindModel() {
        loadModel();
        if (mModel.isFinished())
            showNewGameDialog();
        mBinding.setFragment(this);
    }

    private void saveModel() {
        int score = mModel.getScore();
        PreferenceHelper.saveUserScore(getActivity(), score);
    }

    private void loadModel() {
        int score = PreferenceHelper.loadUserScore(getActivity());
        mModel.setScore(score);
    }

    private void animateClickScoreView() {
        mBinding.tvClickScore.setAlpha(1);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mBinding.tvClickScore.getLayoutParams();
        int parentWidth = mBinding.getRoot().getWidth();
        params.setMargins(mRandom.nextInt(parentWidth - mBinding.tvClickScore.getWidth()), params.topMargin, params.rightMargin, params.bottomMargin);
        mBinding.tvClickScore.animate().alpha(0).setDuration(250).start();
    }

    private void showNewGameDialog() {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.game_over)
                .positiveText(R.string.new_game)
                .cancelable(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        mModel.startNewGame();
                    }
                })
                .show();
    }

    private void changeUI(boolean animate) {
        if (animate) {
            mBinding.btClick.animate().alpha(1).setDuration(500).start();
            mBinding.btQuestion.animate().alpha(1).setDuration(500).start();
            mBinding.tvLevel.animate().alpha(1).setDuration(500).start();
            mBinding.tvScore.animate().alpha(1).setDuration(500).start();
        } else {
            mBinding.btClick.setAlpha(1);
            mBinding.btQuestion.setAlpha(1);
            mBinding.tvLevel.setAlpha(1);
            mBinding.tvScore.setAlpha(1);
        }
    }
}
