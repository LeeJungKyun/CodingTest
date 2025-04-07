class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();

        // 1. 변경 비용 계산
        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }

        // 2. 이동 최소 거리 계산
        int move = len - 1;  // 기본값: 끝까지 오른쪽

        for (int i = 0; i < len; i++) {
            int next = i + 1;

            // 연속된 A 구간 건너뛰기
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            move = Math.min(move, i * 2 + len - next);
            move = Math.min(move, (len - next) * 2 + i);
        }

        return answer + move;
    }
}