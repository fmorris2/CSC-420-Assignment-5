package org;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;

import org.flag.Flag;
import org.flag.FlagLoader;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = -3659990255393448369L;
	
	private JPanel contentPane;
	private FlagCanvas flagCanvas;
	private JSlider leftSlider, rightSlider, topSlider, bottomSlider;
	private JScrollPane scrollPane;
	private JList<Flag> countryList;
	private DefaultListModel<Flag> countryListModel;
	private Flag currentFlag;

	public MainFrame()
	{
		setTitle("CSC-420 - Assignment 5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//Layout, column, row constraints
		MigLayout layout = new MigLayout("", "[grow, fill][][]", "[grow, fill][][][]");
		contentPane.setLayout(layout);
		
		leftSlider = new JSlider();
		rightSlider = new JSlider();
		topSlider = new JSlider();
		bottomSlider = new JSlider();
		flagCanvas = new FlagCanvas(this);
		scrollPane = new JScrollPane();
		countryList = new JList<>();
		countryList.addListSelectionListener((ListSelectionEvent e) -> changeFlag(e));
		countryListModel = new DefaultListModel<>();
		
		countryList.setModel(countryListModel);
		scrollPane.setViewportView(countryList);
		new FlagLoader(this).execute();
		
		//connect pairs of sliders
		topSlider.setModel(bottomSlider.getModel());
		leftSlider.setModel(rightSlider.getModel());
		
		//add listeners to sliders
		topSlider.addChangeListener((ChangeEvent e) -> {horizontalChange();});
		leftSlider.addChangeListener((ChangeEvent e) -> {verticalChange();});
		
		leftSlider.setOrientation(SwingConstants.VERTICAL);
		rightSlider.setOrientation(SwingConstants.VERTICAL);
		
		contentPane.add(topSlider, "dock north, wrap");
		contentPane.add(scrollPane, "dock south");
		contentPane.add(bottomSlider, "dock south");
		contentPane.add(leftSlider, "dock west");
		contentPane.add(flagCanvas, "wmin 10, hmin 10");
		contentPane.add(rightSlider, "dock east");
		
		verticalChange();
		horizontalChange();

		pack();
	}
	
	public void changeFlag(ListSelectionEvent e)
	{
		if(currentFlag == null || !currentFlag.equals(countryList.getSelectedValue()))
		{
			currentFlag = countryList.getSelectedValue();
			flagCanvas.repaint();
		}
	}
	
	private void verticalChange()
	{
		flagCanvas.setVerticalSlider(leftSlider.getValue());
		flagCanvas.repaint();
	}
	
	private void horizontalChange()
	{
		flagCanvas.setHorizontalSlider(topSlider.getValue());
		flagCanvas.repaint();
	}
	
	public void addFlag(Flag f)
	{
		countryListModel.addElement(f);
	}
	
	public Flag getCurrentFlag()
	{
		return currentFlag;
	}
}
