// u:\\ucamusers\java\\ucam\home\workspace\UDrillTime_standalone\src
// numer wersji tutaj: createAndShowGUI()

// Exception in thread "main" java.lang.UnsatisfiedLinkError: no ucam in java.library.path
// PATH C:\mb\Ucam92;c:\mb\Ucam92\bin;c:\mb\Ucam92\classes

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

import com.barco.ets.ucam.hypertool.UcamMain;

public class MainGUI extends JPanel implements ActionListener {

	static final String progVersion = "v2.71";
	protected static final String textFieldString1 = "Job number first part";
	protected static final String textFieldString2 = "Job number second part";

	protected JLabel actionLabel;
	protected JTextArea textArea;
	private JTextField textField1;
	private JTextField textField2;
	private Calculate calc;

	public MainGUI() {

		// to jest constructor?? dziwnie dlugi. kam-aan.
		setLayout(new BorderLayout());

		Color bg = new Color(249, 249, 249);
		Color fg = new Color(0, 0, 0);

		//Create a regular text field.
		textField1 = new JTextField(10);
		textField1.setFont(new Font("Monospaced", Font.BOLD, 24));
		textField1.setBackground(bg);
		textField1.setBorder(BorderFactory.createTitledBorder("Customer"));

		textField2 = new JTextField(10);
		textField2.setFont(new Font("Monospaced", Font.BOLD, 24));
		textField2.setBackground(bg);
		textField2.setBorder(BorderFactory.createTitledBorder("Number"));

		//Create a label to put messages during an action event.
		actionLabel = new JLabel("");

		Dimension preferredSize = new Dimension(220, 60);
		actionLabel.setPreferredSize(preferredSize);
		actionLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
		actionLabel.setBackground(bg);
		actionLabel.setBorder(BorderFactory.createTitledBorder("Approx. drill time"));

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
		areaScrollPane.setPreferredSize(new Dimension(550, 350));
		Border title2 = BorderFactory.createTitledBorder("Drillpath info");
		Border kindOfBorder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border compoundBorder2 = BorderFactory.createCompoundBorder(title2, kindOfBorder2);
		areaScrollPane.setBorder(compoundBorder2);

		//Put everything together.
		JPanel leftPane = new JPanel(new BorderLayout());
		leftPane.add(textControlsPane, BorderLayout.PAGE_START);
		leftPane.add(areaScrollPane, BorderLayout.CENTER);
		add(leftPane, BorderLayout.LINE_START);

		this.calc = new Calculate(textArea, actionLabel);		// uchwyt do klasy i inicjalizacja
		registerEventHandlers();

		// -----------------------------

	}

	public String getTextField1() {
		String text = new String(textField1.getText());
		textField1.selectAll();
		return text;
	}

	public String getTextField2() {
		String text = new String(textField2.getText());
		textField2.selectAll();
		return text;
	}

	private void registerEventHandlers() {
		textField1.selectAll();
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
						calc.calculateDrillTime(cust, custno);
					} else {
						actionLabel.setText("number empty");
					}
				}
			}
		});

		textField2.selectAll();
		textField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custno = getTextField2();
				if (custno.equals("")) {
					actionLabel.setText("number empty");
				} else {
					String cust = getTextField1();
					if (!cust.equals("")) {
						calc.calculateDrillTime(cust, custno);
					} else {
						actionLabel.setText("cust empty");
					}
				}
			}
		});
	}

	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("Drillpath calculator " + progVersion);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				JFrame frame = (JFrame) e.getSource();
				int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit the application?",
						"Exit Application", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		//Add content to the window.
		frame.add(new MainGUI());

		//Display the window.
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		//Schedule a job for the event dispatching thread:
		//creating and showing this application's GUI.

		UcamMain.setup();
//		UcamMain.loadLibraries(); // zaladuj deelelke

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
