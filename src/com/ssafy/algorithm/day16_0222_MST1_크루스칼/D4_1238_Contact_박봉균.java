package com.ssafy.algorithm.day16_0222_MST1_크루스칼;
import java.io.*;
import java.util.*;


/** 
 * <pre>
 * SWEA 1238 [D4] : Contact
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: DFS로 접근했으나, 가중치(1) 같은 최단경로 구하는 문제임을 파악. -> BFS
 *     ==> 연락할 수 있는 여러 경로 중 최단 경로로 연락함. 가장 나중 = 최단 경로 중 최대. 이중 정점번호의 최대를 구하는 문제.
 * 결과: 18676KB, 110ms
 */

/* <요약>
 * 비상연락망과 연락을 시작하는 당번에 대한 정보가 주어질 때, 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구하시오.
 * 당번에서 시작하여 연락망(방향 그래프)을 따라 연락. 한 번 연락을 받은 사람에게 다시 연락X. 연락을 받을 수 없는 사람도 존재할 수 있다.
[입력]
10개 테케: 
각 테케] 1) 데이터의 길이 L, 시작점 start
       2)  {from, to, from, to, …} 형식 (연락 인원 <= 100명이며, 번호: 1~100)
[출력]
#테케번호 <공백> 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람
 */
public class D4_1238_Contact_박봉균 {
	/** 데이터 길이 L, 시작점 start */
	static int L, start;
	/** 인접리스트 */
	static LinkedList<Integer>[] adjList = new LinkedList[104];
	/** 방문 체크 배열 */
	static int[] visited = new int[104];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//10개 테케에 대하여
		for(int t = 1; t <= 10; t++) {
			//초기화
			for(int i = 0; i < adjList.length; i++)
				adjList[i] = new LinkedList<>();
			Arrays.fill(visited, 0);
			
			//데이터 길이 L, 시작 정점 번호 start 입력
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken()) - 1;
			
			//{from, to, from, to, …} 형식 입력 -> 유향 인접리스트
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				
				if(adjList[from].contains(to) == false)
						adjList[from].add(to);
			}
			
			//각 정점에 대한 최단 경로를 구합니다.
			bfs(start);
			
			//구한 최단 경로 중 최댓값(가장 나중)을 가지는 정점 중 최대 번호를 구합니다.
			int max = Integer.MIN_VALUE;
			int ret = Integer.MIN_VALUE;
			for(int i = 0; i < visited.length; i++) {
				if(max <= visited[i]) {
					max = visited[i];
					ret = i;
				}
			}
			
			//가장 나중에 연락을 받게 되는 사람 중 최대 번호 출력
			bw.write("#" + String.valueOf(t) + " " + String.valueOf(ret + 1) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();	
	}
	
	/** BFS */
	static void bfs(int start) {		
		Queue<Integer> q = new ArrayDeque<>();
		
		visited[start] = 1;
		q.add(start);
		
		while(!q.isEmpty()) {
			int now = q.remove();
			
			for(int next : adjList[now]) {
				//방문하지 않았다면
				if(visited[next] == 0) {
					visited[next] = visited[now] + 1; //방문처리, 가중치 더하기
					q.add(next);
				}
			}
		}
	}
}
