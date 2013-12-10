package swing;

import javax.swing.ImageIcon;

public class Shape
{
	private final String	idUser;
	private final String	userState;
	private ImageIcon		imageIcon;
	
	public Shape (String typeStr, String imagePath)
	{
		idUser = typeStr;
		userState = imagePath;
	}

	public ImageIcon getImage()
	{
		if (imageIcon == null)
		{
			imageIcon = new ImageIcon(userState);
		}
		return imageIcon;
	}

	public String getType()
	{
		return (idUser);
	}
}
