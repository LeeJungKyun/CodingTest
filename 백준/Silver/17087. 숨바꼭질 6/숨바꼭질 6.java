import java.util.Scanner;

public class Main {

	static int GCD(int n1, int n2) {
		if(n2 ==0) return n1;
		else return GCD(n2,n1%n2);
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n =sc.nextInt(); 
		int s = sc.nextInt();
		int [] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			int a = sc.nextInt();
			arr[i] = Math.abs(s-a);
		}
		
		int gcd = arr[0];
		for(int i=1; i<arr.length; i++) {
			gcd = GCD(gcd,arr[i]);
		}
		System.out.println(gcd);
		
	}

}