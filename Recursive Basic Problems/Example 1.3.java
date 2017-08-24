import java.util.ArrayList;
import java.util.Scanner;

public class ejer3 {

    
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        double resultado, x;
        ArrayList<Double> d = new ArrayList<Double>();
        
        int n = s1.nextInt();
        s1.nextLine(); // .nextInt() and .nextDouble() does not read the \n

        for (int i = 0; i <= n; i++) {
            d.add(0, s1.nextDouble()); //ading at the first position in order to maintain the order
        }
        s1.nextLine(); // .nextInt() and .nextDouble() does not read the \n
        
        x = s1.nextDouble();
        s1.nextLine(); // .nextInt() and .nextDouble() does not read the \n

        resultado = metodo(d, 0, n, x);

        System.out.format("%.3f%n", resultado); // number formated to X.YYY
    }

    private static double metodo(ArrayList<Double> d, int ini, int fin, double x) {
        if(ini == fin){
            return d.get(fin);
        }else{
            return (d.get(ini) + (x * metodo(d, ini + 1, fin, x))); 
        }
    }
}