package com.dpudov.homeworkandroidapp.ui.list.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpudov.homeworkandroidapp.R;
import com.dpudov.homeworkandroidapp.data.NumberModel;
import com.dpudov.homeworkandroidapp.ui.list.ListViewModel;

import java.util.List;

public class NumbersListAdapter extends RecyclerView.Adapter<NumbersViewHolder> {
    private List<NumberModel> numbers;
    private ListViewModel viewModel;

    public NumbersListAdapter(List<NumberModel> numbers) {
        this.numbers = numbers;
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
        holder.getNumberTextView().setText(numbers.get(position).toString());
        if (position % 2 == 0) {
            holder.getNumberTextView().setTextColor(Color.BLUE);
        } else {
            holder.getNumberTextView().setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
}
