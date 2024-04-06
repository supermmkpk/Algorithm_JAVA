import java.io.*;
import java.util.*;

/*
 * 자연수 N, R 이 주어질 때, C(N, R)를 1234567891로 나눈 나머지는?
[입력]
1) 테케 개수 T. (1 ≤ T ≤ 20)
각 테케] 1) N, R. (1 ≤ N ≤ 1000000, 0 ≤ R ≤ N)
[출력]
#테케번호 정답
 */
public class D3_5607_조합_박봉균 {
    static final int P = 1234567891;

    static int N, R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
        
            bw.write("#" + String.valueOf(t) + " ");
            bw.write(String.valueOf(combi(N, R, P)) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }

    //페르마 소정리 이용
    static long combi(int n, int r, int p) {
        if(r == 0) 
            return 1L;

        long[] fac = new long[n + 1];
        fac[0] = 1;

        for(int i = 1; i <= n; i++) {
            fac[i] = fac[i-1] * i % p;
        }

        return (fac[n] * power(fac[r], p-2, p) % p * power(fac[n-r], p-2, p) % p) % p;
    }
    static long power(long x, long y, long p) {
        long ret = 1L;
        x %= p;
        while(y > 0) {
            if(y % 2 == 1) {
                ret = (ret * x) % p;
            }
            y /= 2;
            x = (x * x) % p;
        }
        return ret;
    }

}
