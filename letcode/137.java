class Solution {
    
    private void toBin(int x, int[] accum){
        int i = accum.length - 1;
        while(x != 0){
            int rem = x % 2;
            accum[i] += rem;
            x /= 2;
            i--;
        }
    }
    
    private long getInt(int[] arr){
        for(int i = 1; i < arr.length; i++){
            arr[i] %= 3; 
        }
        long res = 0;
        for(int i = 0; i < arr.length; i++){
            res += ((Double)Math.pow(2.0, ((Integer)i).doubleValue())).longValue() * arr[arr.length - 1 - i]; 
        }
        return res;
    }
    
    public int singleNumber(int[] nums) {
        int[] allNumbers = new int[32];
        int[] allNumbersNeg = new int[33];
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= 0){
                toBin(nums[i], allNumbers);
            } else {
                toBin(nums[i], allNumbersNeg);
            }
        }
        boolean isPositive = false;
        for(int i = 0; i < allNumbers.length; i++){
            if(allNumbers[i] % 3 != 0){
                isPositive = true;
            }
        }
        if(isPositive){
            return (int)getInt(allNumbers);
        } else {
            return (int)getInt(allNumbersNeg);
        }
    }
}