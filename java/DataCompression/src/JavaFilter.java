import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @comment: ���ɦW�z��
 * @author Han-Hong Wang
 * @MSN phodra@hotmail.com
 * @website http://whh.idv.tw
 * @date 2007/6/15
 */

public class JavaFilter extends FileFilter {
	String extension;	// ���ɦW
	String description;	// �ɮ״y�z
	
	public JavaFilter(String _ext, String _des) {
		extension = _ext.toLowerCase();
		description = _des;
	}

	public boolean accept(File arg0) {
		if (arg0.isDirectory()) {
			return true;
		}
		
		String ext = null;
		String s = arg0.getName();	// ���o�ɮצW��
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
}
