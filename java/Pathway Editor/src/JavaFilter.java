import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @comment: 副檔名篩選
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/6/15
 */

public class JavaFilter extends FileFilter {
	String extension;	// 副檔名
	String description;	// 檔案描述
	
	String extArr[] = {"xml", "jpg"};
	String desArr[] = {"Pathway XML file. (*.xml)",
					   "JPEG Image file. (*.jpg; *.jpeg)"
	};
	
	public JavaFilter(int typeID) {
		setFileType(typeID);
	}

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		
		String ext = null;
		String s = f.getName();	// 取得檔案名稱
		int i = s.lastIndexOf('.');
		
		if (i>0 && i<s.length()-1) {
			ext = s.substring(i+1).toLowerCase();
			if (extension.equals(ext)) {return true;}
		}
		return false;
	}

	public String getDescription() {
		return description;
	}
	
	public void setFileType(int typeID) {
		extension = extArr[typeID];
		description = desArr[typeID];
	}

}
