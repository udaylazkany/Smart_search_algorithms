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
public class humainplayer {
    Scanner in = new Scanner(System.in);
        matrix mat=new matrix();
        copymatrix cm=new copymatrix();

    public humainplayer() {
    
        cm.addnode(mat.grid);
        //mat.addmat();
       boolean solve=false;
        while (!solve) {    
            
          mat.printmat(mat.grid);  
        System.out.println("enter the row number");
       int x=in.nextInt();
        System.out.println("Enter the coloum number");
        int y=in.nextInt();
        System.out.println("Enetr the move char");
        char z=in.next().charAt(0);
        int[][] before=cm.deepCopy(mat.grid);
        move mov=new move();
        mov.direction(mat.grid, x, y, z);
        if(!cm.addnode(mat.grid))
        {
            System.out.println("this move is befor");
            mat.grid=before;
            continue;
        }
         if(mat.solvegride(mat.grid))
        {mat.printmat(mat.grid);
        solve=true;
        }else
        {
            System.out.println("not solved");}
        
        }
        
    }
        
    
}
