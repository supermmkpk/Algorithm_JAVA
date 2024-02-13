package com.ssafy.algorithm.day10_Greedy;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * SWEA 6808 [D3] : 규영이와 인영이의 카드게임
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 규영의 순서는 정해져 있으므로 인영의 순서에 대해 NextPermutation 하면서 각 케이스 확인.
 * 결과: 26504KB, 1615 ms
 */

/* <문제 요약>
 * 1 ~ 18 수가 적힌 18장의 카드로 게임을 하고 있다.
 * 한 번의 게임에 9장씩 카드를 나눈다. 
 * 아홉 라운드에 걸쳐 게임을 진행한다.
 * 한 라운드: 한 장씩 카드를 낸 다음 두 사람이 낸 카드에 적힌 수를 비교해서 점수를 계산한다.
 * 1. 높은 수: 두 카드에 적힌 수의 합,
 * 2. 낮은 수: 점수X.
 * 아홉 라운드 후, 총점이 더 높은 사람이 승자.(같으면 무승부)
 * 이번 게임에 규영이가 받은 9장의 카드에 적힌 수가 주어진다.
 * 규영이가 내는 카드의 순서를 "고정"하면, 인영이가 어떻게 카드를 내는지에 따른 9!가지 순서에 따라 규영이의 승패가 정해질 것이다.
 * 이 때, 규영이가 이기는 경우와 지는 경우가 총 몇 가지 인지 구하라.
[입력]
1) 테케 수 T.
각 테케] 1) 아홉 개의 정수 (규영이는 이 순서대로 카드를 낸다)
[출력]
#테케번호 <공백> 규영이가 이기는 경우의 수 <공백> 지는 경우의 수
 */
public class D3_6808_규영이와인영이의카드게임_박봉균 {
	/** 카드 개수 */
	public static final int N = 9;
	/** 9! */
	public static final int FAC9 = 362880;
	
	/** 규영이가 받는 카드 : true */
	static boolean[]  visited = new boolean[20];
	/** 규영의 카드 */
	static int[] gyu = new int[9];
	/** 인영의 카드 */
	static int[] in = new int[9];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine()); //테스트케이스 수 T 입력
		
		//T개의 테스트 케이스에 대하여
		for(int t = 1; t <= T; t++) {
			//초기화
			Arrays.fill(visited, false);
			st = new StringTokenizer(br.readLine());
			
			//규영의 카드 입력
			for(int i = 0; i < 9; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
				visited[gyu[i]] = true;
			}
			
			//인영의 카드 숫자 저장
			int idx = 0;
			for(int i = 1; i <= 18; i++) {
				if(visited[i] == false)
					in[idx++] = i;
			}
			
			//nextPermutation 위한 정렬
			Arrays.sort(in);

			//인영이 낼 수 있는 카드에 대해 규영이 이기는/지는 경우의 수를 구합니다. 
			int totalGyu = 0; //규영 총점
			int totalIn = 0;  //인영 총점
			int cnt = 0; //이기는 카운트
			do {
				//초기화
				totalGyu = 0;
				totalIn = 0;
				
				//9라운드에 대하여: 높은 수: 두 카드에 적힌 수의 합, 낮은 수: 점수X
				for(int i = 0; i < 9; i++) {
					if(in[i] > gyu[i])
						totalIn += in[i] + gyu[i];
					else
						totalGyu += in[i] + gyu[i];
				}
				
				//9라운드 후, 총점이 더 높으면 이깁니다.
				if(totalGyu > totalIn)
					cnt++;
			} while(nextPermu(in)); //다음 순열이 있을 동안 반복
			
			//테케별로 규영이가 이기는/지는 경우의 수 출력
			bw.write("#" + String.valueOf(t) + " ");
			bw.write(String.valueOf(cnt) + " " + String.valueOf(FAC9 - cnt) + '\n');
		}
		//데이터 출력 및 stream 닫기
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 주어진 배열에 대해 다음 순열을 구하는 함수 */
	static boolean nextPermu(int[] arr) {
		//step1: 꼭대기 찾기(교환지점=i-1)
		int i = N - 1; 
		while(i > 0 && arr[i - 1] >= arr[i])
			i--;
		if(i == 0)
			return false;
		
		//step 2: 교환값 찾기(큰 값 중 최소)
		int j = N - 1;
		while(arr[i - 1] >= arr[j])
			j--;
		
		//step3: 교환
		swap(arr, i - 1, j);
		
		//step4: 오름차순 
		int k = N - 1;
		while(i < k) 
			swap(arr, i++, k--);
		
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}