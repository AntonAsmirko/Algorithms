func findPivot(nums []int) (pivot int){
    l, r := 0, len(nums) - 1
    if nums[l] <= nums[r]{
        pivot = 0
        return
    }
    for ;r - l > 1;{
        mid := l + (r - l) /2
        if nums[mid] < nums[r]{
            r = mid
        } else {
            l = mid
        }
    }
    pivot = r
    return
}

func search(nums []int, target int) int {
    pivot := findPivot(nums)
    l, r := pivot, pivot + len(nums)
    for ;r - l > 1; {
        mid := l + (r - l)/2
        if nums[mid % len(nums)] > target{
            r = mid
        } else {
            l = mid
        }
    }
    if nums[l % len(nums)] == target{
        return l % len(nums)
    } else {
        return -1
    }
}