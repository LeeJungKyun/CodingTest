/**
 * @author 이정균
 * <가능한 결과 조건>
 * - 각 나라의 경기수의 합이 5
 * - 승의 개수 == 패의 개수
 * - 무승부 개수의 총합은 짝수여야함
 * 1. 각 줄은 테스트케이스 -> 총 4줄 입력
 * 2. 각 줄에서 팀 별 결과를 입력 받기
 * 3. 팀 별 결과 배열 초기화
 * 4. 간단한 조건으로 가능한지 아닌지 확인하는 플래그 earlyFail 초기화
 * 	4-1. 팀별로 입력 받으면서 승, 무, 패의 합이 15가 아니면 earlyFail = false 후 break
 * 5. 유효하면 결과를 results배열에 넣기
 * 6. earlyFail조건 확인 시작
 * 	6-1. 승, 패 개수 확인
 *  6-2. 무승부 개수 확인
 *  6-3. earlyFail 했으면 0 출력 후 continue
 * 7. 결과를 만들기 위해 makeResult 백트래킹 실행
 * 	7-1. isPossible == true면 return
 *  7-2. 끝까지 본 경우 isPossible = true
 *  7-3. 현재 선택된 경기에 참여한 팀 team, opponent
 *  7-4. team이 승리할 경우, 무승부인 경우, 패배할 경우 각각에 대해서 해당 팀의 승, 무, 패가 남았는지 확인하고
 *  7-5. 남았으면 -- 하고 백트래킹
 *  7-6. 백트래킹이 끝났으면 다시 ++
 */

import java.io.*;
import java.util.*;

public class Main {
	static class Result {
		int win, draw, lose;
		public Result(int win, int draw, int lose) {
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}
	}
	static int totalSum, totalDraw, totalLose;
	static BufferedReader br;
	static StringTokenizer st;
	static Result[] results;
	static int[][] matches = new int[15][2];
	static boolean isPossible;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//나올 수 있는 경기 조합
		int idx = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				matches[idx][0] = i;
				matches[idx][1] = j;
				idx++;
			}
		}
		//1. 각 줄은 테스트케이스 -> 총 4줄 입력
		for(int tc = 0; tc < 4; tc++) {
			totalSum = 0; totalDraw = 0; totalLose = 0;
			//2. 각 줄에서 팀 별 결과를 입력 받기
			st = new StringTokenizer(br.readLine());
			//3. 팀 별 결과 배열 초기화
			results = new Result[6];
			//4. 간단한 조건으로 가능한지 아닌지 확인하는 플래그 earlyFail 초기화
			boolean earlyFail = false;
			for(int team = 0; team < 6; team++) {
				//4-1. 팀별로 입력 받으면서 승, 무, 패의 합이 15가 아니면 earlyFail = false 후 break
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				if(win + draw + lose != 5) {
					earlyFail = true;
					break;
				}
				totalSum += win;
				totalDraw += draw;
				totalLose += lose;
				//5. 유효하면 결과를 results배열에 넣기
				results[team] = new Result(win, draw, lose);
			}
			//팀별 성적 입력 끝
			
			//6. earlyFail조건 확인 시작
			//6-1. 승, 패 개수 확인
			if(totalSum != totalLose) {
				earlyFail = true;
			}
			
			//6-2. 무승부 개수 확인
			if(totalDraw % 2 != 0) {
				earlyFail = true;
			}
			
			//6-3. earlyFail 했으면 0 출력 후 continue
			if(earlyFail) {
				sb.append(0).append(' ');
				continue;
			}
				
			isPossible = false;
			makeResult(0);
			sb.append(isPossible ? 1 : 0).append(' ');
		}
		System.out.println(sb);
	}
	
	static void makeResult(int selectedGame) {
		//7-1. isPossible == true면 return
		if (isPossible) return;
		
		//7-2. 끝까지 본 경우 isPossible = true
		if (selectedGame == 15) {
			isPossible = true;
			return;
		}

		//7-3. 현재 선택된 경기에 참여한 팀 team, opponent
		int team = matches[selectedGame][0];
		int opponent = matches[selectedGame][1];

		//7-4. team이 승리할 경우, 무승부인 경우, 패배할 경우 각각에 대해서 해당 팀의 승, 무, 패가 남았는지 확인하고
		// 승
		if (results[team].win > 0 && results[opponent].lose > 0) {
			//7-5. 남았으면 -- 하고 백트래킹
			results[team].win--;
			results[opponent].lose--;
			makeResult(selectedGame + 1);
			//7-6. 백트래킹이 끝났으면 다시 ++
			results[team].win++;
			results[opponent].lose++;
		}

		// 무
		if (results[team].draw > 0 && results[opponent].draw > 0) {
			results[team].draw--;
			results[opponent].draw--;
			makeResult(selectedGame + 1);
			results[team].draw++;
			results[opponent].draw++;
		}

		// 패
		if (results[team].lose > 0 && results[opponent].win > 0) {
			results[team].lose--;
			results[opponent].win--;
			makeResult(selectedGame + 1);
			results[team].lose++;
			results[opponent].win++;
		}
	}
}
