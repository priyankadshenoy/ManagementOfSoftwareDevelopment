package msd.user.inf.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JYearChooser;
/**
 * This class creates the panel which has the following input fields:
 * 1) Article Count
 * 2) Conferences/Journals
 * 3) Year range
 */
public class ArticlePanel {
	JPanel panel = new JPanel();
	JTextField papersField = new JTextField();
	JComboBox<String> journalBox = new JComboBox<String>();
	JYearChooser toYear = new JYearChooser();
	JYearChooser fromYear = new JYearChooser();
	
	public ArticlePanel() {
		panel.setBorder(null);
		panel.setPreferredSize(new Dimension(500, 350));
		panel.setLayout(null);
		panel.setBackground(new Color(250, 240, 230));
		Label papersLabel = new Label("Number of Articles");
		papersLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		papersLabel.setAlignment(Label.CENTER);
		papersLabel.setBounds(27, 63, 183, 29);
		panel.add(papersLabel);

		Label journalLabel = new Label("Journal / Conference");
		journalLabel.setAlignment(Label.CENTER);
		journalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		journalLabel.setBounds(27, 139, 183, 24);
		panel.add(journalLabel);

		Label fromToDateLabel = new Label("From - To Year");
		fromToDateLabel.setAlignment(Label.CENTER);
		fromToDateLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		fromToDateLabel.setBounds(27, 217, 155, 24);
		panel.add(fromToDateLabel);
		papersField.setFont(new Font("Dialog", Font.BOLD, 15));

	
		papersField.setBounds(216, 63, 169, 29);
		panel.add(papersField);
		journalBox.setFont(new Font("Dialog", Font.BOLD, 15));
		journalBox.setBackground(Color.WHITE);

		panel.add(journalBox);
		journalBox.addItem("None");
		journalBox.addItem("ecoop");						
		journalBox.addItem("oopsla");
		journalBox.addItem("icfp");
		journalBox.addItem("pldi");
		journalBox.addItem("toplas");
		journalBox.addItem("jacm");
		journalBox.addItem("ieeesp");
		journalBox.addItem("tse");
	
		
		journalBox.setBounds(216, 134, 169, 29);
		fromYear.getSpinner().setBounds(0, 0, 70, 29);
		fromYear.getSpinner().setFont(new Font("Dialog", Font.BOLD, 15));
		panel.add(fromYear);
		fromYear.setYear(2007);
		fromYear.setBounds(216, 217, 70, 29);
		fromYear.setLayout(null);
		JSpinner spinnerF = (JSpinner)fromYear.getSpinner();
		((javax.swing.JTextField)spinnerF.getEditor()).setEditable(false);
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		toYear.getSpinner().setBounds(0, 0, 70, 29);
		JSpinner spinner = (JSpinner)toYear.getSpinner();
		((javax.swing.JTextField)spinner.getEditor()).setEditable(false);
		toYear.getSpinner().setFont(new Font("Dialog", Font.BOLD, 15));
		panel.add(toYear);
		toYear.setMaximum(year);
		toYear.setMinimum(1954);
		fromYear.setMinimum(1954);
		fromYear.setMaximum(year);
		toYear.setBounds(322, 217, 70, 29);
		
		toYear.setLayout(null);
	}

	/************Getters*********/
	
	
	/**
	 * Method to get this panels instance
	 * @return JPanel: the article panel
	 */
	public JPanel getArticlePanel() {
		return panel;
	}
	/**
	 * Method to get the papers
	 * @return papersFeild: the text feild
	 */
	public JTextField getPapersField() {
		return papersField;
	}
	/**
	 * Method to get the component
	 * @return journalBox: the component
	 */
	public JComboBox<String> getJournalBox() {
		return journalBox;
	}
	/**
	 * Method to get the component
	 * @return toYear: the year
	 */
	public JYearChooser getToYear() {
		return toYear;
	}
	/**
	 * Method to get the component
	 * @return fromYear: the year
	 */
	public JYearChooser getFromYear() {
		return fromYear;
	}
}
