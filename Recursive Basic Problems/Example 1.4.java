import java.util.Scanner;

public class ejer4 {
    public static void main(String[] args){
        Scanner s1 = new Scanner(System.in);
        int n = s1.nextInt();
        System.out.println(metodo(n));
    }

    private static int metodo(int n) {
        if(n<3){
            return 1;
        }else{
            return (1 + (bucle(n))); 
        }
    }
    
    private static int bucle(int n){
        int resul = 0;
        for(int i = 1; i <= n-2; i++){
            resul += metodo(i);
        }
        return resul;
    }
}