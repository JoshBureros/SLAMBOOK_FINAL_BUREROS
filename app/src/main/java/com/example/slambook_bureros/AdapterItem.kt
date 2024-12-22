package com.example.slambook_bureros

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.slambook_bureros.databinding.ActivityItemBinding

class AdapterItem(
    private val userInfo: MutableList<UserInfo>,
    private val onItemClick: (UserInfo) -> Unit,
    private val onDeleteClick: (UserInfo) -> Unit
) : RecyclerView.Adapter<AdapterItem.SlambookViewHolder>() {

    // ViewHolder to hold the views for each item
    inner class SlambookViewHolder(private val binding: ActivityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(slambook: UserInfo) {
            // Set the full name
            binding.slambookNameTextView.text = "${slambook.firstName} ${slambook.lastName}"

            // Set up the item click listener
            itemView.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Clicked: ${slambook.firstName} ${slambook.lastName}",
                    Toast.LENGTH_SHORT
                ).show()
                onItemClick(slambook)
            }

            // Set up the delete button listener
            binding.removeButton.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Deleted: ${slambook.firstName} ${slambook.lastName}",
                    Toast.LENGTH_SHORT
                ).show()
                onDeleteClick(slambook)
            }
        }
    }

    // Create the ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlambookViewHolder {
        val binding = ActivityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlambookViewHolder(binding)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: SlambookViewHolder, position: Int) {
        holder.bind(userInfo[position])
    }

    // Return the size of the list
    override fun getItemCount(): Int = userInfo.size

    // Function to remove a slambook from the list and notify the adapter
    fun remove(slambook: UserInfo) {
        val position = userInfo.indexOf(slambook)
        if (position != -1) {
            userInfo.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, userInfo.size)
        }
    }
}
