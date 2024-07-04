package com.example.weatherapp.city.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.city.core.CityData
import com.example.weatherapp.databinding.ItemCityLayoutBinding

class CityAdapter(
    private val chooseCity: ChooseCity
) : RecyclerView.Adapter<CityViewHolder>() {
    private val list = mutableListOf<CityData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(ItemCityLayoutBinding.inflate(LayoutInflater.from(parent.context)), chooseCity)

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int =
        list.size

    fun update(newList : List<CityData>) {
        val diffUtilCallback = CityDiffUtilCallback(list, newList)
        val res = DiffUtil.calculateDiff(diffUtilCallback)
        list.clear()
        list.addAll(newList)
        res.dispatchUpdatesTo(this)
    }
}
class CityDiffUtilCallback(
    private val old : List<CityData>,
    private val new : List<CityData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].areItemsTheSame(new[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].areContentsTheSame(new[newItemPosition])
    }

}
class CityViewHolder(
    private val b : ItemCityLayoutBinding,
    private val chooseCity: ChooseCity
) : RecyclerView.ViewHolder(b.root) {
    fun bind(cityData: CityData) {
        cityData.show(b.cityTextView)
        itemView.setOnClickListener {
            cityData.choose(chooseCity)
        }
    }
}