class Solution {
    public int solution(int n) {
        int answer = 0;
        int target = Integer.bitCount(n);
        while(true){
            n++;
            int cnt= Integer.bitCount(n);
        
            if(cnt == target){
                return n;
            }
            else continue;
        }
    }
}