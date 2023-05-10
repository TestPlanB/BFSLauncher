package com.example.bfslauncher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import com.pika.lib_bfslauncher.TaskGraph

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Debug.waitForDebugger()
        val graph = TaskGraph()
        val task1 = Task1()
        val task2 = Task2()
        val task3 = Task3()
        val task4 = Task4()
        graph.addDependence(task1,task2)
        graph.addDependence(task2,task3)
        graph.addDependence(task3,task4)
        graph.addDependence(task2,task4)
        graph.getCycleNodes()

    }
}