import java.io.*;
import java.util.*;

public class Solution {

	static int[][] map;
	static int N, M, C, ans;
	static int[] target;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			target = new int[2];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝

			ans = 0;
			comb(0, 0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void comb(int tgtIdx, int srcIdx) {
		if (srcIdx == N * N)
			return;
		if (tgtIdx == 2) {
			// 두 구간 겹치면 안 됨 (벌통 구간이 겹치거나 줄 바뀌면 X)
			// 겹침
			if (target[0] + M - 1 >= target[1])
				return;
			 // 줄 다름
			if ((target[0] / N != (target[0] + M - 1) / N) || (target[1] / N != (target[1] + M - 1) / N))
				return;

			int res = getMaxHoney();
			ans = Math.max(ans, res);
			return;
		}
		target[tgtIdx] = srcIdx;
		comb(tgtIdx + 1, srcIdx + 1);
		comb(tgtIdx, srcIdx + 1);
	}

	static int getMaxHoney() {
		int honeyMax0 = calculateMaxHoney(target[0]);
		int honeyMax1 = calculateMaxHoney(target[1]);
		return honeyMax0 + honeyMax1;
	}

	static int calculateMaxHoney(int startIdx) {
		int maxVal = 0;
		int y = startIdx / N;
		int x = startIdx % N;

		int[] honey = new int[M];
		for (int i = 0; i < M; i++) {
			honey[i] = map[y][x + i];
		}

		int limit = 1 << M;
		for (int mask = 0; mask < limit; mask++) {
			int sum = 0;
			int powSum = 0;
			for (int i = 0; i < M; i++) {
				if ((mask & (1 << i)) != 0) {
					sum += honey[i];
					if (sum > C)
						break; // 초과 시 탐색 중단
					powSum += honey[i] * honey[i];
				}
			}
			
			if (sum <= C)
				maxVal = Math.max(maxVal, powSum);
		}
		return maxVal;
	}
}
