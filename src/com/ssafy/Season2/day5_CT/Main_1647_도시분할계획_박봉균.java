import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 아이디어: MST 크루스칼 알고리즘에서 V-2개 간선 연결 -> 최적 비용으로 연결된 두 서브트리 생성
 * 결과: 324540KB, 1508ms
 */

/* <BJ1647_G4 : 도시 분할 계획>
 * <시간제한: 2초>
 * 마을: N개 집, 그 집들을 연결하는 M개 길.
 * 길: 무방향, 각 길마다 유지비 존재.
 * 임의의 두 집 사이에 경로가 항상 존재한다.
 * 두 개의 분리된 마을로 분할하려 한다.
 * 각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다
 * 마을에는 집이 하나 이상 있어야 한다.
 * 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다.
 * 길들을 최대한 없애고 유지비 합의 최소를 구하자.
[입력]
1) 집 개수 N, 길 개수 M. (2 <= N <= 1e5, 1 <= M <= 1e6)
M개 줄) 길정보 A, B, C (A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000))
[출력]
없애고 남은 길 유지비의 합의 최솟값
*/
public class Main_1647_도시분할계획_박봉균 {
    static int N, M, parent[];
    static List<Edge> roads = new ArrayList<>(); //간선 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점(집) 개수
        M = Integer.parseInt(st.nextToken()); //간선(길) 개수
        parent = new int[N + 4]; //부모 정점 번호 배열

        //집 2개면 그냥 0 출력
        if(N == 2) {
            System.out.println(0);
            System.exit(0);
        }

        //길(간선) 정보 입력
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            roads.add(new Edge(a, b, c));
        }

        //정점 생성
        for(int i = 0; i < N; i++)
            parent[i] = i;

        //가중치 기준 정렬
        Collections.sort(roads);

        //작은 가중치부터 연결
        int ret = 0; //유지비의 합
        int cnt = 0; //현재까지 연결된 간선 수
        for(Edge e : roads) {
            //같은 서브트리 소속이라면 연결 X
            if(!union(e.from, e.to))
                continue;
            
            //유지비 갱신(누적)
            ret += e.weight;

            //N-2개 간선 연결 시, 최소 비용으로 두 서브트리가 연결된 상태!
            if(++cnt == N - 2)
                break;
        }
        bw.write(String.valueOf(ret) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }

    //루트 노드 번호를 찾는 함수
    static int find(int a) {
        if(a == parent[a])
            return a;
        return parent[a] = find(parent[a]);
    }

    //두 서브트리를 합치는 함수
    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        //같은 트리 소속이라면 union 불필요
        if(rootA == rootB)
            return false;

        parent[rootB] = rootA;
        return true;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        } 
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
