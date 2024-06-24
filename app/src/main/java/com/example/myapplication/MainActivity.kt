package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var seekBar: SeekBar
    private lateinit var seekBarValue: TextView
    private lateinit var chipGroup: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        spinner = findViewById(R.id.spn)
        seekBar = findViewById(R.id.seekBar)
        seekBarValue = findViewById(R.id.seekBarValue)
        chipGroup = findViewById(R.id.chg)

        // Set a listener for chip selection events
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = findViewById<Chip>(checkedId)
            if (chip != null) {
                if (chip.isChecked) {
                    chip.setChipBackgroundColorResource(R.color.selected_chip_background)
                } else {
                    chip.setChipBackgroundColorResource(R.color.default_chip_background)
                }
            }
        }

        // Alternatively, if you want to handle click events on each chip individually
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as Chip
            chip.setOnClickListener {
                if (chip.isChecked) {
                    chip.setChipBackgroundColorResource(R.color.selected_chip_background)
                } else {
                    chip.setChipBackgroundColorResource(R.color.default_chip_background)
                }
            }

        }


        // Set up Spinner
        val availabilityOptions = arrayOf("Option 1", "Option 2", "Option 3") // Replace with your options
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, availabilityOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter


        // Set up SeekBar
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update seekBarValue TextView
                seekBarValue.text =  progress.toString()
                updateSeekBarValuePosition()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Handle touch start if needed
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Handle touch stop if needed
            }
        })

        // Set initial value for seekBarValue
        seekBarValue.text = seekBar.progress.toString()
        updateSeekBarValuePosition()


    }

    private fun updateSeekBarValuePosition() {
        // Move seekBarValue TextView along with the SeekBar thumb
        val thumb = seekBar.thumb
        val thumbPos = thumb.bounds.centerX() + seekBar.x
        seekBarValue.x = thumbPos - seekBarValue.width / 2
        if(seekBarValue.text == "0"){
            seekBarValue.isVisible = false}
        else{
        seekBarValue.isVisible = true }// Ensure it's visible when dragged
    }
}
