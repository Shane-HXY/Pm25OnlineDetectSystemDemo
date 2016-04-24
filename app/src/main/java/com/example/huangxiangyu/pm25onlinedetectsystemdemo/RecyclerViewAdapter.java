package com.example.huangxiangyu.pm25onlinedetectsystemdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.huangxiangyu.pm25onlinedetectsystemdemo.activity.WeatherItemActivity;
import com.example.huangxiangyu.pm25onlinedetectsystemdemo.model.WeatherData;

import java.util.List;

/**
 * Created by huangxiangyu on 16/4/24.
 * In Pm25OnlineDetectSystemDemo
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.WeatherDataViewHolder> {
    private List<WeatherData> weatherDatas;
    private Context context;

    public RecyclerViewAdapter(List<WeatherData> weatherDatas, Context context) {
        this.weatherDatas = weatherDatas;
        this.context = context;
    }

    @Override
    public WeatherDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        WeatherDataViewHolder viewHolder = new WeatherDataViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherDataViewHolder holder, final int position) {
        final int j = position;
        holder.cityName.setText(weatherDatas.get(position).getCityName());
        holder.code.setImageResource(weatherDatas.get(position).getCode());
        holder.aqi.setText(weatherDatas.get(position).getAqi() + "");
        holder.qlty.setText(weatherDatas.get(position).getQlty());
        holder.update.setText(weatherDatas.get(position).getUpdate());
        holder.pm2_5.setText(weatherDatas.get(position).getPm25() + "");
        holder.pm10.setText(weatherDatas.get(position).getPm10() + "");
        holder.o3.setText(weatherDatas.get(position).getO3() + "");
        holder.no2.setText(weatherDatas.get(position).getNo2() + "");
        holder.so2.setText(weatherDatas.get(position).getSo2() + "");
        holder.co.setText(weatherDatas.get(position).getCo() + "");
        holder.tmp.setText(weatherDatas.get(position).getTmp() + "");
        holder.hum.setText(weatherDatas.get(position).getHum() + "");

        //为CardView添加点击事件
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WeatherItemActivity.class);
                intent.putExtra("WeatherDatas", weatherDatas.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherDatas.size();
    }

    static class WeatherDataViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView cityName;
        ImageView code;
        TextView aqi;
        TextView qlty;
        TextView update;
        TextView pm2_5;
        TextView pm10;
        TextView o3;
        TextView no2;
        TextView so2;
        TextView co;
        TextView tmp;
        TextView hum;

        public WeatherDataViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            cityName = (TextView) itemView.findViewById(R.id.city_name);
            code = (ImageView) itemView.findViewById(R.id.code);
            aqi = (TextView) itemView.findViewById(R.id.aqi);
            qlty = (TextView) itemView.findViewById(R.id.qlty);
            update = (TextView) itemView.findViewById(R.id.update);
            pm2_5 = (TextView) itemView.findViewById(R.id.pm25);
            pm10 = (TextView) itemView.findViewById(R.id.pm10);
            o3 = (TextView) itemView.findViewById(R.id.o3);
            no2 = (TextView) itemView.findViewById(R.id.no2);
            so2 = (TextView) itemView.findViewById(R.id.so2);
            co = (TextView) itemView.findViewById(R.id.co);
            tmp = (TextView) itemView.findViewById(R.id.tmp);
            hum = (TextView) itemView.findViewById(R.id.hum);
        }
    }


}
