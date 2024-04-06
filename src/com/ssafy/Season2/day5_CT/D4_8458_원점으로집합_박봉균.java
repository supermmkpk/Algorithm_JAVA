import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        결과: 39696KB, 336ms
 */

/*
 * N개의 격자점이 있다.
 * 이 점들을 몇 번 움직여 모든 점을 원점((0, 0))으로 이동시키고 싶다.
 * 한 번의 움직임은 모든 점을 움직이게 하고,
 * i번째 움직임에서 각 점은 상하좌우로 i만큼의 거리를 반드시 이동해야 한다.
 * 최소 몇 번의 움직임으로 모든 점을 원점에 모을 수 있는가?
 * [입력]
 * 1)테케 수 T.
 * 각 테케] 1) N. (1 ≤ N ≤ 10)
 * N개 줄) i번째 줄에는 xi, yi(-10^9 ≤ xi,yi ≤ 10^9)
 * [출력]
 * #테케번호 <공백> 최소이동횟수(불가: -1)
 */
public class D4_8458_원점으로집합_박봉균 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 테케 개수 T

        // T개의 테케에 대하여
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine()); // 점 개수
            boolean flag = false; // 불가능

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // x좌표
            int y = Integer.parseInt(st.nextToken()); // y좌표
            int dist = Math.abs(x) + Math.abs(y); // 거리
            int maxDist = dist; // 최대 거리
            int prevDist = dist; // 이전 거리

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                dist = Math.abs(x) + Math.abs(y);

                // 거리의 홀짝 바뀌는지 체크 -> 바뀌면 불가능한 케이스
                if (prevDist % 2 != dist % 2)
                    flag = true;

                // 최대 및 이전 값 갱신
                maxDist = Math.max(maxDist, dist);
                prevDist = dist;
            }

            bw.write("#" + String.valueOf(t) + " ");
            if (flag)
                bw.write("-1\n");
            else {
                // 0~k까지의 누적합(이동거리)에 대하여
                int sum = 0;
                for (int i = 0;; i++) {
                    sum += i;
                    // 누적 이동거리가 최대 거리 이상이고,
                    // 0 또는 짝수라면: 이동 거리가 남더라도 왔다갔다 가능
                    if (sum >= maxDist && (maxDist - sum) % 2 == 0) {
                        bw.write(String.valueOf(i) + '\n');
                        break;
                    }
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}