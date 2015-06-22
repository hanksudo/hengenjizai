import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

public class Palette extends JPanel implements MouseListener,ActionListener{//�զ�Lclass
	private	int i,draw_panel_width=700,draw_panel_height=500;
	private Paint color_border,color_inside;	

		private	JPanel jPanel_color0[]=new JPanel[5];
		private	JPanel jPanel_color1[]=new JPanel[32];
		private	JPanel jPanel_color2[]=new JPanel[32];
		private	ImageIcon special_color[]= new ImageIcon[4];
		private BufferedImage bufImg = new BufferedImage(12 ,12,BufferedImage.TYPE_3BYTE_BGR) ,bufImg2 = new BufferedImage(12 ,12,BufferedImage.TYPE_3BYTE_BGR);
		private JLabel jlbImg=new JLabel() ,jlbImg2=new JLabel();
		private	ImageIcon icon;
		private JDialog jDialog;
		private JButton ok, cancel,left,right;
		private Gradient center = new Gradient();
		JFrame f;
		private	int rgb[][]={
			{0,255,128,192,128,255,128,255,  0,  0,  0,  0,  0,  0,128,255,128,255,  0,  0,  0,128,  0,128,128,255,128,255,255,255,255,255},
			{0,255,128,192,  0,  0,128,255,128,255,128,255,  0,  0,  0,  0,128,255, 64,255,128,255, 64,128,  0,  0, 64,128,255,255,255,255},
			{0,255,128,192,  0,  0,  0,  0,  0,  0,128,255,128,255,128,255, 64,128, 64,128,255,255,128,255,255,128,  0, 64,255,255,255,255}
		};
		
		public Palette(){//���ͪ���//
			f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.getContentPane().add(this);
			f.setSize(1024, 768);
			f.setVisible(true);
			addMouseListener( this );
			jlbImg.setIcon(new ImageIcon(bufImg));
			jlbImg2.setIcon(new ImageIcon(bufImg2));
			
			this.setLayout(null);
			color_border=new Color(0,0,0);
			color_inside=null;
			
			for(i=0;i<jPanel_color0.length;i++){
				jPanel_color0[i]=new JPanel();
				if(i<=2){
					jPanel_color0[i].setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
					jPanel_color0[i].setLayout(null);
				}
				else{
					jPanel_color0[i].setBackground(new Color(rgb[0][i-3],rgb[1][i-3],rgb[2][i-3]));
					jPanel_color0[i].setLayout(new GridLayout(1,1));
					jPanel_color0[i-2].add(jPanel_color0[i]);
				}
			}
			for(i=0;i<jPanel_color2.length;i++){
				jPanel_color2[i]=new JPanel();
				jPanel_color2[i].setLayout(new GridLayout(1,1));
				jPanel_color2[i].setBounds(new Rectangle(2, 2, 12, 12));
				jPanel_color2[i].setBackground(new Color(rgb[0][i],rgb[1][i],rgb[2][i]));
				if(i>=28)
					jPanel_color2[i].add(new JLabel(special_color[i-28]));
			}
			
			for(i=0;i<jPanel_color1.length;i++){
				jPanel_color1[i]=new JPanel();
				jPanel_color1[i].setLayout(null);
				jPanel_color1[i].add(jPanel_color2[i]);
				this.add(jPanel_color1[i]);
				if(i%2==0){jPanel_color1[i].setBounds(new Rectangle(32+i/2*16, 0, 16, 16));}
				else{jPanel_color1[i].setBounds(new Rectangle(32+i/2*16, 16, 16, 16));}
				jPanel_color1[i].setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
			}
			
			jPanel_color0[3].add(jlbImg);
			jPanel_color0[4].add(jlbImg2);
			
			Graphics2D g2d = bufImg2.createGraphics();
			g2d.setPaint( Color.white );
			g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
			g2d.setPaint( Color.red ); 
			g2d.draw( new Line2D.Double( 0, 0, 12, 12 ) );
			g2d.draw( new Line2D.Double( 11, 0, 0, 11 ) );
			repaint();
			
			this.add(jPanel_color0[1]);
			this.add(jPanel_color0[2]);
			this.add(jPanel_color0[0]);

			jPanel_color0[0].setBounds(new Rectangle(0, 0, 32, 32));
			jPanel_color0[1].setBounds(new Rectangle(4, 4, 16, 16));
			jPanel_color0[2].setBounds(new Rectangle(12,12,16, 16));
			jPanel_color0[3].setBounds(new Rectangle(2, 2, 12, 12));
			jPanel_color0[4].setBounds(new Rectangle(2, 2, 12, 12));
			
			jDialog = new JDialog(f, "�п�ܨ���C�ⰵ���h", true);
        	jDialog.getContentPane().setLayout(new FlowLayout());
        	jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
        	jDialog.setSize(250, 110);
        	JPanel temp = new JPanel(new GridLayout(2,1));
        	JPanel up = new JPanel(new FlowLayout());
        	JPanel down = new JPanel(new FlowLayout());
        	
			ok = new JButton("�T�w");
			cancel = new JButton("����");
			left = new JButton(" ");
			right = new JButton(" ");
			center.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
			up.add(left);
			up.add(center);
			up.add(right);
			down.add(ok);
			down.add(cancel);
			temp.add(up);
			temp.add(down);
			jDialog.getContentPane().add(temp);
			
			ok.addActionListener(this);
			cancel.addActionListener(this);
			left.addActionListener(this);
			right.addActionListener(this);
		}
		public void actionPerformed( ActionEvent e ){
			if(e.getSource() == left){
				center.G_color_left = JColorChooser.showDialog(f, "�п����u�C��", center.G_color_left );
				center.repaint();
			}
			else if(e.getSource() == right){
				center.G_color_right = JColorChooser.showDialog(f, "�п����u�C��", center.G_color_right );
				center.repaint();
			}
			else{
				jDialog.dispose();
			}
		}
		
		public Dimension getPreferredSize(){
			return new Dimension( 300, 32 );
		}
		public void mouseClicked( MouseEvent e ){}
		public void mousePressed( MouseEvent e ){
			Graphics2D g2d;
			if(e.getX()>=5 && e.getX()<=20 && e.getY()>=5 && e.getY()<=20){
				g2d = bufImg.createGraphics();
				color_border = JColorChooser.showDialog(f, "�п����u�C��", (Color)color_border );
				g2d.setPaint(color_border);
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				repaint();
			}
			else if(e.getX()>=13 && e.getX()<=28 && e.getY()>=13 && e.getY()<=28){
				g2d = bufImg2.createGraphics();
				color_inside = JColorChooser.showDialog(f, "�п�ܶ�R�C��", (Color)color_inside );
				g2d.setPaint(color_inside);
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				repaint();
			}
			
			if(!(e.getX()>=32 && e.getX()<=288)) return;
			int choose=(e.getX()-32)/16*2+e.getY()/16;
			
			if(e.getButton()==1)//�P�_��R��ةζ񺡤���
				g2d = bufImg.createGraphics();
			else
				g2d = bufImg2.createGraphics();
			
			if(choose==28){//��R�L�C��
				g2d.setPaint( Color.white );
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				g2d.setPaint( Color.red ); 
				g2d.draw( new Line2D.Double( 0, 0, 12, 12 ) );
				g2d.draw( new Line2D.Double( 11, 0, 0, 11 ) );
				repaint();
					
				if(e.getButton()==1)
					color_border=null;
				else
					color_inside=null;
			}
			else if(choose==29){//��R���h
				jDialog.show();
				
				g2d.setPaint( new GradientPaint( 0, 0, center.G_color_left, 12, 12, center.G_color_right, true ) );
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				repaint();
				
				if(e.getButton()==1)
					color_border=new GradientPaint( 0, 0, center.G_color_left, 12, 12, center.G_color_right, true );
				else
					color_inside=new GradientPaint( 0, 0, center.G_color_left, 12, 12, center.G_color_right, true );
			}
			else if(choose==30){//��R�Ϯ�
				FileDialog fileDialog = new FileDialog( new Frame() , "��ܤ@�ӹ���", FileDialog.LOAD );//�Q��FileDialog����ɦW
				fileDialog.show();//�q�X����
				if(fileDialog.getFile()==null) return;//���������B�z
				
				g2d.drawImage(special_color[2].getImage(), 0, 0,this);//��զ�L���贫���y�Ϥ��z
				
				icon = new ImageIcon(fileDialog.getDirectory()+fileDialog.getFile());//�Q��FileDialog�Ƕi�Ӫ��ɦWŪ���Ϥ�
				BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);//�Ф@�i�s��BufferedImage�A���F�nŪ��Ū�i�Ӫ��Ϥ����e�A�H�K���ť�
				bufferedImage.createGraphics().drawImage(icon.getImage(),0,0,this);//��icon�e��BufferedImage�W
				repaint();//��ø�ù�
				
				if(e.getButton()==1)//�P�_��u�C��Τ����񺡦�
					color_border=new TexturePaint(bufferedImage, new Rectangle( icon.getIconWidth(), icon.getIconHeight() ) );//��o�iBufferedImage�]��TexturePaint�Ӷ�
				else
					color_inside=new TexturePaint(bufferedImage, new Rectangle( icon.getIconWidth(), icon.getIconHeight() ) );
			}
			else if(choose==31){//��R��r
				String text=JOptionPane.showInputDialog("�п�J��r","��r");//��J��r
				if(text==null) return;//�������ɪ��B�z
				
				Color FontColor=new Color(0,0,0);//���o�Ӧr�C��
				FontColor = JColorChooser.showDialog(f, "�п�ܤ@���C����r�C��", FontColor );//���ϥΪ̿���C��
				
				g2d.drawImage(special_color[3].getImage(), 0, 0,this);//��զ�L���贫���y�r�z
				
				BufferedImage bufferedImage = new BufferedImage(draw_panel_width,draw_panel_height,BufferedImage.TYPE_3BYTE_BGR);//�Ф@�i�s��BufferedImage
				Graphics2D g2d_bufferedImage = bufferedImage.createGraphics();//��Graphics���X�ӵe
				
				FontRenderContext frc = g2d_bufferedImage.getFontRenderContext();//ŪGraphics����Font
				Font f = new Font("�s�ө���",Font.BOLD,10);//�sFont
				TextLayout tl = new TextLayout(text, f, frc);//�зs��TextLayout�A�çQ��f(Font)��e�ܩ�frc(FontRenderContext)
				
				int sw = (int) (tl.getBounds().getWidth()+tl.getCharacterCount());//�p��TextLayout����
				int sh = (int) (tl.getBounds().getHeight()+3);//�p��TextLayout����
				
				bufferedImage = new BufferedImage(sw,sh,BufferedImage.TYPE_3BYTE_BGR);//�A�Ф@�i�s��BufferedImage�A�o�̧Q�άۦP���Ы��V���P�O����
				g2d_bufferedImage = bufferedImage.createGraphics();//���XGraphics�ӵe�A�e�@�iBufferedImage�u�O���F�p���r���׻P���סA�o�ˤ~�৹���
				
				g2d_bufferedImage.setPaint(Color.WHITE);//�]�w�C�⬰�զ�
				g2d_bufferedImage.fill(new Rectangle(0,0,sw,sh));//�e�@�Ӷ񺡥զ�x��
				g2d_bufferedImage.setPaint(FontColor);//�]�w�C�⬰���e��ܤ�r�C��
				g2d_bufferedImage.drawString(text,0,10);//�e�@��String��BufferedImage�W
				repaint();//��s�e��
				
				if(e.getButton()==1)//�P�_��u�C��Τ����񺡦�
					color_border=new TexturePaint(bufferedImage, new Rectangle(sw,sh) );//��o�iBufferedImage�]��TexturePaint�Ӷ�
				else
					color_inside=new TexturePaint(bufferedImage, new Rectangle(sw,sh) );
			}
			else{//��R�@���
				g2d.setPaint(new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]));
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				repaint();
				
				if(e.getButton()==1)
					color_border=new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]);
				else
					color_inside=new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]);
			}
		}
		public class Gradient extends JPanel{//���h�w����
			public Color G_color_left = new Color(255,255,255);
			public Color G_color_right = new Color(0,0,0);
			
			public Gradient(){
				repaint();
			}
			
			public void paint(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				
				g2d.setPaint( new GradientPaint( 0, 0, G_color_left, 100, 0, G_color_right, true ) );
				g2d.fill( new Rectangle2D.Double( 0, 0, 100, 25 ) );
			}
			
			public Dimension getPreferredSize(){
				return new Dimension( 100, 25 );
			}
		}
		public void mouseReleased( MouseEvent e ){}
		public void mouseEntered( MouseEvent e ){}
		public void mouseExited( MouseEvent e ){}
		
		public static void main( String args[] ){
			new Palette();
		}
	}