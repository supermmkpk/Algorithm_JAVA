package personal.week8_펜윅트리_최단거리;
import java.util.*;
import java.io.*;

/**
 * <pre>
 * BJ17258 [P5] : 인기가 넘쳐흘러
 * </pre>
 * @author 박봉균
 * @since JDK1.8
 * 
 * 아이디어: 구간을 {구간 길이, 사람 수}로 저장 + dp(필요한 인원 구하고 투입하거나 안 하거나)
 * 결과: 12444KB, 88ms
 */

/*
 *  영선이는 초대장을 돌렸는데, 받은 사람들이 언제 와서 언제 떠날 것인지 답변을 줬다.
 *  욱제는 자신을 제외한 파티 인원이 T명 미만이 되는 순간 파티장에서 나가고, T명 이상이 되는 순간 다시 돌아온다. 
 *  답변을 정리하던 영선이는 파티가 진행되는 동안 T명 이상의 인원이 계속 유지되지 못 한다는 것을 알게 되었다. 
 *  그래서 영선이는 급히 자신의 친구들 K명을 부르려고 한다.
 *  영선이의 친구들은 욱제 및 영선이의 친구들을 제외한 파티 인원이 T명 이상이 되는 순간 다 같이 파티장에서 나가 버리고 돌아오지 않는다.
 *   또한 파티 인원이 T명 이상이면 영선이의 친구들은 파티장에 들어가지 않는다. 단, 아직 들어오지 않은 영선이의 친구들은 이후 파티 인원이 T명 미만이 되면 파티장으로 들어 갈 수 있다. 
 *   영선이는 친구들 각각을 적절한 시각에 투입시켜 최대한 오랫동안 욱제가 파티에 머물도록 하고 싶다. 꼭 K명을 모두 투입시킬 필요는 없다. 
 *   영선이는 욱제를 얼마나 오래 파티에 머물게 할 수 있을까?
[입력]
1) 파티가 진행되는 시간 N, 파티에 초대한 사람 수 M, 영선이의 친구 수 K, 기대하는 최소의 파티 인원 T (1 ≤ N ≤ 300, 1 ≤ M ≤ 300, 1 ≤ K ≤ 300, 1 ≤ T ≤ M).
M개 줄) ai, bi가 주어진다. i번째 사람은 ai에 파티에 참여하고, bi에 파티를 떠난다. (1 ≤ ai ≤ N, ai < bi ≤ N + 1)
파티가 시작되는 시각은 1을 기준으로 한다.

[출력]
영선이의 친구들을 파티에 투입시켰을 때 욱제가 파티에 머무를 수 있는 최대 시간을 출력한다.
 */
public class Main_17258_인기가넘쳐흘러_박봉균 {
	static int N, M, K, T;
	
	static int[] nPerTime = new int[304];
	static int[][] dp = new int[304][304]; //상태값: 현재시간, 남은 영선친구수
	static ArrayList<int[]> list = new ArrayList<>();
	
	static int go(int now, int remain, int prev) {
		//기저사례
		if(now == list.size())
			return 0;
		
		//메모이제이션
		if(dp[now][remain] != 0)
			return dp[now][remain];
		
		//로직
		int need = Math.max(0, T - list.get(now)[1]);
		int real_need = (prev >= need) ? 0 : need - prev;
		
		if(remain >= real_need) //가능하면: 투입하거나 안 하거나
			return dp[now][remain] = Math.max(go(now + 1, remain - real_need, need) + list.get(now)[0], go(now + 1, remain, 0));
		else
			return dp[now][remain] = go(now + 1, remain, 0);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for(int j = a; j < b; j++)
				nPerTime[j] = Math.min(T, ++nPerTime[j]);
		}
		
		int prev = nPerTime[1];
		int cnt = 1;
		for(int i = 2; i <= N; i++) {
			if(nPerTime[i] != prev) {
				list.add(new int[]{cnt, prev}); //구간 길이, 사람수로 표현
				cnt = 0;
				prev = nPerTime[i];
			}
			cnt++;
		}
		list.add(new int[]{cnt, prev});
		
		bw.write(String.valueOf(go(0, K, 0)) + '\n');
		bw.flush();
		bw.close();
		br.close();
	}
}

