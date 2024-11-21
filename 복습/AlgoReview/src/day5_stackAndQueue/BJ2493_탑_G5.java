package day5_stackAndQueue;

import java.io.*;
import java.util.*;

/* <시간제한: 1초>
 * 일직선 위에, 높이가 서로 다른 N개의 탑을 왼쪽->오른쪽 방향으로 차례로 세우고, 각 탑의 꼭대기에 레이저 송신기를 설치하였다. 
 * 레이저 송신기: 레이저 신호를 지표면과 평행하게 수평 직선의 "왼쪽" 방향으로 발사. 
 * 하나의 탑에서 발사된 레이저 신호는 "가장 먼저 만나는 단 하나"의 탑에서만 수신 가능. 
 * 탑들의 개수 N과 탑들의 높이가 주어질 때, 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내라. 
[입력]
1) 탑의 수 N. (1 <= N <= 500,000)
2) N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 주어진다. (1 <= 높이 <= 1e8)
[출력]
1) 주어진 탑들의 순서대로 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호. (수신하는 탑 X : 0)
 */
class BJ2493_탑_G5 {
	
	//왼쪽으로 발사!!(단방향)
	//걸리면 거기가 수신한 것 -> 끝!
	//나보다 작은 거 -> 필요 없음(내가 있으니까) -> pop
	//나 -> push
	//나보다 큰거 -> 여기서 수신(걸릴거임) -> 출력 -> 스택에 그대로(break)
	
	/** 탑 개수 */
	static int N;
	
	static Stack<Pair> stk = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			int nowHeight = Integer.parseInt(st.nextToken());
			
			while(!stk.isEmpty()) {
				//나보다 작은 거 -> 필요 없음(내가 있으니까) -> pop
				if(stk.peek().height < nowHeight) {
					stk.pop();
				}
				//나보다 큰거 -> 여기서 수신(걸릴거임) -> 출력 -> 스택에 그대로(break)
				else if(stk.peek().height >= nowHeight) {
					bw.write(stk.peek().num + " ");
					break;
				}
			}
			if(stk.isEmpty()) {
				bw.write("0 ");
			}
			
			//나 -> push
			stk.push(new Pair(i, nowHeight));
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	static class Pair {
		int num, height;
		
		Pair(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}
}
