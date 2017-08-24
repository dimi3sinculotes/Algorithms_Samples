import java.util.Scanner;

public class ejer1 {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        int n = s1.nextInt();
        System.out.println(metodo(n, 1));
    }

    private static int metodo(int n, int i) {
        if(i>n){
            return 0;
        }else if(i==n){
            return(2*i - 1); 
        }else{
            return ((2*i - 1) + metodo(n, i+1));
        }
    }
}
