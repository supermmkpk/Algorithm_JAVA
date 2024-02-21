package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ2623 [G3] : 음악프로그램
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 부분 순서를 지키며 정렬(순서를 정한다) -> 위상정렬!!
 * 결과: 12604KB, 108ms
 */

/* <요약>
 * 보조 PD들이 만든 순서들이 입력으로 주어질 때, 전체 가수의 순서를 정하시오.
[입력]
1) 가수의 수 N, 보조 PD의 수 M. (1 <= N <= 1000, 1 <= M <= 100, 가수 번호: 1~N). 
M개 줄) 보조 PD가 정한 순서들이
[출력]
N 개의 줄로 이뤄지며, 한 줄에 하나의 번호를 출력. (순서 결정 불가능: 0)
답이 여럿일 경우에는 아무거나 하나를 출력 한다.
 */
public class Main_2623_음악프로그램_박봉균 {
	/** 진입 차수 */
	static int[] inCnt;
	/** 가수 수 N, 보조PD 수 M */
	static int N, M;
	/** 인접 리스트 */
	static LinkedList<Integer>[] adjList;
	/** 결과 리스트 */
	static ArrayList<Integer> ret = new ArrayList<>();;
	
	/** 
	 * 위상 정렬
	 * @return : 성공: true, 실패: false
	 */
	static boolean tpSort() {
		Queue<Integer> q = new ArrayDeque<>();
		
		//진입차수 0인 정점 먼저 모두 큐에 추가
		for(int i = 1; i <= N; i++) {
			if(inCnt[i] == 0) {
				q.add(i);
			}
		}
		
		//모든 정점에 대하여
		for(int i = 1; i <= N; i++){
			//모든 정점 탐색 전 큐가 비면 위상정렬 실패
			if(q.isEmpty())
				return false;
			
			//현재 정점
			int now = q.remove();
			ret.add(now); //결과 리스트에 추가
			
			//모든 인접 정점에 대하여
			for(int next : adjList[now]) {
				inCnt[next]--; //간선 제거(진입 차수 감소)
				if(inCnt[next] == 0) //진입 차수 0인 정점 큐에 추가
					q.add(next);
			}
		}
		return true; //성공
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//가수 수 N, 보조PD 수 M, 동적할당
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inCnt =  new int[N + 1];
		adjList = new LinkedList[N + 1];
		for(int i = 0; i < adjList.length; i++)
			adjList[i] = new LinkedList<>();
		
		//보조PD가 정한 순서 입력 -> 인접리스트(유향 간선)
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken(); //보조PD가 담당하는 가수는 중요하지 않음
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			inCnt[to]++;
			from = to;
			while(st.hasMoreTokens()) {
				to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
				inCnt[to]++;
				from = to;
			}
		}

		//실패시: 0 출력
		if(tpSort() == false)
			bw.write("0\n");
		//성공 시: 위상 정렬 결과 출력
		else {
			for(int i : ret) 
				bw.write(String.valueOf(i) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
