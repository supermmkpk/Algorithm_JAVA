package com.ssafy._1일1알골.algo_0204;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1417 : 국회의원 선거
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72912693"> </a>
 * 
 * 아이디어: 배열에 번호별 지지자 수 저장 -> 내림차순 정렬하고 당선 가능일때까지 매수 진행
 * 결과: 11640KB, 80ms
 */

/*
 * 어떤 사람이 누구를 찍을 지 정했으면, 반드시 선거때 그 사람을 찍는다.
 * 국회의원 후보는 N명이다. 다솜이는 주민 M명의 마음을 모두 읽었다.
 * 다솜이는 기호 1번이다. 다솜이는 사람들의 마음을 읽어서 자신을 찍지 않으려는 사람을 돈으로 매수해서 국회의원에 당선이 되게 하려고 한다. 
 *   Ex) 1번 5표, 2번 7표, 3번 7표 : 2번 1명과, 3번 1명을 매수하면, 당선.
 * 매수해야하는 사람의 최솟값을 출력하라.
[입력]
1) 후보의 수 N. (1 <= N <= 50)
N개 줄) 차례대로 1 ~ N번을 찍으려고 하는 사람의 수. (1 <= 득표수 <= 100)

[출력]
매수해야 하는 사람의 최솟값.
 */
public class Main_1417_국회의원선거_박봉균 {
	/** 후보의 수 N */
	static int N;
	/** 번호 별 지지자 수 저장 (인덱스: 2~N 번) */
	static Integer[] stat;
	/** 결과: 매수해야 하는 사람 수(최소) */
	static int ret = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine()); //후보 수 N 입력
		stat = new Integer[N+1]; //stat[] 동적 할당
		Arrays.fill(stat, 0); //초기화
		
		int cur = Integer.parseInt(br.readLine()); //1번 지지자수
		//후보 번호별 지지자수 입력(2~N)
		for(int i = 2; i <= N; i++) 
			stat[i] = Integer.parseInt(br.readLine());
		
		
		//내림차순 정렬, 최대 < 1번 (당선 가능)일때까지 매수 진행
		ret = 0;
		Arrays.sort(stat, Collections.reverseOrder());
		while(cur <= stat[0]) {
			stat[0]--; //매수: 지지자 최대 보유 번호의 지지자수 감소
			cur++; //다솜의 지지자수 증가
			ret++; //결과(매수한 사람 수) 증가
			Arrays.sort(stat, Collections.reverseOrder()); //내림차순 정렬
		}
		
		//출력 및 stream을 닫습니다.
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();	
	}
}
