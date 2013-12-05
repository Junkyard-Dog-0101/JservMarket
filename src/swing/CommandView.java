package swing;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class CommandView extends JPanel
{
	private JList				sampleJList;
	private DefaultListModel	entries;

	public CommandView(ServerControler newControler)
	{
		JLabel jlabel = new JLabel("Clients Entrance Commands :");
		jlabel.setAlignmentX(0);
		add(jlabel);
		entries = new DefaultListModel();
		sampleJList = new JList(entries);
		setBackground(Color.white);
		sampleJList.setFixedCellWidth(940);
		sampleJList.setVisibleRowCount(10);
		JScrollPane listPane = new JScrollPane(sampleJList);
		add(listPane);
	}

	public void updateContent(String newCommandList)
	{
		entries.add(0, newCommandList);
	}
}
