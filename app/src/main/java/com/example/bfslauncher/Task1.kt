package com.example.bfslauncher

import android.content.Context
import android.util.Log
import com.pika.lib_bfslauncher.ITask

class Task1:ITask {
    override fun handleTask(context: Context) {
        Log.e("hello","task1 run")

    }
}