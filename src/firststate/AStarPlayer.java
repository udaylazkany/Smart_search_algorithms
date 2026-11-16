/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firststate;

import java.util.*;

public class AStarPlayer {

    private final matrix mat;
    private final copymatrix cm;
   
  
    private final HashMap<String, Integer> gCost = new HashMap<>();
    private final int cols;
  
    private final HashSet<String> closed = new HashSet<>();
  
    private static class Node {
        int[][] state;
        int g;   
        int f;   

        Node(int[][] s, int g, int f) {
            this.state = s;
            this.g = g;
            this.f = f;
        }
    }

    public AStarPlayer() {
        mat = new matrix();
        cm = new copymatrix();
     

        cols = mat.grid[0].length;

        int[][] start = cm.deepCopy(mat.grid);

        System.out.println("=== Initial State ===");
        mat.printmat(start);
        System.out.println("zeros = " + cm.countZeros(start));
        System.out.println();

      

        boolean found = aStar(start);
        System.out.println(found ? " Solution" : " No solution");
    }

    // ================= A* =================
private boolean aStar(int[][] start) {
    PriorityQueue<Node> open = new PriorityQueue<>(
        Comparator.comparingInt(n -> n.f)
    );

    String startSig = cm.signature(start);
    cm.parent.put(startSig, null);
    gCost.put(startSig, 0);
    open.offer(new Node(start, 0, cm.countZeros(start)));

    int step = 0;
    int popped = 0;

    while (!open.isEmpty()) {
        Node current = open.poll();
        String curSig = cm.signature(current.state);

        if (closed.contains(curSig)) continue;
        closed.add(curSig);

        popped++;
        System.out.println("--- POP #" + popped
                + " (g=" + current.g
                + ", h=" + cm.countZeros(current.state)
                + ", f=" + current.f + ") ---");

      
        if (mat.solvegride(current.state)) {
            System.out.println(" Solved at pop #" + popped
                    + " (g=" + current.g + ")");
            mat.printmat(current.state);
            cm.printPath(current.state);
            return true;
        }

        for (int[][] next : cm.generateNextStates(current.state)) {
            String nextSig = cm.signature(next);
            int newG = current.g + 1;
            int newH = cm.countZeros(next);
            int newF = newG + newH;

            if (newG < gCost.getOrDefault(nextSig, Integer.MAX_VALUE)) {
                gCost.put(nextSig, newG);
                cm.parent.put(nextSig, current.state);
                closed.remove(nextSig);

                step++;
                System.out.println(">>> NEW State #" + step
                        + " (g=" + newG + ", h=" + newH + ", f=" + newF + ")");
                mat.printmat(next);
                System.out.println();

                open.offer(new Node(next, newG, newF));   
            }
        }
    }
    return false;
}}