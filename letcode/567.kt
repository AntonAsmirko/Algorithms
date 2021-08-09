class Solution {
    fun checkInclusion(s1: String, s2: String): Boolean {
        var l = 0
        var r = 0
        var map: HashMap<Char, MyPair> = getHashMap(s1)
        while(r < s2.length){
            if(map.contains(s2[r]) && map[s2[r]]!!.f > 0){
                map[s2[r]]!!.f--
            } else if (map.contains(s2[r])) {
                while(l < r && s2[l] != s2[r]){
                    map[s2[l]]!!.f++
                    l++
                }
                l++
            } else {
                map.forEach{it.value.f = it.value.s}
                l = r+1
            }
            if(map.all{it.value.f == 0}){
                return true
            }
            r++
        }
        return false
    }

    fun getHashMap(s: String): HashMap<Char, MyPair> = HashMap<Char, MyPair>().apply{
        s.forEach{
            if(containsKey(it)){
                this[it]!!.f++
                this[it]!!.s++
            } else {
                this[it] = MyPair(1, 1)
            }
        }
    }

    class MyPair(var f: Int, var s: Int)
}
