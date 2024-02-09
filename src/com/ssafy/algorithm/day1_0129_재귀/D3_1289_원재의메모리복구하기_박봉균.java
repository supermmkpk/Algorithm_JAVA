package com.ssafy.algorithm.day1_0129_재귀;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * 1289 : 원재의 메모리 복구하기
 * </pre>
 * @author 박봉균
 * @since JDK8
 * 결과: Pass
 */

/*
 * 메모리 bit중 하나를 골라 0인지 1인지 결정하면 해당 값이 메모리의 끝까지 덮어씌우는 것이다.
 * Ex) 메모리 값이 0100이고, 3번째 bit를 골라 1로 설정하면 0111이 된다.
 * 원래 상태가 주어질 때, (모든 bit가 0)에서 원래 상태로 돌아가는데 최소 몇 번이나 고쳐야 하는지 구하라.
[입력]
1) 테스트 케이스의 수 T
T개 줄) 메모리의 원래 값 (1 <= 메모리 길이 <= 50)

[출력]
각 테스트 케이스마다 ‘#x’(1부터) <공백> 최소수정횟수
 */
public class D3_1289_원재의메모리복구하기_박봉균 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 수 T
		
		for(int t = 1; t <= T; t++) { //T개 테스트 케이스에 대하여
			//초기화
			String given = br.readLine();
			int cnt = 0;
			char cur = '0'; //현재 비트 저장 변수
			for(int i = 0; i < given.length(); i++) { //주어진 메모리 상태의 각 비트에 대하여
				if(given.charAt(i) == cur) { //0->0, 1->1 : 같으면 수정 불필요
					continue;
				}
				else { //0->1, 1->0 : 다르다면 수정합니다.(cnt 증가)
					cnt++;
					//수정 시, cur 변수 갱신
					if(cur == '0') { 
						cur ='1';
					}
					else if (cur == '1') {
						cur = '0';
					}
				}
			}
			//출력 조건에 따라 출력
			System.out.printf("#%d %d\n", t, cnt);
		}
    }
}

/*
2
0011
100
*/