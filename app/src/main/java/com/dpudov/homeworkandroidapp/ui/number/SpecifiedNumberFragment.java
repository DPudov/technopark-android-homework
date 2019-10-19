package com.dpudov.homeworkandroidapp.ui.number;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dpudov.homeworkandroidapp.AppConstants;
import com.dpudov.homeworkandroidapp.R;
import com.dpudov.homeworkandroidapp.data.db.NumberEntity;


public class SpecifiedNumberFragment extends Fragment {
    private TextView mNumberTextView;

    public SpecifiedNumberFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_specified_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNumberTextView = view.findViewById(R.id.specified_number);
        Bundle args = getArguments();
        if (args != null) {
            int number = args.getInt(AppConstants.SPECIFIED_NUMBER);
            int color = args.getInt(AppConstants.SPECIFIED_NUMBER_COLOR);
            NumberEntity numberModel = new NumberEntity(number);
            mNumberTextView.setText(numberModel.toString());
            mNumberTextView.setTextColor(color);
        }
    }
}
