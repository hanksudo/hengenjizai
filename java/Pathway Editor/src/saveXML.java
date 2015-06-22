import java.io.FileWriter;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class saveXML {

	public saveXML() {
		Element root = new Element("pathway");
		root.setAttribute("name", "path:00010");
		root.setAttribute("org", "map");
		root.setAttribute("number", "00010");
		root.setAttribute("title", "Glycolysis / Gluoneogenesis");
		root.setAttribute("image", "http://www.genome.jp/kegg/pathway/map/map00010.gif");
		root.setAttribute("link", "http://www.genome.jp/dbget-bin/show_pathway?map00010");
		
		Element animitem = new Element("entry");
		animitem.setAttribute("id", "1");
		animitem.setAttribute("name", "ec:1.2.1.3");
		animitem.setAttribute("type", "enzyme");
		animitem.setAttribute("reaction", "rn:R00710");
		animitem.setAttribute("link", "http://www.genome.jp/dbget-bin/www_bget?enzyme+1.2.1.3");
		
		Element graphic = new Element("graphics");
		graphic.setAttribute("name", "1.2.1.3");
		graphic.setAttribute("fgcolor", "#000000");
		graphic.setAttribute("bgcolor", "#FFFFFF");
		graphic.setAttribute("type", "rectangle");
		graphic.setAttribute("x", "170");
		graphic.setAttribute("y", "1018");
		graphic.setAttribute("width", "45");
		graphic.setAttribute("height", "17");
		
		animitem.addContent(graphic);
		root.addContent(animitem);
		
		// 建立輸出格式
		Format format = Format.getCompactFormat();
//		format.setEncoding("big5");	// 設輸出encoding 為big5
		format.setOmitEncoding(true);	// 是否省略輸出encoding
		format.setIndent(" ");	// 設定縮排格式
		format.setLineSeparator(System.getProperty("line.separator")); // 設定隔行的格式
		
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(format);
		Document doc = new Document(root);
		System.out.println(outputter.outputString(doc));
		
		try {
			FileWriter writer = new FileWriter("E:\\test.xml");
			outputter.output(doc, writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new saveXML();
	}

}
