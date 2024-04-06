import java.io.*;
import java.util.*;
import java.math.BigInteger;

/**
 * 결과: 21936KB, 144ms
 */
public class Main_10826_피보나치수4_박봉균 {
    static int N;
    static BigInteger[] dp;

    static BigInteger fibo(int n) {
        // 기저사례
        if (n == 0)
            return BigInteger.ZERO;
        else if (n == 1)
            return BigInteger.ONE;

        // 메모이제이션
        if (!dp[n].equals(BigInteger.ZERO))
            return dp[n];

        // 로직
        return dp[n] = fibo(n - 1).add(fibo(n - 2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        dp = new BigInteger[N + 4];

        // dp 초기화
        Arrays.fill(dp, BigInteger.ZERO);

        bw.write(fibo(N).toString() + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}
