package com.nguyen.codelabandroidlifecycles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainFragment : Fragment() {
    private lateinit var seekBar: SeekBar
    private lateinit var seekBarViewModel: SeekBarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        seekBar = root.findViewById(R.id.seekBar)
        seekBarViewModel = ViewModelProvider(requireActivity())[SeekBarViewModel::class.java]
        subscribeSeekBar()
        return root
    }

    private fun subscribeSeekBar() {
        // Update the ViewModel when the SeekBar is changed.
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(bar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    Log.d("Step5", "Progress changed!")
                    seekBarViewModel.seekbarValue.value = progress
                }
            }

            override fun onStartTrackingTouch(bar: SeekBar) {}
            override fun onStopTrackingTouch(bar: SeekBar) {}
        })

        // Update the SeekBar when the ViewModel is changed.
        seekBarViewModel.seekbarValue.observe(requireActivity()) { value ->
            value?.let {
                seekBar.progress = it
            }
        }
    }
}