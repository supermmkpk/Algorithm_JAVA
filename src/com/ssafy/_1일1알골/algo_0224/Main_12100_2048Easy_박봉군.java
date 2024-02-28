package com.ssafy._1일1알골.algo_0224;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 결과: 78904KB, 556ms	
 */

/*
 * 이동: "전체"를 상하좌우 네 방향 중 하나 이동
 * 같은 값을 갖는 두 블록이 충돌하면 하나로 합쳐진다(숫자 더해짐)
 * 보드의 크기 N*N일 때, 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하시오.
[입력]
1) 보드의 크기 N (1 ≤ N ≤ 20)
N개 줄) 게임판의 초기 상태가 주어진다. (0: 빈 칸, 이외: 2<= 블록 <= 1024, 2^n꼴, 블록은 적어도 하나 주어진다.)
[출력]
최대 5번 이동시켜서 얻을 수 있는 가장 큰 블록 숫자
 */
public class Main_12100_2048Easy_박봉군 {
	static int N, matrix[][], tmpMatrix[][];
	/** 중복 순열 기록 */
	static int[] seq = new int[5]; 
	/** 각 단위 행동에서 존재 블록의 리스트 */ 
	static LinkedList<Integer> blocks = new LinkedList<>();
	/** 결과 변수 : 가장 큰 블록 숫자 */
	static int ret = Integer.MIN_VALUE;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //보드 크기
		matrix = new int[N][N]; //보드
		tmpMatrix = new int[N][N]; //각 케이스별 로직 수행 후 원상복구 위한 임시 배열
		
		//보드 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) 
				matrix[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//게임 플레이
		sequence(0);
		
		//가능한 최대 블록 숫자 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
        bw.close();
        br.close();
	}
	
	/** 
	 * 상하좌우 4개 중 5가지를 뽑는 중복 순열을 구하고 이에 따라 블록을 이동시킨 결과, 블록의 최대를 구하는 함수
	 */
	static void sequence(int cnt) {
		//종료 조건.(최대 5번 이동)
		if(cnt == 5) {
			//해당 순서에 대하여
			//잠깐 tmp에 옮겨놓기
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					tmpMatrix[i][j] = matrix[i][j];
				}
			}

			//0: 위, 1: 오른, 2: 아래, 3: 왼
			for(int i : seq) {
				switch(i) {
				case 0: 
					moveUp();
					break;
				case 1: 
					moveRight();
					break;
				case 2: 
					moveDown();
					break;
				case 3: 
					moveLeft();
					break;
				}
			}
			
			//최대 블록 구하기
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(max < matrix[i][j]) {
						max = matrix[i][j];
					}
				}
			}
		
			//이전 최대와 비교하여 결과 갱신
			ret = Math.max(ret, max);
			
			//원상복구
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					matrix[i][j] = tmpMatrix[i][j];
				}
			}
			return;
		}
		
		//중복 순열 구하기
		for(int i = 0; i < 4; i++) {
			seq[cnt] = i;
			sequence(cnt + 1);
		}
	}
	
//-위-//
	static void moveUp() {
		blocks.clear();
		
		//각 열에 대해
		for(int j = 0; j < N; j++) {
			blocks.clear();
			int prev = -1; //이전 블록 값
			Pair prevPos = new Pair(); //이전 블록 좌표
			
			//해당 행에서 첫 블록(위부터)
			for(int i = 0; i < N; i++) {
				if(matrix[i][j] != 0) {
					prevPos = new Pair(i, j);
					prev = matrix[i][j];
					blocks.add(matrix[i][j]);
					break;
				}
			}
			if(prev < 0) //다 0이면 다음
				continue;
			
			//해당 열 첫 블록 위의 모든 행에 대하여(위부터)
			for(int i = prevPos.first + 1; i < N; i++) {
				//비어있다면 continue;
				if(matrix[i][j] == 0)
					continue;
				
				//블록이라면
				//같으면
				if(prev == matrix[i][j]) { //합친다
					//현재 블록은 이전+현재
					blocks.removeLast();
					blocks.add(matrix[i][j] + prev);
					prev = -1; //연쇄로 합쳐지는 것 방지
				}
				else {
					blocks.add(matrix[i][j]);
					prev = matrix[i][j];
				}				
			}
			
			//쉬프트
			int idx = 0;
			for(int block : blocks)
				matrix[idx++][j] = block;
			
			for(int i = idx; i < N; i++) 
				matrix[i][j] = 0;
		}
	}
	
//-아래-//
	static void moveDown() {
		blocks.clear();
		
		//각 열에 대해
		for(int j = 0; j < N; j++) {
			blocks.clear();
			int prev = -1; //이전 블록 값
			Pair prevPos = new Pair(); //이전 블록 좌표
			
			//해당 행에서 첫 블록(아래부터)
			for(int i = N - 1; i >= 0; i--) {
				if(matrix[i][j] != 0) {
					prevPos = new Pair(i, j);
					prev = matrix[i][j];
					blocks.add(matrix[i][j]);
					break;
				}
			}
			if(prev < 0) //다 0이면 다음
				continue;
			
			//해당 열의 모든 행에 대하여(아래부터)
			for(int i = prevPos.first - 1; i >= 0; i--) {
				//비어있다면 continue;
				if(matrix[i][j] == 0)
					continue;
				
				//블록이라면
				
				//같으면
				if(prev == matrix[i][j]) { //합친다				
					//현재 블록은 이전*현재
					blocks.removeLast();
					blocks.add(matrix[i][j] + prev);
					prev = -1; //연쇄로 합쳐지는 것 방지
				}
				else {
					blocks.add(matrix[i][j]);
					prev = matrix[i][j];
				}
			}
			
			//쉬프트
			int idx = N - 1;
			for(int block : blocks)
				matrix[idx--][j] = block;
			
			for(int i = idx; i >= 0; i--) 
				matrix[i][j] = 0;
		}
	}
	
//-왼쪽-//
	static void moveLeft() {
		blocks.clear();
		
		//각 행에 대해
		for(int i = 0; i < N; i++) {
			blocks.clear();
			int prev = -1; //이전 블록 값
			Pair prevPos = new Pair(); //이전 블록 좌표
			
			//해당 열에서 첫 블록(왼쪽부터)
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] != 0) {
					prevPos = new Pair(i, j);
					prev = matrix[i][j];
					blocks.add(matrix[i][j]);
					break;
				}
			}
			if(prev < 0) //다 0이면 다음
				continue;
			
			//해당 열의 모든 행에 대하여(왼쪽부터)
			for(int j = prevPos.second + 1; j < N; j++) {
				//비어있다면 continue;
				if(matrix[i][j] == 0)
					continue;
				
				//블록이라면
				//같으면
				if(prev == matrix[i][j]) { //합친다					
					//현재 블록은 이전*현재
					blocks.removeLast();
					blocks.add(matrix[i][j] + prev);
					prev = -1; //연쇄로 합쳐지는 것 방지
				}
				else {
					prev = matrix[i][j];
					blocks.add(matrix[i][j]);
				}
			}
			
			//쉬프트
			int idx = 0;
			for(int block : blocks)
				matrix[i][idx++] = block;
			
			for(int j = idx; j < N; j++) 
				matrix[i][j] = 0;
		}
	}
	
//-오른쪽-//
	static void moveRight() {
		blocks.clear();
		
		//각 행에 대해
		for(int i = 0; i < N; i++) {
			blocks.clear();
			int prev = -1; //이전 블록 값
			Pair prevPos = new Pair(); //이전 블록 좌표
			
			//해당 열에서 첫 블록(오른쪽부터)
			for(int j = N - 1; j >= 0; j--) {
				if(matrix[i][j] != 0) {
					prevPos = new Pair(i, j);
					prev = matrix[i][j];
					blocks.add(matrix[i][j]);
					break;
				}
			}
			if(prev < 0) //다 0이면 다음
				continue;
			
			//해당 열의 모든 행에 대하여(오른쪽부터)
			for(int j = prevPos.second - 1; j >= 0; j--) {
				//비어있다면 continue;
				if(matrix[i][j] == 0)
					continue;
				
				//블록이라면
				
				//같으면
				if(prev == matrix[i][j]) { //합친다					
					//현재 블록은 이전*현재
					blocks.removeLast();
					blocks.add(matrix[i][j] + prev);
					prev = -1; //연쇄로 합쳐지는 것 방지
				}
				else {
					blocks.add(matrix[i][j]);
					prev = matrix[i][j];
				}
			}
			
			//쉬프트
			int idx = N - 1;
			for(int block : blocks)
				matrix[i][idx--] = block;
			
			for(int j = idx; j >= 0; j--) 
				matrix[i][j] = 0;
		}
	}
	
	/** 좌표 저장 위한 Pair 클래스 */
	static class Pair {
		int first, second;
		Pair() {}
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}

/*
5
2 2 0 2 2
2 2 0 2 2
2 2 0 2 2
2 2 0 2 2
2 2 0 4 2 
*/
