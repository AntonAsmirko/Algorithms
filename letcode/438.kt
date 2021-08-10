class Solution {
    fun findAnagrams(s: String, p: String): List<Int> {
        val res = mutableListOf<Int>()
        var l = 0
        var r = 0
        val hm = HashMap<Char, MyPair>()
            .apply{
                p.forEach{ 
                    if(contains(it)){
                        this[it]!!.f++
                        this[it]!!.s++
                    } else {
                        this[it] = MyPair(1, 1)
                    }
                }
            }
        while(r < s.length){
           if(hm[s[r]] != null){
                if(hm[s[r]]!!.f > 0){
                    hm[s[r]]!!.f--
                    r++
                    if(hm.all{it.value.f == 0}){
                        res.add(l)
                        hm[s[l]]!!.f++
                        l++
                    }
                } else {
                    val sl = s[r]
                    while(l < r && hm[sl]!!.f == 0){
                        hm[s[l]]!!.f++
                        l++
                    }
                }
            } else {
                while(r < s.length && hm[s[r]] == null){
                    r++
                }
                hm.forEach{it.value.f = it.value.s}
                l = r   
            }
        }
        return res
    }
}

class MyPair(var f: Int, var s: Int)
