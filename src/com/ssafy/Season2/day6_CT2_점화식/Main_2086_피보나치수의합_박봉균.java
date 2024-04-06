import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        아이디어: 0~n까지 f의 누적합 = fn+2 - 1 이다.
 *        결과: 11648KB, 84ms
 */

/*
 * 피보나치 수열의 a번째 항부터 b번째 항까지의 합을 구하시오.
 * 1,000,000,000으로 나눈 나머지를 구하면 된다.
 * [입력]
 * 1) a, b.
 * 1 ≤ a ≤ b ≤ 9,000,000,000,000,000,000
 * [출력]
 * 구한 합
 */
public class Main_2086_피보나치수의합_박봉균 {
    static final long DIV = (long) 1e9;
    static long A, B;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        // 0~A누적합 - 0~(B-1)누적합
        // % 연산 결과가 음수일 수 있으므로 +DIV 하고 다시 %DIV
        long ret = (fibo(B + 2) - fibo(A + 1) + DIV) % DIV;

        bw.write(String.valueOf(ret) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}
