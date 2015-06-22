import java.util.Random;

public class GA {
	
	/**
	 * Test Function by Genetic Algorithm
	 * 
	 * Step 1. Initial 隨機產生初始解
	 * Step 2. Calculate 計算適應函數值來評估族群
	 * Step 3. Evaluate 評估是否找到最佳解?
	 * 	         是-> 輸出最佳解
	 *           否-> Step 4 
	 * Step 4. Reproduction 複製
	 *         Crossover 交配
	 *         Mutation 突變
	 * Step 5. 產生第t+1代 -> 世代交替回 Step 1.
	 * 
	 * 2008-09-09
	 * By Hank
	 */
	
	int D = 10;	// Dimension 維度
	int population = 30; // Population Size 族群數量
	int iteration = 2000;	// 迭代次數
	
	public double num[][] = new double[population][D];
	public double sum[] = new double[population];
	public double circleRate[] = new double[population];
	
	int min[] = new int[2];	// 亂數取適應函數值的索引
	
	Random rnd = new Random();
	
	public GA() {
//		double total=0;
//		
//		System.err.println("維度: "+D+", 子代數: "+population+", 迭代數: "+iteration+"\n");
//		for(int i=10; i<10000; i*=10) {
//			for(int j=0; j<i; j++) {
//				total+=Go();
//			}
//			System.err.println("跑"+i+"次的平均的結果是: "+total/i);
//		}
//		System.err.println("\n==程式結束==");
		
		double temp, prvTemp=10000;
		for (int i=0; i<1000; i++) {
			temp = Go();
			if (temp<prvTemp) {prvTemp=temp;System.err.println(i+" "+prvTemp);}
			
		}
	}
	
	public double Go() {
		for (int i=0; i<population; i++) {
			Initial(num[i]);					// Step 1: 隨機產生初始解
			sum[i] = CalculateFitness(num[i]);	// Step 2: 計算適應函數值
		}
		
		int itera=0;
		do {
			Selection();						// Step 3: Selection 將最接近最佳解的選出
			Crossover();						// Step 4: CrossOver 交配 (Rate=1)
			Mutation();							// Step 5: Mutation 突變 (Rate=0.01)
			itera++;
		} while (itera<iteration);	// 迭代
		
//		 找出最小值
//		double min=10000;
//		for (int i=0; i<population; i++) {
//			System.err.println("第"+i+"個適應值: "+sum[i]);
//			min = Math.min(sum[i], min);
//		}
		return sum[0];
	}
	
	// Step 1: 隨機產生初始解
	public void Initial(double arr[]) {
		//rnd.setSeed(200);	// 亂數種子
		for (int i=0; i<arr.length; i++) {
//			arr[i] = 200*rnd.nextDouble()-100;	// f3 [-100, 100]
			arr[i] = 10.24*rnd.nextDouble()-5.12;	// f5 [-5.12, 5.12]
		}
	}
	
	// 適應函數
	public double CalculateFitness(double refer[]) {
		double s=0;
		for (int i=0; i<refer.length; i++) {
			s += Math.pow(refer[i], 2);
//			s += Math.pow((refer[i]+0.5), 2);
//			s += (Math.pow(refer[i], 2))-10*Math.cos(2*Math.PI*refer[i])+10;
		}
		return s;
	}

	// 輪盤式選出兩個適應值索引
	public void Selection() {
		double sumOfSum = 0;
		
		// 適應值排序
		for (int i=0; i<population; i++) {
			for (int j=i; j<population; j++) {
				if (sum[i]>sum[j]) {
					swap(sum, i, j);
					swapArr(num, i, j);
				}
			}
			sumOfSum += sum[i];	// 適應值總和
		}
		
		// 計算各適應值機率(數值越小, 機率越大)
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
	
	// 交配
	public void Crossover() {
		double tempNum[][] = new double[2][D];
		double tempSum[] = new double[2];
		double temp=0;	
		
		System.arraycopy(num[min[0]], 0, tempNum[0], 0, D);
		System.arraycopy(num[min[1]], 0, tempNum[1], 0, D);
		
		int r1 = 1+rnd.nextInt(8);	// 亂數取得交配點
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
//				System.err.println("交換: "+sum[min[i]]+" "+tempSum[i]);
				sum[min[i]]=tempSum[i];
				System.arraycopy(tempNum[i], 0, num[min[i]], 0, D);
			}
		}
	}
	
	// 突變
	public void Mutation() {
		if (rnd.nextDouble()<0.01) {
			int r = rnd.nextInt(10);	// 亂數取得突變點
			
			//num[min[0]][r] = 200*rnd.nextDouble()-100;
			for (int i=0; i<2; i++) {
				double r2 = 10.24*rnd.nextDouble()-5.12;
				num[min[i]][r] = Math.min(num[min[i]][r], r2);
			}
			
		}
	}
	
	
	// 變數交換
	public void swap(int x,int y) {
		int t;
		t = x;
		x = y;
		y = t;
	}
	
	// 陣列變數交換
	public void swap(double arr[], int x, int y) {
		double temp;
		temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	// 陣列交換
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
