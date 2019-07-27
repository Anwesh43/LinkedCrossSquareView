package com.anwesh.uiprojects.linkedcrosssquareview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.crosssquareview.CrossSquareView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CrossSquareView.create(this)
    }
}
