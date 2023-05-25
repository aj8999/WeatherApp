package com.wheatherapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wheatherapp.R;
import com.wheatherapp.helper.WeatherSubModel;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder>
{
    public ArrayList<WeatherSubModel> arrayListTemp;
    public Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        private AppCompatTextView textViewTime,textViewTemp,textViewTempDescription;
        public MyViewHolder(View v)
        {
            super(v);
            textViewTime = (AppCompatTextView) v.findViewById(R.id.tvWeatherSecondDayAdapterTime);
            textViewTemp = (AppCompatTextView) v.findViewById(R.id.tvWeatherSecondDayAdapterTemp);
            textViewTempDescription = (AppCompatTextView) v.findViewById(R.id.tvWeatherSecondDayAdapterDescription);
        }

    }

    public WeatherAdapter(ArrayList<WeatherSubModel> Data, Context context) {
        this.arrayListTemp=Data;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_weather, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        holder.textViewTime.setText(arrayListTemp.get(position).getTime());
        holder.textViewTemp.setText(arrayListTemp.get(position).getTemp());
        String Des=arrayListTemp.get(position).getTempDescription();
        holder.textViewTempDescription.setText(Des);

    }

    @Override
    public int getItemCount() {
        return arrayListTemp.size();
    }
}
