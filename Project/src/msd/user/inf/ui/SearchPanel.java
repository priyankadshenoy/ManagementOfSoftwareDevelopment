package msd.user.inf.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.lang3.text.WordUtils;

import msd.queryEngine.QueryFormatter;
import msd.queryEngine.interfaces.OutputFormatter;
import msd.queryEngine.interfaces.QueryGenerator;
import msd.user.inf.AuthorQuery;
import msd.user.inf.UserQuery;
import msd.user.inf.interfaces.InputCapture;

public class SearchPanel extends JFrame {

	/**
	 * The search panel is the one stop search tool for the user.
	 * This panel contains panels:
	 * 1. ArticlePanel: to search for specific article counts
	 * 2. CommitteePanel: to search for committees
	 * 3. CitationPanel: to search for titles and citation counts
	 * Also, once the search is completed, the results are shown in 
	 * this panel
	 * The user can perform the following actions on the search results:
	 * a) view author's details
	 * b) select some authors to view later
	 * c) export the authors list
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel panel_1 ;
	JPanel panel_2;
	private JTable table;
	private JTable table_1;
	ArticlePanel article = new ArticlePanel();
	CommitteePanel committee = new CommitteePanel();
	CitationPanel citation = new CitationPanel();
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	int selectedTab =0;
	JButton btnNewButton;
	String titleSearch="";
	String citationSearch="";
	String articleCount="";
	String conference="" ;
	String committeeS="" ;
	int fromYear ;
	int toYear;
	List<OutputFormatter> outputFormatter;
	Object[] column2Names;
	public static JLabel lblNewLabel;
	JProgressBar progressBar;
	/**
	 * Create the frame.
	 */
	public SearchPanel() {
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

		SwingUtilities.updateComponentTreeUI(this);

		this.pack();
		UIManager.getLookAndFeelDefaults().put("ScrollBar.minimumThumbSize", new Dimension(29, 1));

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.contentPane.add(panel_2, BorderLayout.NORTH);
		this.contentPane.setBackground(new Color(253, 245, 230));
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(250, 240, 230));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_2.setBackground(new Color(253, 245, 230));
		// add the search and the result panels
		this.addToPanel1();
		this.addToPanel2();
		JScrollPane jsp = new JScrollPane(contentPane);
		this.setContentPane(jsp);

	}

	/**
	 * This method adds the search panel.
	 * The search panel is a tabbed panel which has the 
	 * ArticlePanel, CommitteePanel and CitationPanel as its
	 * tabs.
	 */
	public void addToPanel1() {
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(250, 240, 230));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		tabbedPane.setFont(new Font("Dialog", Font.PLAIN, 18));

		tabbedPane.setBackground(new Color(250, 240, 230));
		tabbedPane.setBorder(null);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		panel_3.add(tabbedPane);
		// add all the panels to the tabbed pane
		tabbedPane.addTab("Title & Citation", citation.getCitationPanel());
		tabbedPane.setSelectedIndex(0);
		tabbedPane.addTab("Article ", article.getArticlePanel());
		tabbedPane.addTab("Committee", committee.getCommitteePanel());

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(250, 240, 230));
		panel_2.add(panel_4);
		JButton searchButtons = new JButton("Search");
		searchButtons.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_4.add(searchButtons);
		searchButtons.setVerticalAlignment(SwingConstants.BOTTOM);
		searchButtons.setBackground(new Color(143, 188, 143));
		
		JPanel panel = new JPanel();
		panel_2.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		lblNewLabel = new JLabel("Loading...");
		lblNewLabel.setVisible(false);
		progressBar = new JProgressBar();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//panel.add(lblNewLabel, BorderLayout.CENTER);
		panel.add(progressBar);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(255, 99, 71));
		btnLogout.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_4.add(btnLogout);
		btnLogout.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Login();
			}
		});


		// get the selected tab index to identify which data has been provided
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				selectedTab = tabbedPane.getSelectedIndex();
			}
		});		
		
		// to set the progress bar
		searchButtons.getModel().addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
           
                if (model.isPressed()) {
                	searchButtons.setText("Loading...");
                     progressBar.setVisible(true);
                     progressBar.setIndeterminate(true);

                } else {
                	searchButtons.setText("Search");
                	 progressBar.setIndeterminate(false);
                	progressBar.setValue(0);
                }
            }
        });

		
		// method call to fire the search based on the inputs given
		setSearchCriteria(searchButtons);



	}

	/**
	 * This method adds the result tables to the result panel.
	 */
	public void addToPanel2() {

		// result table panel
		JPanel panel_result = new JPanel();
		panel_result.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Authors Found", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		panel_1.add(panel_result);
		panel_result.setBackground(new Color(250, 240, 230));
		JPanel panel_btn = new JPanel();
		panel_btn.setBackground(new Color(250, 240, 230));
		panel_btn.setBounds(50,50,50,50);
		panel_1.add(panel_btn);
		panel_btn.setLayout(new BorderLayout(0, 0));

		JPanel panel_export = new JPanel();
		panel_btn.add(panel_export, BorderLayout.SOUTH);
		panel_export.setLayout(new BorderLayout(0, 0));

		// the button used to extract authors selected from result table to selection table
		btnNewButton = new JButton(">> Select Authors >>");
		panel_export.add(btnNewButton, BorderLayout.NORTH);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(210, 105, 30));

		JPanel panel_select_auth = new JPanel();
		panel_btn.add(panel_select_auth, BorderLayout.NORTH);
		panel_select_auth.setLayout(new BorderLayout(0, 0));

		JButton button = new JButton("Export Authors");
		panel_select_auth.add(button, BorderLayout.SOUTH);
		button.setFont(new Font("Dialog", Font.BOLD, 15));
		button.setBackground(Color.ORANGE);

		JPanel panel_inter = new JPanel();
		panel_btn.add(panel_inter, BorderLayout.CENTER);
		btnNewButton.addMouseListener(myMouseListener);
		button.addMouseListener(exportMouseListener);
		panel_inter.setBackground(new Color(250, 240, 230));
		// selection table panel
		JPanel panel_selected = new JPanel();
		panel_selected.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Selected Authors", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));
		FlowLayout flowLayout_1 = (FlowLayout) panel_selected.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_selected.setBackground(new Color(250, 240, 230));

		// adding the result table to result panel
		table = new JTable(){
			//Implement table cell tool tips.           
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);

				try {
					tip = getValueAt(rowIndex, colIndex).toString();
				} catch (RuntimeException e1) {
					//catch null pointer exception if mouse is over an empty line
				}

				return tip;
			}
		};
		table.setPreferredScrollableViewportSize(new Dimension(700, 500));
		table.setFillsViewportHeight(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color(192, 192, 192));
		scrollPane.setOpaque(true);
		table.setFont(new Font("Courier 10 Pitch", Font.PLAIN, 16));
		panel_result.add(scrollPane);


		// adding the selected table to the selected panel
		table_1 = new JTable(){ //Implement table cell tool tips.           
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);

				try {
					tip = getValueAt(rowIndex, colIndex).toString();
				} catch (RuntimeException e1) {
					//catch null pointer exception if mouse is over an empty line
				}
				return tip;
			}
		};
		table_1.setPreferredScrollableViewportSize(new Dimension(700, 500));
		table_1.setFillsViewportHeight(false);
		JScrollPane scrollPane2 = new JScrollPane(table_1);
		table_1.setFont(new Font("Courier 10 Pitch", Font.PLAIN, 16));

		panel_selected.add(scrollPane2);
		panel_1.add(panel_selected);
	}

	/**
	 * This method fires the search when the search button is 
	 * pressed by the user. It identifies which tab is active 
	 * at the time of search and creates the query object accordingly.
	 * @param searchButtons : The button that is clicked
	 */
	public void setSearchCriteria(JButton searchButtons) {

		searchButtons.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				/********identify which tab is selected and set data*********/
				checkTabIndexAndSetData();

				/********identify if there are any errors in input data*************/
				boolean exceptionExist = showErrorMessagesIfDataNotExist();

				// if there were no errors in data input then send the input to query engine
				if(!exceptionExist){


					String title = titleSearch.isEmpty() ? "Empty" : titleSearch;
					String articleF = articleCount.isEmpty() ? "-1" : articleCount;
					String citationF = citationSearch.isEmpty() ? "-1" : citationSearch;
					String committeeF="Empty";
					if(!conference.isEmpty())
						committeeF = conference;
					if(!committeeS.isEmpty()) 
						committeeF = committeeS;
					boolean confOrCpm = conference.isEmpty() ? true : false;
					if(fromYear < 2007) {
						fromYear = 1954; 
					}


					// Query engine accepts generic object with all possible fields

					// InputCapture interface is identified by the QueryEngine
					InputCapture userQuery = new UserQuery(title, fromYear, 
							toYear, committeeF, Integer.parseInt(citationF), 
							Integer.parseInt(articleF), confOrCpm);

					// Call the QueryGenerator
					QueryGenerator queryGenerator = new QueryFormatter();

					outputFormatter  = queryGenerator.transformUserQueryObject(userQuery);

					// Query Engine returns the result set
					if(outputFormatter == null || outputFormatter.isEmpty()){
						JOptionPane.showMessageDialog(null, "Empty resultset, please widen search criteria" );
					}
					/*****Method to fill the result table with the results****/
					fillTable();
				}
				// if the result set is empty then show an error dialog
				else if(!exceptionExist && outputFormatter == null) {
					JOptionPane.showMessageDialog(null, "Empty resultset, please widen search criteria" );
				}
			}
		});
	}

	/**
	 * This method fills the result table with the result set 
	 * returned by the QueryEngine
	 */
	public void fillTable() {
		String col="";
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// if the result set was not empty then get all the column names
		if(outputFormatter != null) {
			for(OutputFormatter columns : outputFormatter){
				col= columns.columnsToString();
				break;
			}
		}

		// set columns for result table
		Object[] columnNames = (col+",View").split(",");

		DefaultTableModel tableModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			public Class getColumnClass(int columnIndex) {
				if(this.getColumnName(columnIndex).equalsIgnoreCase("CitationCount") || this.getColumnName(columnIndex).equalsIgnoreCase("Year")
						|| (this.getColumnName(columnIndex).equalsIgnoreCase("ArticleCount"))){
					return Integer.class;
				}
				return String.class; 
			}

			public boolean isCellEditable(int row, int column) {
				if(column < this.getColumnCount()-1)
					return false;
				else return true;
			}
		};
		table.setModel(tableModel);
		table.setAutoCreateRowSorter(true);
		DefaultTableModel tableModel2 = new DefaultTableModel(){
			@Override
			public Class getColumnClass(int columnIndex) {
				if(this.getColumnName(columnIndex).equalsIgnoreCase("Count")|| this.getColumnName(columnIndex).equalsIgnoreCase("Year")){
					return Integer.class;
				}
				return String.class; 
			}

			public boolean isCellEditable(int row, int column) {
				if(column < this.getColumnCount()-1)
					return false;
				else return true;
			}
		};


		table_1.setModel(tableModel2);
		table_1.setAutoCreateRowSorter(true);
		DefaultTableModel model_1 = (DefaultTableModel)table.getModel();
		DefaultTableModel model_2 = (DefaultTableModel)table_1.getModel();
		model_1.setColumnCount(0);
		model_1.setNumRows(0);
		for(Object c: columnNames) {
			model_1.addColumn(c.toString());
		}
		for(int i =0; i<columnNames.length-1; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
		table.getColumnModel().getColumn(columnNames.length-1).setMaxWidth(70);

		// set columns for selection table
		column2Names = new Object[(col+",Remove").split(",").length];
		column2Names = (col+",Remove").split(",");
		for(Object c: column2Names) {
			model_2.addColumn(c.toString());
		}
		for(int i =0; i<column2Names.length-1; i++) {
			table_1.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
		}
		table_1.getColumnModel().getColumn(column2Names.length-1).setMaxWidth(120);
		table_1.getColumnModel().getColumn(column2Names.length-1).setPreferredWidth(150);


		// set data according to the data fetched by QE


		if(columnNames.length==4){
			// for conference name
			if(columnNames[0].toString().equalsIgnoreCase("Title")) {
				for(OutputFormatter ofv : outputFormatter){
					Object[] data = {ofv.getTitle(), ofv.getAuthor(), ofv.getYear(), "View"};
					model_1.addRow(data);
				}
			}
			// for article count with conf name and article count alone
			else if(columnNames[0].toString().equalsIgnoreCase("Author")) {
				for(OutputFormatter ofv : outputFormatter){
					Object[] data = {ofv.getAuthor(), ofv.getConferenceName(), ofv.getCount(), "View"};
					model_1.addRow(data);
				}
			}
			// for committee data
			else if(columnNames[0].toString().equalsIgnoreCase("Year")) {
				for(OutputFormatter ofv : outputFormatter){
					Object[] data = {ofv.getYear(), ofv.getMembers(), ofv.getDesignation(), "View"};
					model_1.addRow(data);
				}
			}

		}

		if(columnNames.length ==7){
			for(OutputFormatter ofv : outputFormatter){
				Object[] data = {ofv.getTitle(),ofv.getAuthor(),ofv.getYear(),ofv.getConferenceName(),ofv.getBookTitle(), new Integer(ofv.getCitationCount()), "View"};
				model_1.addRow(data);
			}
		}


		// Set the last column for each table as an actionabble button
		// button that can allow removing the selected row
		ButtonColumn buttonColumn = new ButtonColumn(table_1, delete, column2Names.length-1);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

		// button that can allow to view the author details
		ButtonColumn buttonColumn_view = new ButtonColumn(table, view, columnNames.length-1);
		buttonColumn_view.setMnemonic(KeyEvent.VK_V);
	}

	/**
	 * The custom delete action for the deletion of row
	 */
	Action delete = new AbstractAction()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JTable tableD = (JTable)e.getSource();
			int modelRow = Integer.parseInt( e.getActionCommand() );
			// remove selected row
			((DefaultTableModel)tableD.getModel()).removeRow(modelRow);
		}
	};

	/**
	 * Custom View action to view author details
	 */
	Action view = new AbstractAction()
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JTable tableD = (JTable)e.getSource();
			int modelRow = Integer.parseInt( e.getActionCommand() );
			AuthorDetailsPanel author= new AuthorDetailsPanel();
			Object authorName ;
			if(tableD.getColumnName(1).equalsIgnoreCase("members")){
				authorName = ((DefaultTableModel)tableD.getModel()).getValueAt( modelRow, tableD.getColumn("Members").getModelIndex());

			} else {
				authorName = ((DefaultTableModel)tableD.getModel()).getValueAt( modelRow, tableD.getColumn("Author").getModelIndex());
			}

			// create a user query object and call Query Engine
			InputCapture authorNameQuery = new AuthorQuery(authorName.toString());
			// recieve Arraylist of objects from QueryEngine
			QueryGenerator queryGenerator = new QueryFormatter();
			List<OutputFormatter> auhtorDetails  = queryGenerator.transformUserQueryObject(authorNameQuery);
			for(OutputFormatter data: auhtorDetails) {
				if(data.getRegion() == null){
					author.setRegionVal("Not Found");
				} else{
					author.setRegionVal(WordUtils.capitalize(data.getRegion()));
				}
				if(data.getUniversity()==null){
					author.setUnivVal("Not Found");
				}else {
					author.setUnivVal(WordUtils.capitalize(data.getUniversity()));
				}
				if(data.getHomePage()==null) {
					author.setUnivVal("Not Found");
				} else{
					author.setLinkVal(WordUtils.capitalize(data.getHomePage()));
				}
				if(data.getFieldOfStudy() == null){
					author.setFeildVal("Not Found");
				} else{
					author.setFeildVal(WordUtils.capitalize(data.getFieldOfStudy()));
				}
			}
			author.setNameVal(authorName.toString());
		}
	};


	/**
	 * Custom mouse listener using the Adapter design pattern
	 * This mouse listener performs the exporting functionality 
	 * of the data from the result table to the selection table
	 */
	ExportMouseListener myMouseListener = new ExportMouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			TableModel model_1 = table.getModel();
			int[] indexes = table.getSelectedRows();
			int rowsNumb = model_1.getRowCount();
			if(rowsNumb ==0) {
				JOptionPane.showMessageDialog(null, "Don't have data to export!!" );
			} else if(rowsNumb > 0 && indexes.length ==0) {
				JOptionPane.showMessageDialog(null, "Select some rows to export!" );	
			}
			// get all the rows selected and add to seleciton tables model
			else {
				Object rows[] = new Object[column2Names.length];
				DefaultTableModel modelGet = (DefaultTableModel)table_1.getModel();
				for(int i =0; i< indexes.length; i++){
					for(int j =0; j<column2Names.length-1; j++){
						rows[j] = model_1.getValueAt(indexes[i], j);
					}
					rows[column2Names.length-1] = "Remove";
					modelGet.addRow(rows);
				}
			}
		}
	};

	/**
	 * Custom mouse listener using the Adapter design pattern
	 * This mouse listener performs the exporting functionality 
	 * of the data from the selection table to a CSV document
	 */
	ExportMouseListener exportMouseListener = new ExportMouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			BufferedWriter bfw;
			try {
				File file = new File("SelectedAuthors.csv");
				FileWriter file1 = new FileWriter(file);
				bfw = new BufferedWriter(file1);
				if(table_1.getRowCount() >0 ) {
					for(int i = 0 ; i < table_1.getColumnCount()-1 ; i++)
					{
						bfw.write(table_1.getColumnName(i));
						bfw.write(",");
					}

					for (int i = 0 ; i < table_1.getRowCount(); i++)
					{
						bfw.newLine();
						for(int j = 0 ; j < table_1.getColumnCount()-1;j++)
						{
							if(table_1.getValueAt(i,j) instanceof Integer){
								bfw.write(String.valueOf(table_1.getValueAt(i,j)));
							} else {
								bfw.write(String.valueOf(table_1.getValueAt(i,j)));

							}
							bfw.write(",");
						}
					}
					bfw.close();
					showSaveFileDialog(file);
				}
				else {
					JOptionPane.showMessageDialog(null, "No data to export to file!" );
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}		
		}
	};

	/**
	 * Helper method to show dialog box
	 * @param file
	 */
	private void showSaveFileDialog(File file) {
		JOptionPane.showMessageDialog(null, "Success! File saved at"+ file.getAbsolutePath() );
	}
	/**
	 * Helper method to check which tab is active currently and 
	 * hence which data elements need to be set
	 */
	public void checkTabIndexAndSetData() {
		if(selectedTab == 1 ) {
			committeeS="";
			citationSearch="";
			titleSearch="";
			articleCount = article.getPapersField().getText().trim();
			conference = article.getJournalBox().getSelectedItem().toString();
			if(conference.equals("None"))
				conference="";
			fromYear = article.getFromYear().getYear();
			toYear = article.getToYear().getYear();
		} else if(selectedTab ==2) {
			citationSearch="";
			titleSearch="";
			articleCount="";
			conference="";
			committeeS = committee.getJournalBox().getSelectedItem().toString();
			fromYear = committee.getFromYear().getYear();
			toYear = committee.getToYear().getYear();
		} else {
			articleCount="";
			committeeS="";
			citationSearch = citation.getCitationField().getText().trim();
			titleSearch = citation.getTitleField().getText().trim();
			fromYear = citation.getFromYear().getYear();
			toYear = citation.getToYear().getYear();
		}
	}

	/**
	 * Helper method to identify if the input data is valid 
	 * or not
	 * @return Boolean
	 */
	public boolean showErrorMessagesIfDataNotExist() {
		if(titleSearch.isEmpty() && citationSearch.isEmpty() && articleCount.isEmpty() && conference.isEmpty() && committeeS.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No search criteria applied" );
			return true;
		}
		if(titleSearch.contains("*") || titleSearch.contains("?") 
				|| titleSearch.contains("[") || titleSearch.contains("(")
				|| titleSearch.contains("\\)") || titleSearch.contains("|")
				|| titleSearch.contains("\\") || titleSearch.contains("$")
				|| titleSearch.contains("{") || titleSearch.contains("+")) {
			JOptionPane.showMessageDialog(null, "Keyword cannot contain this character" );
			return true;
		}
		if (fromYear > toYear){
			JOptionPane.showMessageDialog(null, "To year has to be greater than from year" );
			return true;
		}
		if(toYear > 2017) {
			JOptionPane.showMessageDialog(null, "To year cannot be greater than current year" );
			return true;
		}
		if(fromYear < 1970) {
			JOptionPane.showMessageDialog(null, "From year cannot be less than 1970" );
			return true;
		}
		if (!citationSearch.isEmpty()){
			try{
				Integer.parseInt(citationSearch);}
			catch(NumberFormatException exc){
				JOptionPane.showMessageDialog(null, "Please enter a valid number for citation count" );	
				return true;
			}
		}
		if (!articleCount.isEmpty()){				
			try{
				Integer.parseInt(articleCount);
			}
			catch(NumberFormatException exc){
				JOptionPane.showMessageDialog(null, "Please enter a valid number for paper count" );
				return true;
			}
		}

		return false;
	}
}
