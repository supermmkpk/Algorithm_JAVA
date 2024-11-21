package com.ssafy.algorithm.day11_분할정복_백트래킹;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ3109 [G2] : 빵집
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: dfs로 탐색하되, 끝지점 도착하면 다시 시작점으로 돌아가기(한 칸에는 하나의 파이프만)
 * 결과: 41928KB, 332ms
 */

/* <문제 요약>
 * 원웅이는 근처 빵집의 가스관에 몰래 파이프를 설치해 훔쳐서 사용하기로 했다.
 * 빵집이 있는 곳은 R*C 행렬로 표현할 수 있다. 0열은 근처 빵집의 가스관이고, C-1열은 원웅의 빵집이다.
 * 빵집과 가스관 사이에는 건물이 있을 수도 있다. 건물이 있는 경우에는 파이프를 놓을 수 없다.
 * 모든 파이프라인은 0열에서 시작, C-1 열에서 끝. 
 * 각 칸은 오른쪽, 오른쪽 위, 오른쪽 아래 로 연결할 수 있다. 각 칸을 지나는 파이프는 하나이어야 한다.
 * 가스관과 빵집을 연결하는 파이프라인의 최대 개수를 구해보자.
[입력]
1) R, C. (1 ≤ R ≤ 10,000, 5 ≤ C ≤ 500)
R개 줄) 행렬. ('.': 빈 칸, 'x': 건물, 0열,C-1열은 항상 비어있다)
[출력]
놓을 수 있는 파이프라인의 최대 개수.
 */
public class Main_3109_빵집_박봉균 {
	/** 방향벡터 : 오위, 오, 오아래 */
	public static final int dy[] = {-1, 0, 1};
	public static final int dx[] = {1, 1, 1}; 
	
	/** 행수 R, 열수 C */
	static int R, C;
	/** 근처 정보 */
	static char[][] matrix;
	/** 방문 체크 배열 */
	static boolean visited[][];
	/** 끝점 도작 여부 (시작점까지 return하기 위해) */
	static boolean flag = false;
	/** 결과 변수: 파이프 경로 수 */
	static int ret;
	
	static void dfs(int y, int x) {
		//끝 점 도착 시, 경로 카운트 증가, flag true
		if(x == C - 1) {
			ret++;
			flag = true;
			return;
		}
		if(y != 0 && flag) //시작점까지 return
			return;
		if(y == 0)
			flag = false; //시작점 도달 시, 다시 flag 초기화

		/** DFS */
		visited[y][x] = true;
		for(int d = 0; d < 3; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny < 0 || ny >= R || nx < 0 || nx >= C) //오버 언더 체크
				continue;
			
			//방문X, 빈공간이면 다음 지점 탐색
			if(matrix[ny][nx] == '.' && visited[ny][nx] == false) 
				dfs(ny, nx);
			
			if(flag) //끝 지점에서 flag true 후 return했으므로 다른 방향 탐색 불필요: 유망하지 않음(시작점으로 연쇄 복귀)
				break;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//행수 R, 열수 C 입력, 동적할당
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		matrix = new char[R][C];
		visited = new boolean[R][C];
		
		//근처 정보 입력
		for(int i = 0; i < R; i++) {
			String given = br.readLine();
			for(int j = 0; j < given.length(); j++)
				matrix[i][j] = given.charAt(j);
		}
		
		//파이프는 0열에서 시작
		for(int i = 0; i < R; i++) {
			flag = false; //flag 초기화
			if(visited[i][0] == false) //방문 X: DFS 탐색(시작은 0열에서)
				dfs(i, 0);
		}
		
		//결과(파이프 경로 최대 개수) 출력
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
