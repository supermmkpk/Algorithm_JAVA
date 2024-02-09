package com.ssafy.algorithm.day3_0130_조합;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 결과: 161408KB, 2680ms
 */
/* <문제 요약>
[입력]
1) 포켓몬의 개수 N <공백> 맞춰야 하는 문제의 개수 M (1 <= N,M <= 100,000)
N개 줄) 포켓몬 이름 (영어, 첫 글자 대문자) (마지막 문자만 대문자일 수도) 최대 길이는 20, 최소 길이는 2. 
M개 줄) 알파벳 or 숫자(1~N)

[출력]
M개의 줄)답. (숫자 : 이름을, 문자: 번호)
 */
public class Main_1620_포켓몬이다솜_박봉균 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		String[] arr = new String[N + 1]; //배열(숫자->문자열)
		Map<String, Integer> mp = new HashMap<>(); //맵(문자열->숫자)
		
		for(int i = 1; i <= N; i++) {
			String given = sc.next(); 
			mp.put(given, i);
			arr[i] = given;
		}
			
		for(int i = 0; i < M; i++) {
			if(sc.hasNextInt()) //정수라면 이름을 출력(배열)
				System.out.println(arr[sc.nextInt()]);
			else 
				System.out.println(mp.get(sc.next())); //아니면 숫자 출력(맵)
		}		
	}
}
