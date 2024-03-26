package com.ssafy._1일1알골.algo_0303;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 총 8개의 톱니를 가지고 있는 톱니바퀴 4개가 일렬로 놓여져 있다.
 * 톱니는 N극 또는 S극 중 하나를 나타내고 있다. 톱니바퀴에는 1~4번 번호가 매겨져 있다.
 * 톱니바퀴를 K번 회전시키려고 한다. 톱니바퀴의 회전은 한 칸을 기준으로 한다. 회전은 시계/반시계 방향이 있다.
 * 톱니바퀴를 회전시키려면, 회전시킬 톱니바퀴와 회전시킬 방향을 결정해야 한다. 
 * 톱니바퀴가 회전할 때, 서로 맞닿은 극에 따라서 옆에 있는 톱니바퀴를 회전시킬 수도 있고, 회전시키지 않을 수도 있다.
 * 톱니바퀴 A를 회전할 때, 그 옆에 있는 톱니바퀴 B와 서로 맞닿은 톱니의 극이 다르다면, B는 A가 회전한 방향과 반대방향으로 회전하게 된다.
 * 톱니바퀴의 초기 상태와 회전시킨 방법이 주어졌을 때, 최종 톱니바퀴의 상태를 구하시오.
[입력]
4개 줄) 1~4번 톱니바퀴 상태 (8개의 정수, 12시방향부터 시계방향 순서대로 주어진다. N극: 0, S극: 1)
5) 회전 횟수 K. (1 ≤ K ≤ 100)
K개 줄) 회전시킨 방법. (두 개의 정수: 회전시킨 톱니바퀴의 번호 <공백> 방향, 1: 시계 방향, -1: 반시계 방향)
[출력]
K번 회전 후 네 톱니바퀴의 점수의 합. 
점수 다음과 같이 계산한다.
 - 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
 - 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
 - 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
 - 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
 */
public class Main_14891_톱니바퀴_박봉균 {
	
	static int K;
	static int[][] stat = new int[4][8];
	static Pair[] rotate;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int i = 0; i < 4; i++) {
			String given = br.readLine();
			for(int j = 0; j < given.length(); j++) {
				stat[i][j] = given.charAt(j) - '0';
			}
		}
		
		K = Integer.parseInt(br.readLine()); //회전 횟수
		rotate = new Pair[K]; //{회전 톱니 인덱스, 방향} 배열
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1; //번호 -> 배열 인덱스 보정
			int dir = Integer.parseInt(st.nextToken());
			
			rotate[i] = new Pair(num, dir);
		}
		
		
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void doRotate() {
		//톱니바퀴 A를 회전할 때, 그 옆에 있는 톱니바퀴 B와 서로 맞닿은 톱니의 극이 다르다면, B는 A가 회전한 방향과 반대방향으로 회전하게 된다.
		for(Pair r : rotate) {
		//0번: 2번 톱니와 1번의 6
			int num = r.num;
			int dir = r.dir;
		//1,2번 톱니: 2번, 6번 톱니 체크
		//3번 톱니: 6번 톱니 체크
		}
	}
	
	static void rotateCounterClock(int[] arr) {
		int tmp = arr[0];
		for(int i = 0; i < 7; i++) {
			arr[i] = arr[i + 1];
		}
		arr[7] = tmp;
	}
	
	static void rotateClock(int[] arr) {
		int tmp = arr[7];
		for(int i = 0; i < 7; i++) {
			arr[i + 1] = arr[i];
		}
		arr[0] = tmp;
	}
	 
	
	/* 점수 다음과 같이 정의한다.
	 - 0번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
	 - 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
	 - 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
	 - 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
	 */
	static int getScore() {
		int score = 0;
		
		for(int i = 0; i < 4; i++) {
			switch(i) {
			case 0:
 				if(stat[i][0] == 1)
 					score += 1;
 				break;
			case 1:
 				if(stat[i][0] == 1)
 					score += 2;
 				break;
			case 2:
 				if(stat[i][0] == 1)
 					score += 4;
 				break;
			case 3:
 				if(stat[i][0] == 1)
 					score += 8;
 				break;
			}		
		}
		
		return score;
	}
	
	static class Pair {
		int num, dir;
		Pair(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
	
	
	
}
