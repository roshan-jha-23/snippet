import java.util.Scanner;

public class FenwickTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the length of the array
        int n = scanner.nextInt();
        FenwickTree ft = new FenwickTree(n);

        // Read the initial array values
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
            ft.update(i, a[i]);  // Initialize the Fenwick Tree with the array values
        }

        // Read number of queries
        int q = scanner.nextInt();

        // Process each query
        for (int i = 0; i < q; i++) {
            int queryType = scanner.nextInt();
            if (queryType == 1) {
                // Range query: find sum between two indices
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                System.out.println(ft.rangeQuery(left, right));
            } else if (queryType == 2) {
                // Update query: update the value at a given index
                int id = scanner.nextInt();
                int newValue = scanner.nextInt();
                ft.update(id, newValue - a[id]); // Update Fenwick Tree with the difference
                a[id] = newValue; // Update the array
            }
        }

        scanner.close();
    }

    int[] bit;
    int n;

    // Constructor to initialize the Fenwick Tree with size `n`
    public FenwickTree(int n) {
        this.n = n;
        bit = new int[n + 1]; // BIT array is 1-based index
    }

    // Update the Fenwick Tree by adding `val` to index `id`
    void update(int id, int val) {
        while (id <= n) {
            bit[id] += val;
            id += (id & -id);
        }
    }

    // Get the prefix sum from index 1 to `id`
    int query(int id) {
        int sum = 0;
        while (id > 0) {
            sum += bit[id];
            id -= (id & -id);
        }
        return sum;
    }

    // Get the sum of the range [left, right]
    int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }
    int find(int k) {
        int curr = 0;
        int prevSum = 0;
        for (int i = (int)(Math.log(n) / Math.log(2)); i >= 0; i--) {
            int nextCurr = curr + (1 << i);
            if (nextCurr <= n && prevSum + bit[nextCurr] < k) {
                curr = nextCurr;
                prevSum += bit[curr];
            }
        }
        return curr + 1;
    }
}
