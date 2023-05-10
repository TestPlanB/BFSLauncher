package com.pika.lib_bfslauncher

import android.util.Log
import java.util.*

class TaskGraph {
    //邻接表
    val taskGraph = HashMap<ITask,ArrayList<ITask>>()

    fun addDependence(preTask:ITask,backITask: ITask){
        if (!taskGraph.contains(preTask)){
            taskGraph[preTask] = ArrayList()
        }
        taskGraph[preTask]?.add(backITask)
    }


    fun getCycleNodes(){
        val indegree = hashMapOf<ITask,Int>()
        val queue = LinkedList<ITask>()
        val visited = hashSetOf<ITask>()
        val cycleNodes = mutableListOf<ITask>()
        // 计算入度
        taskGraph.keys.forEach { it ->
            taskGraph[it]?.forEach {
                itask->
                indegree[itask] = indegree.getOrElse(itask){0} +1
            }
        }

        taskGraph.keys.forEach {
            if(indegree.getOrElse(it){0} == 0){
                queue.offer(it)
            }
        }
        var count = 0
        val result = mutableListOf<ITask>()

        while (queue.isNotEmpty()){
            val node = queue.poll()?:break
            visited.add(node)
            result.add(node)
            count++
            taskGraph[node]?.forEach {
                indegree[it] = indegree[it]?.minus(1)?:0
                if (indegree[it] == 0){
                    queue.offer(it)
                }else if (visited.contains(it)){
                    cycleNodes.add(it)
                }
            }
        }
        if (count != taskGraph.keys.size){
            Log.e("hello",cycleNodes.toString())
        }else{
            Log.e("hello",result.toString())
        }

    }
}