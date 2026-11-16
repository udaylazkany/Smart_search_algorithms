    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.tmatt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package firststate;


    /**
     *
     * @author asus
     */
    public class move {

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
