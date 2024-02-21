package com.ssafy.algorithm.day15_0221_서로소집합;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * SWEA 3289 [D4] : 서로소 집합
 * </pre>
 * @author 박봉균
 * 
 * 아이디어: 시간 초과 나서 당황했지만 Path Compression 최적화로 해결
 * 결과: 102292KB, 428ms
 */

/* <요약>
 * 초기에 {1}, {2}, ... {n} 이 각각 n개의 집합을 이루고 있다.
 * 여기에 합집합 연산(union)과, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산(find)을 수행해보자
[입력]
1) 테케 수 T
각 테케] 1) 원소 수 N, 연산 수 M. (1 ≤ N ≤1e6), (1 ≤ M ≤ 1e5)
     M개 줄) 각각의 연산(union: 0 a b 형태, find: 1 a b 형태, 1 <= a, b <= N)
union: a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합친다는 의미.
find: a와 b가 같은 집합에 포함되어 있는지를 확인하는 연산이다.

[출력]
각 테스트 케이스) 한줄에 연속하여 find 연산결과 출력(1: 같은 집합에 포함, 0: 다른 집합).
 */
public class D4_3289_서로소집합_박봉균 {
	/** 원소 수 N, 연산 수 M */
	static int N, M;
	/** 부모 정점 번호 저장 배열 */
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); //테케 개수 T
		
		//T개의 테케에 대하여
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
					
			//원소 수 N, 연산 수 M 입력, 동적할당
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N + 4];
			
			//부모 배열 parent 초기화, 정점 생성(초기: 자기 자신)
			for(int i = 1; i <= N; i++) 
				parent[i] = i;
			
			
			bw.write("#" + String.valueOf(t) + " "); //테케 번호 출력
			
			//주어진 입력에 대응하는 연산 수행, 결과 출력
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int oper = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				//연산번호: 0: union 
				if(oper == 0) {
					union(a, b);
				}
				//연산번호: 1: find
				else if(oper == 1) {
					bw.write(String.valueOf(find(a,b)));
				}
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	/**
	 * 같은 집합에 포함되어 있는지를 확인하는 함수(루트가 같은가?)
	 */
	static int find(int a, int b) {
		if(find(a) == find(b))
			return 1;
		else
			return 0;
	}
	
	/** 
	 * 루트를 찾는 함수 
	 * Path Compression 최적화 적용
	 */
	static int find(int a) {
		if(parent[a] == a) //루트라면 해당 정점번호 return
			return a;
		return parent[a] = find(parent[a]); //Path Compression
	}
	
	/** 합집합: a속한 집합과 b속한 집합 합친다(루트끼리) */
	static void union(int a, int b) {
		//1. 루트를 찾는다
		int rootA = find(a);
		int rootB = find(b);
		
		//2. 같은 집합 속해있으면 return
		if(rootA == rootB)
			return;
		//3. 아니라면 a의 루트에 b루트 연결
		else
			parent[rootB] = rootA;
	}
}
