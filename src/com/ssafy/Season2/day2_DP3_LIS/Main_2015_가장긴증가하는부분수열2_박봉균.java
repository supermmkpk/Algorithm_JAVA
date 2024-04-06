import java.io.*;
import java.util.*;

/**
 * @author 박봉균
 * @since JDK1.8
 * 결과: 149660KB, 612ms
*/
/* <BJ12015_G2 : 가장 긴 증가하는 부분 수열 2>
 * <시간제한: 1초>
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하자.
[입력] 
1) 수열 크기 N. (1 ≤ N ≤ 1e6)
2) 수열을 이루는 Ai. (1 ≤ Ai ≤ 1e6)
[출력]
LIS 길이.
 */
public class Main_2015_가장긴증가하는부분수열2_박봉균 {
	
	static int N, A[], ret;
	static ArrayList<Integer> lis = new ArrayList<Integer>();
	
	static int binSearch(int l, int r, int target) {
		
		while(l < r) {
			int mid = (l + r) / 2;
			
			if(lis.get(mid) < target) 
				l = mid + 1;
			else
				r = mid;
		}
		return r;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 
			A[i] = Integer.parseInt(st.nextToken());
		
		//초기화
		lis.add(A[0]);
		
		for(int i = 1; i < N; i++) {
			//증가하면 그냥 추가
			if(getLast() < A[i]) 
				lis.add(A[i]);
			//작아지면 삽입(수정) 위치 찾기
			else {
				int idx = binSearch(0, lis.size()-1, A[i]);
				lis.set(idx, A[i]);
			}
		}
		
		bw.write(String.valueOf(lis.size()) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int getLast() {
		return lis.get(lis.size() - 1);
	}
}
