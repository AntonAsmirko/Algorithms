func twoSum(nums []int, target int) []int {
    for i:=0; i < len(nums); i++{
        for j:=0; j < len(nums); j++{
            if i != j && nums[i] + nums[j] == target{
                result := make([]int, 0)
                result = append(result, i)
                result = append(result, j)
                return result
            }
        }
    }
    return make([]int, 0)
}