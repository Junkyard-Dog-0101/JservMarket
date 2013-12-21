package swing;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UserListView extends JPanel
{
	private JList				sampleJList;
	private ArrayList<Shape>	entries;
	private JScrollPane			scrollList;

	public UserListView()
	{
		setBackground(Color.white);
		JLabel jlabel = new JLabel("Clients List :");
		jlabel.setAlignmentX(0);
		add(jlabel);
		entries = new ArrayList<Shape>();
		sampleJList = new JList(entries.toArray());
		sampleJList.setCellRenderer(
				new DefaultListCellRenderer()
				{
					public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
					{
						Shape shape = (Shape)value;
						setBackground(Color.white);
						setText(shape.getType());
						setIcon(shape.getImage());
						return (this);
					}
				});
		sampleJList.setFixedCellWidth(900);
		sampleJList.setVisibleRowCount(10);
		scrollList = new JScrollPane(sampleJList);
		scrollList.setVisible(true);
		add(scrollList);
	}

	public synchronized void updateContent(String login, boolean status)
	{
		for (Shape str : entries)
		{
			if (str.getType().equals(login))
			{
				if (status == true)
				{
					entries.remove(str);
					entries.add(0, new Shape(login, ".\\res\\greenLight2.png"));
				}
				else
				{
					entries.remove(str);
					entries.add(0, new Shape(login, ".\\res\\redLight2.png"));
				}
				sampleJList.setListData(entries.toArray());
				return;
			}
		}
		if (status == true)
			entries.add(new Shape(login, ".\\res\\greenLight2.png"));
		else
			entries.add(new Shape(login, ".\\res\\redLight2.png"));
		sampleJList.setListData(entries.toArray());
	}
}
