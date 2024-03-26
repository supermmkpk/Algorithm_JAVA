package com.ssafy.algorithm.day21_Season1END;
import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * 아이디어: 시간 1초: 완탐(1e10) 불가 -> 입력이 "오름차순" -> 이분탐색?
 *     ==> 각 원소별로 뒤에 있는 애들 이분탐색으로 0에 가까운 것 찾고, 그 중 최소가 결과 (NlogN = 약 1e5 * 20 : OK!) 
 * 결과: 31776KB, 312ms
 */

/* <BJ2467_G5 : 용액>
 * 산성 용액과 알칼리성 용액을 보유하고 있다. 
 * 산성 용액 특성값 = 1 ~ 1e9, 알칼리성 용액 특성값 = -1 ~ -1e9
 * 두 용액 혼합용액 특성값 = 특성값의 합
 * 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다. 
 * 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
 * 산성 용액과 알칼리성 용액의 특성값이 "정렬된" 순서로 주어졌을 때, --> 이분탐색?
 * 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾으시오.
[입력]
1) 전체 용액 수 N. (2 <= N <= 1e5)
2) 용액의 특성값을 나타내는 N개 정수. ("오름차순", -1e9 ~ 1e9, 특성값은 모두 다르고, 산성 용액만으로나 알칼리성 용액만으로 주어지는 경우도 있다)
[출력]
특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값 오름차순 출력.한다. (두 개 이상일 경우: 아무거나)
 */
public class Main_2467_용액_박봉균 {

	/** 용액 수 */
	static int N;
	/** 용액 특성값 */
	static long[] sol;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 용액 수
		sol = new long[N]; // 용액 특성값 배열

		// 용액의 특성값 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			sol[i] = Long.parseLong(st.nextToken());
		

		/* 이분탐색 */
		long minDiff = Integer.MAX_VALUE; // 0과의 최소거리
		Pair minPos = new Pair(); // 최소에서의 좌표
		for (int i = 0; i < N; i++) {
			long now = sol[i]; // 현재 용액

			// 현재 용액과 그 뒤의 용액들과 혼합합니다.
			int left = i + 1;
			int right = N - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				long sum = now + sol[mid]; // 합(혼합용액의 특성값)
				long diff = Math.abs(now + sol[mid] - 0); // 0과의 거리

				// 0과의 최소거리 갱신
				if (diff < minDiff) {
					minDiff = diff;

					// 좌표 갱신
					minPos.first = i;
					minPos.second = mid;
				}

				// 0보다 크면 작은 범위로, 0보다 작으면 큰 범위로 이분탐색.
				// 0과의 거리를 줄여나갑니다.
				if (0 < sum)
					right = mid - 1;
				else if (sum < 0)
					left = mid + 1;
				else
					break;
			}
		}

		// 최소에서의 좌표의 값을 출력
		bw.write(String.valueOf(sol[minPos.first]) + " ");
		bw.write(String.valueOf(sol[minPos.second]) + '\n');
		bw.flush();
		bw.close();
		br.close();
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