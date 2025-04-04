import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        //최종 후보키를 넣을 HashSet
        HashSet<Integer> candidateSet = new HashSet<>();
        int answer = 0;
        
        int rowNum = relation.length;
        int colNum = relation[0].length;
        
        //어떤 컬럼을 고를지 비트마스킹으로 결정
        for(int mask = 1; mask < (1 << colNum); mask++) {
            HashSet<String> set = new HashSet<>();
            //컬럼은 골랐으니 그것들로 유일성을 가지는지 식별
            for(int i = 0; i < rowNum; i++){
                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < colNum; j++){
                    if((mask & (1 << j)) != 0){
                        sb.append(relation[i][j]).append(" ");
                    }
                }
                set.add(sb.toString());
            }
            //유일성을 가지면 최소성 판단, 그렇지 않으면 넘기기
            if(set.size() == rowNum){
                //mask & candidateSet 안의 원소들이 전부 0이여야 candidateSet에 추가
                boolean flag = true;
                for(int candidateKey : candidateSet){
                    if ((candidateKey & mask) == candidateKey){
                        flag = false;
                        break;
                    }
                }
                
                if(flag){
                    candidateSet.add(mask);
                }
            } else continue;
        }
        
        answer = candidateSet.size();
        
        return answer;
    }
}