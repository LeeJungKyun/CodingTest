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
    HashMap<Integer, Point> map;
    Point left, right;
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[][] keypad = new int[4][3];
        map = new HashMap<>();
        
        //기본 위치
        left = new Point(3, 0);
        right = new Point(3, 2);
        
        int key = 1;
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                map.put(key, new Point(i,j));
                key++;
            }
        }
        
        map.put(0, new Point(3, 1));
        
        StringBuilder sb = new StringBuilder();
        for(int num : numbers){
            if(num == 1 || num == 4 || num == 7){
                sb.append('L');
                left = map.get(num);
            }
            else if(num == 3 || num == 6 || num == 9){
                sb.append('R');
                right = map.get(num);
            }
            //중간이면
            else{
                if (calculateDistance(num, hand) == 'L'){
                    sb.append('L');
                    left = map.get(num);
                }
                else{
                    sb.append('R');
                    right = map.get(num);
                }
            }
        }
        return answer = sb.toString();
    }
    
    public char calculateDistance(int num, String hand){
        //번호의 좌표 가져와서 계산
        Point cur = map.get(num);
        int leftDistance = (Math.max(cur.x, left.x) - Math.min(cur.x, left.x)) + (Math.max(cur.y, cur.y) - Math.min(cur.y, left.y));
        int rightDistance = (Math.max(cur.x, right.x) - Math.min(cur.x, right.x)) + (Math.max(cur.y, right.y) - Math.min(cur.y, right.y));
        System.out.println("Cur num : " + num + " Cur x : "  + cur.x + " Cur y : " + cur.y);
        System.out.println("Left x : "  + left.x + " Left y : " + left.y);
        System.out.println("leftDistance : " + leftDistance);
        System.out.println("Right x : "  + right.x + " Right y : " + right.y);
        System.out.println("rightDistance : " + rightDistance);
        System.out.println();
        //왼쪽이 더 가까우면
        if(leftDistance < rightDistance){
            return 'L';
        }
        else if(rightDistance < leftDistance){
            return 'R';
        }
        else{
            if(hand.equals("right")){
                return 'R';
            }
            else return 'L';
        }
    }
}