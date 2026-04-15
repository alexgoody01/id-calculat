package com.example.idcalc

import android.os.Bundle
import android.widget.*
import android.app.Activity

class MainActivity : Activity() {

    private val points = mapOf(
        316 to 1,
        321 to 6,
        326 to 11,
        341 to 26,
        350 to 35,
        365 to 50,
        370 to 55,
        381 to 66,
        392 to 77
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(50,50,50,50)

        val input = EditText(this)
        val btn = Button(this)
        val result = TextView(this)

        btn.text = "Calculate"

        btn.setOnClickListener {
            val x = input.text.toString().toIntOrNull()
            if (x != null) {
                result.text = (points[x] ?: calc(x)).toString()
            }
        }

        layout.addView(input)
        layout.addView(btn)
        layout.addView(result)

        setContentView(layout)
    }

    private fun calc(x: Int): Int {
        val keys = points.keys.sorted()

        for (i in 0 until keys.size - 1) {
            val x1 = keys[i]
            val x2 = keys[i+1]

            if (x in x1..x2) {
                val y1 = points[x1]!!
                val y2 = points[x2]!!
                return y1 + (x - x1) * (y2 - y1) / (x2 - x1)
            }
        }
        return 0
    }
}
