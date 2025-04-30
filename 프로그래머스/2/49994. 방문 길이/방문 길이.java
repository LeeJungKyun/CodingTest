import java.util.*;

class Solution {

    static class Point {
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 1, 0, 0}; // U, D, R, L
    static int[] dy = {0, 0, 1, -1};
    static Map<Character, Integer> dirMap = Map.of('U', 0, 'D', 1, 'R', 2, 'L', 3);

    public static int solution(String dirs) {
        int answer = 0;
        Set<String> visitedPath = new HashSet<>();

        Point cur = new Point(5, 5);

        for (char cmd : dirs.toCharArray()) {
            int d = dirMap.get(cmd);
            int nx = cur.x + dx[d];
            int ny = cur.y + dy[d];

            if (nx < 0 || nx > 10 || ny < 0 || ny > 10)
                continue;

            String path1 = cur.x + "," + cur.y + "," + nx + "," + ny;
            String path2 = nx + "," + ny + "," + cur.x + "," + cur.y;

            // 경로가 처음이면 정답 증가
            if (!visitedPath.contains(path1) && !visitedPath.contains(path2)) {
                visitedPath.add(path1);
                visitedPath.add(path2);
                answer++;
            }

            // 현재 좌표 갱신
            cur.x = nx;
            cur.y = ny;
        }

        return answer;
    }
}