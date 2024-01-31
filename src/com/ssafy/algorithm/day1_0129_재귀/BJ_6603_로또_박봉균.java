package com.ssafy.algorithm.day1_0129_재귀;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ6603 : 로또
 * </pre>
 * @author 박봉균
 * @since JDK8
 * @see <a href="https://www.acmicpc.net/source/72564791"></a>
 * 
 * 결과: "맞았습니다!!", 17648KB, 316ms
 */

/**
 * 49가지 수 중 k(k>6)개의 수를 골라 집합 S를 만든 다음 그 수만 가지고 번호를 6개 선택.
 * Ex) k=8, S={1,2,3,5,8,13,21,34} -> 집합 S에서 수를 고를 수 있는 경우의 수는 총 28가지
 *      : ([1,2,3,5,8,13], [1,2,3,5,8,21], [1,2,3,5,8,34], [1,2,3,5,13,21], ..., [3,5,8,13,21,34])
 * 집합 S와 k가 주어졌을 때, 수를 고르는 모든 방법을 구하라.
[입력]
각 줄) k(6 < k < 13)이고 <공백> 집합 S에 포함되는 k개 수(오름차순)
마지막 줄) 0. 

[출력]
각 테케) 모든 경우의 수.(사전 순) 
각 테스트 케이스 사이에는 빈 줄을 하나 출력한다.
*/
public class BJ_6603_로또_박봉균 {
	/** 조합 리스트 */
	static LinkedList<Integer> list = new LinkedList<>();
	/** 입력값 배열 */
	static int[] input; 
	/** k */
	static int K;
	/** 6개 고른다 */
	public static int R = 6;
	
	//C(K, R)
	static void combi(int start) {
		//R개 고르면, 출력 후 종료
	    if(list.size() == R) { 
	        for(int i : list) {
	            System.out.print(i + " ");
	        }
	        System.out.println();
	        return;
	    } 
	    //input의 K개 수 중 6개를 재귀적으로 고른다.(백트래킹)
	    for(int i = start + 1; i < K; i++) {
	        list.addLast(input[i]); //고르고 list에 추가
	        combi(i); //뒤 원소들에 대해서도 같은 과정 반복
	        list.removeLast(); //원상복구
	    }
	    return;
	}
	
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	    while(true) {
	    	//초기화
	    	list.clear();
	    	//K 입력
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        K = Integer.parseInt(st.nextToken()); 
	        if(K == 0) { //K가 0이면 종료
	            break;
	        }
	        else {
	        	input = new int[K]; //input[] 배열 동적 할당
	        	//나머지(집합 S에 포함되는 수) input[]에 저장
	        	for(int i = 0; i < K; i++) {
	               input[i] = Integer.parseInt(st.nextToken());
	        	}
	        	combi(-1); //집합에서 6개 고르기
	        	System.out.println(); //케이스 사이 빈줄 출력
	        }
	    }
	    br.close();
	}
}

