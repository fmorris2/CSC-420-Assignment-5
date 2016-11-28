package org;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;

import org.flag.Flag;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -3659990255393448369L;
	
	private JPanel contentPane;
	private CircleCanvas circleCanvas;
	private JSlider horizontalSlider, verticalSlider;
	private JButton displayButton;
	private JList<Flag> countryList;
	private DefaultListModel<Flag> countryListModel;

	public MainFrame()
	{
		setTitle("CSC-420 - Assignment 5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		verticalSlider = new JSlider();
		verticalSlider.setPaintTicks(true);
		verticalSlider.setOrientation(SwingConstants.VERTICAL);
		verticalSlider.setBounds(10, 11, 31, 336);
		verticalSlider.addChangeListener((ChangeEvent e) -> {verticalChange();});
		contentPane.add(verticalSlider);
		
		horizontalSlider = new JSlider();
		horizontalSlider.setBounds(41, 352, 533, 26);
		horizontalSlider.addChangeListener((ChangeEvent e) -> {horizontalChange();});
		contentPane.add(horizontalSlider);
		
		circleCanvas = new CircleCanvas();
		circleCanvas.setBounds(29, 11, 545, 348);
		contentPane.add(circleCanvas);
		
		JButton colorButton = new JButton("Choose Color");
		colorButton.addActionListener((ActionEvent e) -> {openColorPicker();});
		colorButton.setBounds(139, 389, 141, 61);
		contentPane.add(colorButton);
		
		displayButton = new JButton("Hide");
		displayButton.addActionListener((ActionEvent e) -> {changeDisplay();});
		displayButton.setBounds(330, 389, 141, 61);
		contentPane.add(displayButton);
		
		horizontalChange();
		verticalChange();
	}
	
	private void horizontalChange()
	{
		circleCanvas.calculateX(horizontalSlider.getValue());
		circleCanvas.repaint();
	}
	
	private void verticalChange()
	{
		circleCanvas.calculateY(verticalSlider.getValue());
		circleCanvas.repaint();
	}
	
	private void openColorPicker()
	{
		circleCanvas.setColor(JColorChooser.showDialog(null, "Choose a Color", Color.WHITE));
	}
	
	private void changeDisplay()
	{
		circleCanvas.changeDisplay();
		displayButton.setText(circleCanvas.isDisplayed() ? "Hide" : "Show");	
	}
	
	public void addFlag(Flag f)
	{
		countryListModel.addElement(f);
	}
}
