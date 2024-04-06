import java.io.*;
import java.util.*;

public class Main {
	// 로직: /10 + %10
	static long[] number; // 카운팅(0~9)
	static long start, end, mul;
	static long ret;

	/** 각 자릿수 더하는 함수 */
	static void calc(long num) {
		for (long i = num; i > 0; i /= 10) {
			String s = Long.toString(i);
			int t = s.charAt(s.length() - 1) - '0';
			number[t] += mul;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 초기화!!
			st = new StringTokenizer(br.readLine());
			number = new long[10];
			ret = 0;
			mul = 1;

			start = Long.parseLong(st.nextToken());
			end = Long.parseLong(st.nextToken());

			if (start == 0)
				start = 1;
			while (start <= end) {
				while (start % 10 != 0 && start <= end) {
					calc(start);
					start++;
				}
				if (start > end)
					break;
				while (end % 10 != 9 && start <= end) {
					calc(end);
					end--;
				}
				long diff = end / 10 - start / 10 + 1;
				for (int i = 0; i < 10; i++)
					number[i] += diff * mul;
				mul *= 10;
				start /= 10;
				end /= 10;
			}
			for (int i = 1; i < 10; i++)
				ret += (i * number[i]);
			
			bw.write("#" + String.valueOf(t) + " " + String.valueOf(ret));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
