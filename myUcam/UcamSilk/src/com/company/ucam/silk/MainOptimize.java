package com.company.ucam.silk;
// u:\\ucamusers\java\\ucam\home\Workspace\UOptimize\src\myMain.java 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.barco.ets.ucam.hypertool.Uape;
import com.barco.ets.ucam.hypertool.Uextlayer;
import com.barco.ets.ucam.hypertool.Ujob;
import com.barco.ets.ucam.hypertool.Ulayer;
import com.barco.ets.ucam.hypertool.Utxtape;

public class MainOptimize extends JPanel implements ActionListener {

	protected static final String textFieldString1 = "Job number first part";
	protected static final String textFieldString2 = "Job number second part";

	protected static final String passwordFieldString = "JPasswordField";
	protected static final String ftfString = "JFormattedTextField";
	protected static final String buttonString = "JButton";

	protected JLabel actionLabel;
	protected JTextArea textArea;
	private JTextField textField1;
	private JTextField textField2;
	protected String jobNumber;

	public MainOptimize() {
		setLayout(new BorderLayout());

		Color bg = new Color(249, 249, 249);
		Color fg = new Color(0, 0, 0);

		//Create a regular text field.
		textField1 = new JTextField(10);
		textField1.setFont(new Font("Monospaced", Font.BOLD, 24));
		textField1.setBackground(bg);
		textField1.setBorder(BorderFactory.createTitledBorder("customer"));
		// textField1.setActionCommand(textFieldString1);
		// textField1.addActionListener(this);

		textField2 = new JTextField(10);
		textField2.setFont(new Font("Monospaced", Font.BOLD, 24));
		textField2.setBackground(bg);
		textField2.setBorder(BorderFactory.createTitledBorder("number"));
		// textField2.setActionCommand(textFieldString2);
		// textField2.addActionListener(this);

		registerEventHandlers();

		//Create a label to put messages during an action event.
		actionLabel = new JLabel("Type both parts \nof a job number.");
		actionLabel.setFont(new Font("Verdana", Font.BOLD, 14));
		actionLabel.setBackground(bg);
		actionLabel.setBorder(BorderFactory.createTitledBorder("Label"));

		// actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		//Lay out the text controls and the labels.
		JPanel textControlsPane = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		textControlsPane.setLayout(gridbag);
		textControlsPane.add(textField1, gbc);
		textControlsPane.add(textField2, gbc);

		gbc.gridwidth = GridBagConstraints.LINE_START; //last
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 1.0;
		textControlsPane.add(actionLabel, gbc);
		Border title1 = BorderFactory.createTitledBorder("Job number");
		Border kindOfBorder1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder1 = BorderFactory.createCompoundBorder(title1, kindOfBorder1);
		textControlsPane.setBorder(compoundBorder1);

		//Create a text area.
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setForeground(fg);
		textArea.setBackground(bg);

		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(550, 250));
		Border title2 = BorderFactory.createTitledBorder("Drillpath info");
		Border kindOfBorder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		// Border compoundBorder2 = BorderFactory.createCompoundBorder(
		// BorderFactory.createCompoundBorder(title2, kindOfBorder2), areaScrollPane.getBorder());
		Border compoundBorder2 = BorderFactory.createCompoundBorder(title2, kindOfBorder2);
		areaScrollPane.setBorder(compoundBorder2);

		//Put everything together.
		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.add(textControlsPane, BorderLayout.PAGE_START);
		leftPane.add(areaScrollPane, BorderLayout.CENTER);
		add(leftPane, BorderLayout.LINE_START);

		// -----------------------------

	}

	private void calculateDrillTime(String cust, String custno) {

		String jobNumber = cust + "-" + custno;
		String fileName = "u:\\jobs\\" + jobNumber + "\\" + jobNumber + "_p.job";
		textArea.append(fileName + "\n");

		Ujob ujob;

		// Log4Ucam mylogger = Log4Ucam.getLogger();

		// String fileName = "u:\\jobs\\201-11509\\201-11509_p.job";
		// String fileName = "u:\\jobs\\524-621\\524-621_p.job";

		ujob = Ujob.cO.read(fileName);

		if (ujob == null) {
			textArea.append("Error: Invalid job file: " + fileName + "\n");
			return;
		}

		else {
			actionLabel.setText("calculating..");
		}

		// Ulayer dr = (Ulayer) ujob.getlayer("drill", "drill", 1);

		Ulayer dr = ujob.getlayerbyname("dr_p");
		dr.setactive(true); // uaktywnij przed optymalizacja
		dr.block_expand();

		ujob.drill_thermal_optimise(1, 4, "all", 1, 1, 1, 10000000); // pieknie dziala
		// ujob.drill_optimise(1, 4, "all"); // tworzy warstwe dro (aktywna)
		dr.setactive(false);

		Uextlayer uextra = null;
		int layNumber;
		int j1 = ujob.numlayers("extra");
		for (layNumber = 1; layNumber <= j1; layNumber++) {
			uextra = (Uextlayer) ujob.getlayer("extra", null, layNumber);
			if (uextra.subclass().equals("dro") && uextra.name().endsWith("_dro")) {
				System.out.println("dro exists. layer number: " + layNumber);
				break;
			}
		}

		int[] myArray = new int[20];

		Uape myApe;
		Double myDouble;
		int napes = uextra.numapes();
		for (int n = 1; n <= napes; n++) {
			myApe = uextra.getape(n);
			if (myApe.is_it("TXT")) {
				Utxtape myUtxtape = (Utxtape) uextra.getape(n);
				String myString = myUtxtape.string();
				if (myString.endsWith("MM")) {
					// System.out.println(myString);
					// textArea.append(myString);

					int elif = myString.lastIndexOf(" MM");
					int blif = myString.lastIndexOf(" ", elif - 1);
					String myValue = myString.substring(blif + 1, elif);
					Integer myInt = Integer.valueOf(myValue);
					textArea.append("path: " + myInt + " mm\t");

					elif = myString.lastIndexOf(" MM", blif);
					blif = myString.lastIndexOf(" ", elif - 1);
					myValue = myString.substring(blif + 1, elif);
					myInt = Integer.valueOf(myValue);
					textArea.append("pads: " + myInt + "\t");

					elif = myString.lastIndexOf(" MM", blif);
					blif = myString.lastIndexOf(" ", elif - 1);
					if (blif != -1 || elif != -1) {
						myValue = myString.substring(blif + 1, elif);
						myDouble = Double.valueOf(myValue);
					} else {
						myDouble = 0.0;
					}
					textArea.append("tool: " + myDouble + " mm \n");
				}
			}
		}
	}

	public String getTextField1() {
		String text = new String(textField1.getText());
		return text;
	}

	public String getTextField2() {
		String text = new String(textField2.getText());
		return text;
	}

	private void registerEventHandlers() {
		textField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// JTextField source = (JTextField) event.getSource();
				// actionLabel.setText(source.getText());
				String cust = getTextField1();
				if (cust.equals("")) {
					actionLabel.setText("cust empty");
				} else {
					String custno = getTextField2();
					if (!custno.equals("")) {
						calculateDrillTime(cust, custno);
					} else {
						actionLabel.setText("number empty");
					}
				}
			}
		});

		textField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custno = getTextField2();
				if (custno.equals("")) {
					actionLabel.setText("number empty");
				} else {
					String cust = getTextField1();
					if (!cust.equals("")) {
						calculateDrillTime(cust, custno);
					} else {
						actionLabel.setText("cust empty");
					}
				}
			}
		});
	}

	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("Drillpath calc");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JFrame frame = (JFrame) e.getSource();
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});

		//Add content to the window.
		frame.add(new MainOptimize());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public void main() {
		//Schedule a job for the event dispatching thread:
		//creating and showing this application's GUI.

		// UcamMain.setup();
		// UcamMain.loadLibraries(); // zaladuj deelelke

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}