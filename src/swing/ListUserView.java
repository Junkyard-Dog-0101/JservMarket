package swing;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class ListUserView extends JPanel
{
	private JList sampleJList;
	
	@SuppressWarnings("unchecked")
	public ListUserView()
	{
		setBackground(Color.white);
		
		String[] entries = { "Entry 1", "Entry 2", "Entry 3", "Entry 4", "Entry 5", "Entry 6" };
		sampleJList = new JList(entries);
/*		sampleJList.setVisibleRowCount(4);
		sampleJList.setFixedCellHeight(50);*/
		sampleJList.setFixedCellWidth(900);
//		sampleJList.addListSelectionListener(new ValueReporter());
		JScrollPane listPane = new JScrollPane(sampleJList);
		
		Border listPanelBorder = BorderFactory.createTitledBorder("Sample JList");
		setBorder(listPanelBorder);
		add(listPane);
		
	/*	content.add(listPanel, BorderLayout.CENTER);
		JLabel valueLabel = new JLabel("Last Selection:");
		valueLabel.setFont(displayFont);
		valueField = new JTextField("None", 7);
		valueField.setFont(displayFont);
		JPanel valuePanel = new JPanel();
		valuePanel.setBackground(Color.white);
		Border valuePanelBorder = BorderFactory.createTitledBorder("JList Selection");
		valuePanel.setBorder(valuePanelBorder);
		valuePanel.add(valueLabel);
		valuePanel.add(valueField);
		content.add(valuePanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);*/
	}
	
/*	private class ValueReporter implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent event)
		{
			if (!event.getValueIsAdjusting())
			{
				valueField.setText(sampleJList.getSelectedValue().toString());
			}
		}
	}*/
}
