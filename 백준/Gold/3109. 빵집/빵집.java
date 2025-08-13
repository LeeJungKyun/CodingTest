/**
 * @author 이정균
 * R * C 의 격자에서 첫째열이 빵집의 가스관, 마지막 열은 원웅이의 빵집
 * 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결 가능
 * 
 * 
 * 1. r과 c 입력
 * 2. map 입력
 * 3. visited 초기화
 * 4. 모든  row에 대해서 stealPipe(row, 0) 실행
 * 	4-1. row, col 방문처리
 * 	4-2. 종료 조건 확인 -> return true
 * 	4-3. 갈 수 있는 방향 3개에 대해서
 * 	4-4. 범위 내에 있고, 0이고, 방문하지 않았다면
 * 	4-5. 해당 nx, ny에 대해서 다시 stealPipe(nx, ny) 실행
 * 5. stealPipe가 가능하다면 answer 증가
 */
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int r, c, result = 0;
	static int[][] map;
	//오른쪽 위, 오른쪽, 오른쪽 아래
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. r과 c입력
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		//2. map 입력
		map = new int[r][c];
		for(int i = 0; i < r; i++) {
			String line = br.readLine();
			for(int j = 0; j < c; j++) {
				map[i][j] = (line.charAt(j) == '.') ? 0 : 1;
			}
		}
		
		//3. visited 초기화
		visited = new boolean[r][c];
		//4. 모든  row에 대해서 stealPipe(row, 0) 실행
		for(int startRow = 0; startRow < r; startRow++)
			//5. stealPipe가 가능하다면 answer 증가
			if(stealPipe(startRow, 0))
				result++;
		
		System.out.println(result);
	}
	
	public static boolean stealPipe(int row, int col) {
		//4-1. row, col 방문처리
		visited[row][col] = true;
		//4-2. 종료 조건 확인 -> return true
		if(col == c - 1) {
			return true;
		}
		
		//4-3. 갈 수 있는 방향 3개에 대해서
		for(int dir = 0; dir < 3; dir++) {
			int nx = row + dx[dir];
			int ny = col + dy[dir];
			
			//4-4. 범위 내에 있고, 0이고, 방문하지 않았다면
			if(nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == 0 && !visited[nx][ny]) {
				//4-5. 해당 nx, ny에 대해서 다시 stealPipe(nx, ny) 실행
				if(stealPipe(nx, ny))
					return true;
			}
		}
		return false;
	}
}
