/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package firststate;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class DFSPlayer {

private final matrix mat;
private final copymatrix cm;
private final move mov;
private final HashMap<String, int[][]> parent = new HashMap<>();
private final HashMap<Integer, int[]> fixedPositions;
private final int cols;

public DFSPlayer() {
        mat = new matrix();
        cm = new copymatrix();
        mov = new move();

        cols = mat.grid[0].length;

    
        fixedPositions = mov.findpair(mat.grid);

       
        mov.protectedcellset(fixedPositions, cols);

      
        int[][] start = cm.deepCopy(mat.grid);

        System.out.println("=== Initial State ===");
        mat.printmat(start);
        System.out.println("zeros = " + countZeros(start));
        System.out.println();

        cm.addnode(start);

        boolean found = dfs(start);
        System.out.println(found ? " solution" : "No solution");
    }
private boolean dfs(int[][] start) {
        Deque<int[][]> stack = new ArrayDeque<>();
        stack.push(start);
       parent.put(cm.signature(start), null);
        if (mat.solvegride(start)) {
            mat.printmat(start);
            return true;
        }

        int step = 0;
        int popCount = 0;

        while (!stack.isEmpty()) {
            int[][] current = stack.pop();
            popCount++;

            List<int[][]> children = cm.generateNextStates(current);

            System.out.println("--- POP #" + popCount
                    + " (zeros=" + countZeros(current)
                    + ", children=" + children.size() + ") ---");
            mat.printmat(current);
            System.out.println();

            for (int[][] next : children) {
                if (cm.addnode(next)) {
                    parent.put(cm.signature(next), current);
                    step++;

                    System.out.println(">>> NEW State #" + step
                            + " (zeros=" + countZeros(next) + ")");
                    mat.printmat(next);

                   
                    for (int i = 0; i < next.length; i++) {
                        for (int j = 0; j < next[i].length; j++) {
                            if (current[i][j] != next[i][j]) {
                                System.out.println("    Changed: [" + i + "][" + j + "] "
                                        + current[i][j] + " → " + next[i][j]);
                            }
                        }
                    }
                    System.out.println();

                    if (mat.solvegride(next)) {
                          
                        System.out.println(" Solved at state #" + step);
                        mat.printmat(next);
                         printPath(next);
                        return true;
                    }
                    
                    stack.push(next);
                }
            }
        }
        return false;
    }

private int countZeros(int[][] g) {
        int c = 0;
        for (int[] row : g)
            for (int v : row)
                if (v == 0) c++;
        return c;
    }

private List<int[][]> getPath(int[][] goal) {
    List<int[][]> path = new ArrayList<>();
    int[][] cur = goal;

    while (cur != null) {
        path.add(cur);
        cur = parent.get(cm.signature(cur)); 
    }

    Collections.reverse(path); 
    return path;
}

private void printPath(int[][] goal) {
    List<int[][]> path = getPath(goal);
    System.out.println("=== Path (" + path.size() + " steps) ===");
    for (int i = 0; i < path.size(); i++) {
        System.out.println("Step " + i + ":");
        mat.printmat(path.get(i));
        System.out.println();
    }
}
    
}