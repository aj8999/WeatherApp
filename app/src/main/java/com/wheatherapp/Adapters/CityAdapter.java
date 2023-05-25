package com.wheatherapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.wheatherapp.FragmentCity;
import com.wheatherapp.HomeScreenActivity;
import com.wheatherapp.R;
import com.wheatherapp.WeataherActivity;
import com.wheatherapp.helper.AddreesData;
import com.wheatherapp.helper.AddressDb;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {
    public ArrayList<AddreesData> arrayList = new ArrayList<>();
    public Context context;
    AddressDb db;

    public CityAdapter(ArrayList<AddreesData> Data, Context context) {
        this.arrayList = Data;
        this.context = context;
    }

    @Override
    public CityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_city, parent, false);
        CityAdapter.MyViewHolder holder = new CityAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CityAdapter.MyViewHolder holder, int position) {
        AddreesData model = arrayList.get(position);
        holder.tvCity.setText(model.Location);
        db = new AddressDb(context);
holder.ivDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(context.getString(R.string.AlertMessage))
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Boolean insert=db.delete(model.getId());
                        if (insert==true){
                            Toast.makeText(context,"Location Removed Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, HomeScreenActivity.class);
                            context.startActivity(intent);
                            ((HomeScreenActivity)context).finish();
                        }
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }
});
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WeataherActivity.class);
                intent.putExtra("LOCATION",model.getLocation());
                intent.putExtra("LAT",model.getLat());
                intent.putExtra("LONG",model.getLong());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        ImageView ivDelete;

        public MyViewHolder(View v) {
            super(v);
            tvCity = v.findViewById(R.id.tvCity);
            ivDelete = v.findViewById(R.id.ivDelete);
        }

    }
}
