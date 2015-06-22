import javax.swing.*;		
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;
import java.util.*;

public class MyMember implements ActionListener,Printable{ 

	//�إߨù�@�����ج[f
	JFrame f = new JFrame("�|����ƺ޲z�t��");

	//�إߥ|�Ӽ���
	JLabel lblNo = new JLabel("�|���s��(N):");
	JLabel lblName = new JLabel("�|���m�W(A):");
	JLabel lblTel = new JLabel("�|���q��(T):");
	JLabel lblEmail = new JLabel("�q�l�l��(E):");

	//�إߥ|�Ӥ�r��J���
	JTextField txtNo = new JTextField();
	JTextField txtName = new JTextField();
	JTextField txtTel = new JTextField();
	JTextField txtEmail = new JTextField();

	//�إߤK�ӫ��s
	JButton btnFirst = new JButton("����");
	JButton btnPrior = new JButton("�W�@��");
	JButton btnNext = new JButton("�U�@��");
	JButton btnLast = new JButton("����");
	JButton btnAppend = new JButton("�s�W");
	JButton btnDelete = new JButton("�R��");
	JButton btnEdit = new JButton("�ק�");
	JButton btnPrint = new JButton("�C�L");

	Statement s;
	ResultSet rs;
	String sql;		//�x�sSQL�y�k���r��

	MyMember(){
		
		//�]�w�����m��
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int width=320;
		int height=320;
		int x=(screen.width-width)/2;
		int y=(screen.height-height)/2;		
		f.setBounds(x,y,width,height);

		//�]�w�������ϥΪ����޲z��
		f.getContentPane().setLayout(null);

		//�]�w�|�Ӽ��Ҧ�m
		lblNo.setBounds(45,45,80,25);
		lblName.setBounds(45,70,80,25);
		lblTel.setBounds(45,95,80,25);
		lblEmail.setBounds(45,120,80,25);

		//�]�w�|�Ӥ�r��J�����m
		txtNo.setBounds(125,45,145,25);
		txtName.setBounds(125,70,145,25);
		txtTel.setBounds(125,95,145,25);
		txtEmail.setBounds(125,120,145,25);

		//�]�w�K�ӫ��s��m
		btnFirst.setBounds(15,180,73,25);
		btnPrior.setBounds(85,180,73,25);
		btnNext.setBounds(155,180,73,25);
		btnLast.setBounds(225,180,73,25);
		btnAppend.setBounds(15,200,73,25);
		btnDelete.setBounds(85,200,73,25);
		btnEdit.setBounds(155,200,73,25);
		btnPrint.setBounds(225,200,73,25);


		//�]�w���һP��r����ֳt��]�w
		lblNo.setDisplayedMnemonic('N');
		lblNo.setLabelFor(txtNo);
		lblName.setDisplayedMnemonic('A');
		lblName.setLabelFor(txtName);
		lblTel.setDisplayedMnemonic('T');
		lblTel.setLabelFor(txtTel);
		lblEmail.setDisplayedMnemonic('E');
		lblEmail.setLabelFor(txtEmail);

		//�N�|�Ӽ��ҥ[�������
		f.getContentPane().add(lblNo);
		f.getContentPane().add(lblName);
		f.getContentPane().add(lblTel);
		f.getContentPane().add(lblEmail);

		//�N�|�Ӥ�r��J����[�������
		f.getContentPane().add(txtNo);
		f.getContentPane().add(txtName);
		f.getContentPane().add(txtTel);
		f.getContentPane().add(txtEmail);

		//�N�K�ӫ��s�[�������
		f.getContentPane().add(btnFirst);
		f.getContentPane().add(btnPrior);
		f.getContentPane().add(btnNext);
		f.getContentPane().add(btnLast);
		f.getContentPane().add(btnAppend);
		f.getContentPane().add(btnDelete);
		f.getContentPane().add(btnEdit);
		f.getContentPane().add(btnPrint);

		//�N�Ҧ����s�[��ActionListener��ť����
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

			//�]�w�s�u�ϥνs�X��UTF8
			p.put("characterEncoding","UTF8");
			p.put("useUnicode","TRUE");

			//�]�w�s�u�ɨϥΪ��b���αK�X
			p.setProperty("user","root");
			p.setProperty("password","test");

			//���JMySQL Driver
			Class.forName("com.mysql.jdbc.Driver");

			//�i��s�u�A�ϥ�Properties����
			Connection MyConn = 
					DriverManager.getConnection(
						"jdbc:mysql://localhost/test", p);

			//���o�s�u����Statement
			s=MyConn.createStatement();
		
			//���Member��ƪ�椤�Ҧ����
			sql="SELECT * FROM members";

			//����executeQuery()��k�N�d�ߵ��G�s�JResultSet��
			rs = s.executeQuery(sql);
			
			//�N��Ƶ��Ʋ��ܲĤ@��
			rs.first();

			//���o��Ʈw������ơA�é����w��줤
			txtNo.setText(rs.getString(1));
			txtName.setText(rs.getString(2));
			txtTel.setText(rs.getString(3));
			txtEmail.setText(rs.getString(4));

		}catch(ClassNotFoundException e){
			
			//��MySQL Driver�䤣���
			//�|�޵oClassNotFoundException �ҥ~
			System.out.println("�䤣��s�u���O�ɮ�");

		}catch(SQLException e){
	
			//��SQL�y�k�L�k����ɡA�|�޵o SQLException �ҥ~
			System.out.println("���~�A�T���p�U�G");
			System.out.println("=================");
			System.out.println(e);	//��X���~��T

		}

		//�]�w�������s�����s�ɡA�N�N�����۰�����
		f.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			}
		);		

		//�N�������
		f.show();
	}

	public void actionPerformed(ActionEvent e){

		try{
			if (e.getSource()==btnFirst){
				rs.first();		//�N��Ƶ��Ʋ��ܲĤ@��
				getData();		//�N��Ʃ���r����W
			}
	
			if (e.getSource()==btnLast){
				rs.last();		//�N��Ƶ��Ʋ��̫ܳ�@��
				getData();		//�N��Ʃ���r����W
			}

			if (e.getSource()==btnPrior){
				//�P�_�p�G���b�Ĥ@���~����
				if (!rs.isFirst())
					rs.previous();	//�N��Ƶ��Ʋ��ܤW�@��
					getData();		//�N��Ʃ���r����W
			}

			if (e.getSource()==btnNext){
				//�P�_�p�G���b�̫�@���~����
				if (!rs.isLast())
					rs.next();		//�N��Ƶ��Ʋ��ܤU�@��
					getData();		//�N��Ʃ���r����W
			}

			//�s�W�s�{��
			if (e.getSource()==btnAppend){
				if (btnAppend.getText().equals("�s�W")){

					//�N��r������e�M��
					txtNo.setText("");
					txtName.setText("");
					txtTel.setText("");
					txtEmail.setText("");

					//�]�w�n�I�b�s������W
					txtNo.requestFocus();

					//�N�����B�W�@���B�U�@���B�����B�ק�B�R���P�C�L�s����
					btnFirst.setEnabled(false);
					btnPrior.setEnabled(false);
					btnNext.setEnabled(false);
					btnLast.setEnabled(false);
					btnDelete.setEnabled(false);
					btnEdit.setEnabled(false);
					btnPrint.setEnabled(false);
					
					//�N�s�W���s��r�令�u�x�s�v
					btnAppend.setText("�x�s");

				}else{

					//�N��r�����ƥ[��sql�y�k��
					sql="INSERT members VALUES ('" 
						+ txtNo.getText()    + "','"
						+ txtName.getText()  + "','"
						+ txtTel.getText()   + "','"
					    + txtEmail.getText() + "')";

					//�U�Fsql�y�k�s�W���
					int i=s.executeUpdate(sql);

					//���s���oResoultSet���G
					rs=s.executeQuery("SELECT * FROM members");

					//�N��Ƶ��Ʋ��ܷs�W����Ƥ�
					rs.last();

					//�N�����B�W�@���B�U�@���B�����B�ק�B�R���P�C�L�s�}��
					btnFirst.setEnabled(true);
					btnPrior.setEnabled(true);
					btnNext.setEnabled(true);
					btnLast.setEnabled(true);
					btnDelete.setEnabled(true);
					btnEdit.setEnabled(true);
					btnPrint.setEnabled(true);
					
					//�N�x�s���s��r�令�u�s�W�v
					btnAppend.setText("�s�W");
				}
			}

			//�R���s�{��
			if (e.getSource()==btnDelete){

				//�إߧR��������ƪ�SQL�y�k
				sql = "DELETE FROM members WHERE id=" 
					  + txtNo.getText();
				
				//�U�Fsql�y�k�R�����
				int i=s.executeUpdate(sql);

				//���s���oResoultSet���G
				rs=s.executeQuery("SELECT * FROM members");

				//�N��Ƶ��Ʋ��ܲĤ@��
				rs.first();
				getData();
			}

			//�ק�s�{��
			if (e.getSource()==btnEdit){
				if (btnEdit.getText().equals("�ק�")){

					//�]�w�n�I�b�s������W
					txtNo.requestFocus();

					//�N�����B�W�@���B�U�@���B�����B�s�W�B�R���P�C�L�s����
					btnFirst.setEnabled(false);
					btnPrior.setEnabled(false);
					btnNext.setEnabled(false);
					btnLast.setEnabled(false);
					btnDelete.setEnabled(false);
					btnAppend.setEnabled(false);
					btnPrint.setEnabled(false);
					
					//�N�ק���s��r�令�u�x�s�v
					btnEdit.setText("�x�s");

				}else{

					//�N�즳���s���s�JTemp��
					String Temp = rs.getString(1);

					//�N��r�����ƥ[��sql�y�k��
					sql="UPDATE members SET id='" 
						+ txtNo.getText()    + "',name='"
						+ txtName.getText()  + "',tel='"
						+ txtTel.getText()   + "',email='"
					    + txtEmail.getText() + "' WHERE id='"+Temp+"'";

					//�U�Fsql�y�k�s�W���
					int i=s.executeUpdate(sql);

					//���s���oResoultSet���G
					rs=s.executeQuery("SELECT * FROM members");

					//�N��Ƶ��Ʋ���Ĥ@��
					rs.first();
					getData();

					//�N�����B�W�@���B�U�@���B�����B�s�W�B�R���P�C�L�s�}��
					btnFirst.setEnabled(true);
					btnPrior.setEnabled(true);
					btnNext.setEnabled(true);
					btnLast.setEnabled(true);
					btnDelete.setEnabled(true);
					btnAppend.setEnabled(true);
					btnPrint.setEnabled(true);
					
					//�N�x�s���s��r�令�u�ק�v
					btnEdit.setText("�ק�");
				}
			}
			
			//�C�L�s�{��
			if (e.getSource()==btnPrint){
				
				//���o�w�]�C�L�u�@
				PrinterJob p = PrinterJob.getPrinterJob();

				//�]�w�C�L���e 
				p.setPrintable(new MyMember());

				//�P�_�ϥΪ̫��U�T�w�Ψ���
				if (p.printDialog())
					p.print();	//�}�l�C�L
				else
					System.out.println("�����C�L");
			}

		}catch(SQLException e1){
			System.out.println("���ʸ�Ƶ��Ƶo�Ϳ��~!!");
			System.err.println(e1);
		}catch(HeadlessException e1){
			System.out.println("�I�s�C�L��ܤ�����Ϳ��~�G");
			System.out.println(e1);	//�C�L�X���~�T��
		}catch(PrinterException e1){
			System.out.println("�I�s�C�L��k���Ϳ��~�G");
			System.out.println(e1);	//�C�L�X���~�T��	
		}
	}

	//�ۭqgetData()��k�A�N���o����Ʃ���r����W
	public void getData(){
		try{
			//���o��Ʈw������ơA�é����w��줤
			txtNo.setText(rs.getString(1));
			txtName.setText(rs.getString(2));
			txtTel.setText(rs.getString(3));
			txtEmail.setText(rs.getString(4));
		}catch(SQLException e){
			System.out.println("���o��Ƶo�Ϳ��~!!");
		}
	}

	public int print(Graphics g,PageFormat pageFormat,int pageIndex) throws PrinterException{

		//�x�s�ȱi�j�p�P�i�C�L�d�򪺸���ܼ�
		double pw,ph,x,y,w,h;

		//�W�U���k����ܼơA�ùw�]�Ȭ�1.5cm
		double t=1.5;
		double b=1.5;
		double l=1.5;
		double r=1.5;

		//�D�XA4�ؤo�{���ϥΤ��e�׻P����
		pw=(((210/10))/2.54)*72;
		ph=(((297/10))/2.54)*72;

		//�D�X�i�C�L�d��Ѽƭ�
		x=(l/2.54)*72;
		y=(t/2.54)*72;
		w=pw-((r/2.54)*72);
		h=ph-((b/2.54)*72);

		//�إ�A4 Paper�A�ó]�w�ȱi�j�p�ΦC�L�d��
		Paper A4 = new Paper();
		A4.setSize(pw,ph);
		A4.setImageableArea(x,y,w,h);

		//�ۭq�ȱi�j�p
		pageFormat.setPaper(A4);

		try{

			//���Member��ƪ�椤�Ҧ����
			sql="SELECT * FROM members";

			//����executeQuery()��k�N�d�ߵ��G�s�JResultSet��
			rs = s.executeQuery(sql);

			//�N��ƪ�����W�ٿ�X��L���
			for (int j=1;j<=rs.getMetaData().getColumnCount();++j)
				g.drawString(rs.getMetaData().getColumnLabel(j),85*j,100);

			//�]�wy�b�_�l��m
			int sy=120;

			//�N��Ƥ��e��X��L���
			while (rs.next()){
				for (int j=1;j<=rs.getMetaData().getColumnCount();++j)
					g.drawString(rs.getString(j),85*j,sy);

				//�C�@���O���n�ۮt20��쪺�Z��
				sy+=20;
			}

		}catch(SQLException e1){
	
			//��SQL�y�k�L�k����ɡA�|�޵o SQLException �ҥ~
			System.out.println("���~�A�T���p�U�G");
			System.out.println("=================");
			System.out.println(e1);	//��X���~��T

		}

		//ø�s���u�A�y�Ц�m(85,105)��(500,105)
		g.drawLine(85,105,500,105);

		//�Ǧ^�w�C�L�����`��
		return PAGE_EXISTS;
	}

	public static void main(String[] args){	
		//�NMyMember���O�����
		MyMember Test = new MyMember();
	}
}