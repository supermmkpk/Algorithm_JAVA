package com.ssafy.algorithm.day5_0202_스택과큐;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueAPITest {
	
	public static void main(String[] args) {
		Queue<String> q = new ArrayDeque<>();
		System.out.println(q.isEmpty() + "//" + q.size());	
		
		q.add("대전 : 갓병찬");
		q.add("서울 : 자바의신 전은수");
		q.add("서울 : 김동근");
		q.add("서울 : 닥터홍");
		q.add("서울 : The Java 조용준");
		q.add("광주 : 제기차기 선수 임경혜");
		q.add("부울경 : 축신 정상훈");
		q.add("서울 : 그녀는 예뻤다 이은진");
		q.add("구미 : 김재웅 김계희");
		q.add("대전 : 양유진");
		
		System.out.println(q.peek());
		System.out.println(q.isEmpty() + "//" + q.size());		
		System.out.println(q.remove());
		System.out.println(q.isEmpty() + "//" + q.size());
		System.out.println("==============================");
		
		while(!q.isEmpty()) {
			System.out.println(q.remove());
		}
	}
}