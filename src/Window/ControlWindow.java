package Window;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

@SuppressWarnings("serial")
public class ControlWindow extends JFrame{

	Socket socket ;
	public static final int PORT = 9999 ;
	public static final int RANDOM_PORT = 0 ;
	JPanel functionJPanel ;
	JScrollPane contentPane ;
	imageJPanel imageJPanel ;
	JButton connectionbButton ;
	JButton shutdownButton ;
	JTextField ipField ;
	JButton tiredButton ;
	JComboBox appBox ;
	JButton killButton ;
	JButton banButton ;
	JButton obButton ;
	JButton autoPlay ;
	JButton fileButton ;
	InputStream in ;
	OutputStream out ;
	
	boolean shutdownFlag = false ;
	boolean tiredFlag = false ;
	boolean obFlag = false ;
	boolean killFlag = false ;
	boolean banFlag = false ;
	boolean fileFlag = false ;
	boolean autoFlag = false ;
	
	
	public ControlWindow() {
		this.setLayout( null ) ;
		
		functionJPanel = new JPanel() ;
		imageJPanel = new imageJPanel() ;
		contentPane =new JScrollPane( imageJPanel ) ;
		
		Font font = new Font( "΢���ź�" , 1 , 12 ) ;
		
		connectionbButton = new JButton( "����" ) ;
		connectionbButton.setFont(font) ;
		connectionbButton.setSize( 60 , 30 ) ;
		connectionbButton.addActionListener( new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(connectionbButton.getText().equals("����")){
					connectPC() ;					
				}else {
					disConnect() ;
				}
			}	
		}) ;
		
		ipField = new JTextField() ;
		ipField.setFont(font) ;
		ipField.setHorizontalAlignment(JTextField.RIGHT) ;
		ipField.setSize( 100 , 20 ) ;
		ipField.setText("localhost") ;
		ipField.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(connectionbButton.getText().equals("����")){
					connectPC() ;					
				}else {
					disConnect() ;
				}
			}
		}) ;
		
		shutdownButton = new JButton( "�ػ�" ) ;
		shutdownButton.setFont(font) ;
		shutdownButton.setSize( 60 , 30 ) ;
		shutdownButton.addActionListener( new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				shutdownPC() ;
			}
		}) ;
		
		tiredButton = new JButton( "���" ) ;
		tiredButton.setFont(font) ;
		tiredButton.setSize( 60 , 30 ) ;
		tiredButton.addActionListener( new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if( tiredButton.getText().equals("���") ) {					
					tiredPC() ;
				}else {
					relaxPC() ;
				}

			}
		}) ;
		
		appBox = new JComboBox() ;
		appBox.setFont(font) ;
		appBox.setSize( 90 , 20 ) ;
		appBox.addPopupMenuListener( new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				initApplication() ;
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		}) ;
		appBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int state = e.getStateChange() ;
				if( state == e.SELECTED ){
					System.out.println(e.getItem());
				}
			}
		});
		killButton = new JButton( "��ֹ" ) ;
		killButton.setFont(font) ;
		killButton.setSize( 60 , 30 ) ;
		killButton.addActionListener( new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				killPC() ;
			}
		}) ;
		banButton = new JButton( "��ֹ" ) ;
		banButton.setFont(font) ;
		banButton.setSize( 60 , 30 ) ;
		banButton.addActionListener( new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				banPC();
			}
		}) ;
		
		obButton = new JButton( "����" ) ;
		obButton.setFont(font) ;
		obButton.setSize( 60 , 30 ) ;
		obButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				obPC() ;
			}
		}) ;
		
		autoPlay = new JButton( "�ƻ�" ) ;
		autoPlay.setFont(font) ;
		autoPlay.setSize( 60 , 30 ) ;
		autoPlay.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				autoPlayPC() ;
			}
		});

		fileButton = new JButton( "�ļ�" ) ;
		fileButton.setFont(font) ;
		fileButton.setSize( 60 , 30 ) ;
		fileButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fileManager() ;
			}
		}) ;
		
		
		functionJPanel.setLayout( null ) ;
		functionJPanel.add( connectionbButton );
		functionJPanel.add( ipField ) ;
		functionJPanel.add( shutdownButton ) ;
		functionJPanel.add( tiredButton ) ;
		functionJPanel.add( appBox ) ;
		functionJPanel.add( killButton ) ;
		functionJPanel.add( banButton ) ;
		functionJPanel.add( obButton ) ;
		functionJPanel.add( autoPlay ) ;
		functionJPanel.add( fileButton ) ;
		this.add( functionJPanel ) ;
		this.add( contentPane ) ;
		this.addComponentListener( new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				autoLayout() ;
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ) ;
		this.setTitle( "��������Beta  V2.0") ;
		this.setSize( 800 , 600 ) ;
		this.setLocationRelativeTo( null ) ;
		this.setVisible( true ) ;
	}
	//-----------------------------------------ҵ����-----------------------------------------
	public void showMessage ( String message ) {
		JOptionPane.showMessageDialog( this , message ) ;
	}
	/**
	 * ��ɲ���
	 */
	public void autoLayout() {
		functionJPanel.setBounds( 0 , 0 , this.getWidth() , 50 ) ;
		
		for ( int i = 0 ; i < functionJPanel.getComponentCount() ; i++ ) {
			Component comp = functionJPanel.getComponent( i ) ;
			int x = (this.getWidth() - 780)/2; 
			if ( i > 0 ) {
				Component beforeComponent = functionJPanel.getComponent( i - 1 ) ;
				x = beforeComponent.getLocation().x+beforeComponent.getWidth() + 10 ;
			}
			if ( i == 1 || i == 4 ){
				comp.setLocation( x , 15 ) ;
			}else {
				comp.setLocation( x , 10 ) ;				
			}
		}
		contentPane.setBounds( 5 ,  functionJPanel.getHeight() , this.getWidth() - 25 , this.getHeight()-functionJPanel.getHeight() - 45 ) ;
		reflushFunction() ;

	}
	/**
	 * ˢ�¹�����ָ��
	 */
	public void reflushFunction() {
		functionJPanel.invalidate() ;
		appBox.removeAllItems() ;
		if( socket == null || socket.isClosed() ){
			connectionbButton.setText( "����" ) ;
		}else {
			connectionbButton.setText( "�Ͽ�" ) ;
		}
		if( shutdownFlag ) {
			shutdownButton.setText ( "ȡ��" ) ;
		}else if ( !shutdownFlag ) {
			shutdownButton.setText( "�ػ�" ) ;
		}
		if( tiredFlag ) {
			tiredButton.setText( "ȡ��" ) ;
		}else if ( !tiredFlag ) {
			tiredButton.setText( "���" ) ;
		}
		if ( obFlag ) {
			obButton.setText( "ȡ��" ) ;
		}else if ( !obFlag ) {
			obButton.setText( "����" ) ;
		}
		if ( killFlag ) {
			killButton.setText( "ȡ��" ) ;
		}else if( !killFlag) {
			killButton.setText( "��ֹ" ) ;
		}
		if ( banFlag ) {
			banButton.setText( "ȡ��" ) ;
		}else if ( !banFlag ) {
			banButton.setText( "��ֹ" ) ;
		}
		if ( autoFlag ) {
			autoPlay.setText( "ȡ��" ) ;
		}else if ( !autoFlag ) {
			autoPlay.setText( "�ƻ�" ) ;
		}
		if ( fileFlag ) {
			fileButton.setText( "ȡ��" ) ;
		}else if ( !fileFlag ) {
			fileButton.setText( "�ļ�" ) ;
		}
		functionJPanel.invalidate() ;
	}
	
	/**
	 * ����Զ�̼����
	 */
	public void connectPC(){
		String ipInput = this.ipField.getText() ;
//		String s = appBox.getSelectedItem().toString() ;
//		int newport = Integer.parseInt( s );
		//�˿ں� 0 ��ʾһ������˿ڣ��ɻ����Զ�����,���´����ʾ��������Լ��Ķ˿ں�ȥ����Ŀ���ַ
		try {
			socket = new Socket( ipInput , PORT ) ; 
			showMessage( "����Զ�̼�����ɹ���" );
		}catch(UnknownHostException unke) {
			unke.printStackTrace();
			showMessage( "�Ҳ���ָ����������\n"+unke.getMessage() );
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace() ;
			showMessage( "Զ������ʧ�ܣ�\n" +  e.getMessage());
		}
		autoLayout() ; 
//		socket = new Socket() ;
//		InetSocketAddress local_so_addr = new InetSocketAddress( "localhost" , 0 ) ;
//		if( ipInput == null || ipInput.equals( "" ) ) {
//			showMessage( "������IP��ַ��" ) ;
//		}else {
//			try {
//				socket.bind( local_so_addr ) ;
//			} catch (IOException IOe) {
//				// TODO: handle exception
//				IOe.printStackTrace() ;
//				showMessage( "���Լ������ʧ�ܣ�\n" + IOe.getMessage() );
//			}
//			InetSocketAddress remote_so_addr = new InetSocketAddress( ipInput , 0 ) ;
//			try {
//				socket.connect( remote_so_addr ) ;
//				autoLayout() ;
//			}catch (UnknownHostException une) {
//				// TODO: handle exception
//				une.printStackTrace() ;
//				showMessage( "�Ҳ���ָ����������\n"+une.getMessage()) ;
//			} catch (IOException IOe) {
//				// TODO: handle exception
//				IOe.printStackTrace() ;
//				showMessage( "Զ������ʧ�ܣ�\n" + IOe.getMessage() );
//			}
//		}
	}
	
	/**
	 * �Ͽ���������
	 */
	public void disConnect() {
		try {
			out = socket.getOutputStream() ;
			out.write( 0 ) ;
			out.flush() ;
			showMessage( "�Ͽ�����ָ���ѷ��ͣ�") ;
			socket.close() ;
		} catch (Exception e) {
			// TODO: handle exception
		}
		socket = null ;
		autoLayout() ;
	}
	/**
	 * �ر�Զ�̼����
	 */
	public void shutdownPC() {
		if( socket != null && !socket.isClosed() ) {
			if( !shutdownFlag ){
				try {
					out = socket.getOutputStream() ;
					out.write( 1 ) ;
					out.flush() ;
					showMessage( "�ر�Զ�̼����ָ���ѷ��ͣ�") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessage( "�ر�Զ�̼����ָ���ʧ�ܣ�\n" + e.getMessage() ) ;
				}
			}else {
				try {
					OutputStream out = socket.getOutputStream() ;
					out.write( 2 ) ;
					out.flush() ;
					showMessage( "��ֹԶ�̼��������ָ���ѷ��ͣ�") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessage( "��ֹԶ�̼��������ָ���ʧ�ܣ�\n" + e.getMessage() ) ;
				}
			}
			shutdownFlag = !shutdownFlag ;
			reflushFunction() ;
		}
	}
	
	/**
	 * ��ȡ�⼦Ӧ�ó����б���appBox����ʾ����
	 */
	public void initApplication() {
		if( socket != null && !socket.isClosed() ) {
			reflushFunction() ;
			try {
				out = socket.getOutputStream() ;
				out.write( 6 ) ;
				out.flush() ;
				showMessage( "��ȡӦ���б�ָ���ѷ��ͣ�" );
				in = socket.getInputStream();
				while ( true ) {
					byte data[] = new byte[in.read()] ;
					in.read( data ) ;
					String name = new String(data) ;
					if (name.equals("\r\n")) {
						break ;
					}else {						
						appBox.addItem(name) ;					
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace() ;
				showMessage("��ȡ�����б�ʧ�ܣ�"+ e.getMessage() );
			}
		}
	}
	
	/**
	 * ����Զ�̼����
	 */
	public void tiredPC() {
		if( socket != null && !socket.isClosed() ) {
			try {
				tiredFlag = !tiredFlag;
				reflushFunction() ;
				OutputStream out = socket.getOutputStream() ;
				out.write( 3 ) ;
				out.flush() ;
				showMessage( "����Զ�̼��������ָ���ѷ��ͣ�") ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				showMessage( "����Զ�̼��������ָ���ʧ�ܣ�\n" + e.getMessage() ) ;
			}
		}
	}
	
	/**
	 * ȡ������Զ�̼����
	 */
	public void relaxPC() {
		if( socket != null && !socket.isClosed() ) {
			try {
				out = socket.getOutputStream() ;
				out.write( 4 ) ;
				out.flush() ;
				showMessage( "����Զ�̼��������ָ���ѷ��ͣ�") ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				showMessage( "����Զ�̼��������ָ���ʧ�ܣ�\n" + e.getMessage() ) ;
			}
			tiredFlag = !tiredFlag ;
			reflushFunction() ;
		}
	}
	/**
	 * ��ֹԶ�̼����
	 */
	public void killPC() {
		if( socket != null && !socket.isClosed() ) {
			try {
				out = socket.getOutputStream() ;
				out.write( 7 ) ;
				String name = appBox.getSelectedItem().toString() ;
				byte data[] = name.getBytes();
				out.write( data.length ) ;
				out.write( data ) ;
				out.flush() ;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	/**
	 * ��ֹԶ�̼����
	 */
	public void banPC() {
		if( socket != null && !socket.isClosed() ) {
			if ( !banFlag ) {
				try {
					out = socket.getOutputStream() ;
					out.write( 8 );
					String name = appBox.getSelectedItem().toString() ;
					byte data[] = name.getBytes() ;
					out.write( data.length );
					out.write( data );
					out.flush() ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if ( banFlag ) {
				
			}
			banFlag = !banFlag ;
			reflushFunction() ;
		}
	}
	
	/**
	 * ����Զ�̼����
	 */
	public void obPC() {
		if( socket != null && !socket.isClosed() ) {
			try {
				new Thread( new Runnable() {
					@Override
					public void run() {
						if( obFlag ){
							obFlag = false ;
						}else {
							obFlag = true ;
						}
						reflushFunction() ;
						while ( obFlag ) {
							try {
								out = socket.getOutputStream() ;
								out.write( 5 ) ;
								out.flush() ;
								in = socket.getInputStream() ;
								BufferedImage image = ImageIO.read( in ) ;
								imageJPanel.setImage( image ) ;
								in.read( new byte[ in.available() ] ) ;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								showMessage( "��ֹ�����⼦����ָ���ʧ�ܣ�\n" + e.getMessage() ) ;
							}				
						}		
					}
				}).start();	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				showMessage( "����⼦ָ���ʧ�ܣ�\n" + e.getMessage() ) ;
			}
		}
	}

	/**
	 * Զ��ң�ؼ��������ָ��ý���ļ�
	 */
	public void autoPlayPC() {
		if( socket != null && !socket.isClosed() ) {
			if ( !autoFlag ) {
			}else if (autoFlag) {
				
			}
			autoFlag = !autoFlag ;
			reflushFunction() ;
		}
	}
	
	/**
	 * Զ�̲�������������ļ�
	 */
	public void fileManager() {
		if( socket != null && !socket.isClosed() ) {
			if ( !fileFlag ) {
			}else if (fileFlag) {
				
			}
			fileFlag = !fileFlag ;
			reflushFunction() ;
		}
	}
}
