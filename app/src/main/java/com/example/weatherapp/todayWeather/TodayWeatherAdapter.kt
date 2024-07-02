package com.example.weatherapp.todayWeather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemWeatherHourBinding

class TodayWeatherAdapter : RecyclerView.Adapter<WeatherHourViewHolder>() {
    private val list = mutableListOf<TodayWeatherData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHourViewHolder =
        WeatherHourViewHolder(ItemWeatherHourBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: WeatherHourViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =
        list.size
    fun update(newList : List<TodayWeatherData>) {
        val diffUtilCallback = WeatherHourDiffUtilCallback(list, newList)
        val res = DiffUtil.calculateDiff(diffUtilCallback)
        list.clear()
        list.addAll(newList)
        res.dispatchUpdatesTo(this)
    }

}
class WeatherHourDiffUtilCallback(
    private val old : List<TodayWeatherData>,
    private val new : List<TodayWeatherData>
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
class WeatherHourViewHolder(
    private val b : ItemWeatherHourBinding
) : RecyclerView.ViewHolder(b.root) {
    fun bind(hourWeatherData : TodayWeatherData) {
        b.weatherHourImageView.setImageResource(R.mipmap.ic_launcher)
        b.hourWeatherHourTextView.text = hourWeatherData.time
        b.degreesWeatherHourTextView.text = "${hourWeatherData.degrees}C"
    }
}