import java.util.Scanner;

public class MokshAndHisMehar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt and read input values
        System.out.println("Enter the size of the array (n):");
        int n = sc.nextInt();

        System.out.println("Enter the required count (m):");
        int m = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the number of queries (q):");
        int q = sc.nextInt();

        int[][] queries = new int[q][2];

        System.out.println("Enter the queries (pairs of indices):");
        for (int i = 0; i < q; i++) {
            System.out.println("Enter the start and end indices for query " + (i + 1) + ":");
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
            arr[queries[i][0]] += 1;
            if (queries[i][1] + 1 < n) {
                arr[queries[i][1] + 1] -= 1;
            }
        }

        // Apply prefix sum to get the actual array values
        int cnt = arr[0] == m ? 1 : 0;
        for (int i = 1; i < n; i++) {
            arr[i] += arr[i - 1];
            if (arr[i] == m) {
                cnt++;
            }
        }

        // Calculate prefix counts for `m` and `m + 1`
        int[] prefixCntForM = new int[n];
        int[] prefixCntForMPlusOne = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                prefixCntForM[i] = prefixCntForM[i - 1];
                prefixCntForMPlusOne[i] = prefixCntForMPlusOne[i - 1];
            }
            if (arr[i] == m) {
                prefixCntForM[i]++;
            }
            if (arr[i] == m + 1) {
                prefixCntForMPlusOne[i]++;
            }
        }

        // Find the overall maximum count by ignoring each query
        int overallMax = 0;
        for (int i = 0; i < q; i++) {
            int s = queries[i][0];
            int l = queries[i][1];

            // Get counts in the range [s, l]
            int countMInRange = prefixCntForM[l] - (s > 0 ? prefixCntForM[s - 1] : 0);
            int countMPlusOneInRange = prefixCntForMPlusOne[l] - (s > 0 ? prefixCntForMPlusOne[s - 1] : 0);

            // Calculate count after ignoring the current query
            int cntAfterIgnore = cnt - countMInRange + countMPlusOneInRange;
            overallMax = Math.max(overallMax, cntAfterIgnore);
        }

        // Output the result
        System.out.println("Overall maximum count: " + overallMax);
//        output
//        Enter the size of the array (n):
//        10
//        Enter the required count (m):
//        2
//        Enter the number of queries (q):
//        3
//        Enter the queries (pairs of indices):
//        Enter the start and end indices for query 1:
//        2
//        6
//        Enter the start and end indices for query 2:
//        4
//        9
//        Enter the start and end indices for query 3:
//        1
//        4
//        Overall maximum count: 3
    }
}
