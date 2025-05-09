import java.util.*;
import java.io.*;

public class Main {
    static class Person{
        String name;
        int day, month, year;

        public Person(String name, int day, int month, int year) {
            this.name = name;
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            if (p1.year == p2.year) {
                if (p1.month == p2.month) {
                    return p1.day - p2.day;
                } return p1.month - p2.month;
            }
            return p1.year - p2.year;
        }
    }
    static int n;
    static ArrayList<Person> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());

            list.add(new Person(name, day, month, year));
        }

        Collections.sort(list, new PersonComparator());

        System.out.println(list.get(list.size() - 1).name);
        System.out.println(list.get(0).name);
    }
}
