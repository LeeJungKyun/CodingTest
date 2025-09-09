import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * LIS를 구하는데 이분탐색으로 구함
 *
 */
public class Main {
	static int elementCount;
	static int[] elementArr, indexArr;
	static ArrayList<Integer> lisArr;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 배열의 크기 입력
		elementCount = Integer.parseInt(br.readLine());
		
		//2. 배열 입력
		elementArr = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < elementCount; i++) {
			elementArr[i] = Integer.parseInt(st.nextToken());
		}
		
		lisArr = new ArrayList<>();
		//원소가 lisList에서 들어간 인덱스 추적
		indexArr = new int[elementCount];
		
		for (int i = 0; i < elementCount; i++) {
            int currentNum = elementArr[i];
            int index = Collections.binarySearch(lisArr, currentNum);

            if (index < 0) {
                index = -(index + 1);
            }

            if (index == lisArr.size()) {
            	lisArr.add(currentNum);
            } else {
            	lisArr.set(index, currentNum);
            }
            indexArr[i] = index; // 현재 원소의 인덱스를 기록
        }
		
		sb.append(lisArr.size()).append('\n');
		
		// LIS 역추적
        Stack<Integer> resultStack = new Stack<>();
        int lisLength = lisArr.size() - 1;

        for (int i = elementCount - 1; i >= 0; i--) {
            if (indexArr[i] == lisLength) {
                resultStack.push(elementArr[i]);
                lisLength--;
            }
            if (lisLength < 0) {
                break;
            }
        }
        
        while(!resultStack.isEmpty()) {
        	sb.append(resultStack.pop()).append(" ");
        }
        
        System.out.println(sb);
	}
	
}