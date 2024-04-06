import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 결과: 12672KB, 164ms
 */

/*
 * <BJ3055_G4 : 탈출>
 * <시간제한: 1초>
 * 고슴도치가 있다. 친구인 비버의 굴로 가능한 빨리 가려 한다.
 * 지도: R * C 행렬. ('.': 빈칸, '*': 물이 차있는 지역 ,'X': 돌, 'D': 비버굴, 'S': 고슴도치.
 * 고슴도치: 1분당 상하좌우 한칸. -> 가중치 동일 ==> BFS!
 * 물: 1분당 비어있는 칸으로 확장. 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다.
 * 물, 고슴도치: 돌 X, 고슴도치: 물 X, 물: 비버굴 X
 * 비버 굴로 이동하는 최소 시간을 구하시오.
 * (단, 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다)
 * [입력]
 * 1) R, C. (1 <= R,C <= 50)
 * R개 줄) 지도 행렬. ('D', 'S'는 하나씩만)
 * [출력]
 * 고슴도치가 비버의 굴로 이동하는 최소 시간. (비버굴 이동 불가: "KAKTUS")
 */
public class Main_3055_탈출 {
    /** 4방 방향 벡터 */
    static final int[] dy = { -1, 0, 1, 0 };
    static final int[] dx = { 0, 1, 0, -1 };

    static int R, C, visited[][];
    static char[][] matrix;
    static List<Pair> waters = new ArrayList<>(); //물 좌표 리스트

    /** 모든 물 좌표에 대하여 1단계 확장해주는 함수 */
    static void water() {
        //모든 물 칸 좌표에 대하여
        int curLen = waters.size();
        for(int i = 0; i < curLen; i++) {
            int y = waters.get(i).first;
            int x = waters.get(i).second;
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (ny < 0 || ny >= R || nx < 0 || nx >= C) //오버 언더 체크
                    continue;

                //확장!
                if(matrix[ny][nx] == '.') {
                    matrix[ny][nx] = '*';
                    waters.add(new Pair(ny, nx));
                }
            }
        }
    }

    /** 물 확장하며 BFS 탐색하는 함수 */
    static int bfs(int sy, int sx) {
        visited[sy][sx] = 1;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(sy, sx));

        while (!q.isEmpty()) {
            //물 확장
            water();
            
            //레벨 단위 탐색: 다음 레벨 가능하면 q에 넣고 다음 while loop에서 물 확장
            for(int i = 0; i < q.size(); i++) {
                int y = q.peek().first;
                int x = q.remove().second;
                if (matrix[y][x] == 'D')
                    return visited[y][x] - 1;

                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (ny < 0 || ny >= R || nx < 0 || nx >= C) //오버 언더 체크
                        continue;

                    if (visited[ny][nx] == 0) {
                        //빈칸이거나 도착지점이라면 이동
                        if (matrix[ny][nx] == '.' || matrix[ny][nx] == 'D') {
                            visited[ny][nx] = visited[y][x] + 1;
                            q.add(new Pair(ny, nx));
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken()); //행크기
        C = Integer.parseInt(st.nextToken()); //열크기
        matrix = new char[R][C]; //지도 행렬
        visited = new int[R][C]; //방문체크+최소시간

        //지도 행렬 입력
        Pair start = new Pair();
        for (int i = 0; i < R; i++) {
            String given = br.readLine();
            for (int j = 0; j < C; j++) {
                matrix[i][j] = given.charAt(j);
                if (matrix[i][j] == 'S')
                    start = new Pair(i, j);
                else if(matrix[i][j] == '*')
                    waters.add(new Pair(i, j));
            }
        }

        //BFS탐색, 최소시간 출력
        int ret = bfs(start.first, start.second);
        bw.write((ret == -1 ? "KAKTUS" : String.valueOf(ret)) + '\n');
        bw.flush();
        bw.close();
        br.close();
    }

    /** 좌표 저장 위한 Pair 클래스 */
    static class Pair {
        int first, second;
        Pair() {}
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
