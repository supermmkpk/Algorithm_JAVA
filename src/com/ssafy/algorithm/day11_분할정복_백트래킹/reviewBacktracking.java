package com.ssafy.algorithm.day11_분할정복_백트래킹;
import java.io.*;
import java.util.*;

/**
 * BJ_9663[G4] : N-Queen
 * 유망성 체크! 유망하지 않으면 가지 않는다!(가지치기)
 */
public class reviewBacktracking {

static int N, visited[], ret;
    
	static void nQueen(int y) {
        if(y < 0 || y >= N) {
            ++ret;
            return;
        }
        
        for(int col = 0; col < N; col++) {
        	boolean flag = false;
            for(int row = 0; row < y; row++) {
                if(visited[row] == col) {
                    flag = true;
                    break;
                }
                if(Math.abs(col - visited[row]) == Math.abs(row - y)) {
                    flag = true;
                    break;
                }
            }
            if(flag)
                continue;
            
            visited[y] = col;
            nQueen(y + 1);
        }
    }
	
		

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		visited = new int[N];
		
		nQueen(0);
		
		bw.write(String.valueOf(ret) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}

}
