import java.io.*;
import java.util.*;

public class Main {
	static class Block {
		int x, y;
		public Block (int x, int y) {
			this.x = y; // y, x 순서로
			this.y = y;
		}
	}
	
	static final int MOVE_COUNT = 5;
	static int n;
	static int maxValue = Integer.MIN_VALUE;
	//상, 좌, 하, 우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[][] arr, tempArr;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num != 0) {
					int logValue = (int)(Math.log(num) / Math.log(2));
					arr[i][j] = logValue;
				}
			}
		}
		
		//5번 이동하면 경우의 수 4 ^ 5 = 2 ^ 10 = 1024가지 경우의수
		for(int mask = 0; mask < (1 << (2 * MOVE_COUNT)); mask++){
			//조합생성
			 int[] command = new int[MOVE_COUNT];
			 int tempMask = mask;
			 
			 for(int i = 0; i < MOVE_COUNT; i++) {
				 command[i] = tempMask % 4;
				 tempMask /= 4;
			 }
			 
			 start2048(command);
			 maxValue = Math.max(maxValue, findMaxValue());
		}
		
		System.out.println(1 << maxValue);
	}
	
	public static void start2048(int[] command) {
		tempArr = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				tempArr[i][j] = arr[i][j];
			}
		}
		
		for(int moveCount = 0; moveCount < MOVE_COUNT; moveCount++) {
			int curDir = command[moveCount];
			switch(curDir) {
			case 0: moveUp(); break;
			case 1: moveLeft(); break;
			case 2: moveDown(); break;
			case 3: moveRight(); break;
			}
		}
	}
	
	public static void moveUp() {
		for(int col = 0; col < n; col++) {
			List<Integer> list = new ArrayList<>();
			for(int row = 0; row < n; row++) {
				if(tempArr[row][col] != 0) {
					list.add(tempArr[row][col]);
				}
			}
			
			List<Integer> mergedList = mergeBlocks(list);
			
			for(int row = 0; row < n; row++) {
				if(row < mergedList.size()) {
					tempArr[row][col] = mergedList.get(row);
				} else {
					tempArr[row][col] = 0;
				}
			}
		}
	}
	
	public static void moveLeft() {
		for(int row = 0; row < n; row++) {
			List<Integer> list = new ArrayList<>();
			for(int col = 0; col < n; col++) {
				if(tempArr[row][col] != 0) {
					list.add(tempArr[row][col]);
				}
			}
			
			List<Integer> mergedList = mergeBlocks(list);
			
			for(int col = 0; col < n; col++) {
				if(col < mergedList.size()) {
					tempArr[row][col] = mergedList.get(col);
				} else {
					tempArr[row][col] = 0;
				}
			}
		}
	}

	public static void moveDown() {
		for(int col = 0; col < n; col++) {
			List<Integer> list = new ArrayList<>();
			for(int row = n - 1; row >= 0; row--) {
				if(tempArr[row][col] != 0) {
					list.add(tempArr[row][col]);
				}
			}
			
			List<Integer> mergedList = mergeBlocks(list);
			
			for(int row = n - 1; row >= 0; row--) {
				if((n - 1 - row) < mergedList.size()) {
					tempArr[row][col] = mergedList.get(n - 1 - row);
				} else {
					tempArr[row][col] = 0;
				}
			}
		}
	}

	public static void moveRight() {
		for(int row = 0; row < n; row++) {
			List<Integer> list = new ArrayList<>();
			for(int col = n - 1; col >= 0; col--) {
				if(tempArr[row][col] != 0) {
					list.add(tempArr[row][col]);
				}
			}
			
			List<Integer> mergedList = mergeBlocks(list);
			
			for(int col = n - 1; col >= 0; col--) {
				if((n - 1 - col) < mergedList.size()) {
					tempArr[row][col] = mergedList.get(n - 1 - col);
				} else {
					tempArr[row][col] = 0;
				}
			}
		}
	}
	
	public static List<Integer> mergeBlocks(List<Integer> list) {
		if(list.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<Integer> mergedList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			if(i + 1 < list.size() && list.get(i).equals(list.get(i + 1))) {
				mergedList.add(list.get(i) + 1);
				i++; // 다음 블록은 이미 합쳐졌으니 건너뛰기
			} else {
				mergedList.add(list.get(i));
			}
		}
		return mergedList;
	}
	
	public static int findMaxValue() {
		int val = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				val = Math.max(val, tempArr[i][j]);
			}
		}
		return val;
	}
	
	public static void printArr(int[][] array) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(array[i][j] == 0)
					System.out.print(0 + " ");
				else System.out.print((1 << array[i][j]) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}