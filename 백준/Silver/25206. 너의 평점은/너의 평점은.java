import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static double sum = 0;
    static double credit_sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 20; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            double credit = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();
            if (grade.equals("P")) {
                continue;
            }
            else if (grade.equals("A+")) {
                sum += credit * 4.5;
                credit_sum += credit;
            } else if (grade.equals("A0")) {
                sum += credit * 4.0;
                credit_sum += credit;
            }
            else if (grade.equals("B+")) {
                sum += credit * 3.5;
                credit_sum += credit;
            }
            else if (grade.equals("B0")) {
                sum += credit * 3.0;
                credit_sum += credit;
            }
            else if (grade.equals("C+")) {
                sum += credit * 2.5;
                credit_sum += credit;
            }
            else if (grade.equals("C0")) {
                sum += credit * 2.0;
                credit_sum += credit;
            }
            else if (grade.equals("D+")) {
                sum += credit * 1.5;
                credit_sum += credit;
            }
            else if (grade.equals("D0")) {
                sum += credit * 1;
                credit_sum += credit;
            }
            else if (grade.equals("F")) {
                sum += 0;
                credit_sum += credit;
            }
        }
        System.out.println(sum/credit_sum);
    }
}
