import java.io.*;
import java.util.*;

public class Main {
    static class Seat implements Comparable<Seat> {
        int x, y, studentSum, emptySum;

        public Seat(int x, int y, int studentSum, int emptySum) {
            this.x = x;
            this.y = y;
            this.studentSum = studentSum;
            this.emptySum = emptySum;
        }

        @Override
        public int compareTo(Seat other) {
            //인접 좋아하는 학생 수 비교
            if (studentSum != other.studentSum)
                return other.studentSum - this.studentSum;

            //인접 빈칸 수 비교
            if (emptySum != other.emptySum)
                return other.emptySum - this.emptySum;

            //행 비교
            if (x != other.x)
                return this.x - other.x;

            return this.y - other.y;
        }
    }

    static int n, sum;
    static int[] students;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static Map<Integer, Set<Integer>> preferences;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        students = new int[n * n];
        preferences = new HashMap<>();

        //좋아하는 학생 입력받기
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            students[i] = student;
            preferences.put(student, new HashSet<>());
            for (int j = 0; j < 4; j++) {
                preferences.get(student).add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < students.length; i++) {
            Seat seat = findSeat(students[i]);
            map[seat.x][seat.y] = students[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int count = getStudentSum(i, j, map[i][j]);
                if(count > 0)
                    sum += (int) Math.pow(10, count - 1);
            }
        }
        System.out.println(sum);
    }

    public static Seat findSeat(int student) {
        Seat seat = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //자리에 앉아있는 경우
                if(map[i][j] > 0)
                    continue;

                Seat cur = new Seat(i, j, getStudentSum(i, j, student), getEmptySum(i, j));
                if(seat == null){
                    seat = cur;
                    continue;
                }

                if (seat.compareTo(cur) > 0) {
                    seat = cur;
                }
            }
        }
        return seat;
    }

    public static int getStudentSum(int x, int y, int student) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }
            if (preferences.get(student).contains(map[nx][ny])) {
                count++;
            }
        }
        return count;
    }

    public static int getEmptySum(int x, int y) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }
            if (map[nx][ny] == 0) {
                count++;
            }
        }
        return count;
    }
}
