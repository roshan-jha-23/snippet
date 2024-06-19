import java.util.ArrayList;
import java.util.List;

class DisjointSet{
    List<Integer> rank=new ArrayList<>();
    List<Integer> parent=new ArrayList<>();
    List<Integer> size=new ArrayList<>();
    public DisjointSet(int n){
        for(int i=0;i<=n;i++){
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }
    public int findUPar(int node){
        if(node==parent.get(node))return node;
        int ulp=findUPar(parent.get(node));
        parent.set(node,ulp);
        return parent.get(node);
    }
    public void unionByRank(int u,int v){
        int ulpU=parent.get(u);
        int ulpV=parent.get(v);
        if(ulpU==ulpV) return;
        if(rank.get(ulpV)>rank.get(ulpU)){
            parent.set(ulpU,ulpV);
        }else if(rank.get(ulpU)>rank.get(ulpV)){
            parent.set(ulpV,ulpU);
        }else{
            parent.set(ulpV,ulpU);
            int rankU=rank.get(ulpU);
            rank.set(ulpU,rankU+1);
        }
    }
    public void unionBySize(int u,int v){
        int ulpU=parent.get(u);
        int ulpV=parent.get(v);
        if(ulpU==ulpV) return;
        if(size.get(ulpU)<size.get(ulpV)){
            parent.set(ulpU,ulpV);
            size.set(ulpV,size.get(ulpU)+size.get(ulpV));
        }else {
            parent.set(ulpV,ulpU);
            size.set(ulpU,size.get(ulpU)+size.get(ulpV));
        }
    }
}
public class Main {
    public static void main(String[] args) {
        DisjointSet ds=new DisjointSet(7);
        ds.unionBySize(1,2);
        ds.unionBySize(2,3);
        ds.unionBySize(4,5);
        ds.unionBySize(6,7);
        ds.unionBySize(5,6);
        if(ds.findUPar(3)==ds.findUPar(6)){
            System.out.println("Same");
        }else {
            System.out.println("Not Same");
        }
        ds.unionBySize(3,7);
        if(ds.findUPar(3)==ds.findUPar(6)){
            System.out.println("Same");
        }else {
            System.out.println("Not Smae");
        }

    }
}
