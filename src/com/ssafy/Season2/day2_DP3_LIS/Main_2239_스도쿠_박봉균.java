import java.io.*;
import java.util.*;

/*
 * 스도쿠: 9×9 크기의 보드가 있을 때, 각 행과 각 열, 그리고 9개의 3×3 크기의 보드에 1부터 9까지의 숫자가 중복 없이 나타나도록 보드를 채우면 된다.
 하다 만 스도쿠 퍼즐이 주어졌을 때, 마저 끝내시오.
[입력]
9*9 보드. 아직 숫자가 채워지지 않은 칸에는 0이 주어진다. (공백 없음)
[출력]
9개의 줄에 9개의 숫자로 답을 출력한다. 답이 여러 개 있다면 그 중 사전식으로 앞서는 것을 출력한다. 즉, 81자리의 수가 제일 작은 경우를 출력한다.
*/
public class Main {
    static int[][] matrix = new int[10][10]; //스도쿠 행렬
    static boolean flag; //스도쿠 완성 여부

    /** 해당 행,열에 숫자 넣을 수 있는지 체크하는 함수 */
    static boolean check(int row, int col, int num) {
		//행,열 중복 체크
		for(int i = 0; i < 9; i++) 
			if(matrix[i][col] == num || matrix[row][i] == num) 
                return false;

        //3*3 네모 중복 체크
		int startRow = row / 3 * 3;
		int startCol = col - col % 3;
		for(int i = startRow; i < startRow + 3; i++) {
			for(int j = startCol; j < startCol + 3; j++) {
				if(matrix[i][j] == num)
                    return false;
			}
		}

        //중복체크 안 걸렸으면 가능!
		return true;
	}

    /** 재귀적으로 스도쿠를 왼쪽 위부터 순차적으로 채우는 함수 */
    private static void go(int now) {
		//스도쿠 완성! -> flag true, 연쇄 복귀
		if(now == 81) {
			flag = true;
			return;
		}

        //왼쪽 위 부터 -> 방향으로 순차적으로 진행.
		int row = now / 9;
		int col = now % 9;
		
        //채워야 하는 칸이 아니라면 다음
		if(matrix[row][col] != 0)
			go(now + 1);
        //아니면 넣을 수 정하기(1~9) 넣어보기 ==> 완탐+백트래킹
		else {
			for(int i = 1; i <= 9; i++) {
                //가능하다면
				if(check(row, col, i)) {
                    matrix[row][col] = i; //넣기
                    go(now + 1);
                    if(flag) //스도쿠 완성했으면 연쇄복귀!!
                        return;
                    matrix[row][col] = 0; //원상복구
                }
			}
		}
	}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i = 0; i < 9; i++) {
            String given = br.readLine();
            for(int j = 0; j < given.length(); j++) {
                matrix[i][j] = given.charAt(j) - '0';
            }
        }

        go(0); //스도쿠 채우기

        //완성한 스도쿠 출력
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                bw.write(String.valueOf(matrix[i][j]));
            }
            bw.write('\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
