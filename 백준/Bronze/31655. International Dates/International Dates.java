import java.util.Scanner;

public class Main {
    public static String determineDateFormat(String date) {
        String[] parts = date.split("/");
        int AA = Integer.parseInt(parts[0]);
        int BB = Integer.parseInt(parts[1]);
        int CCCC = Integer.parseInt(parts[2]);

        if (AA > 12) {
            return "EU";
        } else if (BB > 12) {
            return "US";
        } else {
            return "either";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        System.out.println(determineDateFormat(date));
        sc.close();
    }
}