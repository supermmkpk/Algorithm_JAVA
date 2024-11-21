package com.ssafy.algorithm.day11_분할정복_백트래킹;

import java.io.*;
import java.util.*;

/**
 * BJ_1074[G5] : Z
 */
public class reviewDivideConquer {

	public static void main(String[] args) throws IOException {
		// 입력 조건에 따라 N, r, c를 입력 받습니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		// 앞의 원소 개수를 저장할 변수입니다.(앞 원소 개수 = 방문 순서)
		int sum = 0;
		// 2^N * 2^N 행렬에서 사분면을 체크하며 r행 c열을 몇 번째로 방문하는지 확인합니다.
		// i : 현 사분면의 한변 길이, 사분면의 한변 길이가 1일 때까지 반복합니다.
		// 계속 4등분하므로 한변 길이는 /2
		for (int i = (int) Math.pow(2, N); i > 0; i /= 2) {
			// 4등분한 각 구역의 원소 개수를 계산합니다.
			int tmpSum = i * i / 4;
			// System.out.println(i +":" +tmpSum + ", " + sum); //디버깅 코드

			// 사분면 체크
			// sum, r, c 갱신
			if (r < i / 2 && c < i / 2) { // 1사분면(왼쪽 위)
				sum += tmpSum * 0; // 그대로
			} else if (r < i / 2 && c >= i / 2) { // 2사분면(오른쪽 위)
				c -= i / 2; // 다음 과정을 위해 c(열 번호)를 갱신합니다.
				sum += tmpSum * 1; // 2사분면의 시작은 1사분면 원소 개수입니다.
			} else if (r >= i / 2 && c < i / 2) { // 3사분면(왼쪽 아래)
				r -= i / 2; // 다음 과정을 위해 r(행 번호)를 갱신합니다.
				sum += tmpSum * 2; // 3사분면의 시작은 1사분면+2사분면 원소 개수입니다.
			} else if (r >= i / 2 && c >= i / 2) { // 4사분면(오른쪽 아래)
				// 다음 과정을 위해 r(행 번호), c(열 번호)를 갱신합니다.
				r -= i / 2;
				c -= i / 2;
				sum += tmpSum * 3; // 4사분면의 시작은 1사분면+2사분면+3사분면 원소 개수입니다.
			}
		}
		// r행 c열의 방문 순서를 출력합니다.
		System.out.println(sum);

		br.close(); // stream을 닫습니다.
	}
}
