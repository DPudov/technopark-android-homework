package com.dpudov.homeworkandroidapp.ui.number;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dpudov.homeworkandroidapp.AppConstants;
import com.dpudov.homeworkandroidapp.R;
import com.dpudov.homeworkandroidapp.data.NumberModel;


public class SpecifiedNumberFragment extends Fragment {
    private TextView mNumberTextView;

    public SpecifiedNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_specified_number, container, false);
        mNumberTextView = view.findViewById(R.id.specified_number);
        Bundle args = getArguments();
        if (args != null) {
            int number = args.getInt(AppConstants.SPECIFIED_NUMBER);
            int color = args.getInt(AppConstants.SPECIFIED_NUMBER_COLOR);
            NumberModel numberModel = new NumberModel(number);
            mNumberTextView.setText(numberModel.toString());
            mNumberTextView.setTextColor(color);
        }

        return view;
    }
}
