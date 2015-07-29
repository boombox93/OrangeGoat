import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Display 
{
	//The window
	private JFrame frame;
	//Draws graphics on window
	private Canvas canvas;
	
	private String _title;
	private int _width; 
	private int _height;
	
	//Constructor
	public Display(String title, int width, int height)
	{
		_title = title;
		_width = width;
		_height = height;
		
		createDisplay();
		
	}
	
	private void createDisplay()
	{
		frame = new JFrame(_title);
		frame.setSize(_width, _height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//User can't resize window
		frame.setResizable(false);
		
		//Window always appears in center of screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		
		//Forces canvas to stay width and height given at all times
		canvas.setPreferredSize(new Dimension(_width, _height));
		canvas.setMaximumSize(new Dimension(_width, _height));
		canvas.setMinimumSize(new Dimension(_width, _height));
		
		frame.add(canvas);
		frame.pack();
	}
}
