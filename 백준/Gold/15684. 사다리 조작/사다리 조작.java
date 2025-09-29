import java.io.*;
import java.util.*;

public class Main {
    static int n, m, h;
    // board[i][j]: i 높이에서 j번 세로선과 j+1번 세로선 사이에 가로선이 존재 (true/false)
    static boolean[][] board;
    static int min = 4; // 최소값 초기화 (문제 조건: 3 이하만 허용)
    
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 세로선 n, 기존 가로선 m, 놓을 수 있는 위치 h
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        
        // 인덱스 1부터 사용: 높이 1..h, 세로선 1..n-1
        board = new boolean[h + 1][n + 1]; 
        
        // 2. 가로선 정보 입력
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int height = Integer.parseInt(st.nextToken()); // 높이 a
            int num = Integer.parseInt(st.nextToken());    // 세로선 b (b와 b+1 연결)
            
            board[height][num] = true; 
        }
        
        // 0개부터 3개까지 사다리를 추가하는 경우를 순서대로 탐색
        for(int num = 0; num <= 3; num++) {
            // depth: 현재 추가한 사다리 개수, num: 목표 개수, start: 탐색 시작 위치 (1차원 인덱스)
            dfs(0, num, 1);
            if(min != 4) { // 최소값이 갱신되었다면, 더 적은 개수에서 찾은 것이므로 종료
                System.out.println(min);
                return;
            }
        }
        
        // 3개까지 추가해도 조건을 만족하지 못하면 -1 출력
        System.out.println(-1);
    }
    
    // depth: 현재 추가한 가로선 개수, num: 목표 가로선 개수, start: 탐색 시작 위치 (1차원 인덱스)
    public static void dfs(int depth, int num, int start) {
        // 목표 개수(num)에 도달했고, 현재 사다리가 모든 조건을 만족하는지 확인
        if (depth == num) {
            if (isPossible()) {
                min = num; // 현재 num이 최소값이 됨
            }
            return;
        }
        
        // 목표 개수보다 더 추가할 수 없거나, 이미 최소값을 찾았으면 탐색 중단
        if (depth > num || depth >= min) {
            return;
        }
        
        // 탐색 시작: 가로선 위치를 1차원 인덱스처럼 처리하여 조합 탐색
        for (int k = start; k <= h * (n - 1); k++) {
            int i = (k - 1) / (n - 1) + 1;
            int j = (k - 1) % (n - 1) + 1;

            // 1. 현재 위치에 사다리가 없어야 하고
            // 2. 왼쪽에 이웃한 사다리(j-1)가 없어야 하며 (j > 1일 때만 검사)
            // 3. 오른쪽에 이웃한 사다리(j+1)가 없어야 함 (j < n-1일 때만 검사)
            if (!board[i][j]) {
                boolean leftFree = (j == 1) || !board[i][j - 1];
                boolean rightFree = (j == n - 1) || !board[i][j + 1];

                if (leftFree && rightFree) {
                    board[i][j] = true;
                    // 다음 재귀 호출 시 현재 위치 다음부터 탐색하도록 start 인덱스 전달
                    dfs(depth + 1, num, k + 1); 
                    board[i][j] = false; // 백트래킹
                }
            }
        }
    }

    // 모든 사다리 시작점(i)이 도착점(i)으로 가는지 확인
    public static boolean isPossible() {
        for (int i = 1; i <= n; i++) { // i: 시작 세로선 번호
            int currentLine = i; // 현재 위치한 세로선 번호
            
            for (int col = 1; col <= h; col++) { // col: 높이 (1부터 h까지 이동)
                // 1. 현재 위치와 오른쪽 세로선(currentLine, currentLine+1) 사이에 가로선이 있는지 확인
                if (currentLine <= n - 1 && board[col][currentLine]) {
                    currentLine++; // 오른쪽으로 이동
                } 
                // 2. 현재 위치와 왼쪽 세로선(currentLine-1, currentLine) 사이에 가로선이 있는지 확인
                else if (currentLine >= 2 && board[col][currentLine - 1]) {
                    currentLine--; // 왼쪽으로 이동
                }
                // 3. 가로선이 없으면 세로로 직진 (currentLine 유지)
            }
            
            // i번 세로선에서 출발한 결과가 i번 세로선으로 끝나지 않으면 실패
            if (currentLine != i) {
                return false;
            }
        }
        
        return true; // 모든 시작점이 자신의 번호로 도착했으므로 성공
    }
}