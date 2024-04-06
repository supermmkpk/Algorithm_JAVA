import java.io.*;
import java.util.*;

/**
 * 결과: 11736KB, 84ms
 */
public class Main_2749_피보나치수3_박봉균 {
    static final long DIV = 1000000L;
    static long N;

    static Map<Long, Long> dp = new HashMap<>();

    static long fibo(long n) {
        // 기저사례
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else if (n == 2)
            return 1;

        // 메모이제이션
        if (dp.containsKey(n))
            return dp.get(n);

        // 로직
        long k = n / 2;
        dp.put(n, (fibo(n - k) * fibo(k + 1) + fibo(n - k - 1) * fibo(k)) % DIV);

        return dp.get(n);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Long.parseLong(br.readLine());

        bw.write(String.valueOf(fibo(N)) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}
