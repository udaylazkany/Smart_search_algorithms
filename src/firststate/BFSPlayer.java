/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firststate;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class BFSPlayer {


private final copymatrix cm;


private final int cols;


    public BFSPlayer() {
       
        
        cm = new copymatrix();
      

        cols = cm.mat.grid[0].length;
      
        int[][] start = cm.deepCopy(cm.mat.grid);

        System.out.println("=== Initial State ===");
        cm.mat.printmat(start);
        System.out.println("zeros = " + cm.countZeros(start));
        System.out.println();

        cm.addnode(start);

        boolean found = bfs(start);
        System.out.println(found ? " solution" : "No solution");
    }


    private boolean bfs(int[][] start) {
   
        Deque<int[][]> queue = new ArrayDeque<>();
        queue.offer(start);                      
        cm.parent.put(cm.signature(start), null);

        if (cm.mat.solvegride(start)) {
            cm.mat.printmat(start);
            return true;
        }

        int step = 0;
        int popCount = 0;

        while (!queue.isEmpty()) {
            int[][] current = queue.poll();       
            popCount++;

            List<int[][]> children = cm.generateNextStates(current);

            System.out.println("--- POLL #" + popCount
                    + " (zeros=" + cm.countZeros(current)
                    + ", children=" + children.size() + ") ---");
            cm.mat.printmat(current);
            System.out.println();

            for (int[][] next : children) {
                if (cm.addnode(next)) {
                    cm.parent.put(cm.signature(next), current);
                    step++;

                    System.out.println(">>> NEW State #" + step
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
                        System.out.println(" Solved at state #" + step);
                        cm.mat.printmat(next);
                        cm.printPath(next);
                        return true;
                    }

                    queue.offer(next);            
                }
            }
        }
        return false;
    }
}