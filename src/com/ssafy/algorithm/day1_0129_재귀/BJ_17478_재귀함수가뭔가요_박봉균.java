package com.ssafy.algorithm.day1_0129_재귀;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ 17478 : 재귀함수가 뭔가요
 * </pre>
 * 
 * @author 박봉균
 * @since JDK8
 * @see <a href="https://www.acmicpc.net/source/72551142"></a>
 *      결과: "맞았습니다!!", 23000KB, 616ms
 */

public class BJ_17478_재귀함수가뭔가요_박봉균 {
	static int end; // 재귀 횟수

	static void recur(int depth) {
		printUnder(depth * 4);
		System.out.println("\"재귀함수가 뭔가요?\"");
		// 종료 조건
		if (depth == end) {
			printUnder(depth * 4);
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			printUnder(depth * 4);
			System.out.println("라고 답변하였지.");
			return;
		}
		printUnder(depth * 4);
		System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		printUnder(depth * 4);
		System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		printUnder(depth * 4);
		System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		recur(depth + 1);
		printUnder(depth * 4);
		System.out.println("라고 답변하였지.");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		end = sc.nextInt();
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		recur(0);
	}

	/**
	 * n개의 '_'를 출력하는 함수
	 * 
	 * @param n
	 */
	static void printUnder(int n) {
		for (int i = 0; i < n; i++)
			System.out.print("_");
	}
}
