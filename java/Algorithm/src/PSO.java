import java.util.Random;


public class PSO {
	Random rnd = new Random();
	int D = 10;	// Default Dimension
	int particleSize = 30; // �ɤl�ƥ�
	double w = 0.4; // �v����
	int c1 = 2, c2 = 2;	// �ǲ߱`��
	double Particles[][] = new double[particleSize][D];
	double pBest[] = new double[particleSize];
	double gBest = 99999999;
	
	
	// �H�����ͪ�l��
	public void Initial(double arr[]) {
		//rnd.setSeed(200);	// �üƺؤl
		
		for (int i=0; i<arr.length; i++) {
//			arr[i] = 200*rnd.nextDouble()-100;	// f3 [-100, 100]
			arr[i] = 10.24*rnd.nextDouble()-5.12;	// f5 [-5.12, 5.12]
		}
	}
	
	// �p��A�����
	public double FitnessFunction(double refer[]) {
		double s=0;
		for (int i=0; i<refer.length; i++) {
			s += Math.pow((refer[i]+0.5), 2);
//			s += (Math.pow(refer[i], 2))-10*Math.cos(2*Math.PI*refer[i])+10;
		}
		return s;
	}
	
	public double Go() {
		for (int i=0; i<particleSize; i++) {
			Initial(Particles[i]);						// Step 1: �H�����ͪ�l��
			pBest[i] = FitnessFunction(Particles[i]);	// Step 2: �p��@�}�l���A����ƭ�
		}
		return 0;
	}
	
	public void FINDgBest() {
		for (int i=0; i<particleSize; i++) {
			
		}
	}
	
	public PSO() {
		
	}
	
	public static void main(String[] args) {

	}

}
