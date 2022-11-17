import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.Vector
 
fun main() {
    val sc = BufferedReader(InputStreamReader(System.`in`))
    val n = sc.readLine().toInt()
    val p = sc.readLine().split(" ").map { it.toInt() - 1 }
    val res = Vector<Pair<Int, Int>>()
    val deg = IntArray(n) { 1 }
    p.forEach { deg[it]++ }
    val leaves = PriorityQueue<Int>()
    for (i in 0 until n)
        if (deg[i] == 1)
            leaves.add(i)
    for (i in p) {
        val leaf = leaves.remove()
        res.add(Pair(leaf, i))
        if (--deg[i] == 1)
            leaves.add(i)
    }
    res.add(Pair(leaves.remove(), leaves.remove()))
    res.forEach { print("${it.first + 1} ${it.second + 1}\n") }
    println()
}

