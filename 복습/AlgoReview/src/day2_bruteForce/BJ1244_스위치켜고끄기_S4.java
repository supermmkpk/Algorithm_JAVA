package day2_bruteForce;

import java.util.*;
import java.io.*;

/* <시간제한: 2초>
 * 1~N번의 스위치들(On(1)/Off(0) 상태)이 있다. N은 스위치 개수.
 * 학생 몇 명을 뽑아서, 학생들에게 1~N 자연수를 하나씩 나누어주었다. 학생들은 자신의 성별과 받은 수에 따라 아래와 같은 방식으로 스위치를 조작하게 된다.
 *  - 남: 스위치 번호가 받은 수의 배수 -> 스위치 상태를 바꿈.
 *  - 여: 받은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를 모두 바꾼다. (구간에 속한 스위치 개수는 항상 홀수)
 *  스위치들의 처음 상태, 각 학생의 성별과 받은 수가 주어진다. 입력되는 순서대로 자기의 성별과 받은 수에 따라 스위치의 상태를 바꾸었을 때, 스위치들의 마지막 상태를 출력하시오.
[입력]
1) 스위치 개수. (100 이하 양의 정수)
2) 스위치의 상태
3) 학생 수.(100 이하 양의 정수)
4~) 한 줄에 한 학생의 성별, 받은 수. (남: 1, 여: 2, 받은 수는 스위치 개수 이하인 양의 정수)
[출력]
스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력.
 */

class BJ1244_스위치켜고끄기_S4 {
	
	/** 스위치 개수, 학생 수 */
	static int switchN, studentN;
	/** 스위치 상태 */
	static int[] stat;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//스위치 개수 입력, 동적할당
		switchN = Integer.parseInt(br.readLine());
		stat = new int[switchN + 4]; 
		
		//스위치 상태 입력
		//1 ~ switchN 번
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= switchN; i++) {
			stat[i] = Integer.parseInt(st.nextToken());
		}
		
		//학생 수 입력
		studentN = Integer.parseInt(br.readLine());
		
		//학생 정보 입력
		for(int i = 0; i < studentN; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int given = Integer.parseInt(st.nextToken()); 
			
			if(gender == 1) {
				// 남
				for(int j = 1; j <= switchN; j++) {
					if(j % given == 0) { // 스위치 번호가 받은 수의 배수이면,
						stat[j] ^= 1; // 스위치 상태를 바꿈.
					}
				}
			}
			else if(gender == 2) {
				// 여
				int diff = 1;
				stat[given] ^= 1;
				while(1 <= given - diff && given + diff <= switchN) {
					//구간 내 범위에 대하여
					//받은 번호 중심으로 양쪽으로 뻗어나가며 확인한다.
					int left = given - diff;
					int right = given + diff;
					
					if(stat[left] == stat[right]) {
						//같으면(좌우 대칭) 바꾸고 전진
						stat[left] ^= 1;
						stat[right] ^= 1;
						++diff;
					}
					else {
						//다르면 그만!
						break;
					}					
				}
			
			}
		}
		
		//스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력.
		for(int i = 1; i <= switchN; i++) {
			bw.write(String.valueOf(stat[i]) + " ");
			if(i % 20 == 0) {
				bw.newLine();
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
