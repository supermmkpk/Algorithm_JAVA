package personal.week4_비트마스킹;
import java.io.*;
import java.util.*;

/**
 * BJ14890 [G3] : 경사로
 * @author 박봉균
 * 결과: 12812KB, 104ms
 */

/* <요약>
 * N*N 지도에서 각 칸에는 그 곳의 높이가 적혀져 있다.
 * 길: 한 행 또는 한 열 전부 (한쪽 끝에서 다른쪽 끝까지 가는 것)
 * 길을 지나가려면 모든 칸의 높이가 모두 같거나, 경사로를 놓아서 지나갈 수 있다.
 * 경사로 높이는 항상 1, 길이는 L.(개수는 매우 많아 부족할 일이 없다) 경사로는 낮은 칸과 높은 칸을 연결하며, 아래와 같은 조건을 만족한다.
 *  1. 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접한다.
 *  2. 낮은 칸과 높은 칸의 높이 차이 1
 *  3. 경사로를 놓을 낮은 칸의 높이는 모두 같아야 하고, L개의 칸이 연속되어 있어야 한다.
 * 아래와 같은 경우에는 경사로를 놓을 수 없다.
 *  1. 경사로를 놓은 곳에 또 경사로를 놓는 경우
 *  2. 낮은 칸과 높은 칸의 높이 차이가 1이 아닌 경우
 *  3. 낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않은 경우
 *  4. 경사로를 놓다가 범위를 벗어나는 경우
 * 지나갈 수 있는 길의 개수를 구하시오.
[입력]
1) 행/열 크기 N, 경사로 길이 L (2 ≤ N ≤ 100, 1 ≤ L ≤ N)
N개 줄) 지도. (1 <= 각 칸의 높이 <= 10)
[출력]
길의 개수
 */
public class Main_14890_경사로_박봉균 {
	
	static int a[][], b[][], cnt, ret, N, L;
	
	static void go(int[][] arr) {
		//모든 행에 대해서
		for(int i = 0; i < N; i++) {
			cnt = 1;
			int j = 0;
			for(;j < N - 1; j++) {
				if(arr[i][j] == arr[i][j+1])
					++cnt;
				//오르막
				else if(arr[i][j] + 1 == arr[i][j+1] && cnt >= L)
					cnt = 1; //초기화, 여기부터 다시 시작(오르막은 카운트 양수로 구분)
				//내리막
				else if(arr[i][j] - 1 == arr[i][j+1] && cnt >= 0)
					cnt = 1 - L; //초기화, 여기부터 다시 시작((내리막은 카운트 음수로 구분)
				//아니면 불가능
				else
					break;
			}
			//끝까지 갔고, 내리막 중간에서 끝나지 않았다면 길 가능 -> ret++
			if(j == N - 1 && cnt >= 0)
				++ret;
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//행,열 크기
		N = Integer.parseInt(st.nextToken());
		//경사로 길이
		L = Integer.parseInt(st.nextToken());
		a = new int[N][N];
		b = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				b[j][i] = a[i][j]; //같은 로직으로 열에 대해서도 처리하기 위해 대칭 배열 b 생성
			}
		}
		
		//길 개수 구하기(같은 로직으로 a: 행, b: 열)
		go(a);
		go(b);
		
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
