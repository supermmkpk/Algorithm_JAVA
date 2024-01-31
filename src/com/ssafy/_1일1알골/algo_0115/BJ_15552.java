package com.ssafy._1일1알골.algo_0115;

import java.io.*;
import java.util.*;

/**
 * <pre>
 * BJ15552_����A+B
 * </pre>
 * 
 * @author �ں���
 * @since JDK17
 *
 * �� ���� A�� B�� �Է¹��� ����, A+B�� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 * 
[�Է�]
1) �׽�Ʈ���̽� �� T (T <= 1e6)
T�� ��) �� ���� A, B (1<= A,B <= 1000)

[���]
�� �׽�Ʈ���̽����� A+B�� �� �ٿ� �ϳ��� ������� ���
 */
public class BJ_15552 {

	public static void main(String[] args) throws IOException {
		
		//���� ������� ���� BufferedReader �� BufferedWriter�� �����մϴ�.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()); //ù �ٿ��� �׽�Ʈ���̽��� ���� �Է� �޽��ϴ�.
		for(int t = 1; t <= T; t++) { //T���� �׽�Ʈ���̽��� ���Ͽ�
			//a, b�� �Է� �޽��ϴ�.
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			bw.write(String.valueOf(a + b)); //a+b ���.
			bw.newLine(); //����
		}
		bw.flush(); //���� ������ ���
		bw.close(); //stream�� �ݽ��ϴ�.
		
	}

}
