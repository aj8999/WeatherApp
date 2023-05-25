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
    public ArrayList<WeatherSubModel> arrayListSchedular;
    public ArrayList<WeatherSubModel> arrayListTemp;
    public Context context;
    String stringTime="",stringTemp="",stringHumidity="";
    int arrayCount;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatTextView textViewTime,textViewTemp,textViewTempDescription;
        AppCompatImageView imageViewWeather;


        public MyViewHolder(View v)
        {
            super(v);
            textViewTime = (AppCompatTextView) v.findViewById(R.id.tvWeatherSecondDayAdapterTime);
            textViewTemp = (AppCompatTextView) v.findViewById(R.id.tvWeatherSecondDayAdapterTemp);
//            textViewHumidity = (AppCompatTextView) v.findViewById(R.id.tvWeatherSecondDayAdapterHumidity);
            textViewTempDescription = (AppCompatTextView) v.findViewById(R.id.tvWeatherSecondDayAdapterDescription);

//            relativeLayoutNext = (RelativeLayout) v.findViewById(R.id.RLFertilizerNext);
//            cardView = (CardView) v.findViewById(R.id.cvFertilizer);

//            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            int position=getAdapterPosition();
//            sharedPreferenceManager = new SharedPreferenceManager(context);
//            sharedPreferenceManager.connectDB();
//            sharedPreferenceManager.setString(IConstant.PLOTSCHEDULE_ID,arrayListSchedular.get(position).getPlotScheduleId());
//            sharedPreferenceManager.closeDB();
//
//            ((DashboardActivity)context).switchingFragments(IDashboard.FERTILIZER_DETAIL_FRAGMENT);
        }
    }

    public WeatherAdapter(ArrayList<WeatherSubModel> Data, Context context) {

//        this.stringTime=time;
        this.arrayListTemp=Data;
//        this.stringHumidity=humidity;

//        this.arrayCount=count;

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
//        holder.textViewHumidity.setText(arrayListTemp.get(position).getHumidity());
//        holder.textViewTempDescription.setText(arrayListTemp.get(position).getTempDescription());

        String Des=arrayListTemp.get(position).getTempDescription();
        holder.textViewTempDescription.setText(Des);

    }

    @Override
    public int getItemCount() {
        return arrayListTemp.size();
    }
}
