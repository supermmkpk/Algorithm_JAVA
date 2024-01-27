package com.ssafy.Main;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static LinkedList<Integer> result = new LinkedList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static void go() throws IOException {
		//종료조건
		if(result.size() == M) {
			for(int i : result) {
				bw.write(String.valueOf(i) + " ");
			}
			bw.write('\n');
				return;
			}
			for(int i = 1; i <= N; i++) {
				result.addLast(i);
				go();
				result.removeLast();
			}
			return;
	}
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
        
		go();
        bw.flush();
        bw.close();
        br.close();
	}
}
