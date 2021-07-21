/* The isBadVersion API is defined in the parent class VersionControl.
      def isBadVersion(version: Int): Boolean = {} */

// n = 5, bad = 3

class Solution: VersionControl() {
    override fun firstBadVersion(n: Int) : Int {
        var l = 0
        var r = n
        var mid = l + (r - l) / 2
        while(r - l > 1){
            mid = l + (r - l) / 2
            when{
                isBadVersion(mid) -> r = mid
                else -> l = mid
            }
        }
        return r
	}
}
