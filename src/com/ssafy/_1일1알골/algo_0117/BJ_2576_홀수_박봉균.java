package com.ssafy._1일1알골.algo_0117;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ2576 : 홀수
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 * @see <a href="https://www.acmicpc.net/source/71920808"></a>
 *
 * 결과: "맞았습니다!!", 14156KB, 124ms
 */

/*
 * 7개의 자연수가 주어질 때, 이 중 홀수 골라 합계를 구하고, 홀수들 중 최솟값을 구하라.
[입력]
7개 줄) 자연수 (< 100)

[출력]
- 홀수가 존재하는 경우, 1) 홀수들의 합, <개행> 2)홀수들 중 최솟값.
- 홀수가 존재하지 않는 경우, -1.
*/
public class BJ_2576_홀수_박봉균 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//7개의 자연수를 담을 배열입니다.
		int[] nums = new int[7];
		for(int i = 0; i < 7; i++) {
			nums[i] = Integer.parseInt(br.readLine()); //7줄에 걸쳐 7개의 자연수를 입력 받아 배열에 저장합니다.
		}
		
		//7개 자연수 중 홀수를 추출하여 리스트에 저장합니다.
		List<Integer> oddList = getOddList(nums); 
		
		//홀수 리스트가 비었다면 -1을 출력합니다.
		if(oddList.isEmpty()) { 
			System.out.println("-1");
		}
		else {
			int sum = 0; //홀수의 합을 나타냅니다.
			//홀수 리스트를 순회하며 sum을 갱신하며 전체합을 구합니다.
			for(Integer odd : oddList) {
				sum += odd;
			}
			System.out.println(sum); //홀수의 전체합을 출력합니다.
			
			//최솟값을 구하기 위해 홀수 리스트를 오름차순 정렬합니다.
			Collections.sort(oddList);
			System.out.println(oddList.get(0)); //정렬된 리스트에서 가장 앞의 원소(최소)를 출력합니다.
		}
	}
	
	/**
	 * 7개의 자연수 중 홀수를 추출하여 리스트 반환
	 * @param arr 7개 자연수 배열
	 * @return List<Integer> 홀수를 추출한 ArrayList
	 */
	static List<Integer> getOddList(int[] arr) {
		//반환할 리스트 선언.
		List<Integer> ret = new ArrayList<>();
		
		for(int i = 0; i < 7; i++) { //7개의 자연수에 대하여
			if(arr[i] % 2 == 1) { //홀수라면
				ret.add(arr[i]); //리스트에 추가합니다.
			}
		}
		
		return ret; //홀수 리스트를 반환합니다.
	}

}
