package personal.week3_완탐_백트래킹;

import java.io.*;
import java.util.*;


public class BJ17071_3I {
	
	public static void main(String[] args) throws IOException {
		final int MAX = 500000;
		int[][] visited = new int[2][MAX + 4];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N == K) {
			bw.write("0\n");
			return;
		}
		
		Queue<Integer> q = new LinkedList<>();
		visited[0][N] = 1;
		q.add(N);
		
		int sec = 1;
		int sister = K;
		boolean ok = false;
		while(!q.isEmpty()) {
			sister += sec;
			
			if(sister > MAX) {
				break;
			}
			//홀짝 맞고, 이미 수빈이가 방문한 곳이면 ok!
			if(visited[sec % 2][sister] != 0) {
				ok = true;
				break;
			}
			
			int qSize = q.size();
			for(int i = 0; i < qSize; i++) {
				int x = q.peek();
				q.remove();
				
				int[] dir = {x + 1, x - 1, x * 2};
				for(int nx : dir) {
					if(nx < 0 || nx > MAX || visited[sec % 2][nx] != 0)
						continue;
					visited[sec % 2][nx] = visited[(sec + 1) % 2][x] + 1;
					if(nx == sister) {
						ok = true;
						break;
					}
					q.add(nx);
				}
				if(ok) 
					break;
			}
			if(ok) 
				break;
			
			sec++;
		}
		
		if(ok) {
			bw.write(String.valueOf(sec) + "\n");
		}
		else {
			bw.write("-1\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

}
