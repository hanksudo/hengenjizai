

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;


public class Grid {
	static int gridWidth;
	static int gridHeight;
	static GeneralPath gridPath;
	static float gridSpacing = (float)7.5;
	static boolean gridEnabled=true;
	static boolean snapEnabled=true;
	
	public static void createGrid() {
		gridPath = new GeneralPath();
		
		// �e�����u
		float i;
		for (i=gridSpacing; i<=gridWidth; i+=gridSpacing) {
			gridPath.moveTo(i, 2);
			gridPath.lineTo(i, gridHeight);
		}
		
		// �e�����u
		for (i=gridSpacing; i<=gridHeight; i+=gridSpacing) {
			gridPath.moveTo(2, i);
			gridPath.lineTo(gridWidth, i);
		}
	}
	
	// ���o���e�ëإ߮�u
	public static void updateGrid(int width, int height) {
		if (gridEnabled) {
			gridWidth = width;
			gridHeight = height;
			createGrid();
		}
	}
	
	// �e��u
	public static void drawGrid(Graphics g) {
		if (gridEnabled) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(new Color(240, 240, 255));
			g2d.draw(gridPath);
		}
	}
	
	// ���ܮ�u�ؤj�p
	public static void changeGridSize(int gridSize) {
		gridSpacing = (float)(Math.pow(2, (gridSize-1))*7.5);
	}
	
	// ����������u X�b
	public static int getModifiedX(double x) {
		if (!snapEnabled) {return (int)x;}
		return (int)(Math.round(x/gridSpacing) * gridSpacing);
	}
	
	// ����������u Y�b
	public static int getModifiedY(double y) {
		if (!snapEnabled) {return (int)y;}
		return (int)(Math.round(y/gridSpacing) * gridSpacing);
	}
	
	// ��u�O�_�}��
	public static void ableGrid(boolean able) {
		gridEnabled = able;
	}

	// ���oGrid�}�Ҫ��A
	public static boolean getGridEnabled() {
		return gridEnabled;
	}
	
	// ����O�_�}��
	public static void ableSnap(boolean able) {
		snapEnabled = able;
	}
}
