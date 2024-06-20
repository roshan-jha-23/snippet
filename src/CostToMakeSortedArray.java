public class CostToMakeSortedArray {
    static int[] farr; // Fenwick Tree array

    public static void main(String[] args) {
        int[] inst = {1, 3,3,2,3,4,2,1,2}; // Example input
        CostToMakeSortedArray obj = new CostToMakeSortedArray();
        int result = obj.createSortedArray(inst);
        System.out.println("Minimum cost to make sorted array: " + result);
    }

    int createSortedArray(int[] inst) {
        int cost = 0;
        farr = new int[100001]; // Fenwick Tree to store frequencies
        int MOD = 1000000007; // To keep the result within bounds

        for (int i = 0; i < inst.length; i++) {
            int smaller = find(inst[i] - 1); // Count of numbers smaller than inst[i]
            int greater = i - find(inst[i]); // Count of numbers greater than inst[i]
            cost = (cost + Math.min(smaller, greater)) % MOD; // Accumulate cost
            update(inst[i]); // Update Fenwick Tree with the current number
        }
        return cost;
    }

    static void update(int x) {
        while (x <= 100000) {
            farr[x]++;
            x += (x & -x); // Move to the next index to update
        }
    }

    public static int find(int x) {
        int cnt = 0;
        while (x > 0) {
            cnt += farr[x];
            x -= (x & -x); // Move to the parent index
        }
        return cnt;
    }
}
