package com.ssafy.algorithm.day17_0223_MST2_프림;
import java.io.*;
import java.util.*;

/** 
 * @author 박봉균
 * 아이디어: 슬라이딩 윈도우. 다만 회전 고려
 * 결과: 249772KB, 628ms
 */

/*[BJ15961 [G4] : 회전초밥]
 * 초밥의 종류를 번호로 표현할 때, 벨트 위에는 같은 번호 중복OK. 
 * 두 가지 행사를 통해서 매상을 올리고자 한다.
 *   1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공한다. 
 *   2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고, 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다. 
 *      만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 새로 만들어 손님에게 제공한다.  
 * 위 할인 행사에 참여하여 가능한 한 다양한 종류의 초밥을 먹으려고 한다. 위 그림의 예를 가지고 생각해보자. k=4이고, 30번 초밥을 쿠폰으로 받았다고 가정하자. 쿠폰을 고려하지 않으면 4가지 다른 초밥을 먹을 수 있는 경우는 (9, 7, 30, 2), (30, 2, 7, 9), (2, 7, 9, 25) 세 가지 경우가 있는데, 30번 초밥을 추가로 쿠폰으로 먹을 수 있으므로 (2, 7, 9, 25)를 고르면 5가지 종류의 초밥을 먹을 수 있다. 
 * 초밥 가짓수의 최댓값을 구하시오. 
[입력]
1) 접시의 수 N, 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c.
    (2 ≤ N ≤ 3e6, 2 ≤ d,k ≤ 3e3, k ≤ N, 1 ≤ c ≤ d)
N개 줄) 초밥의 종류를 나타내는 1 이상 d 이하의 정수
[출력]
먹을 수 있는 초밥의 최대 가짓수
 */
public class Main_15961_회전초밥_박봉균 {
	/** 접시의 수 N, 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c */
	static int N, d, k, c;
	/** 초밥 종류에 따른 카운트 */
	static int[] sushiCnt;
	/** 회전초밥 리스트 */
	static ArrayList<Integer> belt = new ArrayList<>();
	/** 결과 변수: 가능한 최대 종류 */
	static int ret = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //접시의 수
		d = Integer.parseInt(st.nextToken()); //접시 가짓수
		k = Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); //쿠폰 번호

		sushiCnt = new int[d + 4]; //초밥 종류별 카운트
		
		
		int right = 0; //오른쪽 값
		int status = 0; //가짓수
		
		//초기 윈도우
		for(int i = 0; i < k; i++) {
			right = Integer.parseInt(br.readLine());
			//오른쪽으로 들어오는 값은 추가. 
			//추가해서 1이 되면 이전에 없었던 초밥 종류로 가짓수 증가
			if(++sushiCnt[right] == 1) 
				status++;
			belt.add(right);
			
			//쿠폰이 윈도우에 포함돼 있지 않다면 쿠폰번호의 초밥 카운트 0
			//따라서 가짓수+1로 최대 확인후 갱신
			if(sushiCnt[c] == 0)
				ret = Math.max(ret, status + 1);
			else 
				ret = Math.max(ret, status);
		}
		
		//슬라이딩 윈도우
		for(int left = 1; left < N; left++) {
			int rightIdx = (left + k - 1) % N; //회전하므로 범위 넘어가면 보정
			
			if(k <= rightIdx && rightIdx < N) { //범위 내에서 입력 받으며 진행
				right = Integer.parseInt(br.readLine());
				belt.add(right);
			}
			else if(0 <= rightIdx && rightIdx < k) //범위 외(회전하여 연결되는 경우) 리스트에서 추출
				right = belt.get(rightIdx);
				
			//오른쪽 추가해서 1이 되면 이전에 없었던 초밥 종류로 가짓수 증가
			if(++sushiCnt[right] == 1)
				status++;
			
			//이전 윈도우의 왼쪽 제거. 제거해서 0이 되면 없어지는 것으로 가짓수 감소
			if(--sushiCnt[belt.get(left-1)] == 0)
				status--;		
			
			
			
			//쿠폰의 초밥 포함 안 하면 가짓수 추가 후 갱신
			if(sushiCnt[c] == 0)
				ret = Math.max(ret, status + 1);
			else 
				ret = Math.max(ret, status);
		}
		
		//가능한 초밥 가짓수의 최댓값 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
