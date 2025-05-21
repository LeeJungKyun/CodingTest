import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int r, c, t;
    static int[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<Point> cleaner;
    static Point upPoint, downPoint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        arr = new int[r][c];
        cleaner = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    //공기청정기 좌표 저장
                    cleaner.add(new Point(i, j));
                }
            }
        }

        upPoint = cleaner.get(0);
        upPoint.y++;

        downPoint = cleaner.get(1);
        downPoint.y++;

        while (t-- > 0) {
            expand();
            rotate();
        }

        System.out.println(countDust());
    }

    // 함수

    //미세먼지 확산 함수
    public static void expand() {
        int[][] increasing = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (arr[i][j] > 0) {
                    int amount = arr[i][j] / 5;
                    int count = 0;

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];

                        if (nx >= 0 && nx < r && ny >= 0 && ny < c && arr[nx][ny] != -1) {
                            increasing[nx][ny] += amount;
                            count++;
                        }
                    }
                    arr[i][j] -= amount * count;
                }
            }
        }

        // 확산 결과 반영
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                arr[i][j] += increasing[i][j];
            }
        }
    }

    //공기청정기 순환 함수
    public static void rotate() {
        // 윗쪽 반시계 회전
        int top = cleaner.get(0).x;
        for (int i = top - 1; i > 0; i--)
            arr[i][0] = arr[i - 1][0];
        for (int i = 0; i < c - 1; i++)
            arr[0][i] = arr[0][i + 1];
        for (int i = 0; i < top; i++)
            arr[i][c - 1] = arr[i + 1][c - 1];
        for (int i = c - 1; i > 1; i--)
            arr[top][i] = arr[top][i - 1];

        // 공기청정기 바로 오른쪽 칸은 0
        arr[top][1] = 0;

        // 아랫쪽 시계 회전
        int bottom = cleaner.get(1).x;
        for (int i = bottom + 1; i < r - 1; i++)
            arr[i][0] = arr[i + 1][0];
        for (int i = 0; i < c - 1; i++)
            arr[r - 1][i] = arr[r - 1][i + 1];
        for (int i = r - 1; i > bottom; i--)
            arr[i][c - 1] = arr[i - 1][c - 1];
        for (int i = c - 1; i > 1; i--)
            arr[bottom][i] = arr[bottom][i - 1];

        // 공기청정기 바로 오른쪽 칸은 0
        arr[bottom][1] = 0;

        // 공기청정기 위치 복원
        arr[top][0] = -1;
        arr[bottom][0] = -1;
    }

    public static int countDust() {
        int sum = 0;
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                sum += arr[i][j];

        return sum + 2;
    }

    public static void printArr() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
