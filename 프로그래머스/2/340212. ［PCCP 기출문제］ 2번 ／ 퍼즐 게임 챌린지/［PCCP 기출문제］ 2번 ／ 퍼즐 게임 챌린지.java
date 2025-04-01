class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canSolve(diffs, times, mid, limit)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean canSolve(int[] diffs, int[] times, int level, long limit) {
        long time = 0;

        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                time += times[i];
            } else {
                int retry = diffs[i] - level;
                if (i == 0) {
                    time += (long) retry * times[0] + times[0];
                } else {
                    time += (long) retry * (times[i - 1] + times[i]) + times[i];
                }

                if (time > limit)
                    return false;
            }
        }

        return time <= limit;
    }
}