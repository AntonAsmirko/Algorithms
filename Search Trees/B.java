import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val treeSet = TreeSet<Int>()
    while (scanner.hasNext()) {
        when (scanner.next()) {
            "insert" -> treeSet.add(scanner.nextInt())
            "delete" -> treeSet.remove(scanner.nextInt())
            "exists" -> println(treeSet.contains(scanner.nextInt()))
            "next" -> println(treeSet.higher(scanner.nextInt()) ?: "none")
            "prev" -> println(treeSet.lower(scanner.nextInt()) ?: "none")
        }
    }
}