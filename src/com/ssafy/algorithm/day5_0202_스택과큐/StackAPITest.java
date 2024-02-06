package com.ssafy.algorithm.day5_0202_스택과큐;

import java.util.Stack;

public class StackAPITest {
	
	public static void main(String[] args) {
		Stack<String> stk = new Stack<>();
		stk.push("대전 : 갓병찬");
		stk.push("서울 : 자바의신 전은수");
		stk.push("서울 : 김동근");
		stk.push("서울 : 닥터홍");
		stk.push("서울 : The Java 조용준");
		stk.push("광주 : 제기차기 선수 임경혜");
		stk.push("부울경 : 축신 정상훈");
		stk.push("서울 : 그녀는 예뻤다 이은진");
		stk.push("구미 : 김재웅 김계희");
		stk.push("대전 : 양유진");
		
		System.out.println(stk.peek());
		System.out.println(stk.empty() + "//" + stk.size());		
		System.out.println(stk.pop());
		System.out.println(stk.empty() + "//" + stk.size());
		System.out.println("==============================");
		
		while(!stk.empty()) {
			System.out.println(stk.pop());
		}
	}
}