class Solution {
    int max = 0;
    boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return max;
    }

    public void dfs(int k, int[][] dungeons, int depth) {
        max = Math.max(max, depth);

        for (int i = 0; i < dungeons.length; i++) {
            //최소 필요 피로도
            int min = dungeons[i][0];
            // 소모 피로도
            int cost = dungeons[i][1];

            if (!visited[i] && k >= min) {
                visited[i] = true;
                dfs(k - cost, dungeons, depth + 1);
                visited[i] = false;
            }
        }
    }
}