import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long kg = sc.nextLong();
		long count = 0;
		
		while(kg%5!=0 && kg>0) {
			kg-=3;
			count++;
		}
		
		if(kg%5==0) count += kg/5;
		else count = -1;
		
		System.out.println(count);

	}

}