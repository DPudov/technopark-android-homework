package com.dpudov.homeworkandroidapp.ui.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpudov.homeworkandroidapp.R;
import com.dpudov.homeworkandroidapp.data.NumberModel;

import java.util.List;

public class NumbersListAdapter extends RecyclerView.Adapter<NumbersViewHolder> {
    private List<NumberModel> mNumbers;
    private OnItemClickListener<NumberModel> mClickListener;

    public NumbersListAdapter(@NonNull List<NumberModel> numbers, OnItemClickListener<NumberModel> clickListener) {
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
        NumberModel current = mNumbers.get(position);
        if (current != null) {
            holder.getNumberTextView().setText(current.toString());
            holder.bind(current, mClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mNumbers.size();
    }
}
