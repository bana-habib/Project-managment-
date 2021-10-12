import kotlin.collections.HashSet
import java.util.*
import kotlin.collections.List
import kotlin.collections.toTypedArray

object CriticalPathKotlin {
    var maxDuration = 0

    class Task(taskName: String?, durationInt: Int, vararg dependent: Task) {
        //duration of task
        var durationInt = 0

        //duration of task along critical path
        var criticalDuration: Int = 0

        //name of task
        var taskName: String = ""

        //earliest start
        var earliestStart = 0

        //latest start
        var latestStart = 0

        //earliest finish
        var earliestFinish: Int

        //latest finish
        var latestFinish = 0
        var dependent = HashSet<Task>()
        fun calcLatest() {
            latestStart = maxDuration - criticalDuration
            latestFinish = latestStart + durationInt
        }

        //calculating table values
        fun calcResults(): Array<String?> {
            val criticalCond = if (earliestStart == latestStart) "Yes" else "No"
            return arrayOf(
                taskName,
                earliestStart.toString() + "",
                earliestFinish.toString() + "",
                latestStart.toString() + "",
                latestFinish.toString() + "",
                (latestStart - earliestStart).toString() + "",
                criticalCond
            )
        }

        //see if task is dependent
        fun isDependent(t: Task): Boolean {
            // is t a direct dependency?
            if (dependent.contains(t)) {
                return true
            }
            // is t an indirect dependency
            for (dep in dependent) {
                if (dep.isDependent(t)) {
                    return true
                }
            }
            return false
        }

        init {
            for (t in dependent) {
                this.dependent.add(t)
            }
            earliestFinish = -1
        }
    }

    fun main(args: Array<String>) {
        var taskList: List<String?> = ArrayList()
        taskList = readText("tasks")
        val tasksSize = taskList.size
        //creating HashSet
        val allTasks = HashSet<Task>()

        //getting Task Name & Duration from array
        val taskName: String = taskList[1]
        val durationStr: String = taskList[4]
        val durationInt = durationStr.toInt()

        //Adding the tasks to the HashSet
        for (x in 0 until tasksSize) {
            val element: String = taskList[x]
            val array = element.split(",".toRegex()).toTypedArray()
            //if contains "A" then make that start task
            if (array[7] == "A") {
                val first = Task(taskName, durationInt)
                allTasks.add(first)
                break
            //if contains "Z" then make that last task
            } else if (array[7] == "Z") {
                val last = Task(taskName, durationInt)
                allTasks.add(last)
                break
            //otherwise set as middle tasks     
            } else {
                val middle = Task(taskName, durationInt)
                allTasks.add(middle)
                break
            }
        }

        //getting result
        val result = criticalPath(allTasks)
        print(result)
    }

    fun criticalPath(tasks: HashSet<Task>): Array<Task?> {
        //tasks critical duration has been calculated
        val calculated = HashSet<Task>()

        //tasks critical duration needs to be calculated
        val remaining = HashSet(tasks)
        while (!remaining.isEmpty()) {
            //boolean progress = false;
            val it = remaining.iterator()
            while (it.hasNext()) {
                val task = it.next()
                if (calculated.containsAll(task.dependent)) {
                    // all dependencies calculated, critical cost is max
                    // dependency
                    // critical cost, plus our cost
                    var critical = 0
                    for (t in task.dependent) {
                        if (t.criticalDuration > critical) {
                            critical = t.criticalDuration
                        }
                    }
                    task.criticalDuration = critical + task.durationInt
                    // set task as calculated an remove
                    calculated.add(task)
                    it.remove()
                    // DELETE: note we are making progress?
                    //progress = true;
                }
            }
        }
        //getting duration
        maxDuration(tasks)

        //hashset to store initial start
        val initialNodes = initials(tasks)
        calcEarly(initialNodes)
        return arrayOfNulls(0)
    }

    //find earliest start and finish times
    fun calcEarly(initials: HashSet<Task>) {
        for (initial in initials) {
            initial.earliestStart = 0
            initial.earliestFinish = initial.durationInt
            setEarly(initial)
        }
    }

    fun setEarly(initial: Task) {
        val completionTime = initial.earliestFinish
        for (t in initial.dependent) {
            if (completionTime >= t.earliestStart) {
                t.earliestStart = completionTime
                t.earliestFinish = completionTime + t.durationInt
            }
            setEarly(t)
        }
    }

    //finding starting task
    fun initials(tasks: HashSet<Task>): HashSet<Task> {
        val remaining = HashSet(tasks)
        for (t in tasks) {
            for (c in t.dependent) {
                remaining.remove(c)
            }
        }
        //printing start task
        print("Initial nodes: ")
        for (t in remaining) print(t.taskName + " ")
        print("\n\n")
        return remaining
    }

    //calculating critical path longest time to complete project
    private fun maxDuration(tasks: HashSet<Task>) {
        var max = -1
        for (t in tasks) {
            if (t.criticalDuration > max) max = t.criticalDuration
        }
        maxDuration = max
        println("Critical path length (duration) : " + maxDuration)
        for (t in tasks) {
            t.calcLatest()
        }
    }

    //printing out results
    private fun print(tasks: Array<Task?>) {
        for (t in tasks) print(t!!.calcResults() as Array<Any?>)
    }


}
