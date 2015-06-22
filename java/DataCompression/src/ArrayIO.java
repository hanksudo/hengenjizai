/*
 ***************************************************************************
 *
 *   Read and write arrays of various data types.
 *
 ***************************************************************************
 */
import java.io.*;

public class ArrayIO {

  public static void readByteArray(String fname, int array[][], int nrows, int ncols) {
    try {
      File file = new File(fname);
      FileInputStream fin= new FileInputStream(file);
       for (int i=0; i<nrows; i++)
         for (int j=0; j<ncols; j++)
           array[i][j] = (int)(0xFF & fin.read()); // Unsigned char to integer
      fin.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

  public static void writeByteArray(String fname, int array[][],
		  int nrows, int ncols) {
    try {
      File file = new File(fname);
      FileOutputStream fout = new FileOutputStream(file);
      for (int i=0; i<nrows; i++)
        for (int j=0; j<ncols; j++)
          fout.write((byte)(0xFF & array[i][j])); // Integer to unsigned char
      fout.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

  public static void readIntArray(String fname, int array[][],
		  int nrows, int ncols) {
    try {
      File file = new File(fname);
      DataInputStream fin = new DataInputStream(new FileInputStream(file));
      for (int i=0; i<nrows; i++)
        for (int j=0; j<ncols; j++)
          array[i][j] = fin.readInt();
      fin.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

  public static void writeIntArray(String fname, int array[][],
		  int nrows, int ncols) {
    try {
      File file = new File(fname);
      DataOutputStream fout = new DataOutputStream(new FileOutputStream(file));
      for (int i=0; i<nrows; i++)
        for (int j=0; j<ncols; j++)
          fout.writeInt(array[i][j]);
      fout.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

  public static void readFloatArray(String fname, float array[][],
		  int nrows, int ncols) {
    try {
      File file = new File(fname);
      DataInputStream fin = new DataInputStream(new FileInputStream(file));
      for (int i=0; i<nrows; i++)
        for (int j=0; j<ncols; j++)
          array[i][j] = fin.readFloat();
      fin.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

  public static void writeFloatArray(String fname, float array[][],
		  int nrows, int ncols) {
    try {
      File file = new File(fname);
      DataOutputStream fout = new DataOutputStream(new FileOutputStream(file));
      for (int i=0; i<nrows; i++)
        for (int j=0; j<ncols; j++)
          fout.writeFloat(array[i][j]);
      fout.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

  public static void readDoubleArray(String fname, double array[][],
		  int nrows, int ncols) {
    try {
      File file = new File(fname);
      DataInputStream fin = new DataInputStream(new FileInputStream(file));
      for (int i=0; i<nrows; i++)
        for (int j=0; j<ncols; j++)
          array[i][j] = fin.readDouble();
      fin.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

  public static void writeDoubleArray(String fname, double array[][],
		  int nrows, int ncols) {
    try {
      File file = new File(fname);
      DataOutputStream fout = new DataOutputStream(new FileOutputStream(file));
      for (int i=0; i<nrows; i++)
        for (int j=0; j<ncols; j++)
          fout.writeDouble(array[i][j]);
      fout.close();
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
  }

} // end class ArrayIO

