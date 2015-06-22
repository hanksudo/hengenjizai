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

public class Palette extends JPanel implements MouseListener,ActionListener{//調色盤class
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
		
		public Palette(){//產生版面//
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
			
			jDialog = new JDialog(f, "請選擇兩種顏色做漸層", true);
        	jDialog.getContentPane().setLayout(new FlowLayout());
        	jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
        	jDialog.setSize(250, 110);
        	JPanel temp = new JPanel(new GridLayout(2,1));
        	JPanel up = new JPanel(new FlowLayout());
        	JPanel down = new JPanel(new FlowLayout());
        	
			ok = new JButton("確定");
			cancel = new JButton("取消");
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
				center.G_color_left = JColorChooser.showDialog(f, "請選擇邊線顏色", center.G_color_left );
				center.repaint();
			}
			else if(e.getSource() == right){
				center.G_color_right = JColorChooser.showDialog(f, "請選擇邊線顏色", center.G_color_right );
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
				color_border = JColorChooser.showDialog(f, "請選擇邊線顏色", (Color)color_border );
				g2d.setPaint(color_border);
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				repaint();
			}
			else if(e.getX()>=13 && e.getX()<=28 && e.getY()>=13 && e.getY()<=28){
				g2d = bufImg2.createGraphics();
				color_inside = JColorChooser.showDialog(f, "請選擇填充顏色", (Color)color_inside );
				g2d.setPaint(color_inside);
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				repaint();
			}
			
			if(!(e.getX()>=32 && e.getX()<=288)) return;
			int choose=(e.getX()-32)/16*2+e.getY()/16;
			
			if(e.getButton()==1)//判斷填充邊框或填滿內部
				g2d = bufImg.createGraphics();
			else
				g2d = bufImg2.createGraphics();
			
			if(choose==28){//填充無顏色
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
			else if(choose==29){//填充漸層
				jDialog.show();
				
				g2d.setPaint( new GradientPaint( 0, 0, center.G_color_left, 12, 12, center.G_color_right, true ) );
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				repaint();
				
				if(e.getButton()==1)
					color_border=new GradientPaint( 0, 0, center.G_color_left, 12, 12, center.G_color_right, true );
				else
					color_inside=new GradientPaint( 0, 0, center.G_color_left, 12, 12, center.G_color_right, true );
			}
			else if(choose==30){//填充圖案
				FileDialog fileDialog = new FileDialog( new Frame() , "選擇一個圖檔", FileDialog.LOAD );//利用FileDialog抓取檔名
				fileDialog.show();//秀出視窗
				if(fileDialog.getFile()==null) return;//按取消的處理
				
				g2d.drawImage(special_color[2].getImage(), 0, 0,this);//把調色盤左方換成『圖片』
				
				icon = new ImageIcon(fileDialog.getDirectory()+fileDialog.getFile());//利用FileDialog傳進來的檔名讀取圖片
				BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);//創一張新的BufferedImage，為了要讀取讀進來的圖片長寬，以免有空白
				bufferedImage.createGraphics().drawImage(icon.getImage(),0,0,this);//把icon畫到BufferedImage上
				repaint();//重繪螢幕
				
				if(e.getButton()==1)//判斷邊線顏色或內部填滿色
					color_border=new TexturePaint(bufferedImage, new Rectangle( icon.getIconWidth(), icon.getIconHeight() ) );//把這張BufferedImage設成TexturePaint來填滿
				else
					color_inside=new TexturePaint(bufferedImage, new Rectangle( icon.getIconWidth(), icon.getIconHeight() ) );
			}
			else if(choose==31){//填充文字
				String text=JOptionPane.showInputDialog("請輸入文字","文字");//輸入文字
				if(text==null) return;//按取消時的處理
				
				Color FontColor=new Color(0,0,0);//給這個字顏色
				FontColor = JColorChooser.showDialog(f, "請選擇一個顏色當文字顏色", FontColor );//讓使用者選擇顏色
				
				g2d.drawImage(special_color[3].getImage(), 0, 0,this);//把調色盤左方換成『字』
				
				BufferedImage bufferedImage = new BufferedImage(draw_panel_width,draw_panel_height,BufferedImage.TYPE_3BYTE_BGR);//創一張新的BufferedImage
				Graphics2D g2d_bufferedImage = bufferedImage.createGraphics();//把Graphics拿出來畫
				
				FontRenderContext frc = g2d_bufferedImage.getFontRenderContext();//讀Graphics中的Font
				Font f = new Font("新細明體",Font.BOLD,10);//新Font
				TextLayout tl = new TextLayout(text, f, frc);//創新的TextLayout，並利用f(Font)跟畫至於frc(FontRenderContext)
				
				int sw = (int) (tl.getBounds().getWidth()+tl.getCharacterCount());//計算TextLayout的長
				int sh = (int) (tl.getBounds().getHeight()+3);//計算TextLayout的高
				
				bufferedImage = new BufferedImage(sw,sh,BufferedImage.TYPE_3BYTE_BGR);//再創一張新的BufferedImage，這裡利用相同指標指向不同記憶體
				g2d_bufferedImage = bufferedImage.createGraphics();//拿出Graphics來畫，前一張BufferedImage只是為了計算文字長度與高度，這樣才能完整填滿
				
				g2d_bufferedImage.setPaint(Color.WHITE);//設定顏色為白色
				g2d_bufferedImage.fill(new Rectangle(0,0,sw,sh));//畫一個填滿白色矩型
				g2d_bufferedImage.setPaint(FontColor);//設定顏色為之前選擇文字顏色
				g2d_bufferedImage.drawString(text,0,10);//畫一個String於BufferedImage上
				repaint();//更新畫面
				
				if(e.getButton()==1)//判斷邊線顏色或內部填滿色
					color_border=new TexturePaint(bufferedImage, new Rectangle(sw,sh) );//把這張BufferedImage設成TexturePaint來填滿
				else
					color_inside=new TexturePaint(bufferedImage, new Rectangle(sw,sh) );
			}
			else{//填充一般色
				g2d.setPaint(new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]));
				g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
				repaint();
				
				if(e.getButton()==1)
					color_border=new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]);
				else
					color_inside=new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]);
			}
		}
		public class Gradient extends JPanel{//漸層預覽用
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