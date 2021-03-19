class Solution {
    
    private int closestLess(int[][] matrix, int target){
        int l = 0, r = matrix.length;
        while(r - l > 1){
            int mid = l + (r - l) / 2;
            if(matrix[mid][0] > target){
                r = mid;
            } else {
                l = mid;
            }
        }
        return l;
    }
    
    private int closestLess1D(int[] row, int target){
        int l = 0, r = row.length;
        while(r - l > 1){
            int mid = l + (r - l) / 2;
            if(row[mid] > target){
                r = mid;
            } else {
                l = mid;
            }
        }
        return l;
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int closestRow = closestLess(matrix, target);
        int res = closestLess1D(matrix[closestRow], target);
        return matrix[closestRow][res] == target;
    }
}