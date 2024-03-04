package com.nguyen.codelabandroidlifecycles

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var savedStateViewModel: SavedStateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtain the ViewModel
        savedStateViewModel = ViewModelProvider(this)[SavedStateViewModel::class.java]

        // Show the ViewModel property's value in a TextView
        savedStateViewModel.name.observe(this) { savedString ->
            findViewById<TextView>(R.id.saved_vm_tv).text = getString(R.string.saved_in_vm, savedString)
        }

        // Save button
        findViewById<View>(R.id.save_bt).setOnClickListener {
            val newName = findViewById<EditText>(R.id.name_et).text.toString()
            savedStateViewModel.saveNewName(newName)
        }
    }
}