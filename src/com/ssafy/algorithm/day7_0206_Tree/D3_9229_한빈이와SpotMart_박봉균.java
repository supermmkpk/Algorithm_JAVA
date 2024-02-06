package com.ssafy.algorithm.day7_0206_Tree;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 9229 [D3] : 한빈이와 Spot Mart
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 2개 뽑는 조합을 구해서 더하고 무게 통과하는 경우 PQ에 넣어서 최대 관리
 * 결과: Pass, 0.10142s
 */

/* <문제 요약>
 * 한빈이는 과자 두 봉지를 사서 양 손에 하나씩 들고 가려 한다. 
 * 스팟마트에는 N개의 과자 봉지가 있으며, 각 과자 봉지는 ai 그램이다.
 * 최대한 무게가 많이 나가는 과자를 고르고 싶으나, 두 봉지의 무게가 M 그램을 초과하면 안 된다.
 * 구입 가능한 최대 무게 합을 출력하라. (단, 한빈이는 “정확히” 두 봉지 사야 한다)
[입력]
1) 테케 수 TC
각 테케] 1) 과자 봉지의 개수 N, 무게 합 제한 M. (2 ≤ N ≤ 1000 , 2 ≤ M ≤ 2000000)
    N개 줄) 각 과자봉지의 무게. (1 ≤ ai ≤ 1e6)

[출력]
#테케번호 <공백> 무게 합의 최대 (방법이 없는 경우, -1)
 */
public class D3_9229_한빈이와SpotMart_박봉균 {
	/** 과자 봉지의 개수 N, 무게 합 제한 M */
	static int N, M;
	/** 과자 봉지 무게 배열 */
	static int[] weight;
	/** 우선순위 큐 (내림차순) */
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); //테스트케이스 수 T 입력
		
		//T개의 테스트케이스에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화
			pq.clear();
			
			//과자 봉지의 개수 N, 무게 합 제한 M 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//weight[] 동적할당
			weight = new int[N];
			
			//무게 입력
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) 
				weight[i] = Integer.parseInt(st.nextToken());
			
			//2개 조합 찾아서 우선순위 큐에 넣기
			for(int i = 0; i < N; i++) {
				for(int j = i + 1; j < N; j++) {
					int weightNow = weight[i] + weight[j];
					
					//무게 통과하면 PriorityQueue에 넣어서 최댓값 관리
					if(weightNow <= M)
						pq.add(weightNow);
				}
			}
			
			//출력 조건에 따라 최댓값(PQ의 front) 출력.
			bw.write("#" + String.valueOf(t) + " ");
			if(!pq.isEmpty())	
				 bw.write(String.valueOf(pq.remove()) + '\n');
			else 
				 bw.write("-1" + '\n'); //가능한 경우 없으면 -1 출력.
				
		}
		//데이터 출력 및 stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();	
	}
}
