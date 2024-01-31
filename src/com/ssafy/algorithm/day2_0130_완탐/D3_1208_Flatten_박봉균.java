package com.ssafy.algorithm.day2_0130_완탐;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 1208 [D3] : Flatten
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: (min구하고, max 구하고, max->min) 반복 : 재귀 => 종료조건: 평탄화 완료 or 횟수 초과 
 * 결과: Pass
 */

/* 문제 요약
 * 평탄화: 높은 곳의 상자를 낮은 곳으로 옮겨서(덤프) 최고점과 최저점의 간격을 줄이는 작업
 * 		-> 완료 시, 최고점 - 최저점  = 최대 1 이내
 * 제한된 횟수만큼 옮긴 후, 최고점과 최저점의 차이를 반환하라.
[입력]
10개의 테스트 케이스,(가로길이=100)
 	1) 덤프 횟수 (1 <= <= 1000)
 	2) 각 상자의 높이값 (1 <= <= 100)
[출력]
#테케번호 <공백> 최고최저높이차 
(덤프 횟수 이내에 평탄화 완료 : 0 or 1)
 */
public class D3_1208_Flatten_박봉균 {
	/** 덤프 가능 횟수 */
	static int N;
	/** 가로 위치(인덱스)별 높이 저장 배열  */
	static LinkedList<Integer> height = new LinkedList<>();
	
	/**
	 * 평탄화 과정을 수행하는 함수입니다
	 * @param cnt : 지금까지 수행한 평탄화 횟수
	 */
	static void dump(int cnt) {
		Collections.sort(height); //정렬
		
		//종료조건! : 평탄화 완료, 횟수 초과
		if(height.getLast() - height.getFirst() <= 1 || cnt >= N)
			return;
		
		//평탄화를 수행합니다(max->min). 즉, 최고점--, 최저점++
		height.addLast(height.pollLast() - 1);
		height.addFirst(height.pollFirst() + 1);
		
		dump(cnt + 1); //동일한 과정을 수행합니다
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for(int t = 1; t <= 10; t++) { //10개의 테케에 대하여
			//테케 초기화!!
			height.clear();
			N = Integer.parseInt(br.readLine()); //N 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) 
				height.add(Integer.parseInt(st.nextToken())); //높이 입력
			
			dump(0); //평탄화 수행
			//출력 조건에 따라 테케 별 최고점과 최저점의 높이차를 출력합니다.
			bw.write("#" + t + " " + String.valueOf(height.getLast() - height.getFirst()));
			bw.write('\n'); //개행
		}
		bw.flush(); //데이터 출력
		//stream을 닫습니다.
		bw.close();
		br.close();
	}
}
