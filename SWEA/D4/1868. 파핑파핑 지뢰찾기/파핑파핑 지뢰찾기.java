import java.io.*;
import java.util.*;

/**
 * 지뢰 찾기의 보드에서 최소의 클릭을 통해 모든 칸의 숫자들이 표시가 되어야함
 * 지뢰가 없는 칸을 클릭하면 8칸에 대해 몇 개의 지뢰가 있는지 표시 -> 0이면 8칸을 자동으로 채워줌
 * 재귀를 통해서 제일 depth가 깊은 걸 고르면 되나..? 어쨌든 제일 많이 채워지는거니깐
 * @author 이정균
 * 1. 테스트케이스 입력
 * 2. 정수 N입력 후 N x N 배열 초기화
 * 3. 배열 입력받기
 * 4. 필요 변수 초기화
 * 5. 일단 지뢰의 개수를 저장하는 배열 초기화 후 계산 저장
 * 6. 일단 0부터 눌러서 다 채우기
 * 7. 그 다음에 클릭 안된 지뢰가 아닌 칸 확인해서 누르기
 * 
 *
 */
public class Solution {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int testCase;
	static int n;
	static int clickCount;
	static char[][] board;
	static int[][] numBoard;
	static boolean[][] visited;
	static ArrayDeque<Point> queue;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1}; 
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 정수 N입력 후 N x N 배열 초기화
			n = Integer.parseInt(br.readLine());
			board = new char[n][n];
			
			//3. 배열 입력받기
			for(int i = 0; i < n; i++) {
				String input = br.readLine();
				for(int j = 0; j < n; j++) {
					board[i][j] = input.charAt(j);
				}
			}
			
			//4. 필요 변수 초기화
			clickCount = 0;
			visited = new boolean[n][n];
			//5. 일단 지뢰의 개수를 저장하는 배열 초기화 후 계산 저장
			numBoard = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(board[i][j] == '*') {
						numBoard[i][j] = 9;
						continue;
					}
					int mineCount = 0;
					for(int dir = 0; dir < 8; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if(0 <= nx && nx < n && 0 <= ny && ny < n) {
							if(board[nx][ny] == '*')
								mineCount++;
						}
					}
					numBoard[i][j] = mineCount;
				}
			}
//			입력끝
			
			//6. 일단 0부터 눌러서 다 채우기
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					//0이라면 해당 칸에서 BFS시작하고 clickCount++
					if(numBoard[i][j] == 0 && !visited[i][j]) {
						fillBoard(i, j);
						clickCount++;
					}
				}
			}
			
			//7. 그 다음에 클릭 안된 지뢰가 아닌 칸 확인해서 누르기
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					//지뢰가 아닌 칸에서 BFS시작하고 clickCount++
					if(numBoard[i][j] != 9 && !visited[i][j]) {
						clickCount++;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(clickCount).append('\n');
		}
		
		System.out.println(sb);
	}
	
	
	public static void fillBoard(int x, int y) {
		queue = new ArrayDeque<>();
		queue.add(new Point(x, y));
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for(int dir = 0; dir < 8; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				//범위 밖, 방문여부, 지뢰라면 스킵
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || numBoard[nx][ny] == 9) {
					continue;
				}
				
				//방문 처리
				visited[nx][ny] = true;
				//다음 보드가 0이면 확장
				if(numBoard[nx][ny] == 0)
					queue.add(new Point(nx, ny));
				
			}
		}
	}
}