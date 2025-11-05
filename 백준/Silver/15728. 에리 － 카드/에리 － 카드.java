import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * n 장의 공유카드, n 장의 팀 숫자카드
 * 
 * 상대 팀은 우리팀의 팀 숫자카드 k장을 최선으로 견제
 *
 */
public class Main {
	static int n, k;
	static ArrayList<Integer> teamCards;
	static int[] shareCards;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//공유카드 입력
		shareCards = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			shareCards[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(shareCards);
		int minShare = shareCards[0];
		int maxShare = shareCards[n - 1];
		
		//팀카드 입력
		teamCards = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			teamCards.add(Integer.parseInt(st.nextToken()));
		Collections.sort(teamCards);
		
		for(int remove = 0; remove < k; remove++) {
			int minTeamCard = teamCards.get(0);
			int maxTeamCard = teamCards.get(teamCards.size() - 1);
			
			int maxTeamCardScore = Math.max(maxShare * maxTeamCard, minShare * maxTeamCard);
			int minTeamCardScore = Math.max(maxShare * minTeamCard, minShare * minTeamCard);
			
			if(minTeamCardScore > maxTeamCardScore)
				teamCards.remove(0);
			else teamCards.remove(teamCards.size() - 1);
		}
		
		
		int minTeamCard = teamCards.get(0);
		int maxTeamCard = teamCards.get(teamCards.size() - 1);
		
		int maxTeamCardScore = Math.max(maxShare * maxTeamCard, minShare * maxTeamCard);
		int minTeamCardScore = Math.max(maxShare * minTeamCard, minShare * minTeamCard);
		
		int result = Math.max(maxTeamCardScore, minTeamCardScore);
		System.out.println(result);
	}
}