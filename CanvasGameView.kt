package com.example.graphicsassignment

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import kotlin.math.sqrt

class CanvasGameView(context: Context) : View(context) {

    
    private val circlePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val rectanglePaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val linePaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 8f
        isAntiAlias = true
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 60f
        isFakeBoldText = true
        isAntiAlias = true
    }

    
    private var circleX = 250f
    private var circleY = 250f
    private val radius = 80f

    // Animation Speed
    private var dx = 8f
    private var dy = 8f

    // Rectangle
    private var rectangleColor = Color.BLUE

    // Score
    private var score = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.WHITE)

        // Rectangle
        rectanglePaint.color = rectangleColor
        canvas.drawRect(
            100f,
            100f,
            400f,
            250f,
            rectanglePaint
        )

        // Circle
        canvas.drawCircle(
            circleX,
            circleY,
            radius,
            circlePaint
        )

        // Line
        canvas.drawLine(
            width / 2f,
            height / 2f,
            circleX,
            circleY,
            linePaint
        )

        // Text
        val title = "Interactive Canvas Dashboard"

        textPaint.textSize = width * 0.05f

        val textWidth = textPaint.measureText(title)

        canvas.drawText(
            title,
            (width - textWidth) / 2,
            80f,
            textPaint
        )

        canvas.drawText(
            "Score : $score",
            60f,
            height - 100f,
            textPaint
        )

        // Animate Circle
        circleX += dx
        circleY += dy

        if (circleX > width - radius || circleX < radius) {
            dx = -dx
        }

        if (circleY > height - radius || circleY < radius) {
            dy = -dy
        }

        // Redraw
        postInvalidateOnAnimation()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN) {

            val distance = sqrt(
                ((event.x - circleX) * (event.x - circleX) +
                        (event.y - circleY) * (event.y - circleY)).toDouble()
            )

            if (distance <= radius) {

                score++

                circlePaint.color = randomColor()

            } else {

                rectangleColor = randomColor()

            }

            invalidate()
        }

        return true
    }

    private fun randomColor(): Int {

        val colors = arrayOf(
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.YELLOW,
            Color.MAGENTA,
            Color.BLACK,
            Color.GRAY
        )

        return colors.random()
    }
}
