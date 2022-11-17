import java.util.*
import kotlin.math.max
import kotlin.math.min
 
data class Edge(private val a: Int, private val b: Int) {
    val first = min(a, b)
    val second = max(a, b)
 
    override fun equals(other: Any?): Boolean {
        if (other !is Edge)
            return false
        return (first == other.first) && (second == other.second)
    }
 
    override fun hashCode(): Int = 31 * first + second
}
 
class Graph(private val v: IntArray, private val e: Array<Edge>) {
    private val n = v.size
 
    private fun pullLastEdge(): Graph {
        val p = e.last()
        val v1 = v.filter { it != p.second }
        val e1 = mutableSetOf<Edge>()
        for (i in 0 until e.size - 1)
            e1.add(when {
                e[i].first == p.second -> Edge(p.first, e[i].second)
                e[i].second == p.second -> Edge(e[i].first, p.first)
                else -> e[i]
            })
        return Graph(v1.toIntArray(), e1.toTypedArray())
    }
 
    private fun removeLastEdge(): Graph = Graph(IntArray(n) { v[it] }, Array(e.size - 1) { e[it] })
 
    fun chromaticPolynomial(): IntArray {
        if (e.isEmpty())
            return IntArray(n + 1) { if (it == n) 1 else 0 }
        val ch1 = removeLastEdge().chromaticPolynomial()
        val ch2 = pullLastEdge().chromaticPolynomial()
        return IntArray(n + 1) { ch1[it] - if (it == n) 0 else ch2[it] }
    }
}
 
fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val m = sc.nextInt()
    val graph = Graph(IntArray(n) { it + 1 }, Array(m) { Edge(sc.nextInt(), sc.nextInt()) })
    println(n)
    println(graph.chromaticPolynomial().reversed().joinToString(" "))
}

