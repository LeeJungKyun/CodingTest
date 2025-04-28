class Solution {
    public int solution(int n) {
        int answer = 0;
        int[] prefix = new int[n + 2];
        for(int i = 1; i <= n; i++){
            prefix[i] = prefix[i - 1] + i;
        }
        
        int left = 1;
        int right = 1;
        
        while(right <= n){
            int sum = prefix[right] - prefix[left - 1];
            if(sum == n){
                answer++;
                right++;
            }
            else if(sum < n){
                right++;
            }
            else {
                left++;
            }
        }
        return answer;
    }
}