package com.jetwiz.admin.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jetwiz.admin.R
import com.jetwiz.admin.model.CategoriesItem
import com.jetwiz.admin.model.ModelCategories

class AdapterCategories(private val dataSet: ModelCategories, var onClick: (categoriesItem: CategoriesItem) -> Unit):RecyclerView.Adapter<AdapterCategories.ViewHolder>() {

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
        holder.tvName.text = dataSet.categories!![position]!!.strCategory
        holder.containerItem.setOnClickListener {
            onClick(dataSet.categories[position]!!)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.categories!!.size
    }
}