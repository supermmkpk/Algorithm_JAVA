import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        아이디어: 작은 방향, 큰 방향으로 두 번 완탐(DFS)
 *        결과: 111756KB 3643ms
 */

/*
 * 1~N번까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어질 때,
 * 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력하시오.
 * [입력]
 * 1) 테케 개수 T. (1 ≤ T ≤ 15)
 * 각 테케] 1) 학생들의 수 N. (2 ≤ N ≤ 500)
 * 2) 두 학생의 키를 비교한 횟수 M. (0 ≤ M ≤ N*(N-1)/2)
 * M개 줄) 두 학생의 키를 비교한 결과 a, b. (a키 < b키) (a -> b)
 * [출력]
 * #테케 번호 <공백> 자신이 키가 몇 번째인지 알 수 있는 학생수
 */
public class D4_5643_키순서_박봉균 {
    static int N, M, cnt, ret;
    static LinkedList<Integer> adjGreater[], adjLesser[];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 테케 개수

        // T개의 테케에 대하여
        for (int t = 1; t <= T; t++) {
            // 초기화
            ret = 0;
            N = Integer.parseInt(br.readLine()); // 정점 개수(학생수)
            M = Integer.parseInt(br.readLine()); // 간선수(비교 횟수)
            visited = new boolean[N]; // 방문체크 배열
            adjGreater = new LinkedList[N]; // 키 큰 방향의 간선의 인접리스트
            adjLesser = new LinkedList[N]; // 키 작은 방향의 간선의 인접리스트
            for (int i = 0; i < N; i++) {
                adjGreater[i] = new LinkedList<>();
                adjLesser[i] = new LinkedList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                adjGreater[from].add(to); // from의 키 < to의 키
                adjLesser[to].add(from); // from의 키 > to의 키
            }

            // 모든 정점에 대하여
            for (int i = 0; i < N; i++) {
                cnt = 0; // 가거나 올 수 있는 정점 수
                Arrays.fill(visited, false);
                dfs(i, adjGreater);
                Arrays.fill(visited, false);
                dfs(i, adjLesser);
                if (cnt == N - 1) // 가거나 올 수 있는 정점 수 N-1개 면 가능
                    ++ret;
            }
            bw.write("#" + String.valueOf(t) + " " + String.valueOf(ret) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }

    // DFS 탐색하며 경로 상 가거나 올 수 있는 정점 개수를 갱신하는 함수
    static void dfs(int now, LinkedList<Integer>[] adjList) {
        visited[now] = true;

        for (int next : adjList[now]) {
            if (visited[next] == false) {
                ++cnt;
                dfs(next, adjList);
            }
        }
    }
}
