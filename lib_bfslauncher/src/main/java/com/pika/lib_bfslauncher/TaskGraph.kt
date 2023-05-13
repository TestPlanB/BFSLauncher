package com.pika.lib_bfslauncher

import android.content.Context
import java.util.*

class TaskGraph(val context: Context) {
    //邻接表
    private val taskGraph = HashMap<ITask, ArrayList<ITask>>()
    private val allTaskSet = hashSetOf<ITask>()

    fun addDependence(preTask: ITask, backITask: ITask) {
        if (!taskGraph.contains(preTask)) {
            taskGraph[preTask] = ArrayList()
        }
        taskGraph[preTask]?.add(backITask)
        allTaskSet.add(preTask)
        allTaskSet.add(backITask)
    }

    fun addDependence(preTask: ITask, backITask: List<ITask>) {
        if (!taskGraph.contains(preTask)) {
            taskGraph[preTask] = ArrayList()
        }
        taskGraph[preTask]?.addAll(backITask)
        allTaskSet.add(preTask)
        allTaskSet.addAll(backITask)
    }

    fun startAll() {
        val list = detectCycleNodes()
        list.forEach {
            it.handleTask(context)
        }
    }


    fun detectCycleNodes(): List<ITask> {
        val indegree = hashMapOf<ITask, Int>()
        val queue = LinkedList<ITask>()
        val cycleNodes = mutableListOf<ITask>()
        // 计算入度
        taskGraph.keys.forEach { it ->
            taskGraph[it]?.forEach { itask ->

                indegree[itask] = indegree.getOrElse(itask) { 0 } + 1
            }
        }

        taskGraph.keys.forEach {
            if (indegree.getOrElse(it) { 0 } == 0) {
                queue.offer(it)
            }
        }
        var count = 0
        val result = mutableListOf<ITask>()


        while (queue.isNotEmpty()) {
            val node = queue.poll() ?: break
            result.add(node)
            count++
            taskGraph[node]?.forEach {
                indegree[it] = indegree.getOrElse(it) { 1 } - 1
                if (indegree[it] == 0) {
                    queue.offer(it)
                }
            }
        }
        if (count != allTaskSet.size) {
            for (entry in indegree) {
                if (entry.value > 0) {
                    cycleNodes.add(entry.key)
                }
            }
            throw java.lang.RuntimeException("find cycle dependents $cycleNodes")

        }
        return result

    }
}