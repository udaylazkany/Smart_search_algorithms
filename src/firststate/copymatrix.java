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
public final HashMap<String, Integer> direction = new HashMap<>();
public final matrix mat =new matrix() ;
private static final int[][] DIRS = {{-1,0},{1,0},{0,-1},{0,1}};
private final HashMap<String,int[][]> visited=new HashMap<>();
private final ArrayList<Long> time;
public final HashMap<String, int[][]> parent = new HashMap<>();

    public copymatrix() {
        this.time = new ArrayList<>();
    }
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

public int getDirection(int[][] from, int[][] to) {
    int[] p1 = findZero(from);
    int[] p2 = findZero(to);
    if (p1 == null || p2 == null) return -1;
    int dr = p2[0] - p1[0];
    int dc = p2[1] - p1[1];
    if (dr == -1 && dc == 0) return 0; 
    if (dr ==  1 && dc == 0) return 1; 
    if (dr ==  0 && dc == -1) return 2; 
    if (dr ==  0 && dc ==  1) return 3;
    return -1;
}

private int[] findZero(int[][] m) {
    for (int i = 0; i < m.length; i++)
        for (int j = 0; j < m[i].length; j++)
            if (m[i][j] == 0) return new int[]{i, j};
    return null;
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
