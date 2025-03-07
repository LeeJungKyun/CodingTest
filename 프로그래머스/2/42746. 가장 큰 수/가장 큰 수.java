import java.util.*;

class numComparator implements Comparator<String>{
    @Override
    public int compare(String a, String b){
        String order1 = a + b;
        String order2 = b + a;
        return order2.compareTo(order1);
    }
}
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] strNum = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            strNum[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strNum, new numComparator());
    
        if(strNum[0].equals("0")){
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for(String num : strNum){
            sb.append(num);
        }
        
        return sb.toString();
    }
}