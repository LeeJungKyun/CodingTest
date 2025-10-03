import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
    private static boolean isStraight(String[] cards) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (String card : cards) {
            char suit = card.charAt(1);
            int num = card.charAt(0) - '0';
            map.computeIfAbsent(suit, k -> new ArrayList<>()).add(num);
        }

        for (List<Integer> nums : map.values()) {
            if (nums.size() < 3) {
                continue;
            }
            Set<Integer> setNums = new HashSet<>(nums);
            List<Integer> sortedSetNums = new ArrayList<>(setNums);
            Collections.sort(sortedSetNums);

            if (sortedSetNums.size() >= 3) {
                for (int i = 0; i <= sortedSetNums.size() - 3; i++) {
                    if (sortedSetNums.get(i) + 1 == sortedSetNums.get(i + 1)&&
                            sortedSetNums.get(i + 1) + 1 == sortedSetNums.get(i + 2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isTripleOrMore(String[] cards) {
        String[] sortedCards = cards.clone();
        Arrays.sort(sortedCards);
        return sortedCards[0].equals(sortedCards[2]) ||
                sortedCards[1].equals(sortedCards[3]);
    }

    private static boolean isTwoPairOrFour(String[] cards) {
        Map<String, Long> counts = Arrays.stream(cards)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return counts.values().stream()
                .allMatch(count -> count == 2 || count == 4);
    }

    private static boolean isHolyDay(String[] cards) {
        return isStraight(cards) || isTripleOrMore(cards) || isTwoPairOrFour(cards);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            sb.append(isHolyDay(br.readLine().split(" ")) ? ":)" : ":(").append("\n");
        }

        System.out.println(sb);
    }
}