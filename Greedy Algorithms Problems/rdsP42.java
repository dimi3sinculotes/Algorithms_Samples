import java.util.ArrayList;
import java.util.Scanner;

public class rdsP42 {
   
    static int t = 0;
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // Exer 4.2
        int sublists = Integer.parseInt(scanner.nextLine());
        String[] input = scanner.nextLine().split(" ");
        ArrayList<String> lengths = new ArrayList<String>();
        
        for (int i = 0; i < sublists; i++) {
            lengths.add(0, input[i]);
        }
        
        greedyAlg(sublists, lengths);
        
        System.out.println(t);
        
    }
    
    public static void greedyAlg(int sublists, ArrayList<String> lengthsa){
        ArrayList<String> lengths = dup(lengthsa);
        if(sublists > 1){
            String[] smaller = smaller(lengths);
            String sum = String.valueOf(Integer.parseInt(smaller[0]) + Integer.parseInt(smaller[1]));
            
            lengths.remove(smaller[0]);
            lengths.remove(smaller[1]);
            
            sublists--;
            
            lengths.add(sum);
            
            t = t + Integer.parseInt(sum);
            
            greedyAlg(sublists, lengths);
        }
        // else end
    }
    
    public static String[] smaller(ArrayList<String> l){
        int[] aux = {Integer.parseInt(l.get(0)), Integer.parseInt(l.get(1))};
        for (int i = 2; i < l.size(); i++) {
            if(aux[1] < aux[0]){
                int chng = aux[0];
                aux[0] = aux[1];
                aux[1] = chng;
            }
            if(Integer.parseInt(l.get(i)) < aux[1]){
                aux[1] = Integer.parseInt(l.get(i));
            }else if(Integer.parseInt(l.get(i)) < aux[0]){
                aux[0] = Integer.parseInt(l.get(i));
            }
        }
        String[] toRet = {String.valueOf(aux[0]), String.valueOf(aux[1])};
        return toRet;
    }
    
    public static ArrayList<String> dup(ArrayList<String> in){
        ArrayList<String> out = new ArrayList<String>();
        for (String string : in) {
            out.add(0, string);
        }
        return out;
    }
}
