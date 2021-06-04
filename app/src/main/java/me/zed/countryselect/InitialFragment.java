package me.zed.countryselect;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class InitialFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_inital, container, false);

        View countryItem = root.findViewById(R.id.countrySelector);
        ImageView flag = root.findViewById(R.id.flagImage);
        TextView countryName = root.findViewById(R.id.flagName);

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String name  = sharedPref.getString(getString(R.string.country_key), "India");
        String imageUrl = sharedPref.getString(getString(R.string.country_image_url_key), getString(R.string.indian_flag));

        countryName.setText(name);

        Glide.with(requireContext())
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(flag);

        countryItem.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, CountrySelectFragment.class, null)
                    .addToBackStack(null)
                    .commit();
        });

        return root;
    }
}