class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String converted = Integer.toString(n, k);
        System.out.println(converted);
        String[] arr = converted.split("0");
        for (String str : arr) {
            if (str.equals("")) continue;
            if (isPrime(Long.parseLong(str))) {
                answer++;
            }
        }
        return answer;
    }

    public boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}