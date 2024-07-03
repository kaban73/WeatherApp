package com.example.weatherapp.weather.futureWeather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemWeatherDayBinding

class FutureWeatherAdapter : RecyclerView.Adapter<WeatherDayViewHolder>() {
    private val list = mutableListOf<FutureWeatherData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDayViewHolder =
        WeatherDayViewHolder(ItemWeatherDayBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: WeatherDayViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun update(newList : List<FutureWeatherData>) {
        val diffUtilCallback = WeatherDayDiffUtilCallback(list, newList)
        val res = DiffUtil.calculateDiff(diffUtilCallback)
        list.clear()
        list.addAll(newList)
        res.dispatchUpdatesTo(this)
    }
}

class WeatherDayDiffUtilCallback(
    private val old : List<FutureWeatherData>,
    private val new : List<FutureWeatherData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

}
class WeatherDayViewHolder(
    private val b : ItemWeatherDayBinding
) : RecyclerView.ViewHolder(b.root) {
    fun bind(dayWeatherData: FutureWeatherData) {
        b.weatherDayImageView.setImageResource(R.mipmap.ic_launcher)
        b.weatherDayTextView.text = "${dayWeatherData.data}"
        b.degreesWeatherDayTextView.text = "${dayWeatherData.minDegrees}C/${dayWeatherData.maxDegrees}"
    }
}
