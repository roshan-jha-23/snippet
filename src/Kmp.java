import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Kmp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String a = sc.next();
        String b = sc.next();
        int k = sc.nextInt();
        sc.close();
        
        String sa = a + "#" + s;
        String sb = b + "#" + s;
        List<Integer> va = new ArrayList<>();
        List<Integer> vb = new ArrayList<>();
        
        List<Integer> v = kmp(sa);
        for (int i = a.length(); i < v.size(); i++) {
            int el = v.get(i);
            if (el == a.length()) {
                va.add(i - 2 * a.length());
            }
        }
        
        v = kmp(sb);
        for (int i = b.length(); i < v.size(); i++) {
            int el = v.get(i);
            if (el == b.length()) {
                vb.add(i - 2 * b.length());
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        int bi = 0;
        
        for (int i = 0; i < va.size(); i++) {
            bi = lowerBound(vb, va.get(i) - k);
            if (bi != vb.size() && vb.get(bi) - va.get(i) <= k) {
                ans.add(va.get(i));
            }
        }
        
        System.out.println(ans);
    }
    
    static List<Integer> kmp(String s) {
        List<Integer> lps = new ArrayList<>();
        lps.add(0); // base case
        for (int i = 1; i < s.length(); i++) {
            int pidx = lps.get(i - 1);
            while (pidx > 0 && s.charAt(i) != s.charAt(pidx)) {
                pidx = lps.get(pidx - 1);
            }
            if (s.charAt(i) == s.charAt(pidx)) {
                pidx++;
            }
            lps.add(pidx);
        }
        return lps;
    }
    
    static int lowerBound(List<Integer> list, int value) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid) < value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
