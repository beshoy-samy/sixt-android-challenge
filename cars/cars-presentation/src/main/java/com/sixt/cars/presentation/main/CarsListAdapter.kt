package com.sixt.cars.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sixt.cars.domain.entities.Car
import com.sixt.cars.presentation.R
import com.sixt.cars.presentation.databinding.ItemListCarBinding
import com.sixt.core.utils.loadImage
import com.sixt.core.utils.updateBackgroundColor
import com.sixt.core.utils.updateTextColor
import javax.inject.Inject

class CarsListAdapter @Inject constructor() :
    ListAdapter<Car, CarsListAdapter.ViewHolder>(CarsListDiffUtilsCallback()) {

    var onCarClickedCallback: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), onCarClickedCallback)

    class ViewHolder private constructor(private val binding: ItemListCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            car: Car,
            onCarClickedCallback: ((position: Int) -> Unit)?
        ) {
            binding.carIv.loadImage(car.imageUrl, placeholder = R.drawable.ic_car_placeholder)
            binding.carNameTv.text =
                itemView.context.getString(R.string.car_maker_with_name, car.maker, car.name)
            binding.carColorTv.text = car.color
            binding.carGroupTv.text = car.group
            binding.carTransmissionFuelTv.text =
                itemView.context.getString(
                    R.string.car_transmission_fuel_type,
                    car.transmission,
                    car.fuelType
                )
            binding.root.setOnClickListener { onCarClickedCallback?.invoke(layoutPosition) }
            handleSelection(car.isSelected)
        }

        private fun handleSelection(selected: Boolean) {
            val textColor = if (selected) R.color.white else R.color.primary_text
            val backgroundColor = if (selected) R.color.colorPrimary else R.color.white
            binding.root.updateBackgroundColor(backgroundColor)
            binding.carNameTv.updateTextColor(textColor)
            binding.carColorTv.updateTextColor(textColor)
            binding.carGroupTv.updateTextColor(textColor)
            binding.carTransmissionFuelTv.updateTextColor(textColor)
        }

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListCarBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CarsListDiffUtilsCallback : DiffUtil.ItemCallback<Car>() {

    override fun areItemsTheSame(old: Car, aNew: Car): Boolean = old.id == aNew.id

    override fun areContentsTheSame(old: Car, aNew: Car): Boolean = old == aNew

}