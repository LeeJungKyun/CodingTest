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
    
    static int n, m;
    static int[][] arr;
    static int[][] group; // 0인 칸의 그룹 ID를 저장할 배열
    static Map<Integer, Integer> groupSizeMap; // 그룹 ID와 크기를 매핑
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        arr = new int[n][m];
        group = new int[n][m];
        groupSizeMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        
        int groupId = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0 && group[i][j] == 0) {
                    findGroupAndCount(i, j, groupId);
                    groupId++;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    int totalCount = 1;
                    Set<Integer> adjacentGroups = new HashSet<>();
                    for (int dir = 0; dir < 4; dir++) {
                        int nx = i + dx[dir];
                        int ny = j + dy[dir];
                        
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 0) {
                            adjacentGroups.add(group[nx][ny]);
                        }
                    }
                    for (int id : adjacentGroups) {
                        totalCount += groupSizeMap.get(id);
                    }
                    sb.append(totalCount % 10);
                } else {
                    sb.append(0); // 0인 칸은 0을 출력
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    
    public static void findGroupAndCount(int x, int y, int groupId) {
        int count = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        group[x][y] = groupId;
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            count++;
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && arr[nx][ny] == 0 && group[nx][ny] == 0) {
                    group[nx][ny] = groupId;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        groupSizeMap.put(groupId, count);
    }
}