import "sort"

var pairs [][][]int

type p struct {
	val int
	is  []int
}

func makePairs(nums map[int][]int, keys []p, i int, pair [][]int) {
	if i >= len(keys) {
		return
	}
	if len(pair) == 2 {
		pairs = append(pairs, pair)
		return
	}
	makePairs(nums, keys, i+1, pair)
	if len(nums[keys[i].val]) > 0 {
		tmp := nums[keys[i].val][len(nums[keys[i].val])-1]
		nums[keys[i].val] = nums[keys[i].val][:len(nums[keys[i].val])-1]
		makePairs(nums, keys, i, append(pair, []int{keys[i].val, tmp}))
		nums[keys[i].val] = append(nums[keys[i].val], tmp)
	}
}

func threeSum(nums []int) [][]int {
	pairs = make([][][]int, 0)
	nums2 := make(map[int][]int)
	for i, item := range nums {
		if _, found := nums2[item]; !found {
			nums2[item] = []int{i}
		} else {
			nums2[item] = append(nums2[item], i)
		}
	}
	keys := make([]p, 0)
	for key := range nums2 {
		keys = append(keys, p{key, nums2[key]})
	}
	sort.SliceStable(keys, func(i, j int) bool {
		return keys[i].val < keys[j].val
	})
	makePairs(nums2, keys, 0, make([][]int, 0))
	complay := make(map[int]int)
	for i := 0; i < len(nums); i++ {
		complay[nums[i]+100000]++
	}
	result := make([][]int, 0)
	for i := 0; i < len(pairs); i++ {
		if complay[-1*(pairs[i][0][0]+pairs[i][1][0])+100000] > 0 {
			if (complay[-1*(pairs[i][0][0]+pairs[i][1][0])+100000] > 2 && pairs[i][0][0] == 0 && pairs[i][1][0] == 0) || pairs[i][0][0] != 0 || pairs[i][1][0] != 0 {
				if pairs[i][0][0] <= 0 && pairs[i][1][0] <= 0 {
					result = append(result, []int{-1 * (pairs[i][0][0] + pairs[i][1][0]), pairs[i][0][0], pairs[i][1][0]})
				} else if pairs[i][0][0] > 0 && pairs[i][1][0] > 0 {
					result = append(result, []int{-1 * (pairs[i][0][0] + pairs[i][1][0]), pairs[i][0][0], pairs[i][1][0]})
				}
			}
		}
	}
	return result
}