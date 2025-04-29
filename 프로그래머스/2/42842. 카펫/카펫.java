class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int area = brown + yellow;
        
        for (int height = 3; height <= area; height++) {
            if (area % height == 0) {
                int width = area / height;
                if ((width - 2) * (height - 2) == yellow) {
                    answer[0] = width;
                    answer[1] = height;
                    break;
                }
            }
        }
        
        return answer;
    }
}