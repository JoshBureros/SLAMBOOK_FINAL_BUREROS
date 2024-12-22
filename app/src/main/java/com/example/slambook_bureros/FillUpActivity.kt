package com.example.slambook_bureros

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.slambook_bureros.databinding.ActivityFillBinding
import com.google.android.material.textfield.TextInputLayout

class FillUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFillBinding
    private lateinit var progressDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityFillBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupProgressDialog()
        setupSubmitButton()
    }

    private fun setupProgressDialog() {
        val progressDialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        progressDialog = AlertDialog.Builder(this)
            .setTitle("Saving Your SlamBook")
            .setMessage("Please wait while we save your details...")
            .setView(progressDialogView)
            .setCancelable(false)
            .create()
    }

    private fun setupSubmitButton() {
        binding.submitButton.setOnClickListener {
            if (validateInputs()) {
                progressDialog.show()

                val newSlamBook = createSlamBookFromInput()

                Handler().postDelayed({
                    Repository.add(newSlamBook)

                    progressDialog.dismiss()
                    navigateToFinishActivity()
                }, 2000)
            }
        }
    }


    private fun createSlamBookFromInput(): UserInfo {
        return UserInfo(
            firstName = binding.editFirstName.trimString(),
            lastName = binding.editLastName.trimString(),
            nickname = binding.editNickname.trimString(),
            hobby = binding.editHobby.trimString(),
            favFood = binding.editFavFood.trimString(),
            favMovie = binding.editFavMovie.trimString(),
            favMusic = binding.editFavMusic.trimString()
        )
    }


    private fun validateInputs(): Boolean {
        val inputs = listOf(
            binding.editFirstName to binding.inputFirstName,
            binding.editLastName to binding.inputLastName,
            binding.editNickname to binding.inputNickname,
            binding.editHobby to binding.inputHobby,
            binding.editFavFood to binding.inputFavFood,
            binding.editFavMovie to binding.inputFavMovie,
            binding.editFavMusic to binding.inputFavMusic
        )

        var isValid = true
        for ((editText, inputLayout) in inputs) {
            if (!editText.validateInput(inputLayout)) {
                isValid = false
            }
        }
        return isValid
    }


    private fun navigateToFinishActivity() {
        startActivity(Intent(this, FinishActivity::class.java))
        finish()
    }


    private fun EditText.trimString(): String {
        return this.text.toString().trim()
    }


    private fun EditText.validateInput(inputLayout: TextInputLayout, errorMessage: String = "This field is required"): Boolean {
        val input = this.text.toString().trim()
        return if (input.isEmpty()) {
            inputLayout.error = errorMessage
            false
        } else {
            inputLayout.error = null
            true
        }
    }
}
