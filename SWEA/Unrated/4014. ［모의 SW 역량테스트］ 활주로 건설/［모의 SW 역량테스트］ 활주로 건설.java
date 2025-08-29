import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N x N 활주로에 지형의 높이가 주어짐
 * 활주로를 가로 또는 세로 방향으로 건설하고 싶음
 * 높이가 다르면 경사로를 설치
 * 		경사로 : 길이가 X, 높이는 1인데 높이차이가 1이고, 낮은 지형의 높이가 동일하게 경사로의 길이만큼 연속되는 곳에 설치
 *
 * 1. 테스트케이스 입력
 * 2. N, X입력
 * 3. 지형 높이 입력
 * 4. 활주로 건설 확인
 * 	4-1. 모든 열 확인
 * 	4-2. 모든 행 확인
 * 
 * 높이 차 확인하고
 * 내려가는건지, 올라가는건지 보고
 * 범위 벗어나는지, 높이가 다른지 이미 활주로가 있는지 확인
 * 활주로 표시
 */
public class Solution {
	static int testCase;
	static int n, x;
	static int result;
	static int[][] arr;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			
			//변수 초기화
			result = 0;
			
			//2. N, X입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			
			//3. 지형 높이 입력
			arr = new int[n][n];
			for(int i = 0; i < n; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//4. 활주로 건설 확인
			//4-1. 모든 열 확인
			for(int row = 0; row < n; row++) {
				if(canBuildRow(row)) {
					result++;
				}
			}
			//4-2. 모든 행 확인
			for(int col = 0; col < n; col++) {
				if(canBuildCol(col)) {
					result++;
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	// 각 행을 탐색하는 함수
    public static boolean canBuildRow(int rowIndex) {
        boolean[] visited = new boolean[n]; // 활주로가 놓인 칸을 추적하는 배열

        for (int colIndex = 0; colIndex < n - 1; colIndex++) {
            // 현재 칸과 다음 칸의 높이 차이
            int diff = arr[rowIndex][colIndex] - arr[rowIndex][colIndex + 1];

            // 높이 차이가 1보다 크면 불가능
            if (Math.abs(diff) > 1) {
                return false;
            }

            // 높이가 낮아지는 경우 (내려가는 경사로)
            if (diff == 1) {
                // 현재 칸부터 x칸까지 활주로를 놓을 수 있는지 확인
                for (int i = 1; i <= x; i++) {
                    // 범위를 벗어나거나, 높이가 다르거나, 이미 활주로가 있다면 불가능
                    if (colIndex + i >= n || arr[rowIndex][colIndex + 1] != arr[rowIndex][colIndex + i] || visited[colIndex + i]) {
                        return false;
                    }
                    visited[colIndex + i] = true; // 활주로를 놓은 칸으로 표시
                }
            }
            // 높이가 높아지는 경우 (올라가는 경사로)
            else if (diff == -1) {
                // 현재 칸을 포함하여 이전 x칸까지 활주로를 놓을 수 있는지 확인
                for (int i = 0; i < x; i++) {
                    // 범위를 벗어나거나, 높이가 다르거나, 이미 활주로가 있다면 불가능
                    if (colIndex - i < 0 || arr[rowIndex][colIndex] != arr[rowIndex][colIndex - i] || visited[colIndex - i]) {
                        return false;
                    }
                    visited[colIndex - i] = true; // 활주로를 놓은 칸으로 표시
                }
            }
        }
        return true;
    }
	
	//각 행을 탐색하는 함수
    public static boolean canBuildCol(int colIndex) {
        // 활주로가 놓인 칸을 추적하는 배열
        boolean[] visited = new boolean[n];

        for (int rowIndex = 0; rowIndex < n - 1; rowIndex++) {
            // 현재 칸과 바로 아래 칸의 높이 차이를 계산
            int diff = arr[rowIndex][colIndex] - arr[rowIndex + 1][colIndex];

            // 높이 차이가 1보다 크면 활주로 건설 불가
            if (Math.abs(diff) > 1) {
                return false;
            }

            // 높이가 낮아지는 경우 (아래로 내려가는 경사로)
            if (diff == 1) {
                // 현재 칸부터 아래로 'x'칸에 활주로를 놓을 수 있는지 확인
                for (int i = 1; i <= x; i++) {
                    // 범위를 벗어나거나, 높이가 다르거나, 이미 활주로가 놓여 있다면 불가능
                    if (rowIndex + i >= n || arr[rowIndex + 1][colIndex] != arr[rowIndex + i][colIndex] || visited[rowIndex + i]) {
                        return false;
                    }
                    // 활주로를 놓은 칸으로 표시
                    visited[rowIndex + i] = true;
                }
            }
            // 높이가 높아지는 경우
            else if (diff == -1) {
                // 현재 칸을 포함하여 위로 x칸에 활주로를 놓을 수 있는지 확인
                for (int i = 0; i < x; i++) {
                    // 범위를 벗어나거나, 높이가 다르거나, 이미 활주로가 놓여 있다면 불가
                    if (rowIndex - i < 0 || arr[rowIndex][colIndex] != arr[rowIndex - i][colIndex] || visited[rowIndex - i]) {
                        return false;
                    }
                    // 활주로를 놓은 칸으로 표시
                    visited[rowIndex - i] = true;
                }
            }
        }
        return true;
    }
}