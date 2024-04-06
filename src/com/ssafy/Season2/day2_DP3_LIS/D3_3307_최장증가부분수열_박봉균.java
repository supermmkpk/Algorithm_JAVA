import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 결과: 30468KB, 123 ms
 */
/* <SWEA3307_D3 최장 증가 부분 수열>
 * <시간제한: 2초>
 * LIS 길이를 계산하시오.
[입력]
1) 테케 수 T.
각 테케] 1) 수열의 길이 N. (1 ≤ N ≤ 1,000)
		2) 수열의 원소 N개. (1 ~ 2^31-1)
[출력]
#테케 <공백> LIS길이
*/
public class D3_3307_최장증가부분수열_박봉균 {

    static int N, arr[], dp[];
    static int lis; // 현재 lis길이

    /** 길이 별 dp배열에 넣을 위치를 이분탐색으로 구하는 함수 */
    static int binSearch(int lo, int hi, int target, int size) {
        int ret = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (dp[mid] >= target) {
                ret = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        if (lo == size) {
            return -1; // 모두 탐색했는데 없는 경우
        } else {
            return ret;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 초기화
            lis = 0;
            N = Integer.parseInt(br.readLine()); //입력 수열 길이
            arr = new int[N + 4]; //입력 수열
            dp = new int[N + 4]; //길이 배열

            //수열 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            //각 원소에 대하여
            for (int i = 0; i < N; i++) {
                int idx = binSearch(0, lis, arr[i], lis + 1);

                //삽입 위치 찾지 못했으면
                if (idx == -1) {
                    dp[lis++] = arr[i]; //뒤에 추가
                } else {
                    dp[idx] = arr[i]; //찾은 경우, 갱신
                }
            }

            //출력 조건에 따라 LIS 길이 출력
            bw.write("#" + String.valueOf(t) + " ");
            bw.write(String.valueOf(lis) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
