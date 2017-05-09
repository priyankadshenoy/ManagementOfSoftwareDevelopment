package msd.user.inf.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * This class is used to launch the application 
 * with the login page being the first frame *
 */

public class Login extends JFrame{

	private static final long serialVersionUID = 1L;
	private JFrame loginFrame;
	private JTextField userField;
	private JPasswordField pwd;
	JPanel panel;
	/**
	 * Launch the application.
	 */

	public static MongoDatabase database;
	public static MongoClient mongoClient;
	private JLabel lblTheDblpExaminer;
	private JLabel lblLogo;
	private JLabel lblLoginToContinue;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		/**
		 * Initialize the contents of the frame.
		 */
		
		// UI components
		loginFrame = new JFrame();
		panel = new JPanel();
		
		loginFrame.setContentPane(panel);
		panel.setBackground(new Color(253, 245, 230));
		loginFrame.setVisible(true);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		loginFrame.setSize(screenSize.width, screenSize.height);
		JLabel userName = new JLabel("User Name :");
		userName.setFont(new Font("Dialog", Font.BOLD, 20));
		userName.setBounds(736, 502, 183, 15);
		loginFrame.getContentPane().add(userName);
		lblLogo = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("image/dblp-5.png"))));
		lblLogo.setForeground(new Color(46, 139, 87));
		lblLogo.setBounds(720, 149, 467, 109);
		panel.add(lblLogo);
		lblTheDblpExaminer = new JLabel("The DBLP Examiner Tool");
		lblTheDblpExaminer.setFont(new Font("Dialog", Font.BOLD, 24));
		lblTheDblpExaminer.setForeground(new Color(46, 139, 87));
		lblTheDblpExaminer.setBounds(802, 238, 467, 109);
		panel.add(lblTheDblpExaminer);

		JLabel password = new JLabel(" Password :");
		password.setFont(new Font("Dialog", Font.BOLD, 20));
		password.setBounds(742, 651, 177, 15);
		loginFrame.getContentPane().add(password);

		userField = new JTextField();
		userField.setBounds(937, 498, 266, 27);
		loginFrame.getContentPane().add(userField);
		userField.setColumns(10);

		pwd = new JPasswordField();
		pwd.setBounds(937, 647, 266, 27);
		loginFrame.getContentPane().add(pwd);
		
		// on submit check if username and password are admin
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 17));
		btnSubmit.setBackground(new Color(60, 179, 113));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				if(userField.getText().equals("admin") && pwd.getText().equals("admin"))
				{
					// set DB connection
					loginFrame.setVisible(false);		
					mongoClient = new MongoClient(new MongoClientURI( "mongodb://phase4:phase4@ds161950.mlab.com:61950/phase4"));
					database = mongoClient.getDatabase( "phase4" );
					new SearchPanel();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid credentials");
				}

			}
		});
		btnSubmit.setBounds(897, 770, 134, 25);
		loginFrame.getContentPane().add(btnSubmit);
		
		lblLoginToContinue = new JLabel("Login to continue");
		lblLoginToContinue.setForeground(new Color(128, 128, 128));
		lblLoginToContinue.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLoginToContinue.setBounds(897, 334, 201, 40);
		panel.add(lblLoginToContinue);
	}
}
