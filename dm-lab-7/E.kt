import java.util.Scanner
import java.util.Vector
import java.util.PriorityQueue

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val g = Array(n) { Vector<Int>() }
    for (i in 1 until n) {
        val a = sc.nextInt() - 1
        val b = sc.nextInt() - 1
        g[a].addElement(b)
        g[b].addElement(a)
    }
    val deg = IntArray(n) { g[it].size }
    val used = BooleanArray(n) { false }
    val leaves = PriorityQueue<Int>()
    for (i in 0 until n)
        if (deg[i] == 1)
            leaves.add(i)
    println(IntArray(n - 2) {
        val leaf = leaves.remove()
        used[leaf] = true
        var v = 0
        for (i in g[leaf])
            if (!used[i]) {
                v = i
                break
            }
        if (--deg[v] == 1)
            leaves.add(v)
        v + 1
    }.joinToString(" "))
}
