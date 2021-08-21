class Logger() {

    /** Initialize your data structure here. */
    
    val hm = hashMapOf<String, Int>()
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    
    fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
        if(hm.contains(message) && timestamp - hm[message]!! >= 10 || !hm.contains(message)){
            hm[message] = timestamp
            return true
        }
        return false
    }

}

/**
 * Your Logger object will be instantiated and called as such:
 * var obj = Logger()
 * var param_1 = obj.shouldPrintMessage(timestamp,message)
 */
 