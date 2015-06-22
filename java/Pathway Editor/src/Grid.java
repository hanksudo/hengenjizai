

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
		
		// 畫垂直線
		float i;
		for (i=gridSpacing; i<=gridWidth; i+=gridSpacing) {
			gridPath.moveTo(i, 2);
			gridPath.lineTo(i, gridHeight);
		}
		
		// 畫水平線
		for (i=gridSpacing; i<=gridHeight; i+=gridSpacing) {
			gridPath.moveTo(2, i);
			gridPath.lineTo(gridWidth, i);
		}
	}
	
	// 取得長寬並建立格線
	public static void updateGrid(int width, int height) {
		if (gridEnabled) {
			gridWidth = width;
			gridHeight = height;
			createGrid();
		}
	}
	
	// 畫格線
	public static void drawGrid(Graphics g) {
		if (gridEnabled) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(new Color(240, 240, 255));
			g2d.draw(gridPath);
		}
	}
	
	// 改變格線框大小
	public static void changeGridSize(int gridSize) {
		gridSpacing = (float)(Math.pow(2, (gridSize-1))*7.5);
	}
	
	// 讓元件對齊格線 X軸
	public static int getModifiedX(double x) {
		if (!snapEnabled) {return (int)x;}
		return (int)(Math.round(x/gridSpacing) * gridSpacing);
	}
	
	// 讓元件對齊格線 Y軸
	public static int getModifiedY(double y) {
		if (!snapEnabled) {return (int)y;}
		return (int)(Math.round(y/gridSpacing) * gridSpacing);
	}
	
	// 格線是否開啟
	public static void ableGrid(boolean able) {
		gridEnabled = able;
	}

	// 取得Grid開啟狀態
	public static boolean getGridEnabled() {
		return gridEnabled;
	}
	
	// 對齊是否開啟
	public static void ableSnap(boolean able) {
		snapEnabled = able;
	}
}
