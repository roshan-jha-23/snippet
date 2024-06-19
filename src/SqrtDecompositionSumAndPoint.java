import java.util.Scanner;

public class SqrtDecompositionSumAndPoint {


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int []arr=new int[n];
        int sqrtLen=(int) Math.ceil(Math.sqrt(n));
        int[] sqrt=new int[sqrtLen];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
            int sqrtIdx=i/sqrtLen;
            sqrt[sqrtIdx]+=arr[i];
        }
        int q=sc.nextInt();
        int [][] query=new int[q][3];
        for(int i=0;i<q;i++){
            query[i][0]=sc.nextInt();
            query[i][1]=sc.nextInt();
            query[i][2]=sc.nextInt();
        }


        for(int i=0;i<q;i++){
            int type=query[i][0];

            if(type==1){
                int l=query[i][1];
                int r=query[i][2];
                int ans=0;
                while(l<=r){
                    if(l%sqrtLen==0 && l+sqrtLen-1<=r){
                        ans+=sqrt[l/sqrtLen];
                        l+=sqrtLen;
                    }else{
                        ans+=arr[l];
                        l++;
                    }
                }
                System.out.println(ans);
            }else{
                //update case
                int idx=query[i][1];
                int del=query[i][2];
                int blockIdx = idx / sqrtLen;
                sqrt[blockIdx] += del;
                arr[idx] += del;
            }
        }
    }
}
