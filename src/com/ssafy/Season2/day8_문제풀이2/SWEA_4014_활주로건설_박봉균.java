import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        아이디어: 먼저 행 별로 체크하는 로직 세우고 같은 로직으로 배열 돌려서 열 체크
 *        cnt 변수의 음수 초기화도 생각해보자 (음수도 가능하다!)
 *        결과: 27676KB, 141ms
 */
/*
 * N * N 크기의 절벽지대에 활주로를 건설하려고 한다. 각 칸의 숫자는 그 지형의 높이를 의미한다.
 * 활주로를 가로 또는 세로 방향으로 건설할 수 있는지 확인하려고 한다.
 * 활주로는 높이가 동일한 구간에서 건설 가능.
 * 높이가 다른 구간: 경사로를 설치해야
 * 경사로: 길이=X, 높이=1 (높이차=1 이고 낮은 지형의 높이가 동일하게 경사로의 길이만큼 연속되는 곳에 설치 할 수 있다.)
 * 활주로를 건설할 수 있는 경우의 수를 계산하라.
 * [입력]
 * 1) 테케 개수 T.
 * 각 테케] 1) 행열 크기 N, 경사로 길이 X. ( 6 ≤ N ≤ 20 , 2 ≤ X ≤ 4 )
 * N개 줄) 지형 정보 행렬. (1 <= 지형높이(각 칸의 값) <= 6)
 * [출력]
 * #테케번호 <공백> 활주로 건설 가능 경우의 수
 */
public class SWEA_4014_활주로건설_박봉균 {

    static int N, X, ret;

    static void check(int[][] matrix) {
        for (int i = 0; i < N; i++) {
            int cnt = 1; // 같은 높이 구간의 길이
            int j = 1;
            for (; j < N; j++) {
                if (matrix[i][j - 1] == matrix[i][j]) {
                    cnt++;
                }
                // 내려가는 경우
                else if (matrix[i][j - 1] - 1 == matrix[i][j] && cnt >= 0) {
                    cnt = 1 - X;
                }
                // 올라가는 경우
                else if (matrix[i][j - 1] + 1 == matrix[i][j] && cnt >= X) {
                    cnt = 1;
                } else
                    break;
            }

            // 내리막 중간에 끝나지 않았고, 끝까지 갔다면 -> 경우의 수
            if (cnt >= 0 && j == N) {
                ++ret;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 초기화
            ret = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 행렬크기
            X = Integer.parseInt(st.nextToken()); // 경사로 길이(높이는 1)
            int[][] matrixRow = new int[N][N]; // 행 우선
            int[][] matrixCol = new int[N][N]; // 열 우선

            // 지형 정보 행렬 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    matrixRow[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            check(matrixRow); // 행 체크

            // 배열 돌려서 같은 로직으로 한번 더
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrixCol[j][i] = matrixRow[i][j];
                }
            }

            check(matrixCol); // 열 체크

            bw.write("#" + String.valueOf(t) + " ");
            bw.write(String.valueOf(ret) + '\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
