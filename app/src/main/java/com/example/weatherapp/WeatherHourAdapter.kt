package com.example.weatherapp

//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.example.weatherapp.databinding.ItemWeatherHourBinding
//
//class WeatherHourAdapter : RecyclerView.Adapter<WeatherHourViewHolder>() {
//    private val list = mutableListOf<HourWeatherData>()
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHourViewHolder =
//        WeatherHourViewHolder(ItemWeatherHourBinding.inflate(LayoutInflater.from(parent.context)))
//
//    override fun onBindViewHolder(holder: WeatherHourViewHolder, position: Int) {
//        holder.bind(list[position])
//    }
//
//    override fun getItemCount(): Int =
//        list.size
//    fun update(newList : List<HourWeatherData>) {
//        val diffUtilCallback = WeatherHourDiffUtilCallback(list, newList)
//        val res = DiffUtil.calculateDiff(diffUtilCallback)
//        list.clear()
//        list.addAll(newList)
//        res.dispatchUpdatesTo(this)
//    }
//
//}
//class WeatherHourDiffUtilCallback(
//    private val old : List<HourWeatherData>,
//    private val new : List<HourWeatherData>
//) : DiffUtil.Callback() {
//    override fun getOldListSize(): Int {
//        return old.size
//    }
//
//    override fun getNewListSize(): Int {
//        return new.size
//    }
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return old[oldItemPosition] == new[newItemPosition]
//    }
//
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return old[oldItemPosition] == new[newItemPosition]
//    }
//
//}
//class WeatherHourViewHolder(
//    private val b : ItemWeatherHourBinding
//) : RecyclerView.ViewHolder(b.root) {
//    fun bind(hourWeatherData : HourWeatherData) {
//        b.weatherHourImageView.setImageResource(R.mipmap.ic_launcher)
//        b.hourWeatherHourTextView.text = hourWeatherData.hour.toString()
//        b.degreesWeatherHourTextView.text = "${hourWeatherData.degrees}C"
//    }
//}