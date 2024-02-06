package com.ssafy.algorithm.day6_0205_LinkedList;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ2493 [G5] : 탑
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 스택에서 현재 입력과 비교. 현재보다 작으면 pop(지금의 탑이 수신할거니까), 크거나 같으면 답 -> 결과배열에 저장
 *         {현재 인덱스, 높이} Pair를 스택에 푸시
 * 결과: 172864KB, 792ms
 */

/* <문제 요약>
 * N개의 서로 다른 높이의 탑을 수평선의 왼쪽부터 오른쪽 방향으로 차례로 세운다.
 * 레이저 송신기: 지표면과 "평행"하게 "왼쪽"으로 발사 -> 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능. 
 *   Ex) 6, 9, 5, 7, 4 
 *   5번 탑(4) -> 4번 탑(7), 4번 탑(7) -> 2번 탑(9), 3번 탑(5) -> 2번 탑(9). (2번 탑(9), 1번 탑(6): 발사-수신 X)
 * 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 구하라. 
[입력]
1) 탑의 수  N. (1 <= N <= 5e5)
2) N개의 탑들의 높이가 수평직선상에 놓인 순서대로 주어진다. (1<= 높이 <= 1e8 -> int OK)

[출력]
1) 주어진 탑들의 순서대로 발사한 레이저 신호를 수신한 탑들의 번호. (보낸 레이저 신호를 수신X : 0)
 */
public class Main_2493_탑_박봉균 {
	/** 탑 개수 N */
	static int N;
	/** {탑인덱스, 높이} 스택 */
	static Stack<Pair> stk = new Stack<>();
	/** 결과 배열 */
	static int[] ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //탑 개수 N 입력
		ret = new int[N]; //결과 배열 ret[] 동적할당

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int given = Integer.parseInt(st.nextToken()); //현재 높이 입력
			
			//현재 입력과 높이 비교(peek), 크거나 같으면 그 탑의 번호(인덱스+1)가 정답, 작으면 pop
			while(!stk.empty()) {
				if(given <= stk.peek().second) {
					ret[i] = stk.peek().first + 1; //탑 번호 결과배열에 저장(인덱스+1)
					break;
				}
				else {
					stk.pop(); //높이가 더 낮으면 pop
				}
			}
			
			if(stk.empty()) //송신 가능 탑이 없다면 0
				ret[i] = 0;
			
			stk.push(new Pair(i, given)); //입력 push
		}
		
		//결과 배열 출력
		for(int i = 0; i < N; i++) 
			bw.write(String.valueOf(ret[i]) + " ");
		bw.write('\n');
		
		//남은 데이터 출력 및  stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}

/**
 * Pair 클래스
 */
class Pair {
	int first;
	int second;
	Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

/*
5
6 9 5 7 4
*/