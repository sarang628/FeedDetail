package com.sarang.timelinedetail2;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class DataBindingViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {
    V mBinding;

    public DataBindingViewHolder(@NonNull V binding) {
        super(binding.getRoot());
        mBinding = binding;
    }
}
