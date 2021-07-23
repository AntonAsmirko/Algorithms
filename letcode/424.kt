class Solution {
    fun characterReplacement(s: String, k: Int): Int {
        var maxx = Int.MIN_VALUE
        val lattice = listOf('A', 'B', 'C',
            'D', 'E', 'F',
            'G', 'H', 'I',
            'J', 'K', 'L',
            'M', 'N', 'O',
            'P', 'Q', 'R',
            'S', 'T', 'U',
            'V', 'W', 'X',
            'Y', 'Z')
            .forEach{
                var curK = k
                val positions = HashSet<Int>()
                var l = 0
                var r = l+1
                loop@while(r <= s.length){
                    if(s[r - 1] != it && curK > 0){
                        positions.add(r - 1)
                        curK--
                        r++
                    } else if (s[r - 1] != it){
                        maxx = Math.max(maxx, r - l - 1)
                        while(curK == 0 && l < r){
                            if(l in positions){
                                positions.remove(l)
                                curK++
                            }
                            l++
                        }
                    } else {
                        r++
                        maxx = Math.max(maxx, r - l - 1)
                    }

                    maxx = Math.max(maxx, r - l - 1)
                    if(l==r && curK == 0){
                        for(i in l until s.length){
                            if(s[i] == it) {
                                l = i
                                r = i + 1
                                continue@loop
                            }
                        }
                        break
                    }
                }
                maxx = Math.max(maxx, r - l - 1)
            }
        return maxx
    }
}
