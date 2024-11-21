package com.ssafy.algorithm.day3_0130_조합;
import java.io.*;
import java.util.*;

/**
 * BJ_15686[G5] : 치킨 배달
 *
 */
public class reviewCombi2 {

	/** 행,열 크기 N, 치킨집 최소 개수 M */
	static int N, M;
	/** 도시 정보 행렬 */
	static int[][] matrix;
	/** 치킨 집 좌표 저장 리스트 */
	static ArrayList<Pair> chickens = new ArrayList<>();
	/** 집 좌표 저장 리스트 */
	static ArrayList<Pair> homes = new ArrayList<>();
	/** 치킨 집 중 오픈할 가게의 인덱스를 저장하는 리스트 */
	static LinkedList<Integer> opens = new LinkedList<>();
	/** 결과 변수: 도시 치킨 거리의 최솟값 */
	static int ret = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//행,열 크기 N, 치킨집의 최소 개수 M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//도시 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)  {
				int given = Integer.parseInt(st.nextToken());
				
				//1이면 집
				if(given == 1)
					homes.add(new Pair(i, j));
				//2이면 치킨가게
				if(given == 2)
					chickens.add(new Pair(i, j));
			}
		}
		
		//열린 치킨 가게 인덱스의 조합을 구하고, 도시 치킨 거리의 최소를 구합니다.
		combi(0, 0);
	
		//최소 도시 치킨 거리 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 
	 * 열린 가게 인덱스의 조합을 구하는 함수
	 */
	static void combi(int start, int cnt) {
		if(cnt == M) {
			ret = Math.min(ret, getCityDist()); //도시 치킨 거리가 최소라면 갱신
			return;
		}
		
		//조합 구하기
		for(int i = start; i < chickens.size(); i++) {
			opens.add(i);
			combi(i + 1, cnt + 1);
			opens.removeLast();
		}
	}
	
	/**
	 * 열린 치킨 가게와 집 사이의 최소거리를 구하는 함수
	 */
	static int getCityDist() {
		int cityDist = 0;

		//모든 집에 대하여
		for(Pair home : homes) {
			//열린 가게와 거리 중 최소를 구합니다.
			int dist = Integer.MAX_VALUE;
			for(int open : opens) {
				int y = chickens.get(open).first; //열린 치킨 가게의 행
				int x = chickens.get(open).second; //열린 치킨 가게의 열
				dist = Math.min(dist, Math.abs(home.first - y) + Math.abs(home.second - x));
			}
			cityDist += dist; //도시 치킨 거리에 더해주기
		} 
		return cityDist;
	}

	/** 
	 * 좌표 저장 위한 Pair 클래스
	 */
	static class Pair {
		int first;
		int second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

}
