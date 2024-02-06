package com.ssafy.algorithm.day7_0206_Tree;

import java.util.ArrayDeque;
import java.util.Queue;

//완전이진트리 - 배열 구현
//크기 늘리지 않는 버전
public class CompleteBinaryTree<T> {
	
	private Object[] nodes;
	private final int SIZE;
	private int lastIndex;
	public CompleteBinaryTree(int size) {
		SIZE = size;
		nodes = new Object[size + 1]; //0 인덱스 사용하지 않음
	}
	
	public boolean isEmpty() {
		return lastIndex == 0;
	}
	
	public boolean isFull() {
		return lastIndex == SIZE;
	}
	
	public void add(T e) {
		if(isFull()) {
			System.out.println("포화상태입니다.");
			nodes[++lastIndex] = e;
		}		
		nodes[++lastIndex] = e;
	}
	
	public void bfs() {
		if(isEmpty())
			return;
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1); // 방문할 노드를 관리할 값 넣기(노드 번호 인덱스)
		
		while(!q.isEmpty()) {
			int current = q.remove();
			System.out.println(nodes[current]); //노드 처리 로직
			
			//왼쪽 자식 노드
			if(current * 2 <= lastIndex) 
				q.add(current * 2);
			
			//오른쪽 자식 노드
			if(current * 2 + 1 <= lastIndex) 
				q.add(current * 2 + 1);
		}
	}
	
}