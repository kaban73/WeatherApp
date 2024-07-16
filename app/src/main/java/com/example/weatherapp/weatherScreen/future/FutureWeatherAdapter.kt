package com.example.weatherapp.weatherScreen.future

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemFutureWeatherBinding

class FutureWeatherAdapter : RecyclerView.Adapter<WeatherDayViewHolder>() {
    private val list = mutableListOf<FutureWeatherData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherDayViewHolder =
        WeatherDayViewHolder(ItemFutureWeatherBinding.inflate(LayoutInflater.from(parent.context)))

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
    fun clear() {
        list.clear()
        notifyDataSetChanged()
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
    private val b : ItemFutureWeatherBinding
) : RecyclerView.ViewHolder(b.root) {
    fun bind(futureWeatherData: FutureWeatherData) {
        val resId = getResourceIdForWeatherIcon(futureWeatherData.icon)
        if (resId != 0)
            b.futureWeatherImageView.setImageResource(resId)
        else
            b.futureWeatherImageView.setImageResource(R.drawable.ic_something_wrong)
        b.futureWeatherDateTextView.text = futureWeatherData.date
        b.futureWeatherRangeDegreesTextView.text = "${futureWeatherData.minDegrees}C/${futureWeatherData.maxDegrees}C"
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

}