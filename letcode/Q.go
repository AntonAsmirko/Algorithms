import "math"

func square(l, r, lHeight, rHeight int) int {
	return (r - l) * int(math.Min(float64(lHeight), float64(rHeight)))
}

func maxArea(height []int) int {
	l, r, lHeight, rHeight := 0, len(height)-1, height[0], height[len(height)-1]
	max := square(l, r, lHeight, rHeight)
	for l < r {
		if lHeight < rHeight {
			for l < r && height[l] <= lHeight {
				l++
			}
			newSquare := square(l, r, height[l], rHeight)
			if newSquare > max {
				max = newSquare
			}
			lHeight = height[l]
		} else {
			for l < r && height[r] <= rHeight {
				r--
			}
			newSquare := square(l, r, lHeight, height[r])
			if newSquare > max {
				max = newSquare
			}
			rHeight = height[r]
		}
	}
	return max
}
