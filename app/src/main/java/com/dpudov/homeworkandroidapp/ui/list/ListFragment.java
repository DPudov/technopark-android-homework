package com.dpudov.homeworkandroidapp.ui.list;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dpudov.homeworkandroidapp.AppConstants;
import com.dpudov.homeworkandroidapp.R;
import com.dpudov.homeworkandroidapp.data.NumberModel;
import com.dpudov.homeworkandroidapp.data.NumberService;
import com.dpudov.homeworkandroidapp.ui.list.adapter.NumbersListAdapter;
import com.dpudov.homeworkandroidapp.ui.list.adapter.OnItemClickListener;

import java.util.List;


public class ListFragment extends Fragment {

    private NumbersListAdapter mNumbersListAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ImageView mPlusButton;
    private Parcelable mListState;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnItemClickListener<NumberModel> clickListener = new OnItemClickListener<NumberModel>() {
            @Override
            public void onItemClick(NumberModel item) {
                int number = item.getNumber();
                NavController controller = Navigation.findNavController(view);
                Bundle bundle = new Bundle();
                bundle.putInt(AppConstants.SPECIFIED_NUMBER, number);
                int color = item.isOdd() ? Color.BLUE : Color.RED;
                bundle.putInt(AppConstants.SPECIFIED_NUMBER_COLOR, color);
                controller.navigate(R.id.action_listFragment_to_specifiedNumberFragment, bundle);
            }
        };

        mRecyclerView = view.findViewById(R.id.numbers_list_view);
        mPlusButton = view.findViewById(R.id.add_number);

        mLayoutManager = new GridLayoutManager(getContext(), calculateNumberOfColumns(3));

        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(AppConstants.NUMBER_LIST_STATE);
            mLayoutManager.onRestoreInstanceState(mListState);
        }

        mNumbersListAdapter = new NumbersListAdapter(NumberService.getInstance().getData(), clickListener);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mNumbersListAdapter);

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NumberModel> list = NumberService.getInstance().getData();
                NumberModel prev = list.get(list.size() - 1);
                NumberService.getInstance().addNumber(prev.getNumber() + 1);
                mNumbersListAdapter.notifyItemInserted(list.size() - 1);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        mListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(AppConstants.NUMBER_LIST_STATE, mListState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(AppConstants.NUMBER_LIST_STATE);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mLayoutManager != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                ((GridLayoutManager) mLayoutManager).setSpanCount(4);
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                ((GridLayoutManager) mLayoutManager).setSpanCount(3);
            }
        }
    }

    private int calculateNumberOfColumns(int base) {
        int columns = base;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns += 1;
        }

        return columns;
    }
}
