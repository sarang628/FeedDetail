package com.sarang.feed_detail.holder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarang.feed_detail.databinding.ItemCommentBinding;

public class TimeLineCommentHolder extends RecyclerView.ViewHolder {
    public ItemCommentBinding mBinding;

    public TimeLineCommentHolder(@NonNull ItemCommentBinding itemCommentBinding) {
        super(itemCommentBinding.getRoot());
        mBinding = itemCommentBinding;
    }

    public static RecyclerView.ViewHolder create(ViewGroup parent) {
        return new TimeLineCommentHolder(
                ItemCommentBinding.inflate(
                        LayoutInflater.from(parent.getContext()), parent, false));
    }
}
