import java.io.*;
import java.util.*;

public class Main {
	static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
    }

    static int r, c;
    static char[][] arr;
    static char[] pipes = {'|', '-', '+', '1', '2', '3', '4'};
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Map<Character, int[]> pipeDirs = new HashMap<>();
    static Point start, dest;

    static BufferedReader br;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        pipeDirs.put('|', new int[]{UP, DOWN});
        pipeDirs.put('-', new int[]{LEFT, RIGHT});
        pipeDirs.put('+', new int[]{UP, DOWN, LEFT, RIGHT});
        pipeDirs.put('M', new int[]{UP, DOWN, LEFT, RIGHT});
        pipeDirs.put('Z', new int[]{UP, DOWN, LEFT, RIGHT});
        pipeDirs.put('1', new int[]{DOWN, RIGHT});
        pipeDirs.put('2', new int[]{UP, RIGHT});
        pipeDirs.put('3', new int[]{LEFT, UP});
        pipeDirs.put('4', new int[]{LEFT, DOWN});


        arr = new char[r][c];
        for(int i = 0; i < r; i++) {
            String line = br.readLine();
            for(int j = 0; j < c; j++) {
                arr[i][j] = line.charAt(j);
                if(arr[i][j] == 'M') start = new Point(i, j);
                else if(arr[i][j] == 'Z') dest = new Point(i, j);
            }
        }

        //후보 좌표 확인하기
        //하나라도 파이프가 있으면 후보군에 들어감
        Set<Point> candidatePointList = new HashSet<>();
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(arr[i][j] == '.') {
                    for(int dir = 0; dir < 4; dir++) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];

                        if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                        if(arr[nx][ny] != '.') {
                            if(!candidatePointList.contains(new Point(i, j))){
                                candidatePointList.add(new Point(i, j));
                                break;
                            }
                        }
                    }
                }
            }
        }

        //해당 좌표에 바꿔서 넣어서 M에서 Z까지 연결되어있는지, 그리고 모든 파이프가 정상적으로 연결되어있는지 확인하면 됨
        for(Point point : candidatePointList) {
            for(char pipe : pipes) {
                char origin = arr[point.x][point.y];

                //파이프 갈아끼기
                arr[point.x][point.y] = pipe;

                if(canReach() && allConnected()) {
//                	for(int i = 0; i < r; i++) {
//                		for(int j = 0; j < c; j++) {
//                			System.out.print(arr[i][j] + " ");
//                		}
//                		System.out.println();
//                	}
                    System.out.printf("%d %d %c\n", point.x + 1, point.y + 1, pipe);
                    return;
                }


                //원복
                arr[point.x][point.y] = origin;
            }
        }
    }


    //M부터 Z까지 이동 가능함?
    //연결되어있고 나발이고 그냥 갈 수 있는지
    public static boolean canReach() {
        boolean[][] visited = new boolean[r][c];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            char cell = arr[cur.x][cur.y];

            if (cell == 'Z') return true;

            if (!pipeDirs.containsKey(cell)) continue; // 파이프 아니면 패스

            for (int dir : pipeDirs.get(cell)) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if (arr[nx][ny] == '.') continue;
                if (!isConnected(dir, arr[nx][ny])) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
        return false;
    }

    //이제 도달은 되니깐 다 정상적으로 연결되어야함 .이든 아니든 일단 다 큐에 넣어봐
    public static boolean allConnected() {
        boolean[][] visited = new boolean[r][c];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            char cell = arr[cur.x][cur.y];

            // M, Z 처리
            if (cell == 'M' || cell == 'Z') {
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                    if (arr[nx][ny] == '.') continue;
                    if (!isConnected(dir, arr[nx][ny])) continue;
                    if (visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
                continue;
            }

            // 빈칸이면 잘못 연결
            if (cell == '.') return false;

            // 일반 파이프 처리
            if (!pipeDirs.containsKey(cell)) continue;

            for (int dir : pipeDirs.get(cell)) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                
                if (nx < 0 || nx >= r || ny < 0 || ny >= c) return false;
                if (!isConnected(dir, arr[nx][ny])) return false;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
        }
        return true;
    }

    public static boolean isConnected(int fromDir, char nextPipe) {
        if (nextPipe == '.' || !pipeDirs.containsKey(nextPipe)) return false;

        int oppositeDir = reverseDir(fromDir);

        for (int d : pipeDirs.get(nextPipe)) {
            if (d == oppositeDir) return true;
        }
        return false;
    }

    static int reverseDir(int dir) {
        if (dir == UP) return DOWN;
        if (dir == DOWN) return UP;
        if (dir == LEFT) return RIGHT;
        if (dir == RIGHT) return LEFT;
        return -1;
    }
}