class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // dp[c] = maximum items using previous item types
        // with exact cost c
        int[] dp = new int[budget + 1];

        int INF = -1000000;

        // Initially, other costs are impossible
        for (int i = 1; i <= budget; i++) {
            dp[i] = INF;
        }

        // Process each item type
        for (int i = 0; i < n; i++) {

            int factor = items[i][0];
            int price = items[i][1];

            // Count free items
            int free = 0;

            for (int j = 0; j < n; j++) {

                if (i != j && items[j][0] % factor == 0) {
                    free++;
                }
            }

            // First purchase gives purchased + free copies
            int firstGain = 1 + free;

            int[] take = new int[budget + 1];

            for (int c = 0; c <= budget; c++) {
                take[c] = INF;
            }

            // Buy the current item at least once
            for (int c = price; c <= budget; c++) {

                // First copy of this item
                int first = dp[c - price] + firstGain;

                // Additional copy of this item
                int extra = take[c - price] + 1;

                take[c] = Math.max(first, extra);
            }

            // Decide whether to skip or buy this item type
            int[] newDp = new int[budget + 1];

            for (int c = 0; c <= budget; c++) {
                newDp[c] = Math.max(dp[c], take[c]);
            }

            dp = newDp;
        }

        // Find maximum items within the budget
        int answer = 0;

        for (int c = 0; c <= budget; c++) {
            answer = Math.max(answer, dp[c]);
        }

        return answer;
    }
}