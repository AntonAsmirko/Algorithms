class Solution {
    
    var numsMap = HashMap<Int, Int>()
    
    fun compress(chars: CharArray): Int {
        var c1 = 1
        var c2 = 0
        var offset = 0
        numsMap = HashMap<Int, Int>()
        while(c1 < chars.size){
            if(chars[c1] == chars[c2] && c1 - c2 == 1){
                chars[c1] = '2'
                numsMap[c1] = c1 + 1
            } else if (chars[c1] == chars[c2] && c1 - c2 > 1){
                incNum(chars, c2 + 1)
            } else if (chars[c1] != chars[c2]) {
                if(c1 != c2 + 1){
                    offset += c1 - skipNum(chars ,c2 + 1, offset)
                }
                c2 = c1
                chars[c1 - offset] = chars[c1]
            }
            c1++
        }
        if(c1 != c2 + 1){
            offset += c1 - skipNum(chars ,c2 + 1, offset)      
        }
        return c1 - offset
    }
    
    fun incNum(chars: CharArray, pos: Int){
        var initPos = pos
        var pos = numsMap[pos]!! - 1
        while(pos >= 0 && chars[pos] == '9' && numsMap[pos] == null){
            chars[pos] = '0'
            pos--
        }
        if(chars[pos] != '9'){
            chars[pos]++
        } else {
            chars[pos] = '1'
            chars[numsMap[initPos]!!] = '0'            
            numsMap[initPos] = numsMap[initPos]!! + 1
        }
    }
    
    fun skipNum(chars: CharArray, pos:Int, offset: Int = 0): Int{
        val initPos = pos
        var pos = pos
        while(pos < chars.size && pos < numsMap[initPos]!!){
            if(offset > 0){
                chars[pos - offset] = chars[pos]
            }
            pos++
        }
        return pos
    }
}
