# BFSLauncher
BFSLauncher 是采用BFS算法，进行启动任务管理的框架，你可以随意定制自己的任务启动顺序，无需再为启动任务管理发愁，
内部会采用基于BFS的拓扑算法，给出一个合理的启动顺序，同时也增加了环检测能力，如果启动任务循环依赖，会抛出exception并给出形成
环的任务信息




## 功能介绍
### 1. 创建一个TaskGraph对象
```
val graph = TaskGraph(this)
```
### 2.添加依赖，每个Task是一个实现了ITask接口的类 （你可以随意打乱任务的顺序，再也无需关注任务的依赖顺序）
```
val task1 = Task1()
val task2 = Task2()
val task3 = Task3()
val task4 = Task4()
val task4 = Task4()
task2 -> task3 
graph.addDependence(task2,task3)
task3 -> task4
graph.addDependence(task3,task4)
task1 -> task2
graph.addDependence(task1,task2)
```
addDependence接受两个参数，第一个是被依赖任务，第二个是普通任务

比如addDependence(A,B)则B任务依赖了A任务的完成 A->B 

### 3.启动
```
graph.startAll()
```





## 项目层级介绍
* **app下是使用例子**
* **lib_bfslancher 是bfslancher的核心实现**

## 环境准备
建议直接用最新的稳定版本Android Studio打开工程。目前项目已适配`Android Studio Arctic Fox | 2022.3.1`
### 
