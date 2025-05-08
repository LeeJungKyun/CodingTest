import java.io.*;
import java.util.*;

public class Main {
    static class Picture {
        int num, count, time;

        public Picture(int num, int count, int time) {
            this.num = num;
            this.count = count;
            this.time = time;
        }
    }

    static class PictureComparator implements Comparator<Picture> {
        @Override
        public int compare(Picture p1, Picture p2) {
            if (p1.count != p2.count) {
                return p1.count - p2.count; // 추천 수가 적은 순
            } else {
                return p1.time - p2.time;   // 게시된 시간이 오래된 순
            }
        }
    }

    static List<Picture> list = new ArrayList<>();
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int time = 0;

        while (st.hasMoreTokens()) {
            int vote = Integer.parseInt(st.nextToken());
            //있는지 확인
            boolean flag = false;
            for (Picture picture : list) {
                if (picture.num == vote) {
                    flag = true;
                    picture.count++;
                    break;
                }
            }

            if (!flag) {
                if (list.size() >= n) {
                    Collections.sort(list, new PictureComparator());
                    list.remove(0);
                }
                list.add(new Picture(vote, 1, time));
            }

            time++;
        }

        Collections.sort(list, new Comparator<>(){
            @Override
            public int compare(Picture p1, Picture p2) {
                return p1.num - p2.num;
            }
        });

        for (Picture picture : list) {
            System.out.print(picture.num + " ");
        }
    }
}
