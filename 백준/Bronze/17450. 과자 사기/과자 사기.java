import java.util.Scanner;
 
public class Main {
    public static void main(String args[]) {
        
        Scanner sc = new Scanner(System.in);
        
        double bestBangforTheBuck = 0;
        String snack = "";
        
        for (int i = 1; i <= 3; i++) {
            double price = sc.nextDouble();
            double weight = sc.nextDouble();
            
            double bangForTheBuck = 0;
            
            if (price * 10 >= 5000) {
                bangForTheBuck = (weight * 10) / (price * 10 - 500);
            }
            else {
                bangForTheBuck = (weight * 10) / (price * 10 );
            }
            
            if (bestBangforTheBuck < bangForTheBuck) {
                bestBangforTheBuck = bangForTheBuck;
                if (i == 1) snack = "S";
                else if (i == 2) snack = "N";
                else if (i == 3) snack = "U";
            }
        }
        
        System.out.print(snack);
 
    }
}