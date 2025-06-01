import java.io.*;
import java.util.*;

public class Main {
    static class Country implements Comparable<Country> {
        int num, gold, silver, bronze;

        public Country(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country other){
            if(this.gold != other.gold) return other.gold - this.gold;
            if(this.silver != other.silver) return other.silver - this.silver;
            return other.bronze - this.bronze;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Country> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            list.add(new Country(num, gold, silver, bronze));
        }

        Collections.sort(list);  // 메달 기준 정렬

        int rank = 1;
        Country prev = list.get(0);

        if (prev.num == m) {
            System.out.println(rank);
            return;
        }

        for (int i = 1; i < list.size(); i++) {
            Country cur = list.get(i);
            if (cur.gold == prev.gold && cur.silver == prev.silver && cur.bronze == prev.bronze) {
                
            } else {
                rank = i + 1; // 등수 갱신
                prev = cur;
            }

            if (cur.num == m) {
                System.out.println(rank);
                return;
            }
        }
    }
}