import java.util.Scanner;
import java.util.Arrays;

public class SqrtDecompostion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int sqrtLength = (int) Math.ceil(Math.sqrt(n));
        int[] sqrt = new int[sqrtLength];
        Arrays.fill(sqrt, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sqrt[i / sqrtLength] = Math.min(sqrt[i / sqrtLength], arr[i]);
        }

        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        int[] min = new int[q];

        for (int i = 0; i < q; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            min[i] = Integer.MAX_VALUE;

            while (l <= r) {

                if (l % sqrtLength == 0 && l + sqrtLength - 1 <= r) {
                    min[i] = Math.min(min[i], sqrt[l / sqrtLength]);
                    l += sqrtLength;
                } else {
                    min[i] = Math.min(min[i], arr[l]);
                    l++;
                }
            }

            System.out.println(min[i]);
        }

        sc.close();
    }
}
