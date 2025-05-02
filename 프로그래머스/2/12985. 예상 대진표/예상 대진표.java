class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
        int[] nextSeed = new int[2];
        int[] nextGroup = new int[2];
        
        nextSeed[0] = a;
        nextSeed[1] = b;
        
        nextGroup[0] = (a - 1) / 2;
        nextGroup[1] = (b - 1) / 2;
        
        while(nextGroup[0] != nextGroup[1]){
            nextSeed[0] = (nextSeed[0] + 1) / 2;
            nextSeed[1] = (nextSeed[1] + 1) / 2;
            
            nextGroup[0] = (nextSeed[0] - 1) / 2;
            nextGroup[1] = (nextSeed[1] - 1) / 2;
            
            answer++;
        }
        
        return answer;
    }
}