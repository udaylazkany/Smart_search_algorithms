/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firststate;

import java.util.Scanner;

/**
 *
 * @author asus
 */
public class matrix {
    int[][] grid = {
    {1, 0, 0, 0, 1},
    {2, 0, 0, 0, 0},
    {3, 0, 0, 0, 0},
    {4, 0, 4, 3, 2},
    {5, 0, 0, 0, 5}
};
    Scanner in =new Scanner(System.in);
    int mat[][];
  public void addmat(){ System.out.print("Enter number of rows: ");
    int rows = in.nextInt();
       System.out.print("Enter number of columns: ");
        int cols = in.nextInt();
        if (rows <= 0 || cols <= 0) 
        {
            System.out.println("Invalid dimensions!)");
        }
         mat = new int[rows][cols];
        
        for(int row=0;row<mat.length;row++)
        {
            for(int col=0;col<mat.length;col++)
            {
                System.out.print("Enter the item ["+row+"]["+col+"]:");
                mat[row][col]=in.nextInt();
            }
        }
    }
  public void printmat(int[][] mat) {
    for (int row = 0; row < mat.length; row++) {
        for (int col = 0; col < mat[row].length; col++) {
            System.out.printf("%3d", mat[row][col]);
        }
        System.out.println();
    }
  }
  public boolean solvegride(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;

   
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (mat[i][j] == 0) {
                System.out.println(" Zero at [" + i + "][" + j + "]");
System.out.println("this not solve (zero at [" + i + "][" + j + "])");

                return false;
            }
          
        }
    }

   
  
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            int v = mat[i][j];
            boolean hasNeighbor = false;

            if (i - 1 >= 0 && mat[i-1][j] == v) hasNeighbor = true;
            else if (i + 1 < rows && mat[i+1][j] == v) hasNeighbor = true;
            else if (j - 1 >= 0 && mat[i][j-1] == v) hasNeighbor = true;
            else if (j + 1 < cols && mat[i][j+1] == v) hasNeighbor = true;

            if (!hasNeighbor) {System.out.println(" Number " + v + " at [" + i + "][" + j
                   + "] has no matching neighbor");
                return false;  
            }
        }
    }



    System.out.println(" this is solve");
    return true;   
}


}
