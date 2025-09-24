import java.io.*;
import java.util.*;

public class Main {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부

	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		//코드 작성 시작
		String input = br.readLine();
		
		int length = input.length();
		
		for(int i = 0; i < length; i++) {
			System.out.println(i);
		}
	}
}