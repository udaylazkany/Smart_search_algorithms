/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firststate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author asus
 */
public class copymatrix {
    
public final matrix mat =new matrix() ;
private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};
private HashMap<String,int[][]> visited=new HashMap<>();
private ArrayList<Long> time =new ArrayList<>();
public final HashMap<String, int[][]> parent = new HashMap<>();
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

public int countZeros(int[][] g) {
        int c = 0;
        for (int[] row : g)
            for (int v : row)
                if (v == 0) c++;
        return c;
    }

public List<int[][]> getPath(int[][] goal) {
    List<int[][]> path = new ArrayList<>();
    int[][] cur = goal;

    while (cur != null) {
        path.add(cur);
        cur = parent.get(signature(cur)); 
    }

    Collections.reverse(path); 
    return path;
}

public void printPath(int[][] goal) {
    List<int[][]> path = getPath(goal);
    System.out.println("=== Path (" + path.size() + " steps) ===");
    for (int i = 0; i < path.size(); i++) {
        System.out.println("Step " + i + ":");
        mat.printmat(path.get(i));
        System.out.println();
    }
}
  
}
