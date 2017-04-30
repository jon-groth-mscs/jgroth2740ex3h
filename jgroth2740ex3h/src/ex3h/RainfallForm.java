package ex3h;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class RainfallForm extends JFrame {

	private JPanel contentPane;
	private JList rainfallList;
	private JLabel totalLabel;
	private JLabel averageLabel;
	private JLabel maximumLabel;
	private JLabel minimumLabel;
	private JButton calculateButton;
	private JTextField inputMonthTextField;
	private JButton updateButton;
	private String [] strRainfall = {
			"1.2", "2.7", "2.2", "3.1", "2.9", "5.1",
			"3.2", "2.7", "3.6", "1.8", "2.2", "1.7"};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RainfallForm frame = new RainfallForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RainfallForm() {
		setTitle("JGroth 2740 Ex2H Rainfall");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMonthlyRainfall = new JLabel("Monthly Rainfall:");
		lblMonthlyRainfall.setBounds(10, 11, 137, 20);
		contentPane.add(lblMonthlyRainfall);
		
		JList monthList = new JList();
		monthList.setBackground(UIManager.getColor("Button.background"));
		monthList.setEnabled(false);
		monthList.setModel(new AbstractListModel() {
			String[] values = new String[] {"01 Jan", "02Feb", "03 Mar", "04 Apr", "05 May", "06 Jun", "07 Jul", "08 Aug", "09 Sep", "10 Oct", "11 Nov", "12 Dec"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		monthList.setBounds(20, 42, 54, 228);
		contentPane.add(monthList);
		
		rainfallList = new JList(strRainfall);
		rainfallList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				do_RainfallList_valueChanged(arg0);
			}
		});
		rainfallList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rainfallList.setBounds(81, 42, 66, 228);
		contentPane.add(rainfallList);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(183, 55, 82, 14);
		contentPane.add(lblTotal);
		
		JLabel lblAverage = new JLabel("Average:");
		lblAverage.setBounds(183, 97, 82, 14);
		contentPane.add(lblAverage);
		
		JLabel lblHighest = new JLabel("Maximum:");
		lblHighest.setBounds(183, 137, 82, 14);
		contentPane.add(lblHighest);
		
		JLabel lblLowest = new JLabel("Minimum:");
		lblLowest.setBounds(183, 176, 82, 14);
		contentPane.add(lblLowest);
		
		totalLabel = new JLabel("0.0");
		lblTotal.setLabelFor(totalLabel);
		totalLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalLabel.setBounds(275, 48, 54, 28);
		contentPane.add(totalLabel);
		
		averageLabel = new JLabel("0.0");
		lblAverage.setLabelFor(averageLabel);
		averageLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		averageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		averageLabel.setBounds(275, 90, 54, 28);
		contentPane.add(averageLabel);
		
		maximumLabel = new JLabel("0.0");
		lblHighest.setLabelFor(maximumLabel);
		maximumLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		maximumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		maximumLabel.setBounds(275, 130, 54, 28);
		contentPane.add(maximumLabel);
		
		minimumLabel = new JLabel("0.0");
		lblLowest.setLabelFor(minimumLabel);
		minimumLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		minimumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		minimumLabel.setBounds(275, 169, 54, 28);
		contentPane.add(minimumLabel);
		
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_calculateButton_actionPerformed(arg0);
			}
		});
		calculateButton.setBounds(257, 218, 97, 29);
		contentPane.add(calculateButton);
		
		inputMonthTextField = new JTextField();
		inputMonthTextField.setText("0.0");
		inputMonthTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		inputMonthTextField.setBounds(81, 282, 66, 20);
		contentPane.add(inputMonthTextField);
		inputMonthTextField.setColumns(10);
		
		updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_updateButton_actionPerformed(e);
			}
		});
		updateButton.setBounds(70, 313, 89, 23);
		updateButton.setEnabled(false);
		contentPane.add(updateButton);
	}
	protected void do_calculateButton_actionPerformed(ActionEvent arg0) {
		Rainfall rainfall = new Rainfall(strRainfall);
		
		DecimalFormat fmt = new DecimalFormat("0.0");
		totalLabel.setText(fmt.format(rainfall.getTotal()));
		averageLabel.setText(fmt.format(rainfall.getAverage()));
		maximumLabel.setText(fmt.format(rainfall.getHighest()));
		minimumLabel.setText(fmt.format(rainfall.getLowest()));
	}
	
	protected void do_updateButton_actionPerformed(ActionEvent e) {
		int selectedIndex = rainfallList.getSelectedIndex();
		double r = Double.parseDouble(inputMonthTextField.getText());
		strRainfall[selectedIndex] = Double.toString(r);
		rainfallList.repaint();
		
		inputMonthTextField.setText("0.0");
		updateButton.setEnabled(false);
		totalLabel.setText("");
		averageLabel.setText("");
		maximumLabel.setText("");
		minimumLabel.setText("");
	}
	
	protected void do_RainfallList_valueChanged(ListSelectionEvent arg0) {
		updateButton.setEnabled(true);
		inputMonthTextField.setText((String) rainfallList.getSelectedValue());
		inputMonthTextField.requestFocus();
		inputMonthTextField.selectAll();
	}
}
