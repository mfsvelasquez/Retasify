package com.nepobabies.retas.ui.bear

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nepobabies.retas.R

/**
 * Adapter for displaying clothing items in a grid with drag-and-drop support.
 */
class ClothingAdapter(
    private var items: List<ClothingItem>,
    private val onItemClick: ((ClothingItem) -> Unit)? = null
) : RecyclerView.Adapter<ClothingAdapter.ClothingViewHolder>() {

    class ClothingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clothingImage: ImageView = itemView.findViewById(R.id.clothing_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clothing_choice, parent, false)
        return ClothingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClothingViewHolder, position: Int) {
        val item = items[position]
        
        holder.clothingImage.setImageResource(item.drawableResId)
        holder.clothingImage.contentDescription = item.name
        
        // Set alpha based on unlock status
        holder.clothingImage.alpha = if (item.isUnlocked) 1.0f else 0.5f
        
        // Click listener
        holder.itemView.setOnClickListener {
            if (item.isUnlocked) {
                onItemClick?.invoke(item)
            }
        }
        
        // Setup drag-and-drop for unlocked items
        if (item.isUnlocked) {
            holder.itemView.setOnLongClickListener { view ->
                // Create clip data with the item ID
                val clipData = ClipData.newPlainText(item.id, item.id)
                
                // Create drag shadow
                val dragShadowBuilder = View.DragShadowBuilder(view)
                
                // Start drag
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(clipData, dragShadowBuilder, item, 0)
                } else {
                    @Suppress("DEPRECATION")
                    view.startDrag(clipData, dragShadowBuilder, item, 0)
                }
                true
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<ClothingItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
