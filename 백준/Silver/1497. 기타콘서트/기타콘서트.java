import java.util.*;
import java.io.*;

class Main {

    static int answer;

    static long convertToLong(String song) {

        StringBuilder sb = new StringBuilder();
        for (char c : song.toCharArray()) {
            if (c == 'Y') sb.append("1");
            else sb.append("0");
        }

        String num = sb.toString();
        return Long.parseLong(num, 2);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] songs = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            String song = st.nextToken();

            songs[i] = convertToLong(song);
        }


        int minGuitarCount = Integer.MAX_VALUE;
        int maxSongCount = -1;
        for (long i = 1; i < (1L << N); i++) { // 모든 경우의 수

            long songMask = 0; // 백트래킹
            int guitarCount = 0;
            for (int j = 0; j < N; j++) { // 뽑아야 되는 기타
                if ((i & (1L << j)) != 0) { // 뽑는 조건
                    guitarCount++;
                    songMask |= songs[j]; // 비트에 저장
                }
            }

            int songCount = Long.bitCount(songMask); // 가능한 노래 개수

            if (maxSongCount < songCount) {
                maxSongCount = songCount;
                minGuitarCount = guitarCount;
            } else if (maxSongCount == songCount) {
                minGuitarCount = Math.min(minGuitarCount, guitarCount);
            }

        }

        if (maxSongCount == 0) {
            System.out.print(-1);
        } else {
            System.out.print(minGuitarCount);
        }

    }
}