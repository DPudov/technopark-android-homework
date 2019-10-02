package com.dpudov.homeworkandroidapp.ui.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dpudov.homeworkandroidapp.R;
import com.dpudov.homeworkandroidapp.data.NumberService;
import com.dpudov.homeworkandroidapp.ui.list.adapter.NumbersListAdapter;


public class ListFragment extends Fragment {

    private NumbersListAdapter numbersListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        numbersListAdapter = new NumbersListAdapter(NumberService.getInstance().getData());
        layoutManager = new GridLayoutManager(getContext(), calculateNumberOfColumns(3));

        recyclerView = view.findViewById(R.id.numbers_list_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(numbersListAdapter);
        return view;
    }

    protected int calculateNumberOfColumns(int base) {
        int columns = base;
        String screenSize = getScreenSizeCategory();

        switch (screenSize) {
            case "small":
                if (base != 1) {
                    columns = columns - 1;
                }
                break;
            case "normal":
                // Do nothing
                break;
            case "large":
                columns += 2;
                break;
            case "xlarge":
                columns += 3;
                break;
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = (int) (columns * 1.5);
        }

        return columns;
    }

    protected String getScreenOrientation() {
        String orientation = "undefined";

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            orientation = "landscape";
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            orientation = "portrait";
        }

        return orientation;
    }

    // Custom method to get screen size category
    protected String getScreenSizeCategory() {
        int screenLayout = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                // small screens are at least 426dp x 320dp
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                // normal screens are at least 470dp x 320dp
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                // large screens are at least 640dp x 480dp
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                // xlarge screens are at least 960dp x 720dp
                return "xlarge";
            default:
                return "undefined";
        }
    }
}
