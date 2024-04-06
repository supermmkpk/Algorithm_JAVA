import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 아이디어:
    //부분집합 구하고 합? 완탐? 근데 N 크고, 시간이 너무 빠듯하다!
    //DP? - 메모이제이션 ㄴㄴ
    //누적합? 투포인터? ㄱㄱ!
 * 결과: 22640KB, 228ms
 */

/* <BJ1806_G4 : 부분합>
 * <시간제한: 0.5초>
 * 10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 있다.
 * 이 수열의 연속된 수들의 부분합 중 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하시오.
[입력]
1) N, S (10 ≤ N < 1e5, 0 < S ≤ 1e8)
2) 수열. (10,000이하 자연수)
[출력]
최소의 길이. (불가능: 0)
*/
public class Main_1806_부분합_박봉균 {
    static final int INF = (int) 1e9;

    static int N, S, sum[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //수열 원소 개수
        S = Integer.parseInt(st.nextToken()); //부분합 목적(하한)
        sum = new int[N + 4]; //누적합 배열

        //수열 입력, 누적합
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int left = 1, right = 1;
        int ret = INF;
        while(left <= N && right <= N) {
            //구간합이 S보다 작으면 범위 늘리기(right++)
            while(right <= N && sum[right] - sum[left - 1] < S) { 
                right++;
            }
            //구간합이 S이상이라면 다음 left, 길이 최소 갱신 
            if(sum[right] - sum[left - 1] >= S) {
                ret = Math.min(ret, right - left + 1); //길이 최소 갱신
                left++;
            }
        }
        bw.write(String.valueOf(ret==INF ? 0 : ret) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}
