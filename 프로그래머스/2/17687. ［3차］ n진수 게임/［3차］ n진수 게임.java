class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder fullString = new StringBuilder();
        StringBuilder result = new StringBuilder();
        
        int num = 0;
        int totalLength = t * m;  // 전체 필요한 길이
        
        // 필요한 길이만큼만 n진수 숫자 이어붙임
        while (fullString.length() < totalLength) {
            fullString.append(Integer.toString(num++, n));
        }
        
        for (int i = p - 1; result.length() < t; i += m) {
            result.append(Character.toUpperCase(fullString.charAt(i)));
        }

        return result.toString();
    }
}