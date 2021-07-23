class Solution {
    fun addBinary(a: String, b: String): String {
        var maxx = if(a.length > b.length) a else b
        var minn = if(b.length < a.length) b else a
        var res = ""
        var i = 0
        var cur = false
        while(minn.length != i){
            when {
                minn[minn.length - 1 - i] == '1' && maxx[maxx.length - 1 - i] == '1' && cur
                -> res = "1$res"
                minn[minn.length - 1 - i] == '1' && maxx[maxx.length - 1 - i] == '1' && !cur
                -> {res = "0$res"; cur = true }
                minn[minn.length - 1 - i] == '1' && maxx[maxx.length - 1 - i] == '0' && cur  ||
                        minn[minn.length - 1 - i] == '0' && maxx[maxx.length - 1 - i] == '1' && cur
                -> res = "0$res"
                minn[minn.length - 1 - i] == '0' && maxx[maxx.length - 1 - i] == '1' && !cur ||
                        minn[minn.length - 1 - i] == '1' && maxx[maxx.length - 1 - i] == '0' && !cur
                -> res = "1$res"
                minn[minn.length - 1 - i] == '0' && maxx[maxx.length - 1 - i] == '0' && cur
                -> { res = "1$res"; cur = false}
                else -> res = "0$res"
            }
            i++
        }
        println()
        if(i == maxx.length){
            println("1")
            if(!cur){
                println("3")
                return res
            } else {
                println("4")
                return "1$res"
            }
        } else {
            println("2")
            if(cur){
                return "${addBinary(maxx.substring(0, maxx.length - i), "1")}$res"
            } else {
                return "${maxx.substring(0, maxx.length - i)}$res"
            }
        }
    }
}
