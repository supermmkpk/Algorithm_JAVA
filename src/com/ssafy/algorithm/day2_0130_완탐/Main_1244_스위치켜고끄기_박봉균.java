package com.ssafy.algorithm.day2_0130_완탐;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1244 [S4] : 스위치 켜고 끄기 
 * </pre>
 * @author 박봉균
 * @since JDK8
 * @see <a href="https://www.acmicpc.net/source/72654001"></a>
 * 아이디어: 	- 남학생은 %연산자 사용, 여학생은 받은 숫자 기준으로 왼쪽, 오른쪽 체크
 * 			- 스위치 상태는 배열, 학생 정보는 Pair 클래스 생성하여 배열에 저장	
 * 결과: 14332KB, 132ms
 */

/* 문제 요약
 * 1~N번의 스위치들이 있다. 스위치는 켜져 있거나(1) 꺼져있는(0) 상태
 * 학생 몇 명을 뽑아서, 학생들에게 1 이상이고 스위치 개수 이하인 자연수
 * 남학생: 받은 수의 배수이면, 그 스위치 상태를 바꾼다.
 * 여학생: 받은 수 중심으로 좌우가 "대칭"이면서 가장 넓은 구간을 찾아서, 그 구간 내 모든 스위치 상태를 바꾼다.
[입력]
1) 스위치 개수 N. (1 <= N <= 100)
2) 스위치의 상태
3) 학생 수 nStudent.(1<= <= 100)
nStudent개줄) 학생의 성별 <공백> 받은 수 (남학생: 1, 여학생: 2, 1 <= 받은 수 <= N)

[출력]
스위치의 상태를 출력.
(단, 한 줄에 20개씩 출력한다. Ex) 21번 스위치: 다음줄에 이어서)
 */
public class Main_1244_스위치켜고끄기_박봉균 {
	/** 스위치 개수 N(<= 100) */
	static int N;
	/** 스위치 상태 배열 (편의상, 인덱스 1부터) */
	static int[] status;
	/** 학생 수(<= 100) */
	static int nStudent;
	/** 학생 정보 배열(Pair 클래스로 성별, 숫자 저장) */
	static Pair[] students;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); //N 입력
		status = new int[N + 1]; //status[] 동적 할당
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) 
			status[i] = Integer.parseInt(st.nextToken()); //스위치 상태 입력
		
		
		nStudent = Integer.parseInt(br.readLine()); //nStudent 입력
		students = new Pair[nStudent]; //students[] 동적 할당
		for(int i = 0; i < nStudent; i++) {
			st = new StringTokenizer(br.readLine());
			students[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); //학생 정보 입력(Pair)
		}
		
		for(Pair student : students) { //각 학생에 대하여
			switch(student.getFirst()) {
			case 1: //남학생
				for(int i = 1; i <= N; i++)
					if(i % student.getSecond() == 0) //받은 수의 배수이면 
						status[i] ^= 1; //상태를 바꿉니다
				break;
				
			case 2: //여학생
				int num = student.getSecond(); //받은 숫자
				status[num] ^= 1; //가운데 기준 상태부터 반전
				int r = num + 1; //오른쪽
				int l = num - 1; //왼쪽
				while(true) {
					//오버,언더 체크, 대칭 체크 -> 종료
					if(r >= N + 1 || l < 1 || status[l] != status[r])
						break;
					
					//양쪽 각 비트를 반전합니다.
					status[l] ^= 1;
					status[r] ^= 1;
					
					//l,r 갱신
					l--;
					r++;
				}
				break;
			}
		}
		//상태를 모두 출력하되 한줄에 20개씩 출력합니다.
		for(int i = 1; i <= N; i++) {
			bw.write(String.valueOf(status[i]) + " ");
			if(i % 20 == 0) 
				bw.write('\n');
		}
		//남은 데이터 출력 및 stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}

class Pair {
	private int first;
	private int second;
	
	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
	public int getFirst() {
		return first;
	}
	public int getSecond() {
		return second;
	}
}

/*
24
0 1 0 1 0 0 0 1 0 1 0 1 0 0 0 1 0 1 0 1 0 0 0 1
4
1 3
2 3
1 5
2 5
*/
