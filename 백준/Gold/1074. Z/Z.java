/**
 * @author 이정균
 * 2^N x 2^N 2차원배열을 Z모양으로 탐색
 * N > 1 이면 4등분 후에 재귀적으로 순서대로 방문
 * r행 c열을 몇번째로 방문하는지
 * 
 * 1. N, row, col 입력
 * 2. 가로, 세로 사이즈인 2^N 구하기 -> size
 * 3. 순서를 찾기 위해 0, 0부터 재귀적으로 돌면서 Z모양으로 탐색하는 함수 findSequence(x, y, size)
 * 	3-1. size가 1이면 r, c가 속한 칸이기 때문에 count출력
 *  3-2. 재귀를 위해 사이즈를 반으로 줄이기
 *  3-3. Z모양으로 재귀적으로 탐색 (좌상, 우상, 좌하, 우하)
 * 
 */
import java.io.*;
import java.util.*;
public class Main {
	static int n, row, col;
	static int result = 0;
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		//1. N, row, col 입력
		n = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		//2. 가로, 세로 사이즈인 2^N 구하기 -> size
		int size = (int) Math.pow(2, n);
		
		findSequence(0, 0, size);
	}
	
	public static void findSequence(int x, int y, int size) {
		//3-1. size가 1이면 r, c가 속한 칸이기 때문에 count출력
		if(size == 1) {
			System.out.println(result);
			return;
		}
		
		//3-2. 재귀를 위해 사이즈를 반으로 줄이기
		int nextSize = size / 2;
		
		//3-3. Z모양으로 재귀적으로 탐색하는데 r, c가 포함된 사분면 탐색하고 사분면별로 스킵할 숫자 더하기 (좌상, 우상, 좌하, 우하)
		//좌상은 그대로, 우상은 nextSize * nextSize, 좌하는 2 * nextSize * nextSize, 우하는 3 * nextSize * nextSize
		//좌상
		if (row < x + nextSize && col < y + nextSize) {
			findSequence(x, y, nextSize);
		}
		//우상
		else if (row < x + nextSize && col >= y + nextSize) {
			int skip = nextSize * nextSize;
			result += skip;
			findSequence(x, y + nextSize, nextSize);
		}
		//좌하
		else if (row >= x + nextSize && col < y + nextSize) {
			int skip = 2 * nextSize * nextSize;
			result += skip;
			findSequence(x + nextSize, y, nextSize);
		} else{
			int skip = 3 * nextSize * nextSize;
			result += skip;
			findSequence(x + nextSize, y + nextSize, nextSize);
		}
	}
}
