import java.io.*;
import java.util.*;

/**
 * 결과: 11596KB, 76ms
 */
public class Main_2748_피보나치수2_박봉균 {
    static int N;
    static long[] dp;

    static long fibo(int n) {
        // 기저사례
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;

        // 메모이제이션
        if (dp[n] != 0)
            return dp[n];

        // 로직
        return dp[n] = fibo(n - 1) + fibo(n - 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        dp = new long[N + 4];

        // dp 초기화(default 0)

        bw.write(String.valueOf(fibo(N)) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}
