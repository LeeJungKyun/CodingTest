import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * N x N 정사각형 방에 사람들이 P, 계단 입구 S가 주어짐
 * 이동완료시간 == 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * 사람들이 아래층으로 이동하는 시간 == 계단 입구까지 이동시간 + 계단을 내려가는 시간
 * 계단입구까지 이동시간 == 맨해튼거리
 * 
 * 계단을 내려가는 시간 == 입구에 도착한 후부터 계단을 완전히 내려갈 때까지의 시간
 * 계단 입구에 도착하면, 1분 후 아래칸으로 내려갈 수 있다.
 * 계단의 크기는 최대 3, 이미 다 차있으면 입구에서 대기
 * 계단의 길이는 K로, 올라간 후 부터 K분이 걸린다.
 * 
 * 1. size 입력
 * 2. 지도 입력 (0 빈공간, 1 사람, 나머지 계단)
 */

public class Solution {
	//클래스 선언부
	static class Stair {
		int x, y, length;

		public Stair(int x, int y, int length) {
			this.x = x;
			this.y = y;
			this.length = length;
		}
	}
	
	static class Person {
		int x, y, time;

		public Person(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int n;
	static int[][] arr, tempArr;
	static int[] stairArrivalTime;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int minTime;
	static ArrayList<Person> peopleList;
	static ArrayList<Stair> stairs;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			
			//필요 변수 초기화
			peopleList = new ArrayList<>();
			stairs = new ArrayList<>();
			minTime = MAX;
			
			//1. 방 사이즈 입력
			n = Integer.parseInt(br.readLine());
			
			//2. 방 배열 입력
			arr = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					//사람인 경우
					if(arr[i][j] == 1) 
						peopleList.add(new Person(i, j, 0));
					else if(arr[i][j] > 1)
						stairs.add(new Stair(i, j, arr[i][j]));
				}	
			}
			
			//3. 비트마스킹으로 사람들을 나누고 나눈대로 계단 이용해서 최소 시간 구하기
			int peopleCount = peopleList.size();
			//조합은 2 ^ peopleCount
			for(int mask = 0; mask < (1 << peopleCount); mask++) {
				//조합 정해짐
				//0번 인덱스의 계단을 쓸 사람들과 1번 인덱스의 계단을 쓸 사람들을 나눔
				ArrayList<Integer> zeroStairPeopleIndex = new ArrayList<>();
				ArrayList<Integer> oneStairPeople = new ArrayList<>();
				for(int i = 0; i < peopleCount; i++) {
					if((mask & (1 << i)) != 0) {
						oneStairPeople.add(i);
					} else zeroStairPeopleIndex.add(i);
				}
				
				//0번 계단 쓸 사람들의 인덱스와 1번 계단 쓸 사람들의 인덱스를 정했으니깐 최소 시간 구하기
				int zeroStairTime = goDown(zeroStairPeopleIndex, 0);
				int oneStairTime = goDown(oneStairPeople, 1);
				minTime = Math.min(minTime, Math.max(zeroStairTime, oneStairTime));
			}
			
			sb.append('#').append(tc).append(' ').append(minTime).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int goDown(ArrayList<Integer> peopleIndex, int stairNum) {
	    Stair stair = stairs.get(stairNum);
	    int stairLength = stair.length;

	    // 1. 각 사람의 계단 도착 시간 + 1분 대기 포함
	    ArrayList<Integer> arrivalTimes = new ArrayList<>();

	    for (int idx : peopleIndex) {
	        Person p = peopleList.get(idx);
	        int dist = Math.abs(p.x - stair.x) + Math.abs(p.y - stair.y);
	        // +1분 대기 포함
	        arrivalTimes.add(dist + 1);
	    }

	    // 2. 도착 시간순 정렬
	    Collections.sort(arrivalTimes);

	    // 3. 계단에 올라가 있는 사람들의 완료 시간 저장 큐
	    Queue<Integer> stairQueue = new LinkedList<>();
	    int time = 0;

	    for (int arrival : arrivalTimes) {
	        // 먼저, 현재 도착 시간보다 먼저 내려간 사람들 제거
	        while (!stairQueue.isEmpty() && stairQueue.peek() <= arrival) {
	            stairQueue.poll();
	        }

	        // 계단에 3명 미만이면 바로 진입 가능
	        if (stairQueue.size() < 3) {
	            int exitTime = arrival + stairLength;
	            stairQueue.offer(exitTime);
	            time = Math.max(time, exitTime);
	        } else {
	            // 대기 -> 가장 먼저 나가는 시간까지 기다림
	            int earliestExit = stairQueue.poll();
	            int startTime = Math.max(arrival, earliestExit);
	            int exitTime = startTime + stairLength;
	            stairQueue.offer(exitTime);
	            time = Math.max(time, exitTime);
	        }
	    }

	    return time;
	}
}