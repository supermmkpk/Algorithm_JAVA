package com.ssafy._1일1알골.algo_0219;
import java.util.*;
import java.io.*;

/*
 * 김진영이 듣도 못한 사람의 명단과, 보도 못한 사람의 명단이 주어질 때, 듣도 보도 못한 사람의 명단을 구하는 프로그램을 작성하시오.
[입력]
1) 듣도 못한 사람의 수 N, 보도 못한 사람의 수 M (1 <= N,M <= 5e5)
N개 줄) 듣도 못한 사람의 이름
M개 줄) 보도 못한 사람의 이름 (띄어쓰기 없이 알파벳 소문자로만 이루어지며, 길이<=20, 중복X)
[출력]
듣보잡의 수, 그 명단을 사전순으로 출력.
 */
//카운팅은 맵 또는 배열 -> String -> 맵
public class Main_1764_듣보잡 {
	static int N, M;
	static Map<String, Boolean> map = new HashMap<>();
	static ArrayList<String> retList = new ArrayList<>();
	static int ret;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) 
			map.put(br.readLine(), true);
		
		for(int i = 0; i < M; i++) {
			String given = br.readLine(); 
			if(map.containsKey(given)) {
				retList.add(given);
				++ret;
			}
		}
		Collections.sort(retList);
		bw.write(String.valueOf(ret) + '\n');
		for(String s : retList) 
			bw.write(s + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}
