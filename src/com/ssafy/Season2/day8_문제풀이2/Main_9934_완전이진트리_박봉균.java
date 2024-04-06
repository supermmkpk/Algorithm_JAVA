import java.io.*;
import java.util.*;

/**
 * 작성자 : 박봉균
 * 아이디어 : 각 레벨별 가운데의 왼쪽은 왼쪽 서브트리, 오른쪽은 오른쪽 서브트리
 * 결과: 12372KB, 92ms
 */

/*
 * <BJ9934_S1 : 완전이진트리>
 * <시간제한: 1초>
 * 도시의 도로는 깊이가 K인 완전 이진 트리를 이루고 있다. 깊이가 K인 완전 이진 트리는 총 2^K-1개의 노드로 이루어져 있다.
 * 각 노드에는 빌딩 번호가 붙여져 있다. 또, 가장 마지막 레벨을 제외한 모든 집은 왼쪽 자식과 오른쪽 자식을 갖는다.
 * 상근이는 도시에 있는 모든 빌딩에 들어갔고, 들어간 순서대로 번호를 종이에 적어 놓았다.
 * 1. 가장 처음에 상근이는 트리의 루트에 있는 빌딩 앞에 서있다.
 * 2. 현재 빌딩의 왼쪽 자식에 있는 빌딩에 아직 들어가지 않았다면, 왼쪽 자식으로 이동한다.
 * 3. 현재 있는 노드가 왼쪽 자식을 가지고 있지 않거나 왼쪽 자식에 있는 빌딩을 이미 들어갔다면, 현재 노드에 있는 빌딩을 들어가고 종이에
 * 번호를 적는다.
 * 4. 현재 빌딩을 이미 들어갔다 온 상태이고, 오른쪽 자식을 가지고 있는 경우에는 오른쪽 자식으로 이동한다.
 * 5. 현재 빌딩과 왼쪽, 오른쪽 자식에 있는 빌딩을 모두 방문했다면, 부모 노드로 이동한다.
 * 상근이가 종이에 적은 순서가 모두 주어졌을 때, 각 레벨에 있는 빌딩의 번호를 구하시오.
 * [입력]
 * 1) K. (1 ≤ K ≤ 10)
 * 2) 방문한 빌딩 번호가 들어간 순서대로 주어진다. (1 <= 번호 < 2^K, 중복X)
 * [출력]
 * K개 줄) i번째 줄에는 레벨이 i인 빌딩의 번호를 출력한다.
 */
public class Main_9934_완전이진트리_박봉균 {

    static int K, end, nodes[];
    static ArrayList<Integer>[] levelList = new ArrayList[14];

    static void go(int left, int right, int level) {
        if (left > right)
            return;
        else if (left == right) { // 구간 더 쪼갤 수 없으면 서브트리X -> 단말노드
            levelList[level].add(nodes[left]);
            return;
        }

        int mid = (left + right) / 2; // 현 구간의 가운데는 현 레벨의 노드(지금 바라보고 있는 노드)
        levelList[level].add(nodes[mid]);
        go(left, mid - 1, level + 1); // 왼쪽 구간은 왼쪽 서브트리
        go(mid + 1, right, level + 1); // 오른쪽 구간은 오른쪽 서브트리
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine()); // 레벨 개수
        end = (int) Math.pow(2, K) - 1; // 노드 개수
        nodes = new int[end + 4];
        for (int i = 0; i < levelList.length; i++)
            levelList[i] = new ArrayList<>();

        // 노드 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < end; i++) {
            nodes[i] = Integer.parseInt(st.nextToken());
        }

        // inorder 탐색, 레벨 별 노드 저장
        go(0, end, 0);

        // 레벨 별 노드들 출력
        for (int level = 0; level < K; level++) {
            for (int node : levelList[level])
                bw.write(String.valueOf(node) + " ");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
