package com.ssafy.algorithm.day3_0130_조합;

import java.util.Arrays;
import java.util.Scanner;

//주사위 던지기 (던지는 횟수는 6번 이하라 하자)
public class DiceTest {
	/** 주사위 던지는 횟수 N */
	static int N;
	/** 주사위 던지기 결과 저장 배열 */
	static int[] numbers;
	/** 사용 여부 관리 배열*/
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //주사위 던지는 횟수
		numbers = new int[N]; //결과 배열 numbers[] 동적 할당
		int mode = sc.nextInt(); //주사위 던지기 모드
		
		switch(mode) {
		case 1: //중복 순열
			dice1(0);
			break;
		case 2: //순열: 중복 관리
			isSelected = new boolean[7]; //주사위 눈이 사용되었는지 여부 관리
			dice2(0);
			break;
		case 3: //중복 조합
			dice3(0, 1);
			break;
		case 4: //조합
			dice4(0, 1);
			break; 
		}
	}
	
	/**
	 * 주사위 던지기 - 중복 순열 ==> 중복 체크 (isSelected[] 필요 없음!!, 백트래킹 불필요)
	 * @param cnt : 기존까지 던진 주사위 횟수
	 */
	private static void dice1(int cnt) { 
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		for(int i = 1; i <= 6; i++) { //모든 주사위 눈의 수를 시도(i: 주사위 눈의 수)
			numbers[cnt] = i;
			dice1(cnt + 1); //다음 주사위 던지러 가기
		} 
	}
	
	/**
	 * 주사위 던지기 - 순열
	 * @param cnt : 기존까지 던진 주사위 횟수
	 */
	private static void dice2(int cnt) { //주사위 던지기(cnt : 기존까지 던진 주사위 횟수)
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		//입력된 수 순열: input[]
		/*for(int i = 0; i < input.length; i++) {
			if(isSelected[i])
				continue;
			numbers[cnt] = input[i];
			isSelected[i] = true;
			dice2(cnt + 1);
			isSelected[i] = false;
		}*/
				
		// 고정된 수 순열 
		for(int i = 1; i <= 6; i++) { //모든 주사위 눈의 수를 시도(i: 주사위 눈의 수)
			if(isSelected[i])
				continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			dice2(cnt + 1); //다음 주사위 던지러 가기
			isSelected[i] = false;
		}
	}

	/**
	 * 주사위 던지기 - 중복 조합(나부터 시작, 나 포함)
	 * @param cnt : 기존까지 던진 주사위 횟수
	 * @param start : 조합을 시도할 시작 인덱스
	 */
	private static void dice3(int cnt, int start) {
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		//시ㅣ작점부터 가능한 끝까지 선택하는 시도
		for(int i = start; i <= 6; i++) { 
			numbers[cnt] = i; //선택한 수 저장
			dice3(cnt + 1, i); //현재 선택한 수의 다음부터 선택하도록 시작점 주기!!
		}
	}
	
	/**
	 * 주사위 던지기 - 조합
	 * @param cnt : 기존까지 던진 주사위 횟수
	 * @param start : 조합을 시도할 시작 인덱스
	 */
	private static void dice4(int cnt, int start) {
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		//시ㅣ작점부터 가능한 끝까지 선택하는 시도
		for(int i = start; i <= 6; i++) { 
			numbers[cnt] = i; //선택한 수 저장
			dice4(cnt + 1, i + 1); //현재 선택한 수의 다음부터 선택하도록 시작점 주기!!
		}
	}
}
