package com.plantia.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.plantia.Plant
import com.plantia.R


class PlantItemAdapter(private val dataSet: MutableList<Plant>) :
    RecyclerView.Adapter<PlantItemAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantNameTextView: TextView
        val plantDescriptionTextView: TextView

        init {
            // Define click listener for the ViewHolder's View
            plantNameTextView = view.findViewById(R.id.plant_name)
            plantDescriptionTextView = view.findViewById(R.id.plant_description)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.plant_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.plantNameTextView.text = dataSet[position].nombrePlanta
        viewHolder.plantDescriptionTextView.text = dataSet[position].Descripcion
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}