import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @comment: BTC壓縮
 * @author Han-Hong Wang
 * @date 2007/5/2
 */
public class BTC {
	static String file_path = "C:\\LENA.raw";
	static byte imgBuffer[] = new byte[512 * 512]; // 將圖片讀進緩衝區
	static int sum[] = new int[128*128];
	static int avg[] = new int[128*128];
	static int vector[] = new int[512*512]; // 儲存極值
	static int vec1Sum[] = new int[128*128];
	static int vec0Sum[] = new int[128*128];
	static int last1avg[] = new int[128*128];
	static int last0avg[] = new int[128*128];
	static FileWriter fw;
	
	public static void main(String[] args) throws IOException {
		int i,j,k,l;
		
		try {
			FileInputStream reader = new FileInputStream(file_path);
				reader.read(imgBuffer);
		} catch (FileNotFoundException e) {
			System.out.println("找不到檔案");
		} catch (Exception e) {
			System.out.println("寫入緩衝區失敗");
		}
		
		for (i=0;i<128;i++) {
			for (j=0;j<128;j++) {
				for (k=0;k<4;k++) {
					for (l=0;l<4;l++) {
						sum[j+128*i]+=imgBuffer[i*512*4+k*512+j*4+l];
					}
				}
				avg[j+128*i] = sum[j+128*i]/16;
			}
		}
		
		int m=0,n=0;
		for (i=0;i<128;i++) {
			for (j=0;j<128;j++) {
				m=0;n=0;				
				for (k=0;k<4;k++) {
					for (l=0;l<4;l++) {
						if (imgBuffer[i*512*4+k*512+j*4+l]>(int)avg[j+128*i]) {
							vector[i*512*4+k*512+j*4+l]=1;
							vec1Sum[j+128*i]+=imgBuffer[i*512*4+k*512+j*4+l];
							m++;
						} else {
							vector[i*512*4+k*512+j*4+l]=0;
							vec0Sum[j+128*i]+=imgBuffer[i*512*4+k*512+j*4+l];
							n++;
						}
					}
				}
				if (m!=0) {last1avg[j+128*i] = vec1Sum[j+128*i]/m;}
				if (n!=0) {last0avg[j+128*i] = vec0Sum[j+128*i]/n;}
			}
		}
		
		for (i=0;i<128;i++) {
			for (j=0;j<128;j++) {
				for (k=0;k<4;k++) {
					for (l=0;l<4;l++) {
						if (vector[i*512*4+k*512+j*4+l]==1) {
							imgBuffer[i*512*4+k*512+j*4+l]=(byte)last1avg[j+128*i];
						} else {
							imgBuffer[i*512*4+k*512+j*4+l]=(byte)last0avg[j+128*i];
						}
					}
				}
			}
		}
		String out="";
		int mm=0;
		try {
			fw = new FileWriter("code");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (i=0;i<128;i++) {
			for (j=0;j<128;j++) {
				out = String.valueOf(last1avg[j+128*i])+String.valueOf(last0avg[j+128*i]);
				for (k=0;k<4;k++) {
					for (l=0;l<4;l++) {
						out=out+""+String.valueOf(vector[i*512*4+k*512+j*4+l]);
					}
				}
				fw.write(out);
				out="";
			}
		}
		
		fw.close();
		
		try {
			FileOutputStream writer = new FileOutputStream(file_path+"-rec.raw");
			writer.write(imgBuffer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
