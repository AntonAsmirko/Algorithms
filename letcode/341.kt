/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     constructor()
 *
 *     // Constructor initializes a single integer.
 *     constructor(value: Int)
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     fun isInteger(): Boolean
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     fun getInteger(): Int?
 *
 *     // Set this NestedInteger to hold a single integer.
 *     fun setInteger(value: Int): Unit
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     fun add(ni: NestedInteger): Unit
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     fun getList(): List<NestedInteger>?
 * }
 */

class NestedIterator(val nestedList: List<NestedInteger>) {
    
    var pos = 0
    var curList: NestedIterator? = null
    var nextVal: NestedInteger? = null
    
    fun next(): Int {
        if(curList != null && curList!!.hasNext()){
            val res = curList!!.next()
            if(!curList!!.hasNext()){
                curList = null
            }
            return res
        } else if(nextVal != null && nextVal!!.isInteger()){
            var res = nextVal!!.getInteger()
            nextVal = null
            return res
        }
        return 0
    }
    
    fun hasNext(): Boolean {
        if(curList != null && curList!!.hasNext()){
            return true
        }
        if(nextVal != null){
            return true
        } else if(pos < nestedList.size) {
            nextVal = nestedList[pos++]
            if(nextVal!!.isInteger()){
                return true
            } else {
                curList = NestedIterator(nextVal!!.getList())
                nextVal = null
                val tmpRes = curList!!.hasNext()
                if(!tmpRes){
                    curList = null
                    return hasNext()
                } else {
                    return true
                }
            }
        }
        return false
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * var obj = NestedIterator(nestedList)
 * var param_1 = obj.next()
 * var param_2 = obj.hasNext()
 */
 