package com.dpudov.homeworkandroidapp.ui.list.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpudov.homeworkandroidapp.R;

public class NumbersViewHolder extends RecyclerView.ViewHolder {
    private TextView numberTextView;
    private LinearLayout root;

    public NumbersViewHolder(@NonNull View itemView) {
        super(itemView);
        numberTextView = itemView.findViewById(R.id.number);
        root = itemView.findViewById(R.id.root_layout);
    }

    public TextView getNumberTextView() {
        return numberTextView;
    }

    public LinearLayout getRoot() {
        return root;
    }
}
