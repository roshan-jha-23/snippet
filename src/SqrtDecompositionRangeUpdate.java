import java.util.Scanner;

public class SqrtDecompositionRangeUpdate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int sqrtLen = (int) Math.ceil(Math.sqrt(n));
        int[] sqrt = new int[sqrtLen];
        int[] lazy = new int[sqrtLen];

        // Initialize array and sqrt array
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            int sqrtIdx = i / sqrtLen;
            sqrt[sqrtIdx] += arr[i];
        }

        int q = sc.nextInt();
        int[][] query = new int[q][4];

        // Read queries
        for (int i = 0; i < q; i++) {
            query[i][0] = sc.nextInt();
            query[i][1] = sc.nextInt();
            query[i][2] = sc.nextInt();
            if (query[i][0] == 2) {
                query[i][3] = sc.nextInt();
            }
        }

        // Process queries
        for (int i = 0; i < q; i++) {
            int type = query[i][0];

            if (type == 1) {
                int l = query[i][1];
                int r = query[i][2];
                int ans = 0;

                // Range sum query
                while (l <= r) {
                    int block = l / sqrtLen;
                    if (l % sqrtLen == 0 && l + sqrtLen - 1 <= r) {
                        ans += sqrt[block] + lazy[block] * sqrtLen;
                        l += sqrtLen;
                    } else {
                        ans += arr[l] + lazy[block];
                        l++;
                    }
                }
                System.out.println(ans);
            } else {
                // Range update
                int l = query[i][1];
                int r = query[i][2];
                int val = query[i][3];

                while (l <= r) {
                    int block = l / sqrtLen;
                    if (l % sqrtLen == 0 && l + sqrtLen - 1 <= r) {
                        lazy[block] += val;
                        l += sqrtLen;
                    } else {
                        arr[l] += val;
                        sqrt[block] += val;
                        l++;
                    }
                }
            }
        }

        sc.close();
    }
}
