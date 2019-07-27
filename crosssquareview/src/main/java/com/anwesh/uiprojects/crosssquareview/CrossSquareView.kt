package com.anwesh.uiprojects.crosssquareview

/**
 * Created by anweshmishra on 27/07/19.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color
import android.content.Context
import android.app.Activity

val nodes : Int = 5
val lines : Int = 4
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val foreColor : Int = Color.parseColor("#01579B")
val backColor : Int = Color.parseColor("#BDBDBD")
val sqSizeFactor : Float = 2.8f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) * a.inverse() + k * b.inverse()
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawSquare(i : Int, sc : Float, size : Float, paint : Paint) {
    val sci : Float = sc.divideScale(i, lines)
    val sqSize : Float = size / sqSizeFactor
    val x : Float = size * sci
    save()
    rotate(90f * i)
    drawLine(0f, 0f, x, x, paint)
    save()
    translate(x, x)
    drawRect(RectF(-sqSize / 2, -sqSize / 2, sqSize, sqSize), paint)
    restore()
    restore()
}

fun Canvas.drawCSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(w / 2, gap * (i + 1))
    rotate(90f * sc2)
    for (j in 0..(lines - 1)) {
        drawSquare(j, sc1, size, paint)
    }
    restore()
}

class CrossSquareView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    
}