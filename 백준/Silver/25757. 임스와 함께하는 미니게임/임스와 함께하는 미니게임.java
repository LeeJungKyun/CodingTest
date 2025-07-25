import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받기
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 신청 횟수
        char gameType = input[1].charAt(0); // 게임 종류
        
        // 중복된 참가자를 제외한 참가자들을 저장할 HashSet 생성
        HashSet<String> participants = new HashSet<>();
        
        // 참가자 이름 입력
        for (int i = 0; i < N; i++) {
            participants.add(br.readLine());
        }
        
        // GamePlayer 객체 생성하여 게임 종류와 참가자들을 전달
        GamePlayer gamePlayer = new GamePlayer(participants, gameType);
        
        // 임스와 함께 최대로 게임을 할 수 있는 횟수 계산
        int maxGamesWithImes = gamePlayer.getMaxGames();
        
        // 최대 게임 수 출력
        System.out.println(maxGamesWithImes);
    }
}
 
class GamePlayer {
    private HashSet<String> participants; // 참가자들을 저장할 HashSet
    private char gameType; // 게임 종류를 저장할 변수
    
    // constructor
    public GamePlayer(HashSet<String> participants, char gameType) {
        this.participants = participants;
        this.gameType = gameType;
    }
    
    // 임스와 함께 최대로 게임을 할 수 있는 횟수를 계산하는 메서드
    public int getMaxGames() {
        int maxPlayers; // 게임 종류에 따른 최대 플레이어 수를 저장할 변수
        if (gameType == 'Y') {
            maxPlayers = 2;
        } else if (gameType == 'F') {
            maxPlayers = 3;
        } else {
            maxPlayers = 4;
        }
        
        // 임스를 제외한 참가자 수를 해당 게임의 최대 플레이어 수에서 1을 뺀 값으로 나누어 최대 게임 횟수를 계산
        int maxGamesWithImes = (participants.size()) / (maxPlayers - 1);
        
        return maxGamesWithImes;
    }
}