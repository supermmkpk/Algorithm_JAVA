package com.ssafy.algorithm.day2_0130_완탐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BJ_15961[G4] : 회전초밥
 *
 */
public class reviewSlidingWindow {

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
