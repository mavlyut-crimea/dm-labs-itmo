import java.io.BufferedReader
import java.io.InputStreamReader
 
fun main() {
    val sc = BufferedReader(InputStreamReader(System.`in`))
    val n = sc.readLine().toInt()
    print(IntArray(n) { it + 1 }.sortedWith { i, j ->
        print("1 $i $j\n")
        System.out.flush()
        if (sc.readLine() == "YES") -1 else 1
    }.joinToString(separator = " ", prefix = "0 ", postfix = "\n"))
    System.out.flush()
}

