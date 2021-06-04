package me.zed.countryselect;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;

public class CountrySelectFragment extends BottomSheetDialogFragment {

    MainViewModel vm;
    private RecyclerView mRecyclerView;
    private CountryListAdapter mAdapter;
    private ProgressBar pb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_country_select, container, false);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String nameInit  = sharedPref.getString(getString(R.string.country_key), "India");

        vm = new ViewModelProvider(this).get(MainViewModel.class);
        pb = root.findViewById(R.id.progressBar);
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mAdapter = new CountryListAdapter(new ArrayList<>(), nameInit);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        mAdapter.addRadioClickListener((String name, String url) -> {
            editor.putString(getString(R.string.country_key), name);
            editor.putString(getString(R.string.country_image_url_key), url);
            editor.apply();
            mAdapter.currentCountry = name;
            getParentFragmentManager().popBackStack();
        });

        vm.getUsers().observe(getViewLifecycleOwner(), flags -> {
            mAdapter.localDataSet = flags;
            pb.setVisibility(View.GONE);
            mRecyclerView.setAdapter(mAdapter);
        });
        return root;
    }
}