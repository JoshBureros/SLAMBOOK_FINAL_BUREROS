package com.example.slambook_bureros

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slambook_bureros.databinding.ActivityCreateBinding

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private lateinit var userInfo: MutableList<UserInfo> // List to hold user data
    private lateinit var adapter: AdapterItem // Adapter for RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeData()
        setupRecyclerView()

        binding.createSlambookButton.setOnClickListener {
            navigateToFillUpActivity()
        }

        updateEmptyState()
    }

    private fun initializeData() {
        userInfo = Repository.get().toMutableList()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterItem(
            userInfo,
            onItemClick = { slambook ->
                // Navigate to the DisplayActivity and pass the selected Slambook
                val intent = Intent(this, DisplayActivity::class.java)
                intent.putExtra("UserInfo", slambook)
                startActivity(intent)
            },
            onDeleteClick = { slambook ->
                handleDeleteAction(slambook)
            }
        )
        binding.recyclerView.adapter = adapter
    }

    private fun handleDeleteAction(slambook: UserInfo) {
        Repository.delete(slambook)
        refreshData()
        updateEmptyState()
    }

    private fun refreshData() {
        userInfo.clear()
        userInfo.addAll(Repository.get())
        adapter.notifyDataSetChanged()
    }

    private fun navigateToFillUpActivity() {
        startActivity(Intent(this, FillUpActivity::class.java))
        finish()
    }

    private fun updateEmptyState() {
        binding.recyclerView.toggleVisibility(userInfo.isNotEmpty())
        binding.emptyStateContainer.toggleVisibility(userInfo.isEmpty())
    }

    private fun View.toggleVisibility(isVisible: Boolean) {
        this.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
