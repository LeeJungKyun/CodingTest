import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {
    static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    static char[][][] cube;
    static char[] colors = {'w', 'y', 'r', 'o', 'g', 'b'};

    public static void main(String[] args) throws IOException {
        Function<String, Integer> stoi = Integer::parseInt;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = stoi.apply(br.readLine());
        for (int t = 0; t < T; t++) {
            initCube();

            int N = stoi.apply(br.readLine());
            st = new StringTokenizer(br.readLine());

            while (st.hasMoreTokens()) {
                turn(st.nextToken());
            }

            printUp();
        }
    }

    static void initCube() {
        cube = new char[6][3][3];
        for (int i = 0; i < 6; i++) {
            for (int r = 0; r < 3; r++) {
                Arrays.fill(cube[i][r], colors[i]);
            }
        }
    }

    static void turn(String cmd) {
        char face = cmd.charAt(0);
        boolean clockwise = cmd.charAt(1) == '+';

        switch (face) {
            case 'U':
                alter(U, L, F, R, B, clockwise);
                break;
            case 'D':
                alter(D, B, R, F, L, clockwise);
                break;
            case 'F':
                alter(F, U, L, D, R, clockwise);
                break;
            case 'B':
                alter(B, R, D, L, U, clockwise);
                break;
            case 'L':
                alter(L, F, U, B, D, clockwise);
                break;
            case 'R':
                alter(R, D, B, U, F, clockwise);
                break;
        }
    }

    static void alter(int f, int u, int l, int d, int r, boolean clk) {
        char[][] tmpFace = new char[3][3];
        char[] tmp = new char[3];

        // 면 회전
        if (clk) {
            for (int i = 0; i < 3; ++i)
                for (int j = 0; j < 3; ++j)
                    tmpFace[i][j] = cube[f][2 - j][i];

            for (int i = 0; i < 3; i++) tmp[i] = cube[u][i][0];
            for (int i = 0; i < 3; i++) cube[u][i][0] = cube[l][0][2 - i];
            for (int i = 0; i < 3; i++) cube[l][0][2 - i] = cube[d][2][i];
            for (int i = 0; i < 3; i++) cube[d][2][i] = cube[r][2 - i][2];
            for (int i = 0; i < 3; i++) cube[r][2 - i][2] = tmp[i];
        } else {
            for (int i = 0; i < 3; ++i)
                for (int j = 0; j < 3; ++j)
                    tmpFace[i][j] = cube[f][j][2 - i];

            for (int i = 0; i < 3; i++) tmp[i] = cube[u][i][0];
            for (int i = 0; i < 3; i++) cube[u][i][0] = cube[r][2 - i][2];
            for (int i = 0; i < 3; i++) cube[r][2 - i][2] = cube[d][2][i];
            for (int i = 0; i < 3; i++) cube[d][2][i] = cube[l][0][2 - i];
            for (int i = 0; i < 3; i++) cube[l][0][2 - i] = tmp[i];
        }

        cube[f] = tmpFace;
    }

    static void printUp() {
        for (int i = 2; i >= 0; i--) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[U][j][i]);
            }
            System.out.println();
        }
    }
}
