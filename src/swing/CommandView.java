package swing;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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
		entries = new DefaultListModel();
		sampleJList = new JList(entries);
		setBackground(Color.white);
		setForeground(Color.white);
		entries.addElement("pute");
		sampleJList.setFixedCellWidth(900);
		JScrollPane listPane = new JScrollPane(sampleJList);
		Border listPanelBorder = BorderFactory.createTitledBorder("Clients ");
		setBorder(listPanelBorder);
		add(listPane);
	}

	public void updateContent(String newCommandList)
	{
		entries.addElement(newCommandList);
	}
}
