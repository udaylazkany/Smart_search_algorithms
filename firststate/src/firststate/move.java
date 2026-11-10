    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.tmatt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package firststate;

import java.util.HashMap;
import java.util.HashSet;

    /**
     *
     * @author asus
     */
    public class move {

        public HashMap<Integer,int[]>findpair(int [][]mat)
        {
            HashMap<Integer,int[]> map=new HashMap<>();
            for(int i=0;i<mat.length;i++)
                for(int j=0;j<mat[i].length;j++)
                {
                int v=mat[i][j];
                if(v==0)
                {
                continue;}
                int[] p=map.get(v);
                if(p==null)
                {
                    map.put(v, new int[]{i,j,i,j});
                }else
                {p[2]=i;
                p[3]=j;
                }
                }
        return map;
        }
        public HashSet<Integer> protectedcellset(HashMap<Integer,int[]>fixed,int cols)
        {HashSet<Integer> set =new HashSet<>();
        for(int[] p:fixed.values())
        {
            set.add(p[0]*cols+p[1]);
            set.add(p[2]*cols+p[3]);
        }
        return set;
        }
        public boolean moveup(int mat[][], int row, int col) {
            if (row <= 0) return false;

            mat[row-1][col] = mat[row][col];
            return true;
        }

        public boolean movedown(int mat[][], int row, int col) {
            if (row >= mat.length - 1) return false;
            mat[row+1][col] = mat[row][col];
            return true;
        }

        public boolean moveright(int mat[][], int row, int col) {
            if (col >= mat[row].length - 1) return false;
            mat[row][col+1] = mat[row][col];
            return true;
        }

        public boolean moveleft(int mat[][], int row, int col) {
            if (col <= 0) return false;
            mat[row][col-1] = mat[row][col];
            return true;
        }

        public boolean direction(int mat[][], int row, int col, char dir) {
            return switch (Character.toLowerCase(dir)) {
                case 'w' -> moveup(mat, row, col);
                case 's' -> movedown(mat, row, col);
                case 'a' -> moveleft(mat, row, col);
                case 'd' -> moveright(mat, row, col);
                default -> false;
            };
        }
    }
