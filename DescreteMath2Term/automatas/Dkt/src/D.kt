import java.io.*
import java.util.*
import kotlin.math.pow

val scanner = FastReader(FileInputStream("problem4.in"))
val bufferedReader = BufferedWriter(FileWriter("problem4.out"))
lateinit var can: Array<TreeMap<Bitmask, Int>>
val numStates = scanner.nextInt()
val numEdges = scanner.nextInt()
val numTerminalStates = scanner.nextInt()
val l = scanner.nextInt()

class Bitmask(var arr: Array<Boolean>) : Comparable<Bitmask> {
    override fun compareTo(other: Bitmask): Int {
        return when {
            this.arr.contentEquals(other.arr) -> 0
            this.getIntByMod(1000000007) > other.getIntByMod(1000000007) -> 1
            else -> -1
        }
    }

    private fun bitmaskToIntByMod(bitmask: Array<Boolean>, mod: Int): Int {
        var res = 0L
        for (i in bitmask.indices)
            if (bitmask[i]) res = (res + (2.0.pow(i - 1).toLong() % mod.toLong())) % mod.toLong()
        return res.toInt()
    }

    fun getIntByMod(mod: Int): Int = bitmaskToIntByMod(this.arr, mod)
}

fun getNFA(): Pair<Array<Array<Bitmask>>, TreeSet<Bitmask>> {
    val dfa = Pair(Array(numStates + 1) { Array(26) { Bitmask(Array(101) { false }) } }, TreeSet<Bitmask>())
    for (i in 1..numTerminalStates) {
        val pos = scanner.nextInt()
        dfa.second.add(Bitmask(Array(101) { item -> item == pos }))
    }
    for (i in 1..numEdges) {
        val init = scanner.nextInt()
        val dest = scanner.nextInt()
        val thunk = scanner.next1()[0] - 'a'
        dfa.first[init][thunk].arr[dest] = true
    }
    return dfa
}

fun toDFA(nfa: Pair<Array<Array<Bitmask>>, TreeSet<Bitmask>>)
        : Pair<TreeMap<Bitmask, Array<Bitmask>>, TreeSet<Bitmask>> {
    val tempDfa = TreeMap<Bitmask, Array<Bitmask>>()
    val queue = LinkedList<Bitmask>()
    queue.add(Bitmask(Array(101) { i -> i == 1 }))
    tempDfa[Bitmask(Array(101) { i -> i == 1 })] = Array(26) { Bitmask(Array(101) { i -> i == 1 }) }
    while (!queue.isEmpty()) {
        val vertex = queue.removeFirst()
        for (c in 0..25) {
            val newVertex = Bitmask(Array(101) { false })
            var markTerminal = false
            for (i in vertex.arr.indices)
                if (vertex.arr[i]) {
                    var uu = 0
                    newVertex.arr = newVertex.arr.map { item -> item or nfa.first[i][c].arr[uu++] }.toTypedArray()
                    for (k in nfa.first[i][c].arr.indices)
                        if (nfa.first[i][c].arr[k] && Bitmask(Array(101) { item -> item == k }) in nfa.second)
                            markTerminal = true
                }
            if (newVertex != Bitmask(Array(101) { false })) {
                if (!tempDfa.containsKey(newVertex)) {
                    queue.add(newVertex)
                    tempDfa[newVertex] = Array(26) { Bitmask(Array(101) { false }) }
                    if (markTerminal) nfa.second.add(newVertex)
                }
                tempDfa[vertex]!![c] = newVertex
            }
        }
    }
    return Pair(tempDfa, nfa.second)
}

fun bitmaskToIntByMod(bitmask: Array<Boolean>, mod: Int): Int {
    var res = 0L
    for (i in bitmask.indices)
        if (bitmask[i]) res = (res + (2.0.pow(i - 1).toLong() % mod.toLong())) % mod.toLong()
    return res.toInt()
}

fun accepts(dfa: Pair<TreeMap<Bitmask, Array<Bitmask>>, TreeSet<Bitmask>>): Int {
    can = Array(l + 1) { TreeMap<Bitmask, Int>() }
    for (i in dfa.first.keys)
        for (j in 0..l)
            can[j][i] = 0

    can[0][Bitmask(Array(101) { item -> item == 1 })] = 1
    for (i in 0 until l)
        for (u in dfa.first.keys.toList())
            if (can[i][u]!! > 0)
                for (v in dfa.first[u]!!) {
                    if (v.arr.contains(true))
                        can[i + 1][v] = (can[i + 1][v]!! + can[i][u]!!) % 1000000007
                }
    var res = 0
    for (u in dfa.second)
        if (u in dfa.first.keys)
            res = (res + (can[l][u]!! % 1000000007)) % 1000000007
    return res
}

fun main(args: Array<String>) {
    bufferedReader.write(accepts(toDFA(getNFA())).toString())
    bufferedReader.close()
}

class FastReader internal constructor(input: InputStream?) {
    var br: BufferedReader
    var st: StringTokenizer? = null
    operator fun next(): String? {
        while (st == null || !st!!.hasMoreElements()) {
            st = try {
                StringTokenizer(br.readLine())
            } catch (e: Exception) {
                return null
            }
        }
        return st!!.nextToken()
    }

    fun nextInt(): Int {
        return next()!!.toInt()
    }

    fun next1(): String {
        return next().toString()
    }

    init {
        br = BufferedReader(InputStreamReader(input))
    }
}