public class Bit {
    public static void main(String[] args) {
        Solution s=new Solution();
        int y=s.bitwiseComplement(5);
        System.out.println(y);
    }
   static class Solution {
        static public int bitwiseComplement(int n) {
            String s=decimalToBinaryString(n);
            int num=0;
            int pow2=1;
            for(int i=s.length()-1;i>=0;i--){
                if(s.charAt(i)=='0')num=num+pow2;
                pow2=pow2<<1;
            }
            return num;
        }
       static String decimalToBinaryString(int n){
            StringBuilder sb=new StringBuilder();
            while(n>1){
                if((n&1)==1)sb.append("1");
                else sb.append("0");
                n=n>>1;
            }
            if(n==1)sb.append("1");
            return sb.reverse().toString();
        }
    }
}
