package com.ssafy.algorithm.day15_0221_서로소집합;
import java.io.*;
import java.util.*;
/**
 * <pre>
 * BJ15683 [G4] : 감시 
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 카메라 좌표 저장해서 각 카메라 별로 DFS 탐색. 감시 구역 결정해서 사각지대 구하고 최소 결정
 * 결과: 91532KB, 324ms
 */

/* <요약>
 * 사무실은 N×M 크기의 행렬로 나타낼 수 있다.
 * K개의 CCTV가 있는데, 5방향으로 감시 가능(오,오/왼, 오/위, 오/왼/위, 위/오/아래/왼)
 * 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다. 벽은 통과X. 
 * 사각지대: 감시할 수 없는 영역.
 * 회전은 항상 90도 방향으로 해야 하며, 감시 방향이 가로 또는 세로 방향이어야 한다.
 * CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하시오.
[입력]
1) 행크기 N, 열크기 M. (1 ≤ N, M ≤ 8)
N개 줄) 각 칸의 정보. (0: 빈 칸, 6: 벽, 1~5: CCTV) (CCTV개수 <= 8)
[출력]
사각 지대의 최소 크기
 */
public class Main_15683_감시_박봉균 {
	/** 4방 방향벡터: 위, 오, 아, 왼 */
	static final int[] dy = {-1, 0, 1, 0};
	static final int[] dx = {0, 1, 0, -1};
	
	/** 행크기 N, 열크기 M */
	static int N, M;
	/** 사무실 정보 행렬 */
	static int[][] matrix;
	/** 카메라 좌표를 담는 리스트 */
	static ArrayList<Pair> cams = new ArrayList<>();
	/** 결과 변수: 사각지대 최소 크기 */ 
	static int ret = Integer.MAX_VALUE;

	/** 카메라가 닿는 곳은 -1로 바꾸는 함수 */
	static void check(int y, int x, int dir) {
		dir %= 4; //방향이 인덱스 벗어나면 보정
		
		while(true) {
			//다음 좌표 계산
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			//좌표 검증(오버, 언더 체크)
			if(ny < 0 || ny >= N || nx < 0 || nx >= M)
				return;
			//좌표 갱신
			y = ny;
			x = nx;

			//벽(6)이면 종료
			if(matrix[ny][nx] == 6) 
				return;
			//카메라이면 통과
			if(matrix[ny][nx] != 0) 
				continue;
			
			//카메라 감시 구역이면 -1
			matrix[ny][nx] = -1;
		}
	}
	
	/**
	 * 방향 별로 돌리며 카메라 번호 별로 감시구역 체크하고 사각지대의 최소를 구하는 함수
	 * @param idx : 카메라 리스트에서 현재 인덱스
	 */
	static void dfs(int idx) {
		//종료조건! : 모든 카메라 고려
		if(idx == cams.size()) {
			int blind = 0;//사각지대 수
			
			//감시구역은 check()에서 -1로 수정함 -> 아직 0이라면 사각지대!
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(matrix[i][j] == 0)
						++blind;
				}
			}
			
			//최소 갱신, 종료
			ret = Math.min(ret, blind);
			return;
		}
		
		//현재 카메라의 좌표
		int y = cams.get(idx).first;
		int x = cams.get(idx).second;
		
		//케이스 별 원상복구해주기 위한 임시 배열
		int[][] tmp = new int[N][M];
		for(int d = 0; d < 4; d++) {
			//tmp로 옮겨놓기
			for(int i = 0; i < N; i++) 
					tmp[i] = matrix[i].clone();
			
			//카메라 별로 확인
			switch(matrix[y][x]) {
			case 1: //위
				check(y, x, d);
				break;
			case 2: //위,아래
				check(y, x, d);
				check(y, x, d+2);
				break;
			case 3:  //위, 왼
				check(y, x, d);
				check(y, x, d+3);
				break;
			case 4: //위, 아래, 왼
				check(y, x, d);
				check(y, x, d+2);
				check(y, x, d+3);
				break;
			case 5: //위, 오, 아래, 왼
				check(y, x, d);
				check(y, x, d+1);
				check(y, x, d+2);
				check(y, x, d+3);
				break;
			}
			
			//다음 카메라 탐색
			dfs(idx + 1);
			
			//원상복구
			for(int i = 0; i < N; i++) 
				matrix[i] = tmp[i].clone();
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//행크기 N, 열크기 M 입력, 동적할당
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		matrix = new int[N][M];

		//사무실 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				
				//카메라이면 카메라 리스트에 추가
				if(1 <= matrix[i][j] && matrix[i][j] <= 5)
					cams.add(new Pair(i, j));
			}
		}
		
		//카메라 별 사각지대 구하고 최솟값 구하기
		dfs(0);

		//사각지대의 최소 크기 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	/** 좌표 저장 위한 Pair 클래스 */
	static class Pair {
		int first;
		int second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
}