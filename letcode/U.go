func myPow(x float64, n int) float64 {
	if n == 0 {
		return float64(1)
	}
	if n == 1 {
		return x
	}
	if n == -1 {
		return 1 / x
	}
	if n%2 == 0 {
		halfPow := myPow(x, n/2)
		return halfPow * halfPow
	} else {
		lessPow := myPow(x, n-1)
		return lessPow * x
	}
}