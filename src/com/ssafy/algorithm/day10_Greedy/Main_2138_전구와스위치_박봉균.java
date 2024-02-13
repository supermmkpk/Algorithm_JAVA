package com.ssafy.algorithm.day10_Greedy;
import java.io.*;

/**
 * <pre>
 * BJ2138 [G5] : 전구와 스위치
 * </pre>
 * @author 박봉균
 * 아이디어: 첫 전구 키고 끈 경우 각각에 대해 체크(N과 2N은 별 차이 없다), i-1기준 다르면 바꿔줘야 하니까 하나씩 진행하면서 스위치 누를지 결정.
 * 결과: 16684KB, 124ms
 */

/*
 * N개 스위치, N개의 전구가 있다.
 * 각각의 전구: 켜져 있는 상태 or 꺼져 있는 상태. 
 * i(1 < i < N)번 스위치를 누르면 i-1, i, i+1의 세 개의 전구의 상태가 바뀐다. 즉, 꺼져 있는 전구는 켜지고, 켜져 있는 전구는 꺼지게 된다. 
 * 1번: 1, 2번 바뀌고, N번: N-1, N번 바뀐다.
 * N개의 전구들의 현 상태와 만들고자 하는 상태가 주어졌을 때, 그 상태를 만들기 위해 스위치를 최소 몇 번 누르면 되는지 알아내자.
[입력]
1) 자연수 N. (2 ≤ N ≤ 1e5)
2) 전구들의 현재 상태를 나타내는 숫자 N개(공백X) 
3) 만들고자 하는 전구들의 상태를 나타내는 숫자 N개(공백X) (0: 켜져 있는 상태, 1: 꺼져 있는 상태)

[출력]
답 출력. (불가능한 경우: -1)
 */
public class Main_2138_전구와스위치_박봉균 {
	/** 상한: 불가능한 경우 */
	public static final int INF = (int) 1e9;
	
	/** 스위치/전구 개수 N */
	static int N;
	/** 현재상태 첫스위치on: nowOn, 첫스위치off: nowOff */
	static int nowOn[], nowOff[];
	/** 만들고자 하는 상태  todo */
	static int[] todo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//스위치 개수 N 입력 및 동적할당
		N = Integer.parseInt(br.readLine());
		nowOn = new int[N];
		nowOff = new int[N];
		todo = new int[N];
		
		//현재 스위치 상태 입력
		String given = br.readLine();
		for(int i = 0; i < given.length(); i++)  {
			nowOn[i] = given.charAt(i) - '0';
			nowOff[i] = given.charAt(i) - '0';
		}
		//nowOn은 첫 스위치 켜주기
		nowOn[0] ^= 1;
		nowOn[1] ^= 1;
		
		//만들 스위치 상태 todo 입력
		given = br.readLine();
		for (int i = 0; i < given.length(); i++) 
			todo[i] = given.charAt(i) - '0';
		
		int retOn = 1; //첫 스위치 켰을 때 결과
		int retOff = 0; //첫 스위치 껐을 때 결과
		for(int i = 1; i < N; i++) {
			//첫 스위치 켰을 때, 다르면 스위치 켜기
			if(nowOn[i - 1] != todo[i - 1]) { 
				retOn++;
				nowOn[i - 1] ^= 1;
				nowOn[i] ^= 1;
				if(i + 1 < N)
					nowOn[i + 1] ^= 1;
			}

			//첫 스위치 껐을 때, 다르면 스위치 켜기
			if(nowOff[i - 1] != todo[i - 1]) { 
				retOff++;
				nowOff[i - 1] ^= 1;
				nowOff[i] ^= 1;
				if(i + 1 < N)
					nowOff[i + 1] ^= 1;
			}
			
			if(i == N - 1) { //끝까지 갔을때,
				//다르면 실패!
				if(nowOn[i] != todo[i]) 
					retOn = INF;
				if(nowOff[i] != todo[i])
					retOff = INF;
			}
		}
		
		//최소 스위치 누른 횟수 출력 
		if(retOn == INF && retOff == INF) //둘다 INF이면 실패! -> -1
			bw.write("-1\n");
		else
			bw.write(String.valueOf(Math.min(retOn, retOff)) + '\n');
		
		bw.flush();
		bw.close();
		br.close();
	}
}
