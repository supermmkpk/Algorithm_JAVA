package com.ssafy.algorithm.day2_0130_완탐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_17281[G4] : 야구
 *
 */
public class reviewImplementation {
	/** 총 이닝 수 */
	static int N;
	/** 타순 */
	static int[] seq = new int[9];
	/** 입력으로 받는 이닝 결과 */
	static int[][] results;
	/** 0(홈), 1(1루) ,2(2루) ,3(3루)에 선수가 있는지 확인 */
	static boolean[] hasPlayer = new boolean[4]; //각 루에는 한명씩만 있을 수 있다
	/** 점수 */
	static int score;
	/** 아웃카운트 */
	static int outCnt;
	/** 결과 변수 */
	static int ret = Integer.MIN_VALUE;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//총 이닝 수
		N = Integer.parseInt(br.readLine());
		results = new int[N][9]; //이닝 결과 배열 (행: 이닝, 열: 각 선수별 결과)
		
		//이닝 결과 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++)
				results[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//1번(0)은 이미 4번(3) 타자로 결정함. 
		seq[3] = 0;
		//타순 정하기
		permu(0, 0);
		
		//최대 점수 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 순열 구한 후 해당 case의 점수 계산, 최대 갱신 */
	static void permu(int cnt, int visited) {
		if(cnt == 9) {
//-타순 정함-// 			
//-로직시작-//
			//초기화!
			score = 0;
			outCnt = 0;
			Arrays.fill(hasPlayer, false);
			
//-이닝 적용-//
			//정해진 순서에서 타자의 결과 적용
			int inning = 0;
			int idx = 0;
			
			while(inning < N) {
				idx %= 9; //인덱스(0~8)벗어나면 보정 (순서는 항상 그대로)
				hasPlayer[0] = true; //홈에 선수 있음(타자)
				go(results[inning][seq[idx++]]); //해당 선수의 결과 적용
				
				//3아웃 -> 다음 이닝 
				if(outCnt >= 3) {
					inning++; //이닝 증가
					outCnt = 0; //아웃카운트 초기화
					Arrays.fill(hasPlayer, false);
				}
			}

			//결과 변수 갱신(최대)
			ret = Math.max(ret, score);
			return;
//-로직 끝-//
		}
		
		
		//순열 구하기
		for(int i = 1; i < 9; i++) {
			if((visited & 1 << i) != 0)
				continue;
			
			seq[cnt] = i;
			if(cnt == 2) //4번타자(3)는 1번(0)으로 이미 정하고 시작함. 
				permu(cnt + 2, visited | 1 << i); //따라서, 건너뛴다(cnt+2)
			
			else 
				permu(cnt + 1, visited | 1 << i); //다음 번호 고려하기
		}
	}
	
	//안타: 1, 2루타: 2, 3루타: 3, 홈런: 4, 아웃: 0
	static void go(int n) {
		switch(n) {
		case 1: 
			antaa();
			break;
		case 2: 
			ru2();
			break;
		case 3: 
			ru3();
			break;
		case 4: 
			homerun();
			break;
		case 0:
			out();
			break;
		}
	}
	
	
	//안타: 타자와 모든 주자가 한 루씩 진루한다.
	static void antaa() {
		for(int i = 3; i >= 0; i--) {
			if(i < 3) { //0, 1, 2 -> 1, 2, 3
				if(hasPlayer[i] == true) {
					hasPlayer[i] = false;
					hasPlayer[i+1] = true;
				}
			}
			else if(i == 3) { //3 -> 홈 -> score++
				if(hasPlayer[i] == true) {
					hasPlayer[i] = false;
					score++;
				}
			}
		}
	}
	
	//2루타: 타자와 모든 주자가 두 루씩 진루한다.
	static void ru2() {
		for(int i = 3; i >= 0; i--) {
			if(i < 2) { //0, 1 -> 2, 3
				if(hasPlayer[i] == true) {
					hasPlayer[i] = false;
					hasPlayer[i+2] = true;
				}
			}
			else if(i >= 2) { //2, 3 -> 홈 -> score++
				if(hasPlayer[i] == true) {
					hasPlayer[i] = false;
					score++;
				}
			}
		}
	}
	
	//3루타: 타자와 모든 주자가 세 루씩 진루한다.
	static void ru3() {
		for(int i = 3; i >= 0; i--) {
			if(i == 0) { //0 -> 3
				if(hasPlayer[i] == true) {
					hasPlayer[i] = false;
					hasPlayer[i+3] = true;
				}
			}
			else if(i > 0) { //1, 2, 3 -> 홈 -> score++
				if(hasPlayer[i] == true) {
					hasPlayer[i] = false;
					score++;
				}
			}
		}
	}
	
	//홈런: 타자와 모든 주자가 홈까지 진루한다.
	static void homerun() {
		for(int i = 3; i >= 0; i--) { //모든 루 -> 홈 -> score++
			if(hasPlayer[i] == true) {
				hasPlayer[i] = false;
				score++;
			}
		}
	}
	
	//아웃: 아무일 X, 아웃카운트만 1증가.
	static void out() {
		outCnt++; //아웃카운트 증가
	}

}
