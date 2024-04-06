import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 *        아이디어: 빡구현.... (꼼꼼하게 해야 할 거 잘 따져서 다 하기)
 *        결과: 37260KB, 356ms
 */

/*
 * <BJ17144_G4 : 미세먼지 안녕!>
 * <시간제한: 1초>
 * 집을 크기가 R×C인 격자판으로 나타냈고, 1×1 크기의 칸으로 나눴다.
 * 공기청정기는 항상 1 열에 있고, 2행을 차지.
 * 공기청정기가 없는 칸에는 미세먼지가 있고, (r, c)에 있는 미세먼지의 양은 Ar,c.
 * 1초 동안 아래 적힌 일이 순서대로 일어난다.
 * 1. 미세먼지 확산. 미세먼지가 있는 모든 칸에서 동시에 인접한 네 방향으로 확산.
 * 2. 인접한 방향에 공기청정기 or 칸이 없으면 확산X.
 * 3. 확산되는 양은 Ar,c/5이고 소수점은 버린다. 즉, ⌊Ar,c/5⌋이다.
 * 4. (r, c)에 남은 미세먼지의 양은 Ar,c - ⌊Ar,c/5⌋×(확산된 방향의 개수) 이다.
 * 공기청정기가 작동한다.
 * 1. 바람: 위쪽: 반시계방향, 아래쪽: 시계방향
 * 2. 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
 * 3. 공기청정기로 들어간 미세먼지는 모두 정화된다.
 * T초 후 방에 남아있는 미세먼지의 양은?
 * [입력]
 * 1) R, C, T (6 ≤ R, C ≤ 50, 1 ≤ T ≤ 1,000).
 * R개 줄) Ar,c (-1 ≤ Ar,c ≤ 1,000). 공기청정기: -1, 나머지 값: 미세먼지의 양).
 * -1은 2번 위아래로 붙어져 있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.
 * [출력]
 * 첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력한다.
 */
public class Main_17144_미세먼지안녕_박봉균 {
    /** 4방 방향벡터 */
    static final int[] dy = { -1, 0, 1, 0 };
    static final int[] dx = { 0, 1, 0, -1 };

    static int R, C, T, matrix[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); // 행크기
        C = Integer.parseInt(st.nextToken()); // 열크기
        T = Integer.parseInt(st.nextToken()); // 공기청정기 가동시간
        matrix = new int[R][C]; // 방 정보 행렬

        // 방 정보 행렬 입력
        boolean flag = false;
        Pair airCleanerY = new Pair();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] < 0) {
                    if (!flag) // 처음 만났다면
                        airCleanerY.first = i;
                    else
                        airCleanerY.second = i;
                    flag = true;
                }
            }
        }

        // T초동안 미세먼지 확산, 공기청정기 가동
        for (int i = 0; i < T; i++) {
            // --- per 1초 ---
            // 1. 미세먼지 확산
            goDust();
            // 2. 공기청정기 작동
            counterClock(airCleanerY.first); // 위
            clock(airCleanerY.second); // 아래
        }

        bw.write(String.valueOf(getDust()) + '\n'); // 방에 남은 미세먼지 출력
        bw.flush();
        bw.close();
        br.close();
    }

    /** 방의 미세먼지 수를 반환하는 함수 */
    static int getDust() {
        int dustSum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] > 0)
                    dustSum += matrix[i][j];
            }
        }
        return dustSum;
    }

    /** 미세먼지 확산 */
    static void goDust() {
        int[][] tmp = new int[R][C];

        // 확산되는 양 = matrix[i][j] / 5.
        // matrix[i][j] -= (matrix[i][j] / 5) * (확산 칸수)
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                // 5이상이면 확산
                if (matrix[y][x] >= 5) {
                    int cnt = 0; // 확산 칸수
                    int amount = matrix[y][x] / 5; // 확산량
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny < 0 || ny >= R || nx < 0 || nx >= C || matrix[ny][nx] < 0)
                            continue;
                        ++cnt;
                        tmp[ny][nx] += amount; // 확산은 나중에 적용
                    }
                    matrix[y][x] -= amount * cnt; // 칸 자체는 갱신
                }
            }
        }

        // 확산 적용
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                matrix[i][j] += tmp[i][j];
    }

    /** 위로 반시계방향 shift(바람) */
    static void counterClock(int sy) {
        // 큐에 넣어놓기
        Queue<Integer> q = new ArrayDeque<>();
        int x = 1;
        int y = sy;
        for (; x < C - 1; x++) {
            q.add(matrix[y][x]);
            matrix[y][x] = 0;
        }
        for (; y > 0; y--) {
            q.add(matrix[y][x]);
            matrix[y][x] = 0;
        }
        for (; x > 0; x--) {
            q.add(matrix[y][x]);
            matrix[y][x] = 0;
        }
        for (; y < sy - 1; y++) {
            q.add(matrix[y][x]);
            matrix[y][x] = 0;
        }
        matrix[y][x] = 0; // 공기청정기로 들어가는 칸(정화)

        // 큐에서 빼서 shift(바람) 적용하기
        x = 2;
        y = sy;
        for (; x < C - 1; x++)
            matrix[y][x] = q.remove();
        for (; y > 0; y--)
            matrix[y][x] = q.remove();
        for (; x > 0; x--)
            matrix[y][x] = q.remove();
        for (; y < sy; y++)
            matrix[y][x] = q.remove();
    }

    /** 아래로 시계방항 */
    static void clock(int sy) {
        // 큐에 넣어놓기
        Queue<Integer> q = new ArrayDeque<>();
        int x = 1;
        int y = sy;
        for (; x < C - 1; x++) {
            q.add(matrix[y][x]);
            matrix[y][x] = 0;
        }
        for (; y < R - 1; y++) {
            q.add(matrix[y][x]);
            matrix[y][x] = 0;
        }
        for (; x > 0; x--) {
            q.add(matrix[y][x]);
            matrix[y][x] = 0;
        }
        for (; y > sy + 1; y--) {
            q.add(matrix[y][x]);
            matrix[y][x] = 0;
        }
        matrix[y][x] = 0; // 공기청정기로 들어가는 칸(정화)

        // 큐에서 빼서 shift(바람) 적용하기
        x = 2;
        y = sy;
        for (; x < C - 1; x++)
            matrix[y][x] = q.remove();
        for (; y < R - 1; y++)
            matrix[y][x] = q.remove();
        for (; x > 0; x--)
            matrix[y][x] = q.remove();
        for (; y > sy; y--)
            matrix[y][x] = q.remove();
    }

    /** 공기청정기 두 칸의 y좌표를 담기 위한 Pair 클래스 */
    static class Pair {
        int first, second;

        Pair() {
        }

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
