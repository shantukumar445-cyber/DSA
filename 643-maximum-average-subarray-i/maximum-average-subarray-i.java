class Solution {
    public double findMaxAverage(int[] arr, int k) {
        double sum = 0;
        for(int i = 0;i<k;i++){
            sum = sum + arr[i];
        }
        double max = sum;
        for(int i = k;i<arr.length;i++){
            sum = sum - arr[i-k];
            sum = sum + arr[i];
            max = Math.max(max,sum);
        }
        return max/k;
    }
}