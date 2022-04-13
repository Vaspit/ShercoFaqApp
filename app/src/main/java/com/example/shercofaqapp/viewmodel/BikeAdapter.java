package com.example.shercofaqapp.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shercofaqapp.R;
import com.example.shercofaqapp.databinding.GarageItemBinding;
import com.example.shercofaqapp.model.Bike;

import java.util.ArrayList;

public class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.BikeViewHolder> {

    private OnItemClickListener onItemClickListener;
    private ArrayList<Bike> bikesArrayList = new ArrayList<>();

    @NonNull
    @Override
    public BikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        GarageItemBinding garageItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.garage_item,
                parent, false
        );

        return new BikeViewHolder(garageItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BikeViewHolder holder, int position) {

        Bike bike = bikesArrayList.get(position);
        holder.garageItemBinding.setBike(bike);

    }

    @Override
    public int getItemCount() {
        return bikesArrayList.size();
    }

    class BikeViewHolder extends RecyclerView.ViewHolder {

        GarageItemBinding garageItemBinding;

        public BikeViewHolder(@NonNull GarageItemBinding garageItemBinding) {
            super(garageItemBinding.getRoot());

            this.garageItemBinding = garageItemBinding;
            garageItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(bikesArrayList.get(position));
                    }

                }
            });

        }
    }

    public interface OnItemClickListener {

        void onItemClick(Bike bike);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setBikesArrayList(ArrayList<Bike> bikesArrayList) {
        this.bikesArrayList = bikesArrayList;
        notifyDataSetChanged();
    }

}
