import java.io.*;
import java.util.*;

public class Main {
	static long n, r, p;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static long[] factorial;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		r = Long.parseLong(st.nextToken());
		p = Long.parseLong(st.nextToken());

		// 일단 n과 r을 p진법으로 변환이 필요함
		List<Long> nDigits = getBaseDigits(n, p);
		List<Long> rDigits = getBaseDigits(r, p);

		// 변환한 N과 R에 대해서 nCr을 구해서 mod p 하고 다 더해주면 되는데 자릿수를 어떻게 알지
		long result = 1;

		for (int i = 0; i < nDigits.size(); i++) {
			long ni = nDigits.get(i);
			long ri = (i < rDigits.size()) ? rDigits.get(i) : 0;

			if (ri > ni) {
				result = 0;
				break;
			}
			result *= combMod(ni, ri, p);
			result %= p;
		}
		System.out.println(result);
	}

    //진법 변환
	public static List<Long> getBaseDigits(long number, long base) {
		List<Long> digits = new ArrayList<>();
		while (number > 0) {
			digits.add(number % base);
			number /= base;
		}
		return digits;
	}

	public static long power(long a, long b, long mod) {
		long result = 1;
		a %= mod;

		while (b > 0) {
			if ((b & 1) == 1)
				result = (result * a) % mod;
			a = (a * a) % mod;
			b >>= 1;
		}
		return result;
	}

	public static long combMod(long n, long r, long p) {
		if (r == 0 || r == n)
			return 1;

		long numerator = 1;
		long denominator = 1;

		for (long i = 0; i < r; i++) {
			numerator = (numerator * (n - i)) % p;
			denominator = (denominator * (i + 1)) % p;
		}

		return (numerator * power(denominator, p - 2, p)) % p;
	}
}