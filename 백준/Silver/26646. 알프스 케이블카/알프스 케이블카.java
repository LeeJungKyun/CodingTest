import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        new Main().solution();
    }

    private void solution() throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = Integer.parseInt(br.readLine());
        int[] wire = new int[count];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < count; i++) {
            wire[i] = Integer.parseInt(input[i]);
        }

        long result = 0;
        for (int i = 0; i < wire.length - 1; i++) {
            result += (Math.pow(wire[i], 2) * 2) + (Math.pow(wire[i + 1], 2) * 2);
        }

        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}