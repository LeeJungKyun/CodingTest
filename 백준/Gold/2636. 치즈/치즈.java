import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 가장 바깥쪽은 공기니깐 공기에서 시작해서 모든 외부 공기들을 체크 (tempArr에서 -1로 처리)
 *
 */
public class Main {
	static class Point{
		int x, y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] arr;
	static int[][] tempArr;
	static List<Point> toRemoveCheeseList;
	static int h, w;
	static int cheeseCount = 0;
	static int prevCheeseCount = 0, timeCount = 0;;
	static ArrayDeque<Point> queue;
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		arr = new int[h][w];
		
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) cheeseCount++;
			}
		}
		
		//치즈가 모두 없어질때까지 반복
		while(cheeseCount > 0) {
			timeCount++;
			//이전 치즈카운트 초기화
			prevCheeseCount = 0;
			toRemoveCheeseList = new ArrayList<>();
			//tempArr 초기화 후 현재 치즈 배열들을 복사
			tempArr = new int[h][w];
			for (int i = 0; i < h; i++) {
			    for (int j = 0; j < w; j++) {
			        tempArr[i][j] = arr[i][j];
			    }
			}

			//0, 0부터 BFS로 외부 공기들을 -1로 체크
			checkAir(0, 0);
			
			//tempArr를 순회하면서 없어지는 치즈 확인 후 삭제하고 prevCheese에 저장
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					//현재 칸이 치즈라면 공기랑 몇칸이 접해있는지 확인 -> 2칸이상이면 공기랑 맞닿아 있음
					if(tempArr[i][j] == 1) {
						int airCount = 0;
						for(int dir = 0; dir < 4; dir++) {
							int nx = i + dx[dir];
							int ny = j + dy[dir];
							//범위를 벗어나지 않고 근처에 공기가 있다면 공기 개수 증가
							if(0 <= nx && nx < h && 0 <= ny && ny < w && tempArr[nx][ny] == -1)
								airCount++;
						}
						//airCount가 1 이상이라면 해당 치즈를 삭제하고 prevCheeseCount 카운트 증가
						if(airCount >= 1) {
							toRemoveCheeseList.add(new Point(i, j));
						}
					}
				}
			}
			prevCheeseCount = toRemoveCheeseList.size();
			for (Point p : toRemoveCheeseList) {
			    arr[p.x][p.y] = 0;
			}
			cheeseCount -= prevCheeseCount;
		}
		System.out.println(timeCount);
		System.out.println(prevCheeseCount);
	}
	
	public static void checkAir(int x, int y) {
		queue = new ArrayDeque<>();
		queue.add(new Point(x, y));
		tempArr[x][y] = -1;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				
				//범위 안에 있고 공기라면
				if(0 <= nx && nx < h && 0 <= ny && ny < w && tempArr[nx][ny] == 0) {
					queue.add(new Point(nx, ny));
					tempArr[nx][ny] = -1;
				}
			}
		}
	}
}
