/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firststate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author asus
 */
public class copymatrix {
private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};
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
public List<int[][]> generateNextStates(int[][] state) {
    List<int[][]> results = new ArrayList<>();
     

    for (int i = 0; i < state.length; i++) {
        for (int j = 0; j < state[i].length; j++) {
            if (state[i][j] != 0) continue;  
          
            

            for (int[] d : DIRS) {
                int si = i + d[0];
                int sj = j + d[1];

                if (si < 0 || si >= state.length || sj < 0 || sj >= state[0].length) continue;
                if (state[si][sj] == 0) continue;

            
                int[][] copy = deepCopy(state);
                copy[i][j] = state[si][sj];  
                results.add(copy);
            }
        }
    }
    return results;
}

public String signature(int[][] m) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : m)
            for (int v : row)
                sb.append(v).append(',');
        return sb.toString();
    }
}
