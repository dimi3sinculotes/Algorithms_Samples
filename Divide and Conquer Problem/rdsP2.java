
import java.util.ArrayList;
import java.util.Scanner;

public class rdsP2 {

    public static void main(String[] args) {
        // The input will be sotored in a 3xn matrix [(x1,x2,h), (x1,x2,h)...
        // And there will be another matrix for the output [(x, maxH), (x, maxH)...]

        Scanner scanner = new Scanner(System.in);
        String buffer = "";
        int numEntries = Integer.parseInt(scanner.nextLine());
        int[][] inputMatrix = new int[numEntries][3];
        String[] buffer2;

        for (int i = 0; i < numEntries; i++) {
            buffer = scanner.nextLine();
            buffer2 = buffer.split(" ");
            for (int j = 0; j < 3; j++) {
                inputMatrix[i][j] = Integer.parseInt(buffer2[j]);
            }
        }
        printOutput(mainDyV(inputMatrix, 0, inputMatrix.length - 1));
    }

    public static void printOutput(ArrayList<int[]> output) {
        String toPrint = "";
        for (int i = 0; i < output.size() - 1; i++) {
            toPrint = toPrint + output.get(i)[0] + " " + output.get(i)[1] + "\n";
        }
        toPrint = toPrint + output.get(output.size() - 1)[0] + " " + output.get(output.size() - 1)[1];
        System.out.println(toPrint);
    }

    private static ArrayList<int[]> mainDyV(int[][] v, int inf, int sup) {
        if (inf == sup) { // If there is only one element
            ArrayList<int[]> toRet = new ArrayList<int[]>();
            toRet.add(new int[]{v[inf][0], v[inf][2]});
            toRet.add(new int []{v[inf][1], 0});
            // Returns an Building 
            //      |----|      {[start][height]
            //   ___|    |____  ,[end][h = 0]}
            return toRet;
        } else {
            // Recursive case
            // middle point
            int medio = (inf) + (sup - inf) / 2; // For instants, between 1 and 7 the middle is 1 + ((7-1) / 2) = 1 + 3 = 4
            // mixes the left hand half with the right hand side one
            return mix(mainDyV(v, inf, medio), mainDyV(v, medio + 1, sup));
        }
    }

    private static ArrayList<int[]> mix(ArrayList<int[]> matrix1, ArrayList<int[]> matrix2) {
        ArrayList<int[]> toRet = new ArrayList<int[]>();
        int h1, h2, hmax, pos;
        h1 = 0;
        h2 = 0;

        while ((matrix1.size() > 0) && (matrix2.size() > 0)) {
            hmax = 0;
            pos = 0;
            if (matrix1.get(0)[0] == matrix2.get(0)[0]) {
                // I save the heights in their vars
                h1 = matrix1.get(0)[1];
                h2 = matrix2.get(0)[1];
                // I find the maximum one
                hmax = Math.max(h1, h2);
                // Finally i save the initial position and erase
                // the first nodes sinces i wont use em any more
                pos = matrix1.get(0)[0];
                
                matrix1.remove(0);
                matrix2.remove(0);
                
                // will do almost the same on the rest of the elif
                
            } else if (matrix1.get(0)[0] < matrix2.get(0)[0]) {
                h1 = matrix1.get(0)[1];
                hmax = Math.max(h1, h2);
                pos = matrix1.get(0)[0];
                matrix1.remove(0);
                
                
            } else { // >
                h2 = matrix2.get(0)[1];
                hmax = Math.max(h1, h2);
                pos = matrix2.get(0)[0];
                matrix2.remove(0);
            }
            int[] aux = new int[]{pos, hmax};
            if((toRet.size() == 0) || (hmax != toRet.get(toRet.size() - 1)[1])){
                // if the height its not the same or the arrayList is empty
                toRet.add(aux);         
            }
        }
        // Adds the rest of the buildings that are not even, so they are actually a change of height
        toRet.addAll(matrix1);
        toRet.addAll(matrix2);
        return toRet;
    }
}
