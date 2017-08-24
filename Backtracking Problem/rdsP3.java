
import java.util.Scanner;

public class rdsP3 {

    public static int[][] sudoku = new int[9][9];
    public static int sols = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String buf = "";
        String[] bufSplt;

        for (int i = 0; i < 9; i++) {
            buf = sc.nextLine();
            bufSplt = buf.split(" ");
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(bufSplt[j]) - 1;
            }
        }

        backtracking(new Position(0, 0));
        System.out.println(sols);
    }

    public static boolean backtracking(Position pos) {
        boolean toRet = false;
        Position next = null; // Another position so recursive calls dont mess up

        if (pos != null) {
            // If it is not null, it clones initial pos for recursive callings proposes
            next = pos.clone();
        }

        if (pos == null) {
            sols++; // if pos is null, solution counter increments by one, since it means that 
            // the algorithm has reached the end of the matrix with valid values
            return true;

        } else if (sudoku[pos.getX()][pos.getY()] != -1) {
            return backtracking(next(next)); // if a position is already set up, it just calls the next one

        } else {
            for (int i = 0; i < 9; i++) { // 9 -> 0,1,2,3,4,5,6,7,8 posible values

                if (valid(i, pos)) {

                    sudoku[pos.getX()][pos.getY()] = i;
                    next = pos.clone();

                    if (backtracking(next(next))) {
                        toRet = true;
                    }

                    sudoku[pos.getX()][pos.getY()] = -1;

                } else {
                    sudoku[pos.getX()][pos.getY()] = -1;
                }
            }
        }
        return toRet;
    }

    public static Position next(Position pos) {
        int x, y;
        x = pos.getX();
        y = pos.getY();
        if (x < 8) {
            x++;
        } else if (y < 8) {
            x = 0;
            y++;
        } else {
            return null;
        }
        pos.setX(x);
        pos.setY(y);
        return pos;
    }

    public static boolean valid(int key, Position pos) {
        sudoku[pos.getX()][pos.getY()] = -1;
        boolean toRet = true;
        for (int j = 0; j < 9; j++) {
            if (sudoku[pos.getX()][j] == key) {
                toRet = false;
            }
            if (sudoku[j][pos.getY()] == key) {
                toRet = false;
            }
        }

        Position block = whichBlock(pos);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[i + block.getX()][j + block.getY()] == key) {
                    toRet = false;
                }
            }
        }
        sudoku[pos.getX()][pos.getY()] = key;
        return toRet;
    }

    public static void printSudoku() {
        String toPrint = "";
        int control = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                control = control + sudoku[i][j];
                toPrint = toPrint + (sudoku[i][j] + 1) + "  ";
            }
            toPrint = toPrint + "\n";
        }
        System.out.println(toPrint);
        System.out.println(control);
    }

    public static Position whichBlock(Position pos) {
        // by block i mean the 3x3 square, and the int
        // is for instants (0,0) = 0, (4,0) = 1, (0,4) = 3, (8,8) = 8
        int x, y;
        x = pos.getX();
        y = pos.getY();
        int xad = 0, yad = 0;
        if (x < 3) {
            if (y < 3) {
                // do nothing
            } else if (y < 6) {
                yad = 3;
            } else if (y < 9) {
                yad = 6;
            }
        } else if (x < 6) {
            if (y < 3) {
                xad = 3;
            } else if (y < 6) {
                xad = 3;
                yad = 3;
            } else if (y < 9) {
                xad = 3;
                yad = 6;
            }
        } else if (x < 9) {
            if (y < 3) {
                xad = 6;
            } else if (y < 6) {
                xad = 6;
                yad = 3;
            } else if (y < 9) {
                xad = 6;
                yad = 6;
            }
        }
        return new Position(xad, yad);

    }

    static class Position {

        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }

        @Override
        public Position clone() {
            if (this != null) {
                Position newp = new Position(this.getX(), this.getY());
                return newp;
            } else {
                return null;
            }
        }
    }
}
