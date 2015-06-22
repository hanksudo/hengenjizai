import java.util.Random;

public class GA {
	
	/**
	 * Test Function by Genetic Algorithm
	 * 
	 * Step 1. Initial �H�����ͪ�l��
	 * Step 2. Calculate �p��A����ƭȨӵ����ڸs
	 * Step 3. Evaluate �����O�_���̨θ�?
	 * 	         �O-> ��X�̨θ�
	 *           �_-> Step 4 
	 * Step 4. Reproduction �ƻs
	 *         Crossover ��t
	 *         Mutation ����
	 * Step 5. ���Ͳ�t+1�N -> �@�N����^ Step 1.
	 * 
	 * 2008-09-09
	 * By Hank
	 */
	
	int D = 10;	// Dimension ����
	int population = 30; // Population Size �ڸs�ƶq
	int iteration = 2000;	// ���N����
	
	public double num[][] = new double[population][D];
	public double sum[] = new double[population];
	public double circleRate[] = new double[population];
	
	int min[] = new int[2];	// �üƨ��A����ƭȪ�����
	
	Random rnd = new Random();
	
	public GA() {
//		double total=0;
//		
//		System.err.println("����: "+D+", �l�N��: "+population+", ���N��: "+iteration+"\n");
//		for(int i=10; i<10000; i*=10) {
//			for(int j=0; j<i; j++) {
//				total+=Go();
//			}
//			System.err.println("�]"+i+"�������������G�O: "+total/i);
//		}
//		System.err.println("\n==�{������==");
		
		double temp, prvTemp=10000;
		for (int i=0; i<1000; i++) {
			temp = Go();
			if (temp<prvTemp) {prvTemp=temp;System.err.println(i+" "+prvTemp);}
			
		}
	}
	
	public double Go() {
		for (int i=0; i<population; i++) {
			Initial(num[i]);					// Step 1: �H�����ͪ�l��
			sum[i] = CalculateFitness(num[i]);	// Step 2: �p��A����ƭ�
		}
		
		int itera=0;
		do {
			Selection();						// Step 3: Selection �N�̱���̨θѪ���X
			Crossover();						// Step 4: CrossOver ��t (Rate=1)
			Mutation();							// Step 5: Mutation ���� (Rate=0.01)
			itera++;
		} while (itera<iteration);	// ���N
		
//		 ��X�̤p��
//		double min=10000;
//		for (int i=0; i<population; i++) {
//			System.err.println("��"+i+"�ӾA����: "+sum[i]);
//			min = Math.min(sum[i], min);
//		}
		return sum[0];
	}
	
	// Step 1: �H�����ͪ�l��
	public void Initial(double arr[]) {
		//rnd.setSeed(200);	// �üƺؤl
		for (int i=0; i<arr.length; i++) {
//			arr[i] = 200*rnd.nextDouble()-100;	// f3 [-100, 100]
			arr[i] = 10.24*rnd.nextDouble()-5.12;	// f5 [-5.12, 5.12]
		}
	}
	
	// �A�����
	public double CalculateFitness(double refer[]) {
		double s=0;
		for (int i=0; i<refer.length; i++) {
			s += Math.pow(refer[i], 2);
//			s += Math.pow((refer[i]+0.5), 2);
//			s += (Math.pow(refer[i], 2))-10*Math.cos(2*Math.PI*refer[i])+10;
		}
		return s;
	}

	// ���L����X��ӾA���ȯ���
	public void Selection() {
		double sumOfSum = 0;
		
		// �A���ȱƧ�
		for (int i=0; i<population; i++) {
			for (int j=i; j<population; j++) {
				if (sum[i]>sum[j]) {
					swap(sum, i, j);
					swapArr(num, i, j);
				}
			}
			sumOfSum += sum[i];	// �A�����`�M
		}
		
		// �p��U�A���Ⱦ��v(�ƭȶV�p, ���v�V�j)
		for (int i=0; i<population; i++) {
			circleRate[i] = sum[population-i-1]/sumOfSum;
		}
		
		for (int i=0; i<2; i++) {
			double r = rnd.nextDouble();
			for (int j=0; j<population; j++) {
				r -= circleRate[j];
				if (r<0 || j==population-1) {min[i]=j;break;}
			}
		}
	}
	
	// ��t
	public void Crossover() {
		double tempNum[][] = new double[2][D];
		double tempSum[] = new double[2];
		double temp=0;	
		
		System.arraycopy(num[min[0]], 0, tempNum[0], 0, D);
		System.arraycopy(num[min[1]], 0, tempNum[1], 0, D);
		
		int r1 = 1+rnd.nextInt(8);	// �üƨ��o��t�I
		int r2 = 1+rnd.nextInt(8);
		
		if(r1>r2) {swap(r1,r2);}
		for (int i=r1; i<r2; i++) {
			temp = tempNum[0][i];
			tempNum[0][i] = tempNum[1][i];
			tempNum[1][i] = temp;
		}
		
		tempSum[0] = CalculateFitness(tempNum[0]);
		tempSum[1] = CalculateFitness(tempNum[1]);
		
		for (int i=0; i<2; i++) {
			if (tempSum[i]<sum[min[i]]) {
//				System.err.println("�洫: "+sum[min[i]]+" "+tempSum[i]);
				sum[min[i]]=tempSum[i];
				System.arraycopy(tempNum[i], 0, num[min[i]], 0, D);
			}
		}
	}
	
	// ����
	public void Mutation() {
		if (rnd.nextDouble()<0.01) {
			int r = rnd.nextInt(10);	// �üƨ��o�����I
			
			//num[min[0]][r] = 200*rnd.nextDouble()-100;
			for (int i=0; i<2; i++) {
				double r2 = 10.24*rnd.nextDouble()-5.12;
				num[min[i]][r] = Math.min(num[min[i]][r], r2);
			}
			
		}
	}
	
	
	// �ܼƥ洫
	public void swap(int x,int y) {
		int t;
		t = x;
		x = y;
		y = t;
	}
	
	// �}�C�ܼƥ洫
	public void swap(double arr[], int x, int y) {
		double temp;
		temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	// �}�C�洫
	public void swapArr(double arr[][], int x, int y) {
		double tmpArr[] = new double[arr[x].length];
		System.arraycopy(arr[x], 0, tmpArr, 0, arr[x].length);
		System.arraycopy(arr[y], 0, arr[x], 0, arr[y].length);
		System.arraycopy(tmpArr, 0, arr[y], 0, tmpArr.length);
	}
	
	public static void main(String[] args) {
		new GA();
	}

}
