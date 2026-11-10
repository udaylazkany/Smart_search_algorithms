/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firststate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author asus
 */
public class copymatrix {
private HashMap<String,int[][]> visited=new HashMap<>();
private ArrayList<Long> time =new ArrayList<>();
public int[][] deepCopy(int[][] src) {
        int[][] dst = new int[src.length][];
        for (int i = 0; i < src.length; i++)
            dst[i] = src[i].clone();
        return dst;
    }
public boolean addnode(int mat[][]) {
    long start = System.nanoTime();
    String sig = signature(mat);
    if (!visited.containsKey(sig)) {
        visited.put(sig, deepCopy(mat));
        time.add(System.nanoTime() - start);
        return true;
    }
    return false;  
}
    
public String signature(int[][] m) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : m)
            for (int v : row)
                sb.append(v).append(',');
        return sb.toString();
    }
}
