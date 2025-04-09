class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int[][] dp = new int[n + 1][n + 1];
        //기본값 dp에 저장
        dp[0][0] = triangle[0][0];
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i + 1; j++){
                if(j == 0) dp[i][j] = dp[i-1][j] + triangle[i][j];
                else if(j == i) dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
                }
                answer = Math.max(dp[i][j], answer);
            }
        }
        return answer;
    }
}