//Time = O(KN log N), K = number of available eggs
//Space = O(KN), N = number of floors

class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1]; // dp[i][j] stores the minimum number of moves needed to determine the maximum floors that can be checked using i eggs and j floors
        
        // Base cases: if there is only one egg, the maximum floors that can be checked is equal to the number of floors; if there is only one floor, the maximum floors that can be checked is equal to one
        for (int i = 1; i <= K; i++) {
            dp[i][1] = 1;
        }
        for (int j = 1; j <= N; j++) {
            dp[1][j] = j;
        }
        
        // Compute the values for dp[i][j] for i > 1 and j > 1 using dynamic programming
        for (int i = 2; i <= K; i++) {
            for (int j = 2; j <= N; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                
                // Use binary search to find the critical floor k for the current values of i and j
                int lo = 1, hi = j;
                while (lo <= hi) {
                    int mid = lo + (hi - lo) / 2;
                    int broken = dp[i - 1][mid - 1]; // number of moves needed if egg breaks at floor k
                    int notBroken = dp[i][j - mid]; // number of moves needed if egg doesn't break at floor k
                    if (broken > notBroken) {
                        hi = mid - 1;
                        dp[i][j] = Math.min(dp[i][j], broken + 1); // add 1 for the current move
                    } else {
                        lo = mid + 1;
                        dp[i][j] = Math.min(dp[i][j], notBroken + 1); // add 1 for the current move
                    }
                }
            }
        }
        
        // The value of dp[K][N] is the minimum number of moves needed to determine the maximum floors that can be checked using K eggs and N floors
        return dp[K][N];
    }
}
