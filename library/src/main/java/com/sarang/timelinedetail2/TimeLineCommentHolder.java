package com.sarang.timelinedetail2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarang.timelinedetail2.databinding.ItemCommentBinding;

public class TimeLineCommentHolder extends RecyclerView.ViewHolder {
    ItemCommentBinding mBinding;

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
