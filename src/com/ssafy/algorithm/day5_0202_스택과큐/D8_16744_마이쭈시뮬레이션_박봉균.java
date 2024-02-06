package com.ssafy.algorithm.day5_0202_스택과큐;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 16744 [D8] : 마이쭈 시뮬레이션
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: -각 번호의 사람이 전에 받은 마이쭈 개수를 리스트에 저장하여 관리
 * 		- 큐에 사람 번호 넣고, remove 시, 그 번호가 전에 받았던 개수+1을 남은 마이쭈 개수에서 빼기. 
 * 		- remove한 번호 다시 add하고, 다음 추가돼야 할 번호 add 
 * 		- 위 과정을 남은 마이쭈 개수가 0 될때까지 반복
 * 결과: Pass, 0.10448s
 */

/* <문제 요약>
 * 1 / (1-1) 1, 2 / (1-2) 2, 1, 3 / (2-1) 1, 3, 2, 4 / (1-3) 1,3,2,4,1,5 / ...
[입력]
1) 테스트 케이스의 개수 T
그 아래로 각각 총 마이쭈 개수 N이 주어진다.(1 ≤ N ≤ 100)

[출력]
각 줄) #테케번호 <공백> 마지막으로_마이쭈_받은_사람의_번호
 */
public class D8_16744_마이쭈시뮬레이션_박봉균 {
	/** 마이쮸 개수 */
	static int N;
	/** 사용할 큐 */
	static Queue<Integer> q = new ArrayDeque<>();
	/** 전에 받은 마이쮸 개수 0 ~ : 1번 ~*/ 
	static ArrayList<Integer> mychew = new ArrayList<>();
	/** 다음 줄 설 사람 번호(현재 카운트) */
	static int cnt = 2;
	/** 남은 마이쭈 */
	static int remain;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine()); //테스트케이스 개수 T 입력
		
		//T개의 테스트케이스에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화
			q.clear();
			mychew.clear();
			//큐 초기화
			q.add(1);
			mychew.add(0);
			cnt = 2; //다음 사람은 2번
			
			//마이쭈 개수 N 입력
			N = Integer.parseInt(br.readLine());
			remain = N; //remain 초기화

			//마이쭈 받는 과정 반복
			int front = 1;
			while(true) {
				if(remain > 0) { //줄 수 있는지 체크
					//앞에 애 빼기
					front = q.remove();
					
					//앞에 애 마이쭈 주기
					int cur = mychew.get(front - 1); //전에 받은 마이쭈 개수
					remain = remain - cur - 1; //남은 마이쭈 갱신
					mychew.set(front - 1, cur + 1); //현재 보유 개수 갱신

					//다시 줄 서기
					q.add(front);
					q.add(cnt++);
					mychew.add(0);
				}
				else
					break;
			}
			bw.write("#" + t + " " + String.valueOf(front) + '\n'); //출력
		}
		//데이터 출력 및 stream 닫기
		bw.flush();
		bw.close();
		br.close();
	}
}