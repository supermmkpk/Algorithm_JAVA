package day1_DP3_Knapsack;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * 	BJ4486_G4 : 녹색 옷 입은 애가 젤다지?
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 */
/* <시간제한: 1초>
 * 게임에서 화폐의 단위는 루피. 도둑루피: 획득하면 오히려 소지한 루피가 감소!
 * 링크는 지금 도둑루피만 가득한 N*N 동굴 [0][0]에 있다.
 * 링크는 [N-1][N-1]까지 이동해야 한다. 
 * 각 칸마다 도둑루피가 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다.
 * 링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.
 * 링크가 잃을 수밖에 없는 최소 금액은 얼마일까?
[입력]
각 테케] 1) 행렬 크기 N. (2 ≤ N ≤ 125, N == 0이면 종료)
    N개 줄) 행렬. 각칸: 도둑루피의 크기 (크기만큼 잃는다) (0 <= 크기 <= 9)
[출력]
(Problem 테케번호: 정답) 형식에 맞춰서 출력.
 */
public class Main_4485_녹색옷입은애가젤다지_박봉균 {
	/** 상한 */
	static final int INF = (int) 1e9;
	/** 4방 방향벡터 */
	static final int[] dy = {-1, 0, 1, 0};
	static final int[] dx = {0, 1, 0, -1};
	
	static int N, matrix[][], visited[][];
	
	static int bfs(int sy, int sx) {
		//최솟값 갱신 위해 상한으로 초기화
		for(int i = 0; i < visited.length; i++) 
			Arrays.fill(visited[i], INF);
		
		//시작 위치의 도둑루피 값 저장
		visited[sy][sx] = matrix[sy][sx]; 
		
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(sy, sx));
		
		while(!q.isEmpty()) {
			int y = q.peek().first;
			int x = q.remove().second;
			
			for(int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				
				if(ny < 0 || ny >= N || nx < 0 || nx >= N) //오버언더 체크
					continue;
				
				//다음 위치 이동 시, 최소 루피로 이동
				if(visited[ny][nx] > visited[y][x] + matrix[ny][nx]) {
					visited[ny][nx] = visited[y][x] + matrix[ny][nx];
					q.add(new Pair(ny, nx));
				}
			}
		}
		//[N-1][N-1]칸에서의 최소 누적 값을 반환
		return visited[N - 1][N - 1]; 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int t = 1; ; t++) {
			N = Integer.parseInt(br.readLine()); //행렬 크기 N
			if(N == 0) { //N == 0이면 종료
				break;
			}
			matrix = new int[N][N]; //행렬
			visited = new int[N][N]; //방문 배열(잃은 최소 금액 저장)
			
			//행렬(도둑루피 값) 입력
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//bfs 탐색 후, 출력 조건에 따라 잃은 최소 금액 출력
			bw.write("Problem " + String.valueOf(t) + ": ");
			bw.write(String.valueOf(bfs(0, 0)) + '\n');
		}
		bw.flush();
		bw.close();
		br.close();
	}

	/** 좌표 저장 위한 Pair 클래스 */
	static class Pair {
		int first, second;
		Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}
	
}
