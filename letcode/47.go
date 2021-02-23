var permutations [][]int

func cloneI(arr []int) []int{
    b := make([]int, len(arr))
    copy(b, arr)
    return b
}

func cloneB(arr []bool) []bool{
    b := make([]bool, len(arr))
    copy(b, arr)
    return b
}


func makePerms(nums, res []int, wasIndex []bool){
    if len(res) == len(nums){
        permutations = append(permutations, cloneI(res))
        return
    }
    var dummy struct{}
    wasMap := make(map[int]struct{})
    for i:=0; i < len(nums); i++{
        if _, found := wasMap[nums[i]]; !wasIndex[i] && !found{
            wasMap[nums[i]] = dummy
            nw := cloneB(wasIndex)
            nw[i] = true
            makePerms(nums, append(cloneI(res), nums[i]), nw)
        }
    }
}

func permuteUnique(nums []int) [][]int {
    permutations = make([][]int, 0)
    makePerms(nums, make([]int, 0), make([]bool, len(nums)))
    return permutations
}