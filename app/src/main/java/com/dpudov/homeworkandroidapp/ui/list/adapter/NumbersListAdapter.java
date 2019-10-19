package com.dpudov.homeworkandroidapp.ui.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpudov.homeworkandroidapp.R;
import com.dpudov.homeworkandroidapp.data.db.NumberEntity;

import java.util.List;

public class NumbersListAdapter extends RecyclerView.Adapter<NumbersViewHolder> {
    private List<NumberEntity> mNumbers;
    private OnItemClickListener<NumberEntity> mClickListener;

    public NumbersListAdapter(@NonNull List<NumberEntity> numbers, OnItemClickListener<NumberEntity> clickListener) {
        this.mNumbers = numbers;
        this.mClickListener = clickListener;
    }


    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.number_item, parent, false);
        return new NumbersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
        NumberEntity current = mNumbers.get(position);
        if (current != null) {
            holder.getNumberTextView().setText(current.toString());
            holder.bind(current, mClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mNumbers == null ? 0 : mNumbers.size();
    }

    public void setNumbers(List<NumberEntity> mNumbers) {
        this.mNumbers = mNumbers;
        notifyDataSetChanged();
    }
}
