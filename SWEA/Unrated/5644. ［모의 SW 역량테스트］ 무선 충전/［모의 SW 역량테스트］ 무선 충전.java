/**
 * @author 이정균
 * 이동X 상 우 하 좌
 *
 */

import java.io.*;
import java.util.*;

public class Solution {
	static class Person {
		int x, y, dir;

		public Person(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	// x 좌표, y 좌표, AP정보들을 갖고 있게
	static class Point {
		int x, y;
		ArrayList<AP> apList;

		public Point(int x, int y, ArrayList<AP> apList) {
			super();
			this.x = x;
			this.y = y;
			this.apList = apList;
		}
	}

	static class AP {
		int x, y, range, power;

		public AP(int x, int y, int range, int power) {
			super();
			this.x = x;
			this.y = y;
			this.range = range;
			this.power = power;
		}
	}

	static int testCase, moveCount, batteryCount;
	static int[] dx = { 0, -1, 0, 1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Person personA, personB;
	static int answer;

	static int[][] moveCommand;
	static AP[] apList;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			moveCount = Integer.parseInt(st.nextToken());
			batteryCount = Integer.parseInt(st.nextToken());

			personA = new Person(1, 1, 0);
			personB = new Person(10, 10, 0);

			// 사용자 A와 B의 이동 정보 입력받기
			moveCommand = new int[2][moveCount + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= moveCount; i++) {
				moveCommand[0][i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= moveCount; i++) {
				moveCommand[1][i] = Integer.parseInt(st.nextToken());
			}

			apList = new AP[batteryCount];
			// batteryCount개수만큼 AP정보 받기
			for (int i = 0; i < batteryCount; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				apList[i] = new AP(y, x, range, power);
			}

			// 시뮬레이션 시작 (시작 위치도 포함)
			getMaxCharge();
			for (int i = 0; i < moveCount; i++) {
				movePeople(i);
				getMaxCharge();
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	public static void movePeople(int idx) {
		int dirA = moveCommand[0][idx + 1];
	    int dirB = moveCommand[1][idx + 1];
		// A이동
		personA.x = personA.x + dx[dirA];
		personA.y = personA.y + dy[dirA];

		// B이동
		personB.x = personB.x + dx[dirB];
		personB.y = personB.y + dy[dirB];
	}

	public static void getMaxCharge() {
	    int maxCharge = 0;

	    ArrayList<Integer> aList = new ArrayList<>();
	    ArrayList<Integer> bList = new ArrayList<>();

	    for (int i = 0; i < batteryCount; i++) {
	        AP ap = apList[i];
	        int distA = Math.abs(personA.x - ap.x) + Math.abs(personA.y - ap.y);
	        int distB = Math.abs(personB.x - ap.x) + Math.abs(personB.y - ap.y);
	        
	        if (distA <= ap.range) aList.add(i);
	        if (distB <= ap.range) bList.add(i);
	    }

	    if (aList.isEmpty() && bList.isEmpty()) {
	        maxCharge = 0;
	    } else if (aList.isEmpty()) {
	        for (int b : bList) {
	            maxCharge = Math.max(maxCharge, apList[b].power);
	        }
	    } else if (bList.isEmpty()) {
	        for (int a : aList) {
	            maxCharge = Math.max(maxCharge, apList[a].power);
	        }
	    } else {
	        for (int a : aList) {
	            for (int b : bList) {
	                int sum = 0;
	                if (a == b) {
	                    sum = apList[a].power;
	                } else {
	                    sum = apList[a].power + apList[b].power;
	                }
	                maxCharge = Math.max(maxCharge, sum);
	            }
	        }
	    }
	    answer += maxCharge;
	}
}
