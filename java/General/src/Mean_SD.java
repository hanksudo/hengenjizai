/*
 * 平均數與標準差
 * Mean and Standard Deviation
 * 2006/12/29
 */
public class Mean_SD {
	public static void main(String[] args) {
		int num[] = {5,6,8,9};
		int sum=0;
		double mean=0,sd=0;
		int i;
		
		for (i=0;i<num.length;i++) {
			sum += num[i];	//總和
		}
		//平均數
		mean = sum / num.length;
		//標準差
		for (i=0;i<num.length;i++) {
			sd+=Math.pow(num[i]-mean,2);
		}
		sd = Math.sqrt(sd/num.length);
		
		//輸出結果
		System.out.println("Sum: "+sum);
		System.out.println("Mean: "+mean);
		System.out.println("Standard Deviation: "+sd);
	}
}
