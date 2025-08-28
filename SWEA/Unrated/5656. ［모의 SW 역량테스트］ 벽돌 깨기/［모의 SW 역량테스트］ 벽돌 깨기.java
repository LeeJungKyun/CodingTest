import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 1. 테스트 케이스 입력
 * 2. 벽돌 개수 n, 너비 w, 높이 h 입력
 * 3. 벽돌 배열 입력
 * 4. 필요 변수 초기화
 * 5. 모든 경우의 수 탐색해서 시뮬 시작
 * 
 * 최대한 많은 벽돌을 깨뜨리는 방법을 찾는다, N개의 구슬은 W중 하나에서 떨궈질텐데..
 * W ^ N 최악은 12 ^ 4 = 20,736에 각각 완탐느낌으로 하면 (W^N) * W * H = 3,732,480 일단 ㄱㄱ
 *
 */
public class Solution {
	static class Block{
		int x, y, power;

		public Block(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
		}
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int testCase;
	static int n, w, h;
	static int minLeftCount;
	static int[][] arr, tempArr;
	static int[] selectedIndex;
	static boolean[][] isDeleted;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 벽돌 개수 n, 너비 w, 높이 h 입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			//3. 벽돌 배열 입력
			arr = new int[h][w];
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//4. 필요 변수 초기화
			selectedIndex = new int[n];
			minLeftCount = Integer.MAX_VALUE;
			
			//5. 모든 경우의 수 조합해서 호출 시작
			startSimulate(0);
			
			sb.append('#').append(tc).append(' ').append(minLeftCount).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void startSimulate(int k) {
        if (k == n) {
            // 조합이 완성되었으므로, 시뮬레이션 시작
            breakBlock();
            return;
        }

        for (int i = 0; i < w; i++) {
            selectedIndex[k] = i; 
            startSimulate(k + 1);
        }
    }
	
	//selectedIndex에 벽돌을 떨어뜨릴 인덱스들이 저장되어있음
	//tempArr에 복사해놓고 tempArr로만 계산
	public static void breakBlock() {
		//벽돌의 최대 개수는 180개
		int leftCount = 0;
		tempArr = new int[h][w];
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++)
				tempArr[i][j] = arr[i][j];
		}
		
		//모든 공에 대해서 drop함수 실행
		for(int i = 0; i < n; i++) {
			isDeleted = new boolean[h][w];
			dropMarble(selectedIndex[i]);
			dropAllBlocks();
		}
		
		//남아있는 벽돌 계산 후 갱신
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(tempArr[i][j] != 0)
					leftCount++;
			}
		}
		minLeftCount = Math.min(leftCount, minLeftCount);
	}
	
	//파라미터로 어디서 떨어뜨릴지 정한 index가 넘어옴
	public static void dropMarble(int index) {
		//해당 인덱스에서 구슬 떨어뜨리기
		int row = 0;
		//0이 아닌거면 벽돌인거니깐 벽돌 마주칠때까지 row증가
		while(row < h && tempArr[row][index] == 0) {
			row++;
		}
		
		// 이 시점에서의 row가 h이면 벽돌이 없었던 거니깐 그냥 종료
	    if (row == h)
	        return;

	    // 벽돌이면 얼마나 퍼지는지 확인하고 지워지는 배열에 추가
	    int power = tempArr[row][index];
	    
	    // 첫 번째 블록은 바로 큐에 넣고 방문 처리
	    ArrayDeque<Block> queue = new ArrayDeque<>();
	    queue.offer(new Block(row, index, power));
	    
	    // 폭발 시킬 배열에 추가
	    isDeleted[row][index] = true;

	    while(!queue.isEmpty()) {
	        Block cur = queue.poll();
	        
	        // 4방향으로 힘만큼 가야함
	        for(int dir = 0; dir < 4; dir++) {
	            // cur.power가 1이면 루프가 돌지 않고, curPower가 2부터 시작하면 1칸 거리부터
	            for(int curPower = 1; curPower < cur.power; curPower++) {
	                int nx = cur.x + dx[dir] * curPower;
	                int ny = cur.y + dy[dir] * curPower;
	                
	                // 범위 밖이면 해당 방향 탐색 중단
	                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
	                    break;
	                //방문했으면 추가 안하고 넘어감
	                if(isDeleted[nx][ny])
	                	continue;

	                // 지워질 벽돌이니깐 추가
	                isDeleted[nx][ny] = true;
	                
	                // 다음 돌도 벽돌이면 또 퍼질테니깐 추가
	                // tempArr[nx][ny]가 1이상이면 큐에 추가
	                if (tempArr[nx][ny] > 1) { 
	                    queue.add(new Block(nx, ny, tempArr[nx][ny]));
	                }
	            }
	        }
	    }
	    
	    // 폭발 당하면 지우기
	    for(int i = 0; i < h; i++) {
	        for(int j = 0; j < w; j++) {
	            if(isDeleted[i][j])
	                tempArr[i][j] = 0;
	        }
	    }
	}
	
	public static void dropAllBlocks() {
	    // 모든 열에 대해 반복
	    for (int j = 0; j < w; j++) {
	        // 블록을 임시로 담을 배열 (ArrayList를 사용하면 더 편함)
	        ArrayList<Integer> blocksInColumn = new ArrayList<>();
	        
	        // 각 열의 아래에서 위로 올라가며 블록 찾기
	        for (int i = h - 1; i >= 0; i--) {
	            if (tempArr[i][j] != 0) {
	                blocksInColumn.add(tempArr[i][j]);
	            }
	        }
	        
	        // 해당 열을 모두 0으로 초기화
	        for (int i = 0; i < h; i++) {
	            tempArr[i][j] = 0;
	        }
	        
	        // 임시 배열에 담긴 블록들을 다시 아래부터 채워 넣기
	        int row = h - 1;
	        for (int block : blocksInColumn) {
	            tempArr[row][j] = block;
	            row--;
	        }
	    }
	}
	
	
}