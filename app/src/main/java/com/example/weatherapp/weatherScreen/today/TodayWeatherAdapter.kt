package com.example.weatherapp.weatherScreen.today

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemTodayWeatherBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TodayWeatherAdapter : RecyclerView.Adapter<WeatherHourViewHolder>() {
    private val list = mutableListOf<TodayWeatherData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHourViewHolder =
        WeatherHourViewHolder(ItemTodayWeatherBinding.inflate(LayoutInflater.from(parent.context)))

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
    private val b : ItemTodayWeatherBinding
) : RecyclerView.ViewHolder(b.root) {
    fun bind(todayWeatherData : TodayWeatherData) {
        val resId = getResourceIdForWeatherIcon(todayWeatherData.icon)
        if (resId != 0)
            b.todayWeatherIamgeView.setImageResource(resId)
        else
            b.todayWeatherIamgeView.setImageResource(R.drawable.ic_something_wrong)
        b.todayWeatherTimeTextView.text = getTime(todayWeatherData.date)
        b.todayWeatherDegreesTextView.text = todayWeatherData.degrees.toString() + 'C'
    }
    private fun getResourceIdForWeatherIcon(iconName: String): Int {
        return try {
            val field = R.mipmap::class.java.getField("ic_${iconName}")
            field.getInt(null)
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }
    private fun getTime(unixTime : Long) : String {
        val date = Date(unixTime * 1000L)
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formattedDate = sdf.format(date)
        return formattedDate
    }
}