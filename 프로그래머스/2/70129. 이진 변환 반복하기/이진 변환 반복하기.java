class Solution {
    int zeroCount = 0;
    int transformCount = 0;

    public int[] solution(String s) {
        int[] answer = new int[2];
    
        while(!s.equals("1")){
            s = checkAndRemove(s);  
            s = toBinary(s);
            transformCount++;
        }
        
        answer[0] = transformCount;
        answer[1] = zeroCount;
        
        return answer;
    }
    
    public String checkAndRemove(String str){
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for(int i = 0; i < len; i++) {
            if(str.charAt(i) == '0'){
                zeroCount++;
                continue;
            } else sb.append(str.charAt(i));
        }
        return sb.toString();
    }
    
    public String toBinary(String str){
        String binaryStr;
        int len = str.length();
        
        return binaryStr = Integer.toBinaryString(len);
    }
}