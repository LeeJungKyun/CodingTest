import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N x N 정사각형의 게임판
 * 1 ~ 5 블록
 * 웜홀 6 ~ 10
 * 블랙홀 -1
 * 
 * 1. 사이즈 입력
 * 2. 배열 입력
 * 	2-1. 주변은 5블록으로 둘러싸면 계산이 편해짐!
 * 3. 0인 곳에서 방향 정해서 핀볼 게임 시작
 */

public class Solution {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int n;
	static int maxScore = MIN;
	static int[][] board;
	static ArrayList<int[]>[] wormHoleList;
	//상 하 좌 우
	//0 1 2 3
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] blockDir = {
			//0번 (그냥 그대로)
			{0, 1, 2, 3},
			//1번 블록
			{1, 3, 0, 2},
			//2번 블록
			{3, 0, 1, 2},
			//3번 블록
			{2, 0, 3, 1},
			//4번 블록
			{1, 2, 3, 0},
			//5번 블록
			{1, 0, 3, 2}
		};
	
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= testCase; tc++) {
			maxScore = MIN;
			//1. 사이즈 입력
			n = Integer.parseInt(br.readLine().trim());
			
			//2. 배열 입력
			//TODO : 웜홀도 같이 저장
			wormHoleList = new ArrayList[11];
			for(int i = 0; i <= 10; i++)
				wormHoleList[i] = new ArrayList<>();
			
			board = new int[n + 2][n + 2];
			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 1; j <= n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					//웜홀 저장
					if(board[i][j] >= 6)
						wormHoleList[board[i][j]].add(new int[] {i, j});
				}
			}
			
			//2-1. 주변은 5블록으로 둘러싸면 계산이 편해짐!
            for (int i = 0; i <= n + 1; i++) {
            	board[i][0] = board[i][n + 1] = board[0][i] = board[n + 1][i] = 5;
            }
			
			//3. 0인 곳에서 방향 정해서 핀볼 게임 시작
			for(int row = 1; row <= n; row++) {
				for(int col = 1; col <= n; col++) {
					if(board[row][col] == 0) {
						for(int dir = 0; dir < 4; dir++) {
							int score = startPinBall(row, col, dir);
							maxScore = Math.max(maxScore, score);
						}
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(maxScore).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int startPinBall(int startRow, int startCol, int startDir) {
		int currentScore = 0;
		
		int curRow = startRow;
		int curCol = startCol;
		int curDir = startDir;
		
		while(true) {
			//다음 좌표 구하기
			int nx = curRow + dx[curDir];
			int ny = curCol + dy[curDir];
			
			//종료 조건 확인 (출발지로 복귀 또는 블랙홀 ㅠㅠ)
			if((nx == startRow && ny == startCol) || board[nx][ny] == -1) {
				return currentScore;
			}
			
			//뭔가 이벤트가 발생한 경우
			if(board[nx][ny] > 0) {
				//블록 처리
				if(board[nx][ny] < 6) {
					int blockNum = board[nx][ny];
					//블록만났으니 방향 바꿔주기
					curDir = blockDir[blockNum][curDir];
					currentScore++;
				}
				else {
					//웜홀 처리
					//나랑 같은 웜홀을 찾아줘
					int wormHoleNum = board[nx][ny];
					int[] nextWormHole;
					
					 if (wormHoleList[wormHoleNum].get(0)[0] == nx
							 && wormHoleList[wormHoleNum].get(0)[1] == ny){
					     nextWormHole = wormHoleList[wormHoleNum].get(1);
					 } else {
					     nextWormHole = wormHoleList[wormHoleNum].get(0);
					 }
					 nx = nextWormHole[0];
					 ny = nextWormHole[1];
				}
			}
			//이벤트 처리 끝
			//그냥 옮기면 됩니다
			curRow = nx;
			curCol = ny;
		}
	}
}