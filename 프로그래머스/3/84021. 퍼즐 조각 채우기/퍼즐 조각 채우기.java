import java.util.*;

class Solution {
    class Point {
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    List<List<Point>> fragmentList = new ArrayList<>();
    List<List<Point>> emptySpaceList = new ArrayList<>();
    
    boolean[] usedFragment; 
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        
        findFragment(table);
        findEmptyBoard(game_board);
        
        usedFragment = new boolean[fragmentList.size()];
        
        for (List<Point> emptySpace : emptySpaceList) {
            int emptySize = emptySpace.size();
            
            for (int i = 0; i < fragmentList.size(); i++) {
                if (usedFragment[i]) continue;
                
                List<Point> fragment = fragmentList.get(i);
                int fragmentSize = fragment.size();

                if (emptySize != fragmentSize) continue;
                
                if (fragment.equals(emptySpace)) {
                    answer += fragmentSize;
                    int fragmentIndex = i / 4;
                    
                    for (int k = 0; k < 4; k++) {
                        usedFragment[fragmentIndex * 4 + k] = true;
                    }
                    
                    break; 
                }
            }
        }
        
        return answer;
    }
    
    public List<Point> rotate(List<Point> piece) {
        List<Point> rotatedList = new ArrayList<>();
        
        for (Point p : piece) {
            rotatedList.add(new Point(p.y, -p.x));
        }

        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;

        for (Point p : rotatedList) {
            minRow = Math.min(p.x, minRow);
            minCol = Math.min(p.y, minCol);
        }
        
        for (Point p : rotatedList) {
            p.x -= minRow;
            p.y -= minCol;
        }

        Collections.sort(rotatedList, (p1, p2) -> {
            if (p1.x != p2.x) {
                return Integer.compare(p1.x, p2.x);
            }
            return Integer.compare(p1.y, p2.y);
        });

        return rotatedList;
    }
    
    public void findFragment(int[][] table){
        int size = table.length;
        boolean[][] visited = new boolean[size][size];
        
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                if(!visited[row][col] && table[row][col] == 1) {
                    List<Point> tempList = new ArrayList<>();
                    Queue<Point> queue = new ArrayDeque<>();
                    
                    queue.add(new Point(row, col));
                    visited[row][col] = true;
                    
                    int minRow = row;
                    int minCol = col;
                    
                    while(!queue.isEmpty()){
                        Point cur = queue.poll();
                        tempList.add(cur);
                        
                        minRow = Math.min(cur.x, minRow);
                        minCol = Math.min(cur.y, minCol);
                        
                        for(int dir = 0; dir < 4; dir++){
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];
                            
                            if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
                            if(table[nx][ny] == 0 || visited[nx][ny]) continue;
                            
                            queue.add(new Point(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }
                    
                    for(Point point : tempList){
                        point.x -= minRow;
                        point.y -= minCol;
                    }
                    
                    Collections.sort(tempList, (p1, p2) -> {
                        if (p1.x != p2.x) {
                            return Integer.compare(p1.x, p2.x);
                        }
                        return Integer.compare(p1.y, p2.y);
                    });
                    
                    fragmentList.add(tempList); 
                    
                    List<Point> currentPiece = new ArrayList<>();
                    for (Point p : tempList) {
                        currentPiece.add(new Point(p.x, p.y)); 
                    }
                    
                    for (int i = 0; i < 3; i++) {
                        List<Point> nextPiece = rotate(currentPiece);
                        fragmentList.add(nextPiece);
                        currentPiece = nextPiece;
                    }
                }        
            }
        }
    }

    public void findEmptyBoard(int[][] game_board){
        int size = game_board.length;
        boolean[][] visited = new boolean[size][size]; 
        
        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                if(!visited[row][col] && game_board[row][col] == 0) {
                    List<Point> tempList = new ArrayList<>();
                    Queue<Point> queue = new ArrayDeque<>();
                    
                    queue.add(new Point(row, col));
                    visited[row][col] = true;
                    
                    int minRow = row;
                    int minCol = col;
                    
                    while(!queue.isEmpty()){
                        Point cur = queue.poll();
                        tempList.add(cur);
                        
                        minRow = Math.min(cur.x, minRow);
                        minCol = Math.min(cur.y, minCol);
                        
                        for(int dir = 0; dir < 4; dir++){
                            int nx = cur.x + dx[dir];
                            int ny = cur.y + dy[dir];
                            
                            if(nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
                            
                            if(game_board[nx][ny] == 1 || visited[nx][ny]) continue; 
                            
                            queue.add(new Point(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }
                    
                    for(Point point : tempList){
                        point.x -= minRow;
                        point.y -= minCol;
                    }
                    
                    Collections.sort(tempList, (p1, p2) -> {
                        if (p1.x != p2.x) {
                            return Integer.compare(p1.x, p2.x);
                        }
                        return Integer.compare(p1.y, p2.y);
                    });
                    
                    emptySpaceList.add(tempList); 
                }        
            }
        }
    }
}