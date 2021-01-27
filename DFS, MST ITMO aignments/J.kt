import java.util.Scanner

data class Edge(
        val start: Int,
        val end: Int,
        var cost: Long,
        val num: Int,
        var mark: Boolean = false
)

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val m = scanner.nextInt()

    val edges =
            Array(m) { i ->
                        Edge(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextLong(), i)
                    }
                    .sortedWith(
                            Comparator { t, t2 ->
                                when {
                                    t.cost < t2.cost -> -1
                                    t.cost > t2.cost -> 1
                                    else -> 0
                                }
                            })

    val unionFind = Array(n) { i -> i }

    val get: (v: Int) -> Int = { v ->
        var vVar = v
        val descendants = mutableListOf<Int>()
        while (unionFind[vVar] != vVar) {
            descendants.add(vVar)
            vVar = unionFind[vVar]
        }
        descendants.forEach { unionFind[it] = vVar }
        vVar
    }

    val union: (x: Int, y: Int) -> Unit = { x, y ->
        val u = get.invoke(x)
        val v = get.invoke(y)
        if (u != v) unionFind[u] = v
    }
    var weight = 0L
    for (edge in edges) if (get.invoke(edge.start) != get.invoke(edge.end)) {
        weight += edge.cost
        union.invoke(edge.start, edge.end)
    }
    println(weight.toString())
    scanner.close()
}
