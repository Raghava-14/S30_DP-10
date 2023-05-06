//Time = O(n^3), length of the input array
//Space = O(n^2), 2D array of size n*n

class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        // Compute the maximum coins that can be obtained for subarrays of length 1, 2, 3, ..., n
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    int left = k == i ? 0 : dp[i][k - 1];
                    int right = k == j ? 0 : dp[k + 1][j];
                    int val = nums[k] * (i > 0 ? nums[i - 1] : 1) * (j < n - 1 ? nums[j + 1] : 1);
                    dp[i][j] = Math.max(dp[i][j], left + val + right);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}
