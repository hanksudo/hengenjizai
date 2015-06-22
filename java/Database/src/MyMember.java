import javax.swing.*;		
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.util.*;

public class MyMember implements ActionListener,Printable{ 

	//建立並實作視窗框架f
	JFrame f = new JFrame("會員資料管理系統");

	//建立四個標籤
	JLabel lblNo = new JLabel("會員編號(N):");
	JLabel lblName = new JLabel("會員姓名(A):");
	JLabel lblTel = new JLabel("會員電話(T):");
	JLabel lblEmail = new JLabel("電子郵件(E):");

	//建立四個文字輸入方塊
	JTextField txtNo = new JTextField();
	JTextField txtName = new JTextField();
	JTextField txtTel = new JTextField();
	JTextField txtEmail = new JTextField();

	//建立八個按鈕
	JButton btnFirst = new JButton("首筆");
	JButton btnPrior = new JButton("上一筆");
	JButton btnNext = new JButton("下一筆");
	JButton btnLast = new JButton("尾筆");
	JButton btnAppend = new JButton("新增");
	JButton btnDelete = new JButton("刪除");
	JButton btnEdit = new JButton("修改");
	JButton btnPrint = new JButton("列印");

	Statement s;
	ResultSet rs;
	String sql;		//儲存SQL語法的字串

	MyMember(){
		
		//設定視窗置中
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width=320;
		int height=320;
		int x=(screen.width-width)/2;
		int y=(screen.height-height)/2;		
		f.setBounds(x,y,width,height);

		//設定視窗不使用版面管理員
		f.getContentPane().setLayout(null);

		//設定四個標籤位置
		lblNo.setBounds(45,45,80,25);
		lblName.setBounds(45,70,80,25);
		lblTel.setBounds(45,95,80,25);
		lblEmail.setBounds(45,120,80,25);

		//設定四個文字輸入方塊位置
		txtNo.setBounds(125,45,145,25);
		txtName.setBounds(125,70,145,25);
		txtTel.setBounds(125,95,145,25);
		txtEmail.setBounds(125,120,145,25);

		//設定八個按鈕位置
		btnFirst.setBounds(15,180,73,25);
		btnPrior.setBounds(85,180,73,25);
		btnNext.setBounds(155,180,73,25);
		btnLast.setBounds(225,180,73,25);
		btnAppend.setBounds(15,200,73,25);
		btnDelete.setBounds(85,200,73,25);
		btnEdit.setBounds(155,200,73,25);
		btnPrint.setBounds(225,200,73,25);


		//設定標籤與文字方塊快速鍵設定
		lblNo.setDisplayedMnemonic('N');
		lblNo.setLabelFor(txtNo);
		lblName.setDisplayedMnemonic('A');
		lblName.setLabelFor(txtName);
		lblTel.setDisplayedMnemonic('T');
		lblTel.setLabelFor(txtTel);
		lblEmail.setDisplayedMnemonic('E');
		lblEmail.setLabelFor(txtEmail);

		//將四個標籤加到視窗中
		f.getContentPane().add(lblNo);
		f.getContentPane().add(lblName);
		f.getContentPane().add(lblTel);
		f.getContentPane().add(lblEmail);

		//將四個文字輸入方塊加到視窗中
		f.getContentPane().add(txtNo);
		f.getContentPane().add(txtName);
		f.getContentPane().add(txtTel);
		f.getContentPane().add(txtEmail);

		//將八個按鈕加到視窗中
		f.getContentPane().add(btnFirst);
		f.getContentPane().add(btnPrior);
		f.getContentPane().add(btnNext);
		f.getContentPane().add(btnLast);
		f.getContentPane().add(btnAppend);
		f.getContentPane().add(btnDelete);
		f.getContentPane().add(btnEdit);
		f.getContentPane().add(btnPrint);

		//將所有按鈕加到ActionListener傾聽器中
		btnFirst.addActionListener(this);
		btnPrior.addActionListener(this);
		btnNext.addActionListener(this);
		btnLast.addActionListener(this);
		btnAppend.addActionListener(this);
		btnDelete.addActionListener(this);
		btnEdit.addActionListener(this);
		btnPrint.addActionListener(this);

		try{
			Properties p = new Properties();

			//設定連線使用編碼為UTF8
			p.put("characterEncoding","UTF8");
			p.put("useUnicode","TRUE");

			//設定連線時使用的帳號及密碼
			p.setProperty("user","root");
			p.setProperty("password","test");

			//載入MySQL Driver
			Class.forName("com.mysql.jdbc.Driver");

			//進行連線，使用Properties物件
			Connection MyConn = 
					DriverManager.getConnection(
						"jdbc:mysql://localhost/test", p);

			//取得連線物件的Statement
			s=MyConn.createStatement();
		
			//顯示Member資料表格中所有資料
			sql="SELECT * FROM members";

			//執行executeQuery()方法將查詢結果存入ResultSet中
			rs = s.executeQuery(sql);
			
			//將資料筆數移至第一筆
			rs.first();

			//取得資料庫中的資料，並放到指定欄位中
			txtNo.setText(rs.getString(1));
			txtName.setText(rs.getString(2));
			txtTel.setText(rs.getString(3));
			txtEmail.setText(rs.getString(4));

		}catch(ClassNotFoundException e){
			
			//當MySQL Driver找不到時
			//會引發ClassNotFoundException 例外
			System.out.println("找不到連線類別檔案");

		}catch(SQLException e){
	
			//當SQL語法無法執行時，會引發 SQLException 例外
			System.out.println("錯誤，訊息如下：");
			System.out.println("=================");
			System.out.println(e);	//輸出錯誤資訊

		}

		//設定視窗按鈕關閉鈕時，就將視窗自動關閉
		f.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			}
		);		

		//將視窗顯示
		f.show();
	}

	public void actionPerformed(ActionEvent e){

		try{
			if (e.getSource()==btnFirst){
				rs.first();		//將資料筆數移至第一筆
				getData();		//將資料放到文字方塊上
			}
	
			if (e.getSource()==btnLast){
				rs.last();		//將資料筆數移至最後一筆
				getData();		//將資料放到文字方塊上
			}

			if (e.getSource()==btnPrior){
				//判斷如果不在第一筆才執行
				if (!rs.isFirst())
					rs.previous();	//將資料筆數移至上一筆
					getData();		//將資料放到文字方塊上
			}

			if (e.getSource()==btnNext){
				//判斷如果不在最後一筆才執行
				if (!rs.isLast())
					rs.next();		//將資料筆數移至下一筆
					getData();		//將資料放到文字方塊上
			}

			//新增鈕程式
			if (e.getSource()==btnAppend){
				if (btnAppend.getText().equals("新增")){

					//將文字方塊內容清除
					txtNo.setText("");
					txtName.setText("");
					txtTel.setText("");
					txtEmail.setText("");

					//設定駐點在編號方塊上
					txtNo.requestFocus();

					//將首筆、上一筆、下一筆、尾筆、修改、刪除與列印鈕關閉
					btnFirst.setEnabled(false);
					btnPrior.setEnabled(false);
					btnNext.setEnabled(false);
					btnLast.setEnabled(false);
					btnDelete.setEnabled(false);
					btnEdit.setEnabled(false);
					btnPrint.setEnabled(false);
					
					//將新增按鈕文字改成「儲存」
					btnAppend.setText("儲存");

				}else{

					//將文字方塊資料加到sql語法中
					sql="INSERT members VALUES ('" 
						+ txtNo.getText()    + "','"
						+ txtName.getText()  + "','"
						+ txtTel.getText()   + "','"
					    + txtEmail.getText() + "')";

					//下達sql語法新增資料
					int i=s.executeUpdate(sql);

					//重新取得ResoultSet結果
					rs=s.executeQuery("SELECT * FROM members");

					//將資料筆數移至新增的資料中
					rs.last();

					//將首筆、上一筆、下一筆、尾筆、修改、刪除與列印鈕開啟
					btnFirst.setEnabled(true);
					btnPrior.setEnabled(true);
					btnNext.setEnabled(true);
					btnLast.setEnabled(true);
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
					btnPrint.setEnabled(true);
					
					//將儲存按鈕文字改成「新增」
					btnAppend.setText("新增");
				}
			}

			//刪除鈕程式
			if (e.getSource()==btnDelete){

				//建立刪除此筆資料的SQL語法
				sql = "DELETE FROM members WHERE id=" 
					  + txtNo.getText();
				
				//下達sql語法刪除資料
				int i=s.executeUpdate(sql);

				//重新取得ResoultSet結果
				rs=s.executeQuery("SELECT * FROM members");

				//將資料筆數移至第一筆
				rs.first();
				getData();
			}

			//修改鈕程式
			if (e.getSource()==btnEdit){
				if (btnEdit.getText().equals("修改")){

					//設定駐點在編號方塊上
					txtNo.requestFocus();

					//將首筆、上一筆、下一筆、尾筆、新增、刪除與列印鈕關閉
					btnFirst.setEnabled(false);
					btnPrior.setEnabled(false);
					btnNext.setEnabled(false);
					btnLast.setEnabled(false);
					btnDelete.setEnabled(false);
					btnAppend.setEnabled(false);
					btnPrint.setEnabled(false);
					
					//將修改按鈕文字改成「儲存」
					btnEdit.setText("儲存");

				}else{

					//將原有的編號存入Temp中
					String Temp = rs.getString(1);

					//將文字方塊資料加到sql語法中
					sql="UPDATE members SET id='" 
						+ txtNo.getText()    + "',name='"
						+ txtName.getText()  + "',tel='"
						+ txtTel.getText()   + "',email='"
					    + txtEmail.getText() + "' WHERE id='"+Temp+"'";

					//下達sql語法新增資料
					int i=s.executeUpdate(sql);

					//重新取得ResoultSet結果
					rs=s.executeQuery("SELECT * FROM members");

					//將資料筆數移到第一筆
					rs.first();
					getData();

					//將首筆、上一筆、下一筆、尾筆、新增、刪除與列印鈕開啟
					btnFirst.setEnabled(true);
					btnPrior.setEnabled(true);
					btnNext.setEnabled(true);
					btnLast.setEnabled(true);
					btnDelete.setEnabled(true);
					btnAppend.setEnabled(true);
					btnPrint.setEnabled(true);
					
					//將儲存按鈕文字改成「修改」
					btnEdit.setText("修改");
				}
			}
			
			//列印鈕程式
			if (e.getSource()==btnPrint){
				
				//取得預設列印工作
				PrinterJob p = PrinterJob.getPrinterJob();

				//設定列印內容 
				p.setPrintable(new MyMember());

				//判斷使用者按下確定或取消
				if (p.printDialog())
					p.print();	//開始列印
				else
					System.out.println("取消列印");
			}

		}catch(SQLException e1){
			System.out.println("移動資料筆數發生錯誤!!");
			System.err.println(e1);
		}catch(HeadlessException e1){
			System.out.println("呼叫列印對話方塊產生錯誤：");
			System.out.println(e1);	//列印出錯誤訊息
		}catch(PrinterException e1){
			System.out.println("呼叫列印方法產生錯誤：");
			System.out.println(e1);	//列印出錯誤訊息	
		}
	}

	//自訂getData()方法，將取得的資料放到文字方塊上
	public void getData(){
		try{
			//取得資料庫中的資料，並放到指定欄位中
			txtNo.setText(rs.getString(1));
			txtName.setText(rs.getString(2));
			txtTel.setText(rs.getString(3));
			txtEmail.setText(rs.getString(4));
		}catch(SQLException e){
			System.out.println("取得資料發生錯誤!!");
		}
	}

	public int print(Graphics g,PageFormat pageFormat,int pageIndex) throws PrinterException{

		//儲存紙張大小與可列印範圍的資料變數
		double pw,ph,x,y,w,h;

		//上下左右邊界變數，並預設值為1.5cm
		double t=1.5;
		double b=1.5;
		double l=1.5;
		double r=1.5;

		//求出A4尺寸程式使用之寬度與高度
		pw=(((210/10))/2.54)*72;
		ph=(((297/10))/2.54)*72;

		//求出可列印範圍參數值
		x=(l/2.54)*72;
		y=(t/2.54)*72;
		w=pw-((r/2.54)*72);
		h=ph-((b/2.54)*72);

		//建立A4 Paper，並設定紙張大小及列印範圍
		Paper A4 = new Paper();
		A4.setSize(pw,ph);
		A4.setImageableArea(x,y,w,h);

		//自訂紙張大小
		pageFormat.setPaper(A4);

		try{

			//顯示Member資料表格中所有資料
			sql="SELECT * FROM members";

			//執行executeQuery()方法將查詢結果存入ResultSet中
			rs = s.executeQuery(sql);

			//將資料表格欄位名稱輸出到印表機
			for (int j=1;j<=rs.getMetaData().getColumnCount();++j)
				g.drawString(rs.getMetaData().getColumnLabel(j),85*j,100);

			//設定y軸起始位置
			int sy=120;

			//將資料內容輸出到印表機
			while (rs.next()){
				for (int j=1;j<=rs.getMetaData().getColumnCount();++j)
					g.drawString(rs.getString(j),85*j,sy);

				//每一筆記錄要相差20單位的距離
				sy+=20;
			}

		}catch(SQLException e1){
	
			//當SQL語法無法執行時，會引發 SQLException 例外
			System.out.println("錯誤，訊息如下：");
			System.out.println("=================");
			System.out.println(e1);	//輸出錯誤資訊

		}

		//繪製直線，座標位置(85,105)到(500,105)
		g.drawLine(85,105,500,105);

		//傳回已列印完畢常數
		return PAGE_EXISTS;
	}

	public static void main(String[] args){	
		//將MyMember類別實體化
		MyMember Test = new MyMember();
	}
}