import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int result = 0;
        int temp = 0;
        int R = 0;
        int B = 0;
        int Y = 0;
        int G = 0;

        int[] num = new int[10];
        int min = 10;
        int max = 0;
        for(int i=0;i<5;i++){
            StringTokenizer st  = new StringTokenizer(br.readLine());
            String word = st.nextToken();
            switch (word){
                case "R":
                    R++;
                    break;
                case "B":
                    B++;
                    break;
                case "Y":
                    Y++;
                    break;
                case "G":
                    G++;
                    break;
            }
            int number = Integer.parseInt(st.nextToken());
            if(min>number){ //가장 작은 수 (연속적인 숫자 찾을때 사용)
                min = number;
            }
            if(max<number){ //가장 큰 수 (결과값 구할 때 사용)
                max = number;
            }
            num[number]++;

        }
        //알파벳 케이스 시작
        if((R==5||B==5||Y==5||G==5)){ //1번 , 4번 케이스
                                    //(카드 5장같은 색)
            temp = max+ 600; //숫자가 연속적이지 않을때
            if(result<temp){
                result = temp;
            }
            for(int i=min;i<min+5;i++){
                if(num[i]!=1){//숫자가 연속적이지 않을때
                    break;
                }
                else if(i == min+4){//숫자가 연속적일때
                    temp = max+900;
                    if(result<temp){
                        result = temp;
                    }
                }
            }
        }else{ //5번 숫자가 연속적일때(카드 5장같은 색 아님)
            for(int i=min;i<min+5;i++){
                if(num[i]!=1){
                    break;
                }
                else if(i == min+4){
                    temp = max+500;
                    if(result<temp){
                        result = temp;
                    }
                }
            }
        }
        //알파벳 케이스 끝
        //숫자 케이스 시작
        for(int i=1;i<10;i++){
            if(num[i]==4){ //2번 (4장이 같은 수)
                temp = 800+i;
                if(result<temp){
                    result = temp;
                }
            }if(num[i]==3){//3번, 6번 (3장이 같은 수)
                temp=i+400; // 6번 (나머지 2장이 같은 수가 아닐때)
                if(result<temp){
                    result = temp;
                }
                for(int j=1;j<10;j++){
                    if(num[j]==2){ //3번 (나머지 2장이 같은 수)
                        temp = i*10+j+700;
                        if(result<temp){
                            result = temp;
                        }
                        break;
                    }
                }
            }if(num[i]==2) { //3번, 7번, 8번 (2장이 같은 수)
                temp = i + 200; //8번 (나머지 같은 수 없음)
                if (result < temp) {
                    result = temp;
                }
                for (int j = i+1; j < 10; j++) {//7번 (2장만 같을때)
                    if (num[j] == 2) {
                        temp = j * 10 + i + 300;
                        if (result < temp) {
                            result = temp;
                        }
                        break;
                    }
                }
                for(int j=0;j<10;j++){//3번 (나머지 다 같을 때)
                    if(num[j] == 3){
                        temp = j*10+i+700;
                        if(result<temp){
                            result = temp;
                        }
                        break;
                    }
                }
            }
        }
        //숫자 케이스 끝
        if(result==0){ //9번 어떠한 경우에도 해당되지 않음
            result = max+100;
        }


        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}