import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val sc = BufferedReader(InputStreamReader(System.`in`))
    val n = sc.readLine().toInt()
    val q = Array(n) { Vector<Int>() } // edges "in"
    for (i in 0 until n)
        sc.readLine().toCharArray().forEachIndexed { j, c -> if (c == '1') q[i] += j else q[j] += i }
    // Search of Hamiltonian path
    val path = mutableListOf(0)
    val used = BooleanArray(n) { it == 0 }
    for (k in 1 until n) {
        var tmp = 0
        while (used[tmp])
            tmp++
        used[tmp] = true
        var i = 0
        while (i < path.size && tmp in q[path[i]])
            i++
        path.add(i, tmp)
    }
    // Rebuild of Hamiltonian path to cycle
    var k = 2
    for (t in 2 until n)
        if (path[0] in q[path[t]])
            k = t
    val cycle = MutableList(k + 1) { path.removeFirst() }
    l@while (cycle.size < n)
        for (i in path.indices)
            for (j in cycle.indices)
                if (cycle[j] in q[path[i]]) {
                    for (t in 0..i)
                        cycle.add(t + j, path.removeFirst())
                    continue@l
                }
    println(cycle.joinToString(" ") { (it + 1).toString() })
}
