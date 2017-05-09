package msd.user.inf.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import com.toedter.calendar.JYearChooser;
/**
 * This class creates the panel which has the following input fields:
 * 1) Committees
 * 2) Year range
 */
public class CommitteePanel {
	JPanel panel = new JPanel();
	JComboBox<String> journalBox = new JComboBox<String>();


	JYearChooser fromYear = new JYearChooser();
	JYearChooser toYear = new JYearChooser();


	public CommitteePanel() {
		panel.setBorder(null);
		panel.setPreferredSize(new Dimension(500, 350));
		panel.setLayout(null);
		panel.setBackground(new Color(250, 240, 230));
		Label journalLabel = new Label("Conference");
		journalLabel.setAlignment(Label.CENTER);
		journalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		journalLabel.setBounds(27, 139, 183, 24);
		panel.add(journalLabel);

		Label fromToDateLabel = new Label("From - To Year");
		fromToDateLabel.setAlignment(Label.CENTER);
		fromToDateLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		fromToDateLabel.setBounds(27, 217, 155, 24);
		panel.add(fromToDateLabel);
		journalBox.setFont(new Font("Dialog", Font.BOLD, 15));
		journalBox.setBackground(new Color(255, 255, 255));

		panel.add(journalBox);
		journalBox.addItem("ecoop");						
		journalBox.addItem("oopsla");
		journalBox.addItem("icfp");
		journalBox.addItem("pldi");
		journalBox.addItem("ppopp");
		journalBox.addItem("popl");
		journalBox.addItem("issta");
		journalBox.addItem("ismm");
		journalBox.addItem("icse");
		journalBox.addItem("esop");
		journalBox.addItem("fse");
		journalBox.addItem("ase");
		journalBox.setBounds(216, 134, 169, 29);
		fromYear.getSpinner().setBounds(0, 0, 70, 29);
		panel.add(fromYear);
		fromYear.setYear(2007);
		fromYear.setBounds(216, 217, 70, 29);
		fromYear.setLayout(null);
		JSpinner spinnerF = (JSpinner)fromYear.getSpinner();
		((javax.swing.JTextField)spinnerF.getEditor()).setEditable(false);

		toYear.getSpinner().setBounds(0, 0, 70, 29);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		toYear.setMaximum(year);
		JSpinner spinner = (JSpinner)toYear.getSpinner();
		((javax.swing.JTextField)spinner.getEditor()).setEditable(false);
		panel.add(toYear);
		fromYear.setMaximum(year);
		toYear.setBounds(322, 217, 70, 29);
		toYear.setLayout(null);
		toYear.setMinimum(1954);
		fromYear.setMinimum(1954);
	}

	/*********Getters*********/
	/**
	 * Method to get the panel instance
	 * @return JPanel: the committee panel
	 */
	public JPanel getCommitteePanel() {
		return panel;
	}


	/**
	 *  method to get the journal box component
	 * @return journalBox: the component
	 */
	public JComboBox<String> getJournalBox() {
		return journalBox;
	}

	/**
	 * Method to get the year component
	 * @return fromYear: the component
	 */
	public JYearChooser getFromYear() {
		return fromYear;
	}

	/**
	 * Method to get the year component
	 * @return toYear: the component
	 */
	public JYearChooser getToYear() {
		return toYear;
	}
}
