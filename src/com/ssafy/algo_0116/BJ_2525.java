package com.ssafy.algo_0116;

import java.util.*;

/**
 * <pre>
 * BJ2525_오븐 시계 
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 *
 * 오리구이를 시작하는 시각과 필요한 시간이 분단위로 주어졌을 때, 끝나는 시각을 계산하시오.

[입력]
1) 현재 시각(A시 <공백> B분) (0 ≤ A ≤ 23 , 0 ≤ B ≤ 59)
2) 필요한 시간 C분 (0 ≤ C ≤ 1,000)

[출력]
종료되는 시각의 시 <공백> 분 (단, 시는 0~23, 분은 0~59의 정수이다. 디지털 시계는 23시 59분에서 1분이 지나면 0시 0분이 된다.)
 */
public class BJ_2525 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//현재 시각을 입력 받습니다.
		int curHr = sc.nextInt(); //시
		int curMin = sc.nextInt(); //분
		
		//필요한 시간을 입력 받습니다.
		int c = sc.nextInt();
		
		//필요한 시간을 시간/분 단위로 계산합니다.
		int reqHr = c / 60;
		int reqMin = c % 60;
		
		//현재 시각과 필요한 시/분을 기준으로 종료 시각을 계산합니다.
		int endHr = curHr + reqHr;
		int endMin = curMin + reqMin;
		
		//종료 시각의 분이 60 이상인 경우
		if(endMin >= 60) {
			endHr++; //종료 시각의 시 증가
			endMin -= 60; //종료시각의 분은 60 감소합니다.
		}
		
		//디지털 시계는 23시 59분에서 1분이 지나면 0시 0분이 된다.
		//종료시각의 시가 24 이상이 되는 경우
		if(endHr >=24) {
			endHr -= 24; //종료 시각의 시 24 감소
		}
		
		//출력 조건에 따라 종료 시각을 출력합니다.
		System.out.print(endHr + " " + endMin + "\n");
	}

}
