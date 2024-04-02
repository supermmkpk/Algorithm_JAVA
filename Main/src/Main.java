import java.io.*;
import java.util.*;

//N개의 원소를 입력 받아 가능한 모든 부분집합 생성
//1 <= N <= 10
public class Main {
	/** 회의 개수 N */
	static int N;
	/** 회의 정보 저장 PQ */
	static PriorityQueue<Meeting> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//회의 개수 N 입력
		N = Integer.parseInt(br.readLine());
		
		//회의 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.add(new Meeting(start, end));
		}
		
		//종료 시간 기준 PQ에서 이전 종료 이후의 front 선택 
		int cnt = 1;
		int prev = pq.remove().end; //이전 종료시간
		while(!pq.isEmpty()) {
			if(prev > pq.peek().start) //이전 종료 시간 이전: remove
				pq.remove();
			else { //이전 종료 시간 이후: 카운트 증가
				prev = pq.remove().end;
				cnt++;
			}
		}
		
		//가능한 회의 최대 개수 출력
		bw.write(String.valueOf(cnt) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
	
	static class Meeting implements Comparable<Meeting> {
		int start; 
		int end;
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		/** 정렬 규칙: 종료시간 기준 오름차순, 같을 경우 시작시간 기준 오름차순 */
		@Override
		public int compareTo(Meeting o) {
			if(this.end == o.end)
				return	Integer.compare(this.start, o.start);
			else
				return Integer.compare(this.end, o.end);
		}
	}
}