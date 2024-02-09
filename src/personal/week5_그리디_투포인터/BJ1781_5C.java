package personal.week5_그리디_투포인터;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ1781 [G2] : 컵라면
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * @see <a href="https://www.acmicpc.net/source/72718683"></a>
 * 
 * 아이디어: - {데드라인, 컵라면} pair 리스트에 저장하고, 정렬(데드라인 기준 오름차순)
 *          - priority_queue의 size와 데드라인 비교해서 최소 컵라면 수를 빼 버리기
 *          ** 최대: 최소가 적다. or 최대가 많다.
 * 결과: 76180KB, 796ms
 */

/*
 * 동호가 받을 수 있는 최대 컵라면 수를 구하라.
 * 문제를 푸는데는 단위 시간 1이 걸리며, 각 문제의 데드라인은 N이하의 자연수이다.
[입력]
1) 숙제의 개수 N. (1 ≤ N ≤ 200,000)이 들어온다.
N개 줄) 문제에 대한 데드라인 <공백> 받을 수 있는 컵라면 수 (1<= 컵라면수 <= 230)

[출력]
받을 수 있는 최대 컵라면 수
*/
public class BJ1781_5C {
	/** {데드라인, 컵라면} pair 리스트 */
	static ArrayList<Pair> list = new ArrayList<>();
	/** 최대 경우를 저장할 PriorityQueue */
	static PriorityQueue<Integer> pq = new PriorityQueue<>(); //Java는 기본: 오름차순
	/** 결과: 최대 컵라면 수 */
	static int ret = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 컵라면 개수 N 입력
		int N = Integer.parseInt(br.readLine());
		
		//<데드라인, 컵라면> 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadLine = Integer.parseInt(st.nextToken());
			int cup = Integer.parseInt(st.nextToken());
			list.add(new Pair(deadLine, cup));
		}
		
		//데드라인 기준 오름차순 정렬
		Collections.sort(list);
		
		//PriorityQueue의 size와 데드라인 비교, 갱신(size보다 데드라인이 더 크면 최소 빼 버리기)
		ret = 0;
		for(Pair pair : list) {
			ret += pair.second;
			pq.add(pair.second);
			if(pq.size() > pair.first) {
				ret -= pq.peek();
				pq.remove();
			}
		}
		bw.write(String.valueOf(ret) + '\n'); //출력
		
		//남은 데이터 출력, stream을 닫습니다.
		bw.flush();
		bw.close();
		br.close();
	}
}

/**
 * Pair 클래스
 */
class Pair implements Comparable<Pair> {
	int first;
	int second;
	
	Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public int compareTo(Pair p) {
		if(this.first == p.first) 
			return Integer.compare(this.second, p.second);
		else
			return Integer.compare(this.first, p.first);
	}
}
