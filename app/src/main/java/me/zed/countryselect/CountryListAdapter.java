package me.zed.countryselect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {

    public List<Result> localDataSet;
    private OnRadioClickListener radioClickListener = null;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private final RadioButton radioButton;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.countryName);
            imageView = view.findViewById(R.id.flag_image);
            radioButton = view.findViewById(R.id.radioButtonCountry);
        }
    }

    public CountryListAdapter(List<Result> dataSet) {
        localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.country_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String name = localDataSet.get(position).getName();
        String url = getUrlFor(localDataSet.get(position).getCode());
        viewHolder.textView.setText(name);
        Glide.with(viewHolder.itemView.getContext())
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(viewHolder.imageView);
        viewHolder.radioButton.setOnClickListener(v -> {
            if(radioClickListener != null){
                radioClickListener.onRadioClick(name, url);
            }
        });
    }

    interface OnRadioClickListener{
        void onRadioClick(@NonNull String name, @NonNull String url);
    }

    public void addRadioClickListener(@NonNull OnRadioClickListener listener) {
        radioClickListener = listener;
    }

    private String getUrlFor(String code) {
        return "https://www.countryflags.io/" + code + "/flat/32.png";
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}



