import java.util.Scanner;

public class ejer2 {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        int n = s1.nextInt();
        System.out.println(metodo(n));
    }

    private static int metodo(int n) {
        if(n<10){
            return 1;
        }else{
            return ((1) + (metodo(n/10))); 
        }
    }
}