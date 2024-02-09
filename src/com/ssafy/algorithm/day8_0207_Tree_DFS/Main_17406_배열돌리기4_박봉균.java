package com.ssafy.algorithm.day8_0207_Tree_DFS;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ17406 [G4] : 배열 돌리기 4
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/73101244"></a>
 * 
 * 아이디어: 배열 돌리기1과 같은 로직 + 순열 + 깊은 복사
 * 결과: 184816KB, 800ms
 */
public class Main_17406_배열돌리기4_박봉균 {
	/** 방향벡터 */
	public static final int[] dy = {0, 1, 0, -1};
	public static final int[] dx = {1, 0, -1, 0};
	/** 배열의 크기 N, M, 연산 수 K */
	static int N, M, K;
	/** 주어지는 행렬 A */
	static int[][] A;
	/** {인덱스, 값} Pair 리스트 */
	static LinkedList<Pair> list = new LinkedList<>();
	/** 주어진 연산의 rcs 정보 배열 */
	static Tuple[] oper;
	/** 방문 배열 : 순열에 사용 */
	static boolean[] visited;
	/** 결과 변수 */
	static int ret = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//배열의 크기 N, M, 연산수 K 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		//배열 동적할당
		A = new int[N][M];
		oper = new Tuple[K];
		visited = new boolean[K];
		
		//배열 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) 
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		//K개 연산의 {r,c,s} Tuple을 oper배열 넣습니다.
		int r, c, s;
		for(int i = 0; i < K; i++) { 
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			
			oper[i] = new Tuple(r, c, s);
		}
		
		permu(0, A); //연산 순서를 정하고 회전합니다

		//배열의 합의 최솟값 출력
		bw.write(String.valueOf(ret) + '\n');

		//데이터 출력 및 stream 닫기
		bw.flush();
		bw.close();
		br.close();
	}

	/** 계산의 순서의 순열을 정하고, 그 중 배열의 값의 최솟값을 구합니다 */
	static void permu(int cnt, int[][] matrix) {
		//종료조건
		if(cnt == K) {
			int min= Integer.MAX_VALUE; //각 행의 합의 최소
			int sum = 0;
			for(int i = 0; i < N; i++) {
				 sum = 0;
				 for(int j = 0; j < M; j++) {
					 sum += matrix[i][j];
				 }
				 min = Math.min(min, sum); //각 행의 합의 최소
			}
			ret = Math.min(ret, min); //모든 순서에서의 배열합의 최소
			return;
		}
		for(int i = 0; i < K; i++) { //연산의 순서를 정하고 그에 따라 회전
			if(visited[i] == true)
				continue;
			
			visited[i] = true;
			//rotate 함수에서 내부적으로 깊은복사 후 회전한 배열을 반환하므로 matrix는 오염되지 않음!
			permu(cnt + 1, rotate(oper[i].r, oper[i].c, oper[i].s, matrix)); 
			visited[i] = false;
		}
	} 

	/** 깊은 복사한 배열을 회전 후 반환 */
	static int[][] rotate(int r, int c, int s, int[][] matrix) {
		int[][] rotatedMatrix = new int[N][M];
		//깊은 복사
		for(int i = 0; i < N; i++)
			rotatedMatrix[i]= matrix[i].clone();
		
		//행수 = 열수 = s * 2 + 1
		int nRow = s * 2 + 1; //행 수
		int nCol = s * 2 + 1; //열 수
		
		//행: r-s ~ r+s, 열: c-s ~ c+s
		int yInit = r - s;
		int xInit = c - s; 
		int yEnd = r + s;
		int xEnd = c + s;
	
		int depth = 0; //깊이
		
/* ----- 돌리기 시작 ----- */
		while(nRow > 0 && nCol > 0) { //행수, 열수가 0보다 클 때,
			list.clear(); //초기화
			
			int nNow = (nRow - 2) * 2 + nCol + nCol; //이번 깊이의 링의 원소 수
	
			int y = yInit + depth; //시작하는 행번호
			int x = xInit + depth; //시작하는 열번호
			int idx = 1; //탐색한 원소 개수 트래킹
			for(int d = 0; d < 4; d++) {
				boolean flag = false;
				
				while(true) {
					if(idx > nNow) 
						break; //모두 탐색했다면 종료
					int ny = y + dy[d]; //다음 행 
					int nx = x + dx[d]; //다음 열
					
					//오버, 언더 검증
					if(ny < yInit + depth || ny > yEnd - depth || nx < xInit + depth || nx > xEnd - depth) {
						flag = true;
						break;
					}
					//검증 후 갱신
					y = ny;
					x = nx;
					
					//인덱스 1만큼 이동 후 리스트 추가
					list.addLast(new Pair((idx + 1) % nNow, rotatedMatrix[y][x]));
					++idx; //탐색 원소수 카운트 증가
				}
				if(idx > nNow) break; //모두 탐색 시, 종료
				if(flag) continue; //방향 전환
			}
			
			//인덱스 기준 정렬
			Collections.sort(list); 

			y = yInit + depth; //시작하는 행번호
			x = xInit + depth; //시작하는 열번호
			idx = 1; //탐색한 원소 개수 트래킹
			if(!list.isEmpty())
				rotatedMatrix[y][x] = list.removeFirst().second;
			for(int d = 0; d < 4; d++) { //다시 링 탐색
				boolean flag = false;
	
				while(true) {
					if(idx > nNow) break; //모두 탐색 시 종료
					int ny = y + dy[d];
					int nx = x + dx[d];
	
					//오버, 언더 체크
					if(ny < yInit + depth || ny > yEnd - depth || nx < xInit + depth || nx > xEnd - depth) {
						flag = true;
						break;
					}
					//검증 후 갱신
					y = ny;
					x = nx;
					
					if(!list.isEmpty()) 
						rotatedMatrix[y][x] = list.removeFirst().second; //하나씩 꺼내서 배열 수정
				}
				if(idx > nNow) break; //모든 원소 탐색 후 종료
				if(flag) continue; //방향 전환
			}
			
			nRow -= 2; //행 수 2 감소
			nCol -= 2; //열수 2 감소
			depth++; //깊이 증가
		}
/* ----- 돌리기 끝 ----- */	 
		
		return rotatedMatrix;
	}
}

/** r, c, s 저장 튜플 */
class Tuple {
	int r;
	int c; 
	int s;
	Tuple(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

class Pair implements Comparable<Pair> {
	int first;
	int second;
	Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
	//정렬 규칙: first 기준 오름차순
	@Override
	public int compareTo(Pair p) {
		return this.first - p.first;
	}
}
