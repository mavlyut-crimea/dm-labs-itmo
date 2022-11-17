import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet
 
class Graph(private val n: Int) {
    private val g = Array(n) { Vector<Int>() }
 
    fun pushEdge(u: Int, v: Int) {
        g[u - 1].add(v - 1)
        g[v - 1].add(u - 1)
    }
 
    fun maxDeg() = g.maxOf { it.size }
 
    fun paint(): IntArray {
        val tr = IntArray(n)
        val d = IntArray(n) { Int.MAX_VALUE }
        val mD = maxDeg()
        val wCol = (0 until n).filter { g[it].size != mD }
        if (wCol.isEmpty()) {
            if (mD == n - 1)
                return IntArray(n) { it }
            return IntArray(n) { if (it != 0) 2 else it % 2 }
        }
        d[wCol.first()] = 0
        bfs(wCol.first(), 0, d, tr)
        val colour = IntArray(n) { -1 }
        var cUsed = 0
        var cTmp: Int
        val set = HashSet<Int>()
        for (i in n - 1 downTo 0) {
            set.clear()
            for (j in g[tr[i]])
                set.add(colour[j])
            cTmp = -1
            for (c in 0 until cUsed)
                if (c !in set) {
                    cTmp = c
                    break
                }
            if (cTmp == -1)
                cTmp = cUsed++
            colour[tr[i]] = cTmp
        }
        return colour
    }
 
    private fun bfs(u: Int, k: Int, d: IntArray, ans: IntArray): Int {
        if (k == n)
            return n
        ans[k] = u
        var t = k
        for (r in g[u])
            if (d[r] == Int.MAX_VALUE) {
                d[r] = d[u] + 1
                t = bfs(r, ++t, d, ans)
            }
        return t
    }
}
 
fun main() {
    val sc = BufferedReader(InputStreamReader(System.`in`))
    var s = sc.readLine().split(" ")
    val n = s[0].toInt()
    val m = s[1].toInt()
    val gr = Graph(n)
    (1..m).forEach { _ ->
        s = sc.readLine().split(" ")
        gr.pushEdge(s[0].toInt(), s[1].toInt())
    }
    println(gr.maxDeg() / 2 * 2 + 1)
    gr.paint().forEach { println(it + 1) }
}

