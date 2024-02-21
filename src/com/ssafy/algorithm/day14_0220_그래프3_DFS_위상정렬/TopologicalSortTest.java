package com.ssafy.algorithm.day14_0220_그래프3_DFS_위상정렬;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

//순서가 정해져 있는 작업을 차례로 수행해야 할때
public class TopologicalSortTest {
	/** 정점 8개(1~8) */
	
	/** 진입차수 배열 */
	static int[] inCnt = new int[9];
    /** 인접 리스트 */
	static LinkedList<Integer>[] adjList = new LinkedList[10];
	
	static void topologySort() {
		Queue<Integer> q = new ArrayDeque<>();
		
		//1. 진입차수 0인 정점 큐에 넣기
        for(int i = 1; i < inCnt.length; i++)
        	if(inCnt[i] == 0) 
        		q.add(i);
        
		while(!q.isEmpty()) {
			//2. 진입차수 0인 노드 꺼내기
			int now = q.remove();
			//System.out.print(now + " ");
			
			for(int next : adjList[now]) {
				//3. 간선 제거 ==> 인접 노드의 진입차수 감소
				inCnt[next]--;
				
				//4. 인접 노드 진입차수 0인 경우 큐에 넣기
				if(inCnt[next] == 0) 
					q.add(next);
			}
		}
		
		for(int i=1; i<9; i++) {
			System.out.print(inCnt[i] + " ");
		}
	}
	
	public static void main(String[] args) {
        for (int i = 0; i < 9; i++) 
            adjList[i] = new LinkedList<>();

        // 그래프 각 노드별 인접한 노드정보 초기화
        adjList[1].add(2);
        adjList[1].add(4);
        adjList[2].add(5);
        adjList[2].add(6);
        adjList[3].add(6);
        adjList[4].add(2);
        adjList[4].add(8);
        adjList[7].add(3);
        adjList[8].add(6);

        // 진입차수 테이블 초기화 (1~8)
        inCnt[2] = 2;
        inCnt[3] = 1;
        inCnt[4] = 1;
        inCnt[5] = 1;
        inCnt[6] = 3;
        inCnt[8] = 1;
        
        //위상정렬
        topologySort();
        
        System.out.println();
	}
}

