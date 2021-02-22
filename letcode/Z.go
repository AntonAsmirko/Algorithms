func searchRange(nums []int, target int) []int {
    if len(nums) == 0{
        return []int{-1, -1}
    }
    l, r := 0, len(nums) - 1
    for r - l > 1{
        mid := l + (r - l) / 2
        if nums[mid] >= target{
            r = mid
        } else {
            l = mid
        }
    }
    left := r
    if nums[l] == target{
        left = l
    }
    l, r = 0, len(nums) - 1
    for r - l > 1{
        mid:= l + (r - l)/ 2
        if nums[mid] <= target{
            l = mid
        } else {
            r = mid
        }
    }
    right := l
    if nums[r] == target{
        right = r
    }
    if nums[left] != target || nums[right] != target{
        return []int{-1, -1}
    }
    return []int{left, right}
}