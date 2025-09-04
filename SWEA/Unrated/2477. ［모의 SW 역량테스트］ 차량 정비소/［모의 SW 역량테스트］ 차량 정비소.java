import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 * 차량 정비소에 지갑을 두고 갔는데 돌려줘야함
 * 모두 전화해서 확인 불가능
 * N개의 접수 창구와 M개의 정비 창구가 있음 (1-based)
 *
 * 차량정비소 : 접수 창구에서 고장을 접수 -> 정비 창구에서 차량을 정비
 * 담당자의 처리 시간은 항상 일정
 *
 * 지금까지 차량 정비소를 방문한 고객은 K명 (1-based)
 * 번호가 k인 고객이 정비소에 도착하는 시간은 tk
 *
 * <접수 창구>
 * ????? 접수창구에 먼저 도착해도 고객번호가 낮은 사람이 먼저 들어감?
 *
 * 1. 고객번호 낮은 사람 먼저
 * 2. 빈 창구가 여러곳이면 창구번호가 작은 곳으로 감
 *
 * <정비 창구>
 * 1. 먼저 기다리는 고객이 우선
 * 2. 두 명 이상의 고객들이 동시에 접수를 완료하고 장비창구로 가면, 이용했던 접수 창구번호가 작은 고객이 우선
 * 3. 빈 창구가 여러곳이면 정비 창구번호가 작은 곳으로 감
 *
 * 1. 시간을 1분씩 증가시키면서 반복문을 수행
 * 2. 매 시간마다 현재 도착한 고객을 receptionWaitQueue에 추가
 * 3. 현재 시간에 처리가 완료된 고객들을 각 창구(정비 -> 접수 순)에서 처리
 * - 정비 완료: 최종 결과에 포함될 고객인지 확인하고 합산
 * - 접수 완료: 해당 고객을 repairWaitQueue로 이동
 * 4. 비어있는 창구(정비 -> 접수 순)에 대기열의 고객 배정
 * - PriorityQueue를 사용했으니깐 자동으로 우선순위가 가장 높은 고객 선택
 * 5. 모든 고객의 정비가 완료될 때까지 위 과정을 반복
 *
 */

public class Solution {

    static class Customer {
        int customerNo;
        int arrivalTime;
        int receptionNo, receptionStart, receptionEnd;
        int repairNo, repairStart, repairEnd;

        public Customer(int customerNo, int arrivalTime) {
            this.customerNo = customerNo;
            this.arrivalTime = arrivalTime;
        }
    }

    // 접수 창구 대기열 (고객 번호가 낮은 순)
    static PriorityQueue<Customer> receptionWaitQueue;
    // 정비 창구 대기열 (접수 완료 시간이 빠른 순, 접수 창구 번호가 낮은 순)
    static PriorityQueue<Customer> repairWaitQueue;
    // 모든 고객 정보
    static Customer[] allCustomers;

    static int N, M, K, A, B;
    static int[] receptionTime, repairTime;
    static Customer[] receptionDesk, repairDesk;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            receptionTime = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                receptionTime[i] = Integer.parseInt(st.nextToken());
            }

            repairTime = new int[M + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                repairTime[i] = Integer.parseInt(st.nextToken());
            }

            allCustomers = new Customer[K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                allCustomers[i] = new Customer(i, Integer.parseInt(st.nextToken()));
            }

            sb.append('#').append(tc).append(' ').append(simulate()).append('\n');
        }
        System.out.print(sb);
    }

    public static int simulate() {
        receptionWaitQueue = new PriorityQueue<>((c1, c2) -> c1.customerNo - c2.customerNo);
        repairWaitQueue = new PriorityQueue<>((c1, c2) -> {
            if (c1.receptionEnd == c2.receptionEnd) {
                return c1.receptionNo - c2.receptionNo;
            }
            return c1.receptionEnd - c2.receptionEnd;
        });

        receptionDesk = new Customer[N + 1];
        repairDesk = new Customer[M + 1];
        int finishedCustomers = 0;
        int customerIndex = 1;
        int time = 0;
        int resultSum = 0;

        while (finishedCustomers < K) {
            // 1. 현재 시간에 도착한 고객들 접수 창구 대기열에 추가
            while (customerIndex <= K && allCustomers[customerIndex].arrivalTime <= time) {
                receptionWaitQueue.add(allCustomers[customerIndex]);
                customerIndex++;
            }

            // 2. 정비 창구에서 처리 완료된 고객 처리
            for (int i = 1; i <= M; i++) {
                if (repairDesk[i] != null && repairDesk[i].repairEnd == time) {
                    Customer c = repairDesk[i];
                    if (c.receptionNo == A && c.repairNo == B) {
                        resultSum += c.customerNo;
                    }
                    repairDesk[i] = null;
                    finishedCustomers++;
                }
            }

            // 3. 접수 창구에서 처리 완료된 고객 정비 창구 대기열로 이동
            for (int i = 1; i <= N; i++) {
                if (receptionDesk[i] != null && receptionDesk[i].receptionEnd == time) {
                    repairWaitQueue.add(receptionDesk[i]);
                    receptionDesk[i] = null;
                }
            }

            // 4. 빈 정비 창구에 대기 중인 고객 배정
            for (int i = 1; i <= M; i++) {
                if (repairDesk[i] == null && !repairWaitQueue.isEmpty()) {
                    Customer c = repairWaitQueue.poll();
                    c.repairNo = i;
                    c.repairStart = time;
                    c.repairEnd = time + repairTime[i];
                    repairDesk[i] = c;
                }
            }

            // 5. 빈 접수 창구에 대기 중인 고객 배정
            for (int i = 1; i <= N; i++) {
                if (receptionDesk[i] == null && !receptionWaitQueue.isEmpty()) {
                    Customer c = receptionWaitQueue.poll();
                    c.receptionNo = i;
                    c.receptionStart = time;
                    c.receptionEnd = time + receptionTime[i];
                    receptionDesk[i] = c;
                }
            }

            time++;
        }
        return (resultSum == 0) ? -1 : resultSum;
    }
}