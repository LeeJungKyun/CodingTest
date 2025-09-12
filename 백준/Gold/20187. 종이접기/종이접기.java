import java.io.*;
import java.util.*;


/**
 * @author 이정균
 * 
 * 일단 마지막 상태는 1 x 1 인 상태의 종이에 구멍을 표시해놓고
 * 명령을 역순으로 실행하면서 종이의 크기를 늘려가면서 반전을 시켜서 저장하면 될듯
 * 
 * 
 *
 */
public class Main {
	static int UP_LEFT = 0;
	static int UP_RIGHT = 1;
	static int DOWN_LEFT = 2;
	static int DOWN_RIGHT = 3;

    static int k;
    static String[] foldCommands;
    static int hole;
    static int[][] paper;

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 1. k 입력
        k = Integer.parseInt(br.readLine());

        // 2. 2k개의 접는 명령 입력
        foldCommands = new String[2 * k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * k; i++) {
            foldCommands[i] = st.nextToken();
        }

        // 3. 구멍 위치 입력
        hole = Integer.parseInt(br.readLine());

        // 4. 종이 초기 상태 (1x1 크기)
        paper = new int[1][1];
        paper[0][0] = hole;

        // 5. 명령을 역순으로 처리
        for (int i = 2 * k - 1; i >= 0; i--) {
            paper = unfoldPaper(paper, foldCommands[i]);
        }

        // 6. 결과 출력
        int n = 1 << k;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(paper[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    // 방향에 따라 종이를 펼치는 함수
    private static int[][] unfoldPaper(int[][] curPaper, String dir) {
        int h = curPaper.length;
        int w = curPaper[0].length;
        
        int[][] newPaper = new int[h][w];

        switch (dir) {
            case "D":
                newPaper = new int[2 * h][w];
                // 아래쪽이 대칭 (상하반전 + 회전)
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        newPaper[i][j] = rotate(curPaper[h - 1 - i][j], 2);
                    }
                }
                // 위쪽이 원본
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        newPaper[h + i][j] = curPaper[i][j];
                    }
                }
                break;

            case "U":
                newPaper = new int[2 * h][w];
                // 아래쪽이 원본 
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        newPaper[i][j] = curPaper[i][j];
                    }
                }
                //위쪽이 대칭
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        newPaper[h + i][j] = rotate(curPaper[h - 1 - i][j], 2);
                    }
                }
                break;

            case "R":
                newPaper = new int[h][2 * w];
                // 왼쪽이 원본 
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        newPaper[i][j] = rotate(curPaper[i][w - 1 - j], 1);
                    }
                }
                // 오른쪽이 대칭 (좌우반전 + 회전)
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        newPaper[i][w + j] = curPaper[i][j];
                    }
                }
                break;

            case "L":
                newPaper = new int[h][2 * w];
                // 왼쪽이 대칭 (좌우반전 + 회전)
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        newPaper[i][j] = curPaper[i][j];
                    }
                }
                // 오른쪽이 원본 
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        newPaper[i][w + j] = rotate(curPaper[i][w - 1 - j], 1);
                    }
                }
                break;
        }

        return newPaper;
    }

    // 구멍 번호 회전
    // 회전 방향: 1 = 좌우반전, 2 = 상하반전
    private static int rotate(int value, int axis) {
    	//좌우 반전
        int[] leftRight = {UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT};
        //상하반전
        int[] upDown = {DOWN_LEFT, DOWN_RIGHT, UP_LEFT, UP_RIGHT};

        if (axis == 1)
        	return leftRight[value];
        else
        	return upDown[value];
    }
}