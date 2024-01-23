package personal.week4;

import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1987 : 알파벳
 * </pre>
 * 
 * @author 박봉균
 * @since JDK17
 */

/*
 * 세로 R칸, 가로 C칸의 표 모양 보드가 있다. 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 1행 1열에는 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 말이 최대한 몇 칸을 지날 수 있는지 구하라. (단, 좌측 상단의 칸도 포함.)

[입력]
1) R <공백> C. (1 ≤ R,C ≤ 20)
R개 줄) C개의 대문자 알파벳들이 빈칸 없이 주어진다.

[출력]
말이 지날 수 있는 최대의 칸 수.
 */
public class BJ1987_4D {
	public static final int[] dy = {-1, 0, 1, 0};
	public static final int[] dx = {0, 1, 0, -1};
	
	static int R, C, ret; //전역 변수 선언하면 다른 데에서 선언하는지 주의(체크)!!
	static char board[][] = new char[24][24];
	
	static void go(int sy, int sx, int status, int cnt) {
		ret = Math.max(ret, cnt);
		for(int d = 0; d < 4; d++) {
			int ny = sy + dy[d];
			int nx = sx + dx[d];
			//오버 언더 체크
			if(ny < 0 || ny >= R || nx < 0 || nx >= C) 
				continue;
			
			int nextVal = 1 << (int)(board[ny][nx] - 'A');
			if((status & nextVal) == 0) {
				go(ny, nx, (status | nextVal), cnt + 1);
			}
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		go(0, 0, 1 << (int)(board[0][0] - 'A'), 1);
		System.out.println(ret);
	}
}
