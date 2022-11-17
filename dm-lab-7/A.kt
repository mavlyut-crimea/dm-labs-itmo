import java.util.*

class Queue(private val n: Int) {
    private val a = IntArray(n) { it + 1 }
    private var head = 0

    fun firstToEnd() {
        head = (head + 1) % n
    }

    operator fun get(i: Int): Int = a[head + i - if (head + i < n) 0 else n]

    private fun set(i: Int, v: Int): Int {
        val ans = a[(head + i) % n]
        a[(head + i) % n] = v
        return ans
    }

    fun swap(f: Int, l: Int) {
        var i = 0
        while (f + i < l - i) {
            set(l - i, set(f + i, get(l - i)))
            i++
        }
    }

    fun print() {
        for (i in head until n)
            print("${a[i]} ")
        for (i in 0 until head)
            print("${a[i]} ")
        println()
    }
}

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val g = Array(n + 1) { BooleanArray(n + 1) }
    for (i in 2..n)
        sc.next().toCharArray().forEachIndexed { j, c ->
            g[j + 1][i] = (c == '1')
            g[i][j + 1] = (c == '1')
        }
    val q = Queue(n)
    var i: Int
    for (k in 0..n * (n - 1)) {
        if (!g[q[0]][q[1]]) {
            i = 2
            while (!g[q[0]][q[i]] || !g[q[1]][q[i + 1]])
                i++
            q.swap(1, i)
        }
        q.firstToEnd()
    }
    q.print()
}
