package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ2252 [G3] : 줄 세우기
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: A -> B 순서 있는 간선 주어진다. 이 순서 지키며 줄 세워야 한다. 결과가 여러 가지인 경우에는 아무거나 출력한다
 *     ==> 위상 정렬
 * 결과: 48664KB, 452ms
 */

/* <요약>
 * N명의 학생들을 키 순서대로 줄을 세우려 한다. 
 * 두 학생의 키를 비교하는 방법을 사용하기로 하였다. 일부 학생들의 키만을 비교해 보았다.
 * 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세워 보자.
[입력]
1) 학생 수 N, 키 비교 회수 M. (1 ≤ N ≤ 32,000, 1 ≤ M ≤ 100,000)M은 이다. 
M개 줄) 두 학생 번호 A, B. (A는 B 앞에 서야 한다. A키 < B키)
[출력]
1) 줄. (여러 가지인 경우에는 아무거나 출력한다. -> 위상 정렬) 
 */
public class Main_2252_줄세우기_박봉균 {
	/** 학생 수 N, 키 비교 횟수 M */
	static int N, M; 
	/** 진입차수 배열 */
	static int[] inCnt;
	/** 인접리스트 */
	static LinkedList<Integer>[] adjList;
	static StringBuilder sb = new StringBuilder();
	
	/** 위상 정렬을 수행하는 함수 */
	static void tpSort() {
		Queue<Integer> q = new ArrayDeque<>(); 
		
		//진입차수 0인 애들 큐에 넣기
		for(int i = 1; i < adjList.length; i++) {
			if(inCnt[i] == 0)
				q.add(i);
		}
		
		while(!q.isEmpty()) {
			int now = q.remove();
			sb.append(now + " ");
			
			//현재 정점의 모든 인접 노드에 대하여
			for(int next : adjList[now]) {
				//간선 없애기(진입 차수 감소)
				inCnt[next]--;
				
				//진입차수 0이면 큐에 추가
				if(inCnt[next] == 0) 
					q.add(next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		
		//학생 수 N, 키 비교 횟수 M 입력, 동적할당
		//N: 정점 수, M: 간선 수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inCnt = new int[N + 1];
		adjList = new LinkedList[N + 1];
		for(int i = 0; i < adjList.length; i++) 
			adjList[i] = new LinkedList<>();
		
		//키 비교 입력(간선 입력 - 방향 있음!)
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer( br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to); //간선 입력
			inCnt[to]++; //들어가는 정점의 진입차수 증가
		}
		
		//위상 정렬 수행
		tpSort();
		
		//위상 정렬 결과(줄세우기 결과) 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
