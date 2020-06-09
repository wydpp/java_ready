package com.wydpp.pufa;

public class MergeArray {

    /**
     * 合并两个有序数组，不重复，并输出
     * @param a
     * @param b
     * @return
     */
    public static int[] merge(int[] a,int[] b){
        int[] result = new int[a.length+b.length];
        int i=0;
        int j=0;
        int r = 0;
        for(;i<a.length;i++){
            int av = a[i];
            if(j == b.length){
                result[r++] = av;
            }else{
                for(;j<b.length;j++){
                    int bv = b[j];
                    if(av<bv){
                        result[r++] = av;
                        break;
                    }else if(av == bv){
                        result[r++] = av;
                        j++;
                        break;
                    }else{
                        result[r++] = bv;
                    }
                }
            }
        }
        int[] merged = new int[r];
        for (int m = 0; m < r; m++) {
            merged[m] = result[m];
        }
        return merged;
    }

    public static void main(String[] args) {
        int[] a = {1,4,8,9};
        int[] b = {3,4,6,9};
        int[] merged = merge(a,b);
        for(int i=0;i<merged.length;i++){
            System.out.println(merged[i]+",");
        }
    }
}
