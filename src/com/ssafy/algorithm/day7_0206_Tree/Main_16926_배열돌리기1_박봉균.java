package com.ssafy.algorithm.day7_0206_Tree;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ16926 [S1] : 배열 돌리기1
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/73023915"></a>
 * 아이디어: 각각의 링에서의 상대적 위치를 R 더해서 갱신하여 LinkedList에 {idx, 값}으로 저장 후 idx 기준 정렬 -> 링 다시 탐색 하면서  배열 수정
 * 결과: 46784KB, 476ms
 */

/* N×M 2차원 배열이 있다.
 * 다음과 같이 반시계 방향으로 돌리려 한다.
Ex) 아래와 같은 배열을 2번 회전시키면 다음과 같이 변하게 된다.
1 2 3 4       2 3 4 8       3 4 8 6
5 6 7 8       1 7 7 6       2 7 8 2
9 8 7 6   →   5 6 8 2   →   1 7 6 3
5 4 3 2       9 5 4 3       5 9 5 4
 <시작>         <회전1>        <회전2>
 
 * 2차원 배열을 R번 회전시킨 결과를 구하라.
[입력]
1) 배열의 크기 N, M, 회전 수 R. (2 ≤ N, M ≤ 300, min(N, M) % 2 = 0, 1 ≤ R ≤ 1,000)
N개 줄) 배열 A의 원소 Aij. (1 ≤ Aij ≤ 1e8)

[출력]
배열을 R번 회전시킨 결과.
 */
public class Main_16926_배열돌리기1_박봉균 {
	/** 방향벡터 */
	public static final int[] dy = {1, 0, -1, 0};
	public static final int[] dx = {0, 1, 0, -1};
	/** 배열의 크기 N, M, 회전 수 R */
	static int N, M, R;
	/** 주어지는 2차원 배열 */
	static int[][] A;
	/** {인덱스, 값} Pair 리스트 */
	static LinkedList<Pair> list = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//배열의 크기 N, M, 회전 수 R 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		//A 동적할당
		A = new int[N][M];
		
		//배열 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) 
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int nRow = N; //행 수
		int nCol = M; //열 수
		int depth = 0; //깊이
		while(nRow > 0 && nCol > 0) { //행수, 열수가 0보다 클 때,
			int nNow = (nRow - 2) * 2 + nCol + nCol; //이번 깊이의 링의 원소 수
	
			//초기화
			list.clear();
			
			int y = depth; //시작하는 행번호
			int x = depth; //시작하는 열번호
			int idx = 1; //탐색한 원소 개수 트래킹
			for(int d = 0; d < 4; d++) {
				boolean flag = false;
			
				while(true) {
					if(idx > nNow) break; //모두 탐색했다면 종료
					int ny = y + dy[d]; //다음 행 
					int nx = x + dx[d]; //다음 열
	
					//오버, 언더 검증
					if(ny < depth || ny >= N - depth || nx < depth || nx >= M - depth) {
						flag = true;
						break;
					}
					//검증 후 갱신
					y = ny;
					x = nx;
					
					//인덱스 R만큼 이동 후 리스트 추가
					list.addLast(new Pair((idx + R) % nNow, A[y][x]));

					++idx; //탐색 원소수 카운트 증가
				}
				if(idx > nNow) break; //모두 탐색 시, 종료
				if(flag)
					continue; //방향 전환
			}
			
			//인덱스 기준 정렬
			Collections.sort(list); 
			
			y = depth;
			x = depth;
			idx = 1;
			A[y][x] = list.remove().second;
			for(int d = 0; d < 4; d++) { //다시 링 탐색
				boolean flag = false;
	
				while(true) {
					if(idx > nNow) break; //모두 탐색 시 종료
					int ny = y + dy[d];
					int nx = x + dx[d];
	
					//오버, 언더 체크
					if(ny < depth || ny >= N - depth || nx < depth || nx >= M - depth) {
						flag = true;
						break;
					}
					//검증 후 갱신
					y = ny;
					x = nx;
					if(!list.isEmpty()) 
						A[y][x] = list.removeFirst().second; //하나씩 꺼내서 배열 수정
				}
				if(idx > nNow) break; //모든 원소 탐색 후 종료
				if(flag)
					continue; //방향 전환
			}
			
			nRow -= 2; //행 수 2 감소
			nCol -= 2; //열수 2 감소
			depth++; //깊이 증가
		}

		//출력
		for(int[] row : A) {
			for(int col : row) 
				bw.write(String.valueOf(col) + " ");
			bw.write('\n');
		}
		//데이터 출력 및 stream 닫기
		bw.flush();
		bw.close();
		br.close();
	}
}

/**
 * Pair 클래스
 */
class Pair implements Comparable<Pair> {
	int first;
	int second;
	
	Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * 정렬 규칙: first 기준 오름차순
	 */
	@Override
	public int compareTo(Pair p) {
		return this.first - p.first;
	}
}