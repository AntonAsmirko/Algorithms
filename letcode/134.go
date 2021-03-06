func canCompleteCircuit(gas []int, cost []int) int {
	badSegments := make([][]int, len(gas))
	petrol := 0
out:
	for i := 0; i < len(gas); i++ {
		for j := i; j < i+len(gas); j++ {
			petrol += gas[j%len(gas)]
			petrol -= cost[j%len(gas)]
			if petrol < 0 {
				badSegments[i] = []int{j, petrol}
				i = j
				petrol = 0
				continue out
			}
			if badSegments[j%len(gas)] != nil && petrol < badSegments[j%len(gas)][1] {
				badSegments[i] = []int{i, badSegments[j%len(gas)][0], petrol + badSegments[j%len(gas)][0]}
				i = badSegments[j%len(gas)][0]
				petrol = 0
				continue out
			}
			if i+len(gas)-1 == j {
				return i
			}
		}
	}
	return -1
}