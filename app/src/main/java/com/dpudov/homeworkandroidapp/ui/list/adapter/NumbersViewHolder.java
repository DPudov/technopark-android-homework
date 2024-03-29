package com.dpudov.homeworkandroidapp.ui.list.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpudov.homeworkandroidapp.R;
import com.dpudov.homeworkandroidapp.data.db.NumberEntity;

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

    public void bind(@NonNull final NumberEntity numberModel, @NonNull final OnItemClickListener<NumberEntity> clickListener) {
        getNumberTextView().setText(numberModel.toString());
        getRoot().setOnClickListener(view -> clickListener.onItemClick(numberModel));
        if (numberModel.isOdd()) {
            getNumberTextView().setTextColor(Color.BLUE);
        } else {
            getNumberTextView().setTextColor(Color.RED);
        }
    }
}
