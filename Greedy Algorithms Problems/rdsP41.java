import java.util.ArrayList;
import java.util.Scanner;
    
public class rdsP41{
    static int howManyTasks = 0;
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // Exer 4.1
        int tasks = Integer.parseInt(scanner.nextLine());
        String[] startTimes = scanner.nextLine().split(" ");
        String[] endTimes = scanner.nextLine().split(" ");
        
        ArrayList<int[]> taskss = new ArrayList<int[]>();
        for (int i = 0; i < tasks; i++) {
            int[] toAd = {Integer.parseInt(startTimes[i]), Integer.parseInt(endTimes[i])};
            taskss.add(toAd);
        }
        
        greedyAlg(0, taskss);
        
        System.out.println(howManyTasks);
        
    }
    
    public static void greedyAlg(int time, ArrayList<int[]> tasksa){
        ArrayList<int[]> tasks = dup(tasksa);
        if(anyValid(time, tasks)){
            int[] earlier = earlier(tasks, time);
            tasks.remove(earlier);
            howManyTasks++;
            time = earlier[1];
            greedyAlg(time, tasks);
        }
    }
    
    public static int[] earlier(ArrayList<int[]> tasks, int time){
        int[] task = {-1, -1};
        for (int i = 0; i < tasks.size(); i++) {
            if(valid(tasks.get(i), time)){
                if(task[1] == -1){
                    task = tasks.get(i);
                }else if(tasks.get(i)[1] < task[1]){
                    task = tasks.get(i);
                }
            }
        }
        return task;
    }
    
    public static boolean valid(int[] task, int time){
        return task[0] >= time;
    }
    
    public static boolean anyValid(int time, ArrayList<int[]> tasks){
        boolean toRet = false;
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i)[0] >= time){
                toRet = true;
            }
        }
        return toRet;   
    }

    public static ArrayList<int[]> dup(ArrayList<int[]> in){
        ArrayList<int[]> out = new ArrayList<int[]>();
        for (int[] string : in) {
            out.add(0, string);
        }
        return out;
    }
}
