import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		char[][] cookie=new char[n+1][n+1];
		
		int heart_x=0,heart_y=0;
		boolean head=false;
		for(int i=1;i<=n;i++) {
			String str=sc.next();
			for(int j=1;j<=n;j++) {
				char ch=str.charAt(j-1);
				
				if(ch=='*'&&!head) {
					heart_x=i+1;
					heart_y=j;
					head=true;
				}
				cookie[i][j]=ch;
			}
		}
		
		// 왼쪽 손 길이 계산
		int left_arm=0;
		for(int j=heart_y-1;j>0;j--)
			if(cookie[heart_x][j]=='*')
				left_arm++;
		
		int right_arm=0;
		for(int j=heart_y+1;j<=n;j++)
			if(cookie[heart_x][j]=='*')
				right_arm++;
		
		int waist=0;
		int waist_x=0, waist_y=heart_y;
		for(int i=heart_x+1;i<=n;i++) {
			if(cookie[i][heart_y]=='*') {
				waist++;
				waist_x=i; 
			}
		}
		
		int left_leg=0;
		for(int i=waist_x+1;i<=n;i++)
			if(cookie[i][heart_y-1]=='*')
				left_leg++;
		
		int right_leg=0;
		for(int i=waist_x+1;i<=n;i++)
			if(cookie[i][heart_y+1]=='*')
				right_leg++;
				
		
		System.out.println(heart_x+" "+heart_y);
		System.out.println(left_arm+" "+right_arm+" "+waist+" "+left_leg+" "+right_leg);
	}
}