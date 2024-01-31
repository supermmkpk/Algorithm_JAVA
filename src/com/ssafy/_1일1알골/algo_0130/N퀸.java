package com.ssafy._1일1알골.algo_0130;
import java.util.*;
import java.io.*;

public class N퀸 {
	static int N;
	static int visited[];
	static int cnt = 0;

	static void go(int col) {
		if(col == N) {
			cnt++;
			return;
		}
		for(int i = 0; i < N; i++) {
			if(visited[col] != -1)
				continue;
			visited[col] = i;
			go(col + 1);
			visited[i] = -1;
			
		}

	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new int[N];
		go(0);
		System.out.println(cnt);
	}

}
