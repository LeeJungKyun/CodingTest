import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int length = elements.length;
        
        int[] arr = new int[length * 2];
        
        for(int i = 0; i < length; i++){
            arr[i] = elements[i];
            arr[length + i] = arr[i];
        }
        
        HashSet<Integer> set = new HashSet<>();
        //시작할 원소를 i로 고르고 그 자리에서부터 j개 선택
        for(int i = 0; i < length; i++){
            int sum = 0;
            for(int j = 0; j < length; j++){
                sum += arr[i + j];
                set.add(sum);
            }
        }
        
        
        return set.size();
    }
}