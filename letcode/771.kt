class Solution {
    fun numJewelsInStones(jewels: String, stones: String): Int {
        val set = jewels.split("").filter{ it.length > 0 }.map{it[0]}.toSet()
        return stones.fold(0){acc, it -> if(set.contains(it)){ acc+1 } else { acc }}
    }
}
