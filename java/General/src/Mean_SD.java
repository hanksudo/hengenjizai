/*
 * �����ƻP�зǮt
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
			sum += num[i];	//�`�M
		}
		//������
		mean = sum / num.length;
		//�зǮt
		for (i=0;i<num.length;i++) {
			sd+=Math.pow(num[i]-mean,2);
		}
		sd = Math.sqrt(sd/num.length);
		
		//��X���G
		System.out.println("Sum: "+sum);
		System.out.println("Mean: "+mean);
		System.out.println("Standard Deviation: "+sd);
	}
}
