class ZigzagIterator(val v1: IntArray, val v2: IntArray) {
    
    var areFirstCur = true
    var v1Pos = 0
    var v2Pos = 0
    
    fun next(): Int {
        if(areFirstCur && v1Pos < v1.size){
            areFirstCur = false
            return v1[v1Pos++]
        } else if (!areFirstCur && v2Pos < v2.size){
            areFirstCur = true
            return v2[v2Pos++]
        } else if (v2Pos >= v2.size){
            return v1[v1Pos++]
        } else {
            return v2[v2Pos++]
        }
    }
    
    fun hasNext(): Boolean {
        return v1Pos < v1.size || v2Pos < v2.size
    }
}

// Your ZigzagIterator object will be instantiated and called as such:
// var i = ZigzagIterator(v1, v2)
// var ret = ArrayList<Int>()
// while(i.hasNext()){
//		ret.add(i.next())
// }
