import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 *********************************************************************
 *
 *    Computes the PSNR between two images.
 *
 *********************************************************************
 */
public class Psnr {
	String msg;
	
	public static double log10(double x) {
		return Math.log(x) / Math.log(10);
	}

	public void calculate(int _nrows, int _ncols, String _img1, String _img2) {
		int nrows, ncols;
		int img1[][], img2[][];
		double peak, signal, noise, mse;
		
		nrows = _nrows;
		ncols = _ncols;
		img1 = new int[nrows][ncols];
		img2 = new int[nrows][ncols];
		ArrayIO.readByteArray(_img1, img1, nrows, ncols);
		ArrayIO.readByteArray(_img2, img2, nrows, ncols);

		signal = noise = peak = 0;
		for (int i = 0; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				signal += img1[i][j] * img1[i][j];
				noise += (img1[i][j] - img2[i][j]) * (img1[i][j] - img2[i][j]);
				if (peak < img1[i][j])
					peak = img1[i][j];
			}
		}

		mse = noise / (nrows * ncols); // Mean square error

		msg += "MSE: " + mse;
		msg += "\nSNR: " + 10 * log10(signal / noise);
		msg += "\nPSNR(max=255): " + (10 * log10(255 * 255 / mse));
		msg += "\nPSNR(max=" + peak + "): " + 10 * log10((peak * peak) / mse);
	}
	
	public void showPSNR(JFrame f) {
		JOptionPane.showMessageDialog(f, msg, "PSNR­pºâµ²ªG", JOptionPane.INFORMATION_MESSAGE);
	}
}
