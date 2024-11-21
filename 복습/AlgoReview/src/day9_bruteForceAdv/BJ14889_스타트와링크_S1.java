package day9_bruteForceAdv;

import java.io.*;
import java.util.*;

/* <시간제한: 2초>
 * 축구를 위해 모인 사람은 N명(N은 짝수). N/2명의 스타트 팀, 링크 팀으로 나눠야 한다.
 * 1~N번을 배정하고, 능력치를 조사했다. 
 * 능력치 Sij는 i번,j번이 같은 팀에 속했을 때, 팀에 더해지는 능력치. 
 * 팀의 능력치 = 팀에 속한 모든 쌍의 능력치 Sij의 합. 
 * Sij는 Sji와 다를 수 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, Sij, Sji 모두 더해짐.
 * 스타트 팀의 능력치와 링크 팀의 능력치의 차이를 최소로 하자. 
[입력]
1) 사람수 N. (4 ≤ N ≤ 20, N은 짝수)
N개 줄) 행렬 S. (Sii=0, 1 <= Sij <= 100)

[출력]
스타트 팀과 링크 팀의 능력치의 차이의 최솟값
 */
class BJ14889_스타트와링크_S1 {
	
	static int N, S[][], startTeam[], linkTeam[];
	static int ret = (int) 1e9;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//사람수 입력, 능력치 배열 동적 할당
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		startTeam = new int[N/2];
		linkTeam = new int[N/2];
		
		//능력치 배열 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//N/2명의 스타트 팀, 링크 팀으로 나눠야 한다. -> C(N, N/2)
		//그 안에서의 능력치는?
		//팀의 능력치 = 팀에 속한 모든 쌍의 능력치 Sij의 합. (Sij는 Sji와 다를 수 있으며, i번 사람과 j번 사람이 같은 팀에 속했을 때, Sij, Sji 모두 더해짐.)
		// -> N/2개 중 순서를 고려하여 두개(쌍)를 뽑아서 더한다! -> 순열 : P(N/2, 2) -> 2개니까 이중 for문
		
		combi(0, 0);
		
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	//C(N, N/2)
	static void combi(int cnt, int start) {
		if(cnt == N/2) {
			int idxStart = 0;
			int idxLink = 0;
			for(int i = 0; i < N; i++) {
				if(idxStart < N/2 && startTeam[idxStart] == i) {
					idxStart++;
				}
				else {
					linkTeam[idxLink++] = i;
				}
			}
			ret = Math.min(ret, Math.abs(permu(startTeam) - permu(linkTeam)));
			return;
		}
		
		for(int i = start; i < N; i++) {
			startTeam[cnt] = i;
			combi(cnt + 1, i + 1);
		}
	}
	
	//P(N/2, 2)
	static int permu(int[] team) {
		int statSum = 0;
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < N/2; j++) {
				if(i == j) {
					continue;
				}
				statSum += S[team[i]][team[j]];
			}
		}
		return statSum;
	}
}
