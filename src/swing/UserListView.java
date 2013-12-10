package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
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
		setLayout(new GridLayout(1,1));
		entries = new ArrayList<Shape>();
		updateScroll();
	}

	public synchronized void updateContent(String login, boolean status)
	{
		remove(scrollList);
		for (Shape str : entries)
		{
			if (str.getType().equals(login))
			{
				if (status == true)
				{
					entries.remove(str);
					entries.add(new Shape(login, ".\\res\\greenLight2.png"));
				}
				else
				{
					entries.remove(str);
					entries.add(new Shape(login, ".\\res\\redLight2.png"));
				}
				updateScroll();
				return;
			}
		}
		if (status == true)
			entries.add(new Shape(login, ".\\res\\greenLight2.png"));
		else
			entries.add(new Shape(login, ".\\res\\redLight2.png"));
		updateScroll();
	}

	@SuppressWarnings("unchecked")
	private void updateScroll()
	{
		sampleJList = new JList(entries.toArray());
		sampleJList.setCellRenderer(new DefaultListCellRenderer()
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
		scrollList = new JScrollPane(sampleJList);
		scrollList.setVisible(true);
		add(scrollList);
		scrollList.repaint();
		validate();
		scrollList.validate();
	}
}
