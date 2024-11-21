package day4_powerSet;

import java.util.*;
import java.io.*;

/* <시간제한: 1초>
 * 재료가 N개. 각 재료의 신맛 S와 쓴맛 B를 알고 있다. 
 * 신맛 = 재료의 신맛의 곱, 쓴맛 = 합.
 * 신맛과 쓴맛의 차이를 작게 만들려고 한다. (재료는 적어도 하나 사용해야 한다)
 * 신맛과 쓴맛의 차이가 가장 작은 요리를 만드시오.
[입력]
1) 재료의 개수 N. (1 ≤ N ≤ 10)
N개 줄) 신맛 <공백> 쓴맛. (모든 재료를 사용 시, 신맛 쓴맛 < 1e9 인 양의 정수)
[출력]
신맛과 쓴맛의 차이의 최솟값
 */
class BJ2961_도영이가만든맛있는음식_S2 {
	
	//멱집합 구하자! -> 들어가거나 안들어가거나 -> O(2^10)
	
	/** 재료 개수 */
	static int N;
	/** 재료 배열 */
	static Pair[] ingredients;
	/** 맛 차이 최소 */
	static int ret = (int) 1e9;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//재료 개수 입력, 동적할당
		N = Integer.parseInt(br.readLine());
		ingredients = new Pair[N];
		
		//재료의 {신맛, 쓴맛} 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ingredients[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		//멱집합(비트마스킹)
		//1부터 (재료는 적어도 하나 사용해야 하므로)
		for(int i = 1; i < (1 << N); i++) {
			//해당 조합에 대하여
			//초기화
			int sour = 0;
			int bitter = 0;
			for(int j = 0; j < N; j++) {
				if((i & (1 << j)) != 0) { //포함
					//신맛 = 재료의 신맛의 곱, 쓴맛 = 합.
					if(sour == 0) {
						sour = ingredients[j].first;
					}
					else {
						sour *= ingredients[j].first;
					}
					
					bitter += ingredients[j].second;
				}
			}
			ret = Math.min(ret, Math.abs(sour - bitter));
		}
		
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Pair {
		int first, second;
		
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
	
}
