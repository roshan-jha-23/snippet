public class TwoDFen {
    static int[][] arr; // Original array
    static int[][] farr; // Fenwick Tree array

    public static void main(String[] args) {
      
        arr = new int[6][6];
        farr = new int[6][6];

       

        preprocessor();

      
        System.out.println(query(1, 3, 1, 3)); 
        update(2, 2, 5);
        System.out.println(query(1, 3, 1, 3)); 
    }

    public static int prefixSum(int x, int y) {
        int sum = 0;
        int xDash = x;
        while (xDash > 0) {
            int yDash = y;
            while (yDash > 0) {
                sum += farr[xDash][yDash];
                yDash -= (yDash & -yDash);
            }
            xDash -= (xDash & -xDash);
        }
        return sum;
    }

    public static int query(int x1, int x2, int y1, int y2) {
        return prefixSum(x2, y2)
             - prefixSum(x1 - 1, y2)
             - prefixSum(x2, y1 - 1)
             + prefixSum(x1 - 1, y1 - 1);
    }

    public static void update(int x, int y, int val) {
        int xDash = x;
        while (xDash < farr.length) {
            int yDash = y;
            while (yDash < farr[0].length) {
                farr[xDash][yDash] += val;
                yDash += (yDash & -yDash);
            }
            xDash += (xDash & -xDash);
        }
    }

    public static void preprocessor() {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                update(i, j, arr[i][j]);
            }
        }
    }
}
