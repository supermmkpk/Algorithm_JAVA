package com.ssafy.algorithm.day5_0202_스택과큐;
import java.util.*;

/**
 * <pre>
 * BJ2164 [S4] : 카드2 
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72828906"></a>
 * 
 * 아이디어: 큐 이용
 * 결과: 24988KB, 160ms
 */

/* <문제 요약> 
 * N장의 카드: 차례로 1~N의 번호, 1번이 제일 위에, N번이 제일 아래인 상태로 순서대로 카드가 놓여 있다.
 * 제일 위에 있는 카드를 버린다 -> 그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
 *   => 한 장 남을 때까지 반복.
 * Ex) N=4 : 1234 -> 342 -> 24 -> 4 (종료)
[입력]
1) N. (1 ≤ N ≤ 500,000)

[출력]
마지막에 남게 되는 하나의 카드의 번호.
 */
public class Main_2164_카드2_박봉균 {
	/** 큐 */
	static Queue<Integer> q = new ArrayDeque<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); //카드 개수 N 입력
		
		//1 ~ N 큐에 넣기
		for(int i = 1; i <= N; i++) 
			q.add(i);
		
		while(true) {
			//가장 앞에 remove, 그다음 앞 remove, add
			if(q.size() > 1) 
				q.remove();
			if(q.size() <= 1) 
				break;
			q.add(q.remove());
		}
		
		System.out.println(q.remove()); //출력
		sc.close();
	}
}
