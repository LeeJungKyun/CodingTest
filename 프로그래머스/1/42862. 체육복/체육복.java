import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();

        for (int l : lost) lostSet.add(l);
        for (int r : reserve) {
            if (lostSet.contains(r)) {
                lostSet.remove(r);
            } else {
                reserveSet.add(r);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (lostSet.contains(i)) {
                if (reserveSet.contains(i - 1)) {
                    lostSet.remove(i);
                    reserveSet.remove(i - 1);
                } else if (reserveSet.contains(i + 1)) {
                    lostSet.remove(i);
                    reserveSet.remove(i + 1);
                }
            }
        }

        answer = n - lostSet.size();

        return answer;
    }
}