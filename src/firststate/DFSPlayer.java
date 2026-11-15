/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package firststate;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class DFSPlayer {


private final copymatrix cm;
private final move mov;
private final HashMap<Integer, int[]> fixedPositions;
private final int cols;

public DFSPlayer() {
     
        cm = new copymatrix();
        mov = new move();

        cols = cm.mat.grid[0].length;

    
        fixedPositions = mov.findpair(cm.mat.grid);

       
        mov.protectedcellset(fixedPositions, cols);

      
        int[][] start = cm.deepCopy(cm.mat.grid);

        System.out.println("=== Initial State ===");
        cm.mat.printmat(start);
        System.out.println("zeros = " + cm.countZeros(start));
        System.out.println();

        cm.addnode(start);

        boolean found = dfs(start);
        System.out.println(found ? " solution" : "No solution");
    }
private boolean dfs(int[][] start) {
        Deque<int[][]> stack = new ArrayDeque<>();
        stack.push(start);
       cm.parent.put(cm.signature(start), null);
        if (cm.mat.solvegride(start)) {
            cm.mat.printmat(start);
            return true;
        }

        int step = 0;
        int popCount = 0;

        while (!stack.isEmpty()) {
            int[][] current = stack.pop();
            popCount++;

            List<int[][]> children = cm.generateNextStates(current);

            System.out.println("--- POP :" + popCount
                    + " (zeros=" + cm.countZeros(current)
                    + ", children=" + children.size() + ") ---");
            cm.mat.printmat(current);
            System.out.println();

            for (int[][] next : children) {
                if (cm.addnode(next)) {
                    cm.parent.put(cm.signature(next), current);
                    step++;

                    System.out.println(">>> NEW State :" + step
                            + " (zeros=" + cm.countZeros(next) + ")");
                   cm.mat.printmat(next);

                   
                    for (int i = 0; i < next.length; i++) {
                        for (int j = 0; j < next[i].length; j++) {
                            if (current[i][j] != next[i][j]) {
                                System.out.println("    Changed: [" + i + "][" + j + "] "
                                        + current[i][j] + " → " + next[i][j]);
                            }
                        }
                    }
                    System.out.println();

                    if (cm.mat.solvegride(next)) {
                          
                        System.out.println(" Solved at state :" + step);
                        cm.mat.printmat(next);
                         cm.printPath(next);
                        return true;
                    }
                    
                    stack.push(next);
                }
            }
        }
        return false;
    }
  
}