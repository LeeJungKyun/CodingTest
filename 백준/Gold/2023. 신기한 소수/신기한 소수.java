/**
 * @author 이정균
 * N자리의 숫자에서 신기한 소수
 * 신기한소수: 왼쪽부터 시작해서 1, 2, ... , N자리까지 모두 소수인 숫자
 * 1자리 중 소수인 숫자만 시작해서 dfs (2, 3, 5, 7)
 * dfs해서 나온 숫자들을 각각 isPrime 함수
 * 자릿수가 N에 도달하면 count++
 * 
 * 1. N입력받기
 * 2. findSpecificPrimeNumber라는 함수를 실행해서 파라미터로 1의 자리 숫자와 현재의 자리수를 넘김
 * 	2-1. 현재 자리수가 N이라면 출력 후 return
 * 	2-2. 파라미터로 넘어온 숫자를 한자리 올리고 1부터 9까지 중 홀수만 더해서 소수인지 판별하는 isPrime함수 실행
 * 		2-2-1. 2부터 루트 input 까지 돌리면서 소수 판별
 */

import java.io.*;
import java.util.*;

public class Main {
	static int n, count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//N 입력받기
		n = Integer.parseInt(br.readLine());
		//2. findSpecificPrimeNumber라는 함수를 실행해서 파라미터로 1의 자리 숫자와 현재의 자리수를 넘김
		findSpecificPrimeNumber(2, 1);
		findSpecificPrimeNumber(3, 1);
		findSpecificPrimeNumber(5, 1);
		findSpecificPrimeNumber(7, 1);
	}
	
	public static void findSpecificPrimeNumber(int currentNum, int digit) {
		//2-1. 현재 자리수가 N이라면 출력 후 return
		if(digit == n) {
			System.out.println(currentNum);
			return;
		}
		
		//2-2. 파라미터로 넘어온 숫자를 한자리 올리고 1부터 9까지 더해서 소수인지 판별
		for(int plus = 1; plus < 10; plus += 2) {
			int nextNum = 10 * currentNum + plus;
			if(isPrime(nextNum)) {
				findSpecificPrimeNumber(nextNum, digit + 1);
			}
		}
	}
	
	public static boolean isPrime(int num) {
		for(int divide = 2; divide <= Math.sqrt(num); divide++) {
			if(num % divide == 0)
				return false;		
		}
		return true;
	}
}
