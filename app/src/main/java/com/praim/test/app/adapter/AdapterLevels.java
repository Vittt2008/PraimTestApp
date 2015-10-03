package com.praim.test.app.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.praim.test.app.R;
import com.praim.test.app.databinding.ElementLevelBinding;
import com.praim.test.app.models.Level;

import java.util.HashMap;

public class AdapterLevels extends RecyclerView.Adapter<AdapterLevels.ViewHolder> {
    private HashMap<Integer, Level> mLevels;

    public AdapterLevels(HashMap<Integer, Level> levels) {
        mLevels = levels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ElementLevelBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.element_level, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getBinding().setLevel(mLevels.get(position + 1));

    }

    @Override
    public int getItemCount() {
        return mLevels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ElementLevelBinding mBinding;

        public ViewHolder(ElementLevelBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public ElementLevelBinding getBinding() {
            return mBinding;
        }
    }
}
