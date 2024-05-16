package com.example.classdemo3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<WeatherInfo> weatherList;

    public WeatherAdapter(List<WeatherInfo> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherInfo weatherInfo = weatherList.get(position);

        holder.textObsTime.setText("Obs Time: " + weatherInfo.getObsTime());
        holder.textTemp.setText("Temp: " + weatherInfo.getTemp());
        holder.textFeelsLike.setText("Feels Like: " + weatherInfo.getFeelsLike());
        holder.textIcon.setText("Icon: " + weatherInfo.getIcon());
        holder.textText.setText("Text: " + weatherInfo.getText());
        holder.textWind360.setText("Wind360: " + weatherInfo.getWind360());
        holder.textWindDir.setText("WindDir: " + weatherInfo.getWindDir());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textObsTime, textTemp, textFeelsLike, textIcon, textText, textWind360, textWindDir;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textObsTime = itemView.findViewById(R.id.textObsTime);
            textTemp = itemView.findViewById(R.id.textTemp);
            textFeelsLike = itemView.findViewById(R.id.textFeelsLike);
            textIcon = itemView.findViewById(R.id.textIcon);
            textText = itemView.findViewById(R.id.textText);
            textWind360 = itemView.findViewById(R.id.textWind360);
            textWindDir = itemView.findViewById(R.id.textWindDir);
        }
    }
}
