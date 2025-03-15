import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int n, m;
    int[][] info;
    Map<String, Boolean> visited; // 중복 방문 방지

    public int solution(int[][] info, int n, int m) {
        this.info = info;
        this.n = n;
        this.m = m;
        this.visited = new HashMap<>();

        // DFS로 모든 경우 탐색 (가지치기 추가)
        dfs(0, 0, 0);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public void dfs(int idx, int aSum, int bSum) {
        // 가지치기 (조건을 넘으면 탐색 중단)
        if (aSum >= n || bSum >= m) return;

        // 모든 아이템을 고려한 경우
        if (idx == info.length) {
            answer = Math.min(answer, aSum); // 최소값 업데이트
            return;
        }

        // 방문 체크 (중복 탐색 방지)
        String key = idx + "," + aSum + "," + bSum;
        if (visited.containsKey(key)) return;
        visited.put(key, true);

        // 현재 아이템을 A가 훔치는 경우
        dfs(idx + 1, aSum + info[idx][0], bSum);

        // 현재 아이템을 B가 훔치는 경우
        dfs(idx + 1, aSum, bSum + info[idx][1]);
    }
}