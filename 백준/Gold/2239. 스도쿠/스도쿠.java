import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 스도쿠가 성립하기 위한 조건은 가로열에 같은 숫자 X, 세로 열에 같은 숫자 X, 내가 속해 있는 칸에 같은 숫자 X
 * 이걸 관리하려면 3차원짜리 isUsed가 필요함 isUsed[3][9][10]
 * [0][i][num] : i 번째 가로줄에 num이 쓰였냐
 * [1][i][num] : i 번째 세로줄에 num이 쓰였냐
 * [2][i][num] : i 번째 격자칸에 num이 쓰였냐
 * 
 * 
 * 1. 초기 상태의 보드 입력
 * 		1-1. 입력받으면서 이미 숫자가 있는 경우 isUsed를 채워줘야함
 * 		1-2. 가로줄 i, 세로줄 j, 격자는 (i / 3) * 3 + (j / 3)
 * 2. makeSudoku함수를 실행 (x, y) 넘겨줄거임 초기 값은 0, 0
 * 		2-1. x, y가 (8, 8)을 채워야 하면 이번에 채우고 isDone을 true로 바꿔주기
 * 		2-2. 이미 채워진 칸인 경우 범위의 오버플로우를 방지
 * 		2-3. 아직 안채워진 칸인 경우(0)
 * 		2-4. 1부터 9까지 보면서 체크
 * 		2-5. 일단 가능하긴 하면 arr를 바꾸고 isUsed에 체크
 * 		2-6. 재귀 ㄱㄱ
 * 		2-7. 원복
 */
public class Main {
	static final int SIZE = 9;
	static boolean[][][] isUsed = new boolean[3][9][10];
	static int[][] board;
	static boolean isDone = false;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 초기 상태의 보드판 입력
		board = new int[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			String line = br.readLine();
			for(int j = 0; j < SIZE; j++) {
				board[i][j] = line.charAt(j) - '0';
				//1-1. 입력받으면서 이미 숫자가 있는 경우 isValid를 채워줘야함
				if(board[i][j] != 0) {
					//1-2. 가로줄 i, 세로줄 j, 격자는 (i / 3) * 3 + (j / 3)
					int num = board[i][j];
					//가로줄 체크
					isUsed[0][i][num] = true;
					//세로줄 체크
					isUsed[1][j][num] = true;
					//격자 체크
					int cell = (i / 3) * 3 + (j / 3);
					isUsed[2][cell][num] = true;
				}
			}
		}
		
		
		//2. makeSudoku함수를 실행 (x, y) 넘겨줄거임 초기 값은 0, 0
		makeSudoku(0, 0);
		
		//출력 
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col < SIZE; col++) {
				sb.append(board[row][col]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	public static void makeSudoku(int x, int y) {
		//종료 조건
		//2-1. x, y가 (8, 8)을 채워야 하면 이번에 채우고 끝내면 될듯 한데
		if (x == 8 && y == 8) {
            //마지막 칸에 들어갈 번호 확인
            for (int i = 1; i <= 9; i++) {
                if (!isUsed[0][8][i]) {
                    board[8][8] = i;
                    break;
                }
            }
            //isDone true로 바꿔주기
            isDone = true;
            return;
        }
		
		//2-2. 이미 채워진 칸인 경우 범위의 오버플로우를 방지
		if(board[x][y] != 0) {
			if(y == 8) {
				//가로 열 다 봤으니깐 다음 줄 넘어가기
				makeSudoku(x + 1, 0);
			}
			//그냥 오른쪽에 있는 거 탐색
			else {
				makeSudoku(x, y + 1);
			}
		}
		//내가 채워야할 셀에 도착
		else {
			int currentCellNum = (x / 3) * 3 + (y / 3);
			//2-4. 1부터 9까지 보면서 체크
			for(int fillNumber = 1; fillNumber < 10; fillNumber++) {
				if(!isUsed[0][x][fillNumber] &&
						!isUsed[1][y][fillNumber] &&
						!isUsed[2][currentCellNum][fillNumber]) {
					//2-5. 일단 가능하긴 하면 board를 바꾸고 isUsed에 체크
					board[x][y] = fillNumber;
					isUsed[0][x][fillNumber] = true;
					isUsed[1][y][fillNumber] = true; 
					isUsed[2][currentCellNum][fillNumber] = true;
					
					//2-6. 재귀 ㄱㄱ 근데 여기도 범위 체크
					if(y == 8) {
						makeSudoku(x + 1, 0);
					} else {
						makeSudoku(x, y + 1);
					}
					
					//성공했으면 종료
					if(isDone)
						return;
					
					//2-7. 원복
					board[x][y] = 0;
					isUsed[0][x][fillNumber] = false;
					isUsed[1][y][fillNumber] = false; 
					isUsed[2][currentCellNum][fillNumber] = false;
				}
			}
		}
	}
}