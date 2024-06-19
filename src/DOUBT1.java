import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//I got this question in a test ,
//        We have n items
//        Every item has Type, weight, value
//        And we have maximum weight allowed W.Find the maximum value we can get within this W and condition is we can take a type of item only ones
//        Ex 1 2 3
//          3 4 10
//          2 6 7
//          2 4 5
//        If we take item type 2 then we cannot take an item of type 2 again
//        Any suggestions how to do it
public class DOUBT1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Number of items
        List<List<int[]>> itemsByType = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int type = sc.nextInt();
            int weight = sc.nextInt();
            int value = sc.nextInt();

            if (itemsByType.size() < type) {
                for (int j = itemsByType.size(); j < type; j++) {
                    itemsByType.add(new ArrayList<>());
                }
            }
            itemsByType.get(type - 1).add(new int[]{weight, value});
        }
        int wt = sc.nextInt();
        int ans = knapsackWithTypeConstraint(itemsByType, wt);
        System.out.println(ans);
    }

    static int knapsackWithTypeConstraint(List<List<int[]>> itemsByType, int maxWeight) {
        int n = itemsByType.size();
        int[][] dp = new int[n + 1][maxWeight + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                for (int[] item : itemsByType.get(i - 1)) {
                    int weight = item[0];
                    int value = item[1];
                    if (j >= weight) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight] + value);
                    }
                }
            }
        }

        return dp[n][maxWeight];
    }
}
