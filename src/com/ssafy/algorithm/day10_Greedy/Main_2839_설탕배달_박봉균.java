package com.ssafy.algorithm.day10_Greedy;
import java.io.*;

/**
 * <pre>
 * BJ2839 [S4] : 설탕 배달
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 결과: 11624KB, 76ms
 */

/*
 * 설탕을 정확하게 N킬로그램을 배달해야 한다. 
 * 설탕은 3킬로그램 봉지와 5킬로그램 봉지에 있다.
 * 최대한 적은 봉지를 들고 가려고 한다. 
 * 봉지 몇 개를 가져가면 되는지 구하시오.
[입력]
1) N. (3 ≤ N ≤ 5000)
[출력]
봉지의 최소 개수 (정확하게 N킬로그램을 만들 수 없다면 -1)
 */
public class Main_2839_설탕배달_박봉균 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); //배달해야 하는 무게 N 입력
		int cnt = 0; //봉지 개수
		
		//5kg에 담을 수 있을 때까지 3kg 사용
		while(N % 5 != 0) {
			N -= 3;
			cnt++;
			if(N < 0) { //불가능 -> -1 출력
				System.out.println(-1);
				return;
			}
		}
		
		//5kg 봉지에 담기
		cnt += N / 5; 

		//봉지 최소 개수 출력
		bw.write(String.valueOf(cnt) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
