import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int maxWidth = 0, maxHeight = 0;
        for(int i = 0; i < sizes.length; i++){
            int width = sizes[i][0];
            int height = sizes[i][1];
            int temp;
            
            if(width < height){
                temp = width;
                width = height;
                height = temp;
            }
            maxWidth = Math.max(width, maxWidth);
            maxHeight = Math.max(height, maxHeight);
        }
        answer = maxWidth * maxHeight;
        return answer;
    }
}