package com.sarang.timelinedetail2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.sarang.timelinedetail2.databinding.ItemTimelineDetailPictureBinding;


public class PictureViewHolder extends DataBindingViewHolder<ItemTimelineDetailPictureBinding> {

    public static PictureViewHolder create(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ItemTimelineDetailPictureBinding binding = ItemTimelineDetailPictureBinding
                .inflate(layoutInflater, parent, false);

        return new PictureViewHolder(binding);
    }

    public PictureViewHolder(@NonNull ItemTimelineDetailPictureBinding binding) {
        super(binding);
    }
}
