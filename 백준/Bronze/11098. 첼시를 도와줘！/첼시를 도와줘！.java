import java.util.*;

public class Main {
    static int n;
    static int p;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int testCase = 0; testCase < n; testCase++) {
            p = sc.nextInt();
            HashMap<String, Integer> map = new HashMap<>();
            for (int i = 0; i < p; i++) {
                int price = sc.nextInt();
                String name = sc.next();
                map.put(name, price);
            }

            List<String> keySet = new ArrayList<>(map.keySet());

            keySet.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return map.get(o2).compareTo(map.get(o1));
                }
            });
            System.out.println(keySet.get(0));
        }
    }
}
