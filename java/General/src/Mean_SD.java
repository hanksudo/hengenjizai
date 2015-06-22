/*
 * キА计籔夹非畉
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
			sum += num[i];	//羆㎝
		}
		//キА计
		mean = sum / num.length;
		//夹非畉
		for (i=0;i<num.length;i++) {
			sd+=Math.pow(num[i]-mean,2);
		}
		sd = Math.sqrt(sd/num.length);
		
		//块挡狦
		System.out.println("Sum: "+sum);
		System.out.println("Mean: "+mean);
		System.out.println("Standard Deviation: "+sd);
	}
}
