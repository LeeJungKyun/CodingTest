import java.io.*;
import java.util.*;

/**
 *  일반 2차원 좌표평면 (x, y)
 *  모든 원자들의 이동속도는 1초에 1만큼의 거리를 이동
 *  두 개 이상의 원자가 동시에 충돌할 경우 모두 보유한 에너지를 방출하고 소멸
 *
 *  <구현 포인트>
 *  1. 영원히 소멸되지 않는 친구들도 있음 -> 어떻게 판단할건지
 *  	-> 최악의 경우는 -1000에서 시작하고 1000에서 시작하고 둘이 만나는경우 -> 2000초 후인데 좌표 2배이벤트할거니깐 4000초,,, 근데 도중에 survivalAtom의 크기가 0이면 일찍 나와도 될듯
 *  2. 0.5초의 단위가 있음 (0, 0) (3, 0)을 생각해보면 1.5초 뒤에 만날텐데 어떻게 처리할건지
 *      -> 그냥 2배 때려놓으면 될것 같음
 *
 *  N <= 1000
 *  1 <= 에너지 K <= 100
 *  위치가 -1000 ~ 1000 : 실제로 선언하면 터질 가능성 높음
 *  이동방향 : 상(0), 하(1), 좌(2), 우(3)
 *
 *  1. 테스트 케이스 입력
 *  2. 원자의 수 n 입력
 *  3. 원자들의 x위치, y위치, 이동방향과 보유 에너지가 주어짐
 *      3-1. 입력받을때, 좌표는 두배로 저장 (0.5초 방지)
 *
 * <시뮬레이션 로직>
 * 1. 모든 원자들이 평생 마주치지 않음이 보장될때까지 while문을 반복
 * 2. 모든 원소들에 대해서 한칸씩 자신의 방향으로 이동을 진행
 * 3. 자신의 방향대로 한칸을 이동하고 해당 좌표를 HashMap<key(좌표), value(Atom 리스트)>에 저장
 * 4. 만약에 키값이 이미 있다? 그러면 collided에 추가 -> 에너지 다 더해서 energySum에 합산
 * 5. 그렇지 않은 애들을 다시 atomArrayList에 저장
 * 6. 끝
 */

public class Solution {
    static class Atom {
        //좌표 정보
        int x, y;

        //방향 정보
        int dir;

        //보유 에너지
        int energy;

        public Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }
    static final int OFFSET = 1000;
    static final int SIZE = 4001;
    static final int MAX = SIZE * SIZE;
    static int testCase;
    static int atomCount;
    static int energySum = 0;
    static ArrayList<Atom> atomArrayList;
    //일반 좌표평면 기준으로 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        //1. 테스트케이스 입력
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            //변수 초기화
            energySum = 0;

            //2. 원자의 수 입력
            atomCount = Integer.parseInt(br.readLine());

            //3. 원자 정보 받기
            atomArrayList = new ArrayList<>();
            for (int idx = 0; idx < atomCount; idx++) {
                st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + OFFSET) * 2;
                int y = (Integer.parseInt(st.nextToken()) + OFFSET) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                //0.5초 방지를 위한 좌표 2배 이벤트
                Atom atom = new Atom(x, y, dir, energy);
                //원자들을 arrayList에 저장해두기
                atomArrayList.add(atom);
            }

            startMovingAtom();

            //출력 저장
            sb.append('#').append(tc).append(' ').append(energySum).append('\n');
        }
        System.out.println(sb);
    }

    public static void startMovingAtom() {
    	Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> collided = new HashSet<>();
        //반복문의 종료 조건 : 남은 모든 원자들이 평생 마주치지 않음이 보장될때 -> 4000초 이내로
        while (!atomArrayList.isEmpty()) {
        	map.clear();
            collided.clear();
            ArrayList<Atom> next = new ArrayList<>();
        	//원자 탐색 시작
            for (Atom atom : atomArrayList) {
            	//다음 좌표로 간 후에 HashMap에 저장, 범위에 제한은 없지만 그래도 초기에 주어진 위치 밖에서 충돌할일은 없음
            	atom.x += dx[atom.dir];
                atom.y += dy[atom.dir];
                
                if(atom.x < 0 || atom.x >= SIZE || atom.y < 0 || atom.y >= SIZE) continue;
                
                int atomKey = atom.x * SIZE + atom.y;
            	
            	//맵에 아직 해당 좌표가 없다면 새로 생성해서 넣기
                if (map.containsKey(atomKey)) {
                    map.put(atomKey, map.get(atomKey) + atom.energy);
                    collided.add(atomKey);
                } else {
                    map.put(atomKey, atom.energy);
                    next.add(atom);
                }
            }
            //원자 탐색 끝
            // 충돌 위치 에너지 합산
            for (int key : collided) {
                energySum += map.get(key);
            }
            
   
            atomArrayList.clear();
            for (Atom atom : next) {
                int key = atom.x * SIZE + atom.y;
                if (!collided.contains(key)) {
                	atomArrayList.add(atom);
                }
            }
        }
    }
}