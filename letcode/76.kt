class Solution {
    fun minWindow(s: String, t: String): String {
        var minimumLen = Int.MAX_VALUE
        var minimumL = 0
        var minimumR = 0
        var l = 0
        var r = 0
        val tipoT = HashMap<Char, Int>()
        loadT(tipoT, t)
        val curState = HashMap<Char, Int>()
        while(r < s.length){
            if(tipoT.containsKey(s[r])){
                load(curState, s[r])
                while(check(curState, tipoT)){
                    if(minimumLen > r - l + 1){
                        minimumLen = r - l + 1
                        minimumL = l
                        minimumR = r
                    }
                    if(l == r){
                        break
                    }
                    if(curState.containsKey(s[l])){
                        dump(curState, s[l])
                    }
                    if(l < r){
                        l++
                    }
                }
            }
            r++
        }
        if(minimumLen == Int.MAX_VALUE){
            return ""
        }
        return s.substring(minimumL, minimumR + 1)
    }

    fun loadT(map: HashMap<Char, Int>, t: String){
        t.forEach{
            load(map, it)
        }
    }

    fun load(map: HashMap<Char, Int>, ch: Char){
        if(map.containsKey(ch)){
            map[ch] = map[ch]!! + 1
        } else {
            map[ch] = 1
        }
    }

    fun dump(map: HashMap<Char, Int>, ch: Char){
        if(map.containsKey(ch) && map[ch]!! > 1){
            map[ch] = map[ch]!! - 1
        } else if (map.containsKey(ch) && map[ch] == 1){
            map.remove(ch)
        }
    }

    fun check(map: HashMap<Char, Int>, tMap: HashMap<Char, Int>): Boolean{
        for((k, v) in tMap){
            if(!map.contains(k) || map[k]!! < v){
                return false
            }
        }
        return true
    }
}
