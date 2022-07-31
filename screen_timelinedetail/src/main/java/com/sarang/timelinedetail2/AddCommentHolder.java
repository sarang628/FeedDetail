package com.sarang.timelinedetail2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.sarang.timelinedetail2.databinding.ItemWriteCommentBinding;

public class AddCommentHolder extends DataBindingViewHolder<ItemWriteCommentBinding> {

    public static AddCommentHolder create(ViewGroup parent) {
        return new AddCommentHolder(ItemWriteCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public AddCommentHolder(@NonNull ItemWriteCommentBinding binding) {
        super(binding);
    }

    public void setViewModel(TimeLineDetailViewModel timeLineDetailViewModel) {
        mBinding.setViewModel(timeLineDetailViewModel);
    }
}
