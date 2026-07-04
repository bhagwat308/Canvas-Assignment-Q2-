package com.example.graphicsassignment

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load the XML layout
        setContentView(R.layout.activity_main)

        // Create the custom Canvas view
        val canvasView = CanvasGameView(this)

        // Add it inside the FrameLayout
        val frameLayout = findViewById<FrameLayout>(R.id.frameLayout)

        frameLayout.addView(canvasView)
    }
}
