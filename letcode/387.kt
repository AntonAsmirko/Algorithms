class Solution {
    fun firstUniqChar(s: String): Int {
        var map = HashMap<Char, MyPair>()
        s.forEachIndexed{ index, it ->
            if(map[it] != null){
                map[it]!!.f++
            } else {
                map[it] = MyPair(1, index)
            }
        }
        return map.filter{it.value.f == 1}.minBy{ it.value.s }?.value?.s ?: -1
    }
}

class MyPair(var f: Int, var s: Int)
