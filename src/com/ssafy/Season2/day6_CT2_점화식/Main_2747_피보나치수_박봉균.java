import java.io.*;
import java.util.*;

/**
 * 11596KB, 80ms
 */
public class Main_2747_피보나치수_박봉균 {
    static int N, dp[];

    static int fibo(int n) {
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
        dp = new int[N + 4];

        // dp 초기화(default 0)

        bw.write(String.valueOf(fibo(N)) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}
