package msd.user.inf.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JYearChooser;
/**
 * This class creates the panel which has the following input fields:
 * 1) Citation Count
 * 2) Title Keyword
 * 3) Year range
 */
public class CitationPanel {
	JPanel panel = new JPanel();
	JTextField citationField = new JTextField();
	JYearChooser toYear = new JYearChooser();
	JYearChooser fromYear = new JYearChooser();
	private JTextField titleField;

	/**
	 * Constructor that creates the panel
	 */
	public CitationPanel() {
		panel.setBorder(null);
		panel.setPreferredSize(new Dimension(500, 350));
		panel.setLayout(null);
		panel.setBackground(new Color(250, 240, 230));
		Label citationLabel = new Label("Number of Citations");
		citationLabel.setAlignment(Label.CENTER);
		citationLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		citationLabel.setBounds(27, 139, 183, 24);
		panel.add(citationLabel);
		Label titleLabel = new Label("Title Search");
		titleLabel.setAlignment(Label.CENTER);
		titleLabel.setBounds(27, 63, 120, 29);
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(titleLabel);

		Label fromToDateLabel = new Label("From - To Year");
		fromToDateLabel.setAlignment(Label.CENTER);
		fromToDateLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		fromToDateLabel.setBounds(27, 217, 155, 24);
		panel.add(fromToDateLabel);

		titleField = new JTextField();
		titleField.setFont(new Font("Dialog", Font.BOLD, 15));
		titleField.setBounds(216, 63, 169, 29);
		panel.add(titleField);
		titleField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Each keyword should be separated by comma");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(53, 98, 347, 15);
		panel.add(lblNewLabel);
		citationField.setText("0");
		citationField.setFont(new Font("Dialog", Font.BOLD, 15));

		citationField.setBounds(216, 134, 169, 29);
		panel.add(citationField);
		fromYear.getSpinner().setFont(new Font("Dialog", Font.BOLD, 15));
		panel.add(fromYear);
		fromYear.setYear(2007);
		fromYear.setBounds(216, 217, 70, 29);
		JSpinner spinnerF = (JSpinner)fromYear.getSpinner();
		((javax.swing.JTextField)spinnerF.getEditor()).setEditable(false);
		toYear.getSpinner().setFont(new Font("Dialog", Font.BOLD, 15));

		panel.add(toYear);
		toYear.setBounds(322, 217, 70, 29);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		toYear.setMaximum(year);
		fromYear.setMaximum(year);
		toYear.setMinimum(1954);
		fromYear.setMinimum(1954);
		JSpinner spinner = (JSpinner)toYear.getSpinner();
		((javax.swing.JTextField)spinner.getEditor()).setEditable(false);
	}

	/**** Getters*****/
	/**
	 * Method to get the panel instance
	 * @return JPanel: the citation panel
	 */
	public JPanel getCitationPanel() {
		return panel;
	}

	/**
	 * Method to get the component
	 * @return titleField: the component
	 */
	public JTextField getTitleField() {
		return titleField;
	}
	/**
	 * Method to get the component
	 * @return citationField: the component
	 */
	public JTextField getCitationField() {
		return citationField;
	}
	/**
	 * Method to get the component
	 * @return toYear: the component
	 */
	public JYearChooser getToYear() {
		return toYear;
	}
	/**
	 * Method to get the component
	 * @return fromYear: the component
	 */
	public JYearChooser getFromYear() {
		return fromYear;
	}
}
