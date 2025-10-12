import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 * N명의 참가자 -> 이름, 점수, 비공개 여부 3가지 값
 * 
 * 
 */

public class Main {
	// 클래스 선언부
	static class User implements Comparable<User> {
		String name;
		int score;
		boolean isHidden;

		public User(String name, int score, boolean isHidden) {
			this.name = name;
			this.score = score;
			this.isHidden = isHidden;
		}

		@Override
		public int compareTo(User o) {
			if (this.score != o.score) {
				return Integer.compare(o.score, this.score);
			}
			return this.name.compareTo(o.name);
		}
	}

	// 상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	// 변수 선언부
	static int n;
	static ArrayList<User> userList;
	// 입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 참가자 수 입력
		n = Integer.parseInt(br.readLine());

		// 2. 정보 입력
		userList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String cleaned = br.readLine().replaceAll("[\\[\\]{}\"]", "");
			String[] tokens = cleaned.split(",");
			String name = tokens[0].split(":")[1];
			int score = Integer.parseInt(tokens[1].split(":")[1]);
			boolean isHidden = tokens[2].split(":")[1].equals("1") ? true : false;

			userList.add(new User(name, score, isHidden));
		}
		
		Collections.sort(userList);
		int rank = 1;
		int prevScore = -1;
		int actualRank = 0;
		
		for(int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			
			if(user.score != prevScore) {
				prevScore = user.score;
				rank = actualRank + 1;
			}
			++actualRank;
			if(!user.isHidden) {
				sb.append(rank).append(" ").append(user.name).append(" ").append(user.score).append('\n');
			}
		}
		
		System.out.println(sb);
	}
}