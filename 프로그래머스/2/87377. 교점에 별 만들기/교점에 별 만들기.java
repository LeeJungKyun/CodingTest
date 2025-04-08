import java.util.*;

class Point{
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Solution {
    ArrayList<Point> points;
    public String[] solution(int[][] line) {
        String[] answer = {};
        points = new ArrayList<>();
        int n = line.length;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        
        int width, height;
        
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                calculate(line[i], line[j]);
            }
        }
        
        for(Point point : points){
            maxX = Math.max(maxX, point.x);
            minX = Math.min(minX, point.x);
            maxY = Math.max(maxY, point.y);
            minY = Math.min(minY, point.y);
        }
        
        width = maxX - minX + 1;
        height = maxY - minY + 1;
        
        char[][] result = new char[height][width];
        for(int i = 0; i < height; i++){
            Arrays.fill(result[i], '.');
        }
        
        for(Point point : points){
            int x = point.x - minX;
            int y = maxY - point.y;
            result[y][x] = '*';
        }
        
        answer = new String[height];
        for(int i = 0; i < height; i++){
            answer[i] = new String(result[i]);
        }
        
        return answer;
    }
    
    public void calculate(int[] first, int[] second){
        int a = first[0];
        int b = first[1];
        int e = first[2];
        int c = second[0];
        int d = second[1];
        int f = second[2];
        
        long numeratorX = (long)b * f - (long)e * d;
        long denominator = (long)a * d - (long)b * c;
        long numeratorY = (long)e * c - (long)a * f;

        //평행인 경우
        if (denominator == 0) return;

        if (numeratorX % denominator == 0 && numeratorY % denominator == 0) {
            long x = numeratorX / denominator;
            long y = numeratorY / denominator;
            points.add(new Point((int)x, (int)y));
        }
    }
    
    public boolean isInteger(double num){
        return num == (int) num;
    }
}