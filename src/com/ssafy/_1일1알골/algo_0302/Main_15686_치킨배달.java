package com.ssafy._1일1알골.algo_0302;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 
 */

/* <BJ15686_G5 : 치킨배달>
 * N*N 크기의 도시가 있다. 각 칸은 빈 칸, 치킨집, 집 중 하나이다. 행과 열 번호는 1부터 시작한다.
 * 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리이다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
 * 임의의 두 칸 사이의 거리는 |y1-y2| + |x1-x2|로 정의한다.
 * 치킨집 중에서 M개를 고르고, 나머지 치킨집은 모두 폐업시켜야 한다. 도시의 치킨 거리의 최솟값을 구하자
[입력]
1) N, M. (2 ≤ N ≤ 50, 1 ≤ M ≤ 13)
N개 줄) 도시 정보 랭렬 (0: 빈 칸, 1: 집, 2: 치킨집, 집 개수: 1~2N, 치킨집 수: M~13)
[출력]
유지할 치킨집을 M개를 골랐을 때, 도시의 치킨 거리의 최솟값
 */
public class Main_15686_치킨배달 {
	
	static int N, M, matrix[][], nums[];
	static List<Pair> homes  = new ArrayList<>(); //집 좌표 모음
	static List<Pair> chickens = new ArrayList<>(); //치킨집 좌표 모음
	static int nChickens = 0;
	static int ret = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		matrix = new int[N][N];
		nums = new int[M];
		
		//도시 정보 입력, 치킨집/집 좌표 기록
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if(matrix[i][j] == 1) {
					homes.add(new Pair(i, j));
				}
				else if(matrix[i][j] == 2) {
					chickens.add(new Pair(i, j));
					++nChickens;
				}
			}
		}
		
		//1. 치킨집 개수 중 M개를 뽑는 경우의 수를 구한다.
		//2. 도시 치킨 거리를 구하고, 최소를 갱신한다.
		combi(0, 0);
		
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 유지할 치킨집 M개를 뽑고, 도시 치킨 거리를 구하여, 최소를 갱신하는 함수 */
	static void combi(int cnt, int start) {
		if(cnt == M) {
			//2. 도시 치킨 거리를 구하고, 최소를 갱신한다.
			ret = Math.min(ret, getCityDist());
			return;
		}
		
		//1. 치킨집 개수 중 M개를 뽑는 경우의 수를 구한다.
		//뽑은 결과 배열 nums에는 뽑힌 치킨 가게의 인덱스
		for(int i = start; i < nChickens; i++) {
			nums[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}
	
	/** 도시의 치킨거리를 구하는 함수 */
	static int getCityDist() {
		int cityDist = 0;
		for(Pair home : homes) {
			int minDist = Integer.MAX_VALUE;
			for(int idx : nums) {
				minDist = Math.min(minDist, getDist(home, chickens.get(idx)));
			}
			cityDist += minDist;
		}
		return cityDist;
	}
	
	/** 임의의 두 칸 사이의 거리는 |y1-y2| + |x1-x2|로 정의한다. */
	static int getDist(Pair p1, Pair p0) {
		return Math.abs(p1.first - p0.first) + Math.abs(p1.second - p0.second);
	}
	
	static class Pair {
		int first, second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}
