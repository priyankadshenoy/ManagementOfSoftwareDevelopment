package msd.user.inf.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * This class creates the panel which has the following input fields:
 * 1) Author name
 * 2) Field of study
 * 3) University
 * 4) Region
 * 5) Link to homepage
 */
public class AuthorDetailsPanel extends JFrame{
	/**
	 * This class is used to display the author information 
	 * from the csrankings when a particular author is
	 * selected from the search results
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel = new JPanel();
	JLabel nameLabel;
	JLabel getNameVal;
	JLabel univLabel;
	JLabel getUnivVal;
	JLabel regionLabel;
	JLabel getRegionVal;
	JLabel feildLabel;
	JLabel getFeildVal;
	JLabel homePageLabel;
	JLabel getLinkVal;
	
	public AuthorDetailsPanel() {
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
		this.setVisible(true);
		this.setDefaultCloseOperation(AuthorDetailsPanel.DISPOSE_ON_CLOSE);
		JScrollPane jsp = new JScrollPane(panel);
		this.setContentPane(jsp);
		this.setBounds(350, 50, 750, 550);
		panel.setBackground(new Color(253, 245, 230));
		panel.setBorder(null);
		panel.setPreferredSize(new Dimension(700, 500));
		panel.setLayout(null);

		nameLabel = new JLabel("Author Name");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		nameLabel.setBounds(132, 96, 154, 50);
		panel.add(nameLabel);
		
		getNameVal = new JLabel("n");
		getNameVal.setFont(new Font("Dialog", Font.BOLD, 14));
		getNameVal.setBounds(321, 94, 222, 56);
		panel.add(getNameVal);
		
		
		univLabel = new JLabel("University");
		univLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		univLabel.setBounds(166, 154, 107, 24);
		panel.add(univLabel);
		
		getUnivVal = new JLabel("Not Found");
		getUnivVal.setFont(new Font("Dialog", Font.BOLD, 14));
		getUnivVal.setBounds(321, 149, 298, 36);
		panel.add(getUnivVal);
		
		regionLabel = new JLabel("Region");
		regionLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		regionLabel.setBounds(190, 313, 83, 42);
		panel.add(regionLabel);

		getRegionVal = new JLabel("Not Found");
		getRegionVal.setFont(new Font("Dialog", Font.BOLD, 14));
		getRegionVal.setBounds(321, 322, 135, 27);
		panel.add(getRegionVal);
		
		feildLabel = new JLabel("Feild Of Study");
		feildLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		feildLabel.setBounds(132, 271, 154, 24);
		panel.add(feildLabel);
				
		getFeildVal = new JLabel("Not Found");
		getFeildVal.setFont(new Font("Dialog", Font.BOLD, 14));
		getFeildVal.setBounds(321, 259, 267, 50);
		panel.add(getFeildVal);
		
		homePageLabel = new JLabel("Link to homepage");
		homePageLabel.setFont(new Font("Dialog", Font.BOLD, 17));
		homePageLabel.setBounds(101, 213, 185, 24);
		panel.add(homePageLabel);
				
		getLinkVal = new JLabel("Not Found");
		getLinkVal.setFont(new Font("Dialog", Font.BOLD, 14));
		getLinkVal.setBounds(321, 208, 334, 36);
		panel.add(getLinkVal);
		
		JLabel lblNewLabel = new JLabel("Author Details");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(46, 139, 87));
		lblNewLabel.setBounds(216, 44, 257, 24);
		panel.add(lblNewLabel);
		
	}
	
	/*** Setters ****/
	/**
	 * Method to set the authors name
	 * @param getNameVal: the name to set
	 */
	public void setNameVal(String getNameVal) {
		this.getNameVal.setText(getNameVal);
	}

	/**
	 * Method to get authors university
	 * @param getUnivVal: the university of the author
	 */
	public void setUnivVal(String getUnivVal) {
		this.getUnivVal.setText(getUnivVal);
	}

	/**
	 * Method to get authors region
	 * @param getRegionVal: the region of the author
	 */
	public void setRegionVal(String getRegionVal) {
		this.getRegionVal.setText(getRegionVal);
	}
	

	/**
	 * Method to set the authors Feild
	 * @param getFeildVal: the region of the author
	 */
	public void setFeildVal(String getFeildVal) {
		this.getFeildVal.setText(getFeildVal);
	}
	
	/**
	 * Method to set the author's homepage link
	 * @param getLinkVal: the authors homepage link
	 */
	public void setLinkVal(String getLinkVal) {
		this.getLinkVal.setText("<html><a href=\"\">"+getLinkVal+"</a></html>");
		goWebsite(this.getLinkVal, getLinkVal);
	}
	
	/**
	 * Method to create the JLabel into a working HTML link
	 * and naviage to author's homepage
	 * @param website: the label that contains the url
	 * @param url: the url
	 */
	private void goWebsite(JLabel website, String url) {
        website.setCursor(new Cursor(Cursor.HAND_CURSOR));
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    try {
                            Desktop.getDesktop().browse(new URI(url));
                    } catch (URISyntaxException | IOException ex) {
                            //It looks like there's a problem
                    }
            }
        });
    }
}
