package com.jetwiz.admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jetwiz.admin.R
import com.jetwiz.admin.model.MealsItem
import com.jetwiz.admin.model.ModelMeals
import com.jetwiz.admin.utils.U_Coroutine
import kotlinx.coroutines.*

class AdapterMeals(private val dataSet: ModelMeals, var onClick: (mealsItem: MealsItem) -> Unit):RecyclerView.Adapter<AdapterMeals.ViewHolder>() {

    val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + U_Coroutine.getErrorHandler()
    )

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName:TextView
        val containerItem:ConstraintLayout
        init {
            tvName = view.findViewById(R.id.tvName)
            containerItem = view.findViewById(R.id.containerItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = dataSet.meals!![position]!!.strMeal
        holder.containerItem.setOnClickListener {
            onClick(dataSet.meals[position]!!)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.meals!!.size
    }
}