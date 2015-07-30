import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Display 
{
	//The window
	private JFrame _frame;
	//Draws graphics on window
	private Canvas _canvas;
	
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
		_frame = new JFrame(_title);
		_frame.setSize(_width, _height);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//User can't resize window
		_frame.setResizable(false);
		
		//Window always appears in center of screen
		_frame.setLocationRelativeTo(null);
		_frame.setVisible(true);
		
		_canvas = new Canvas();
		
		//Forces canvas to stay width and height given at all times
		_canvas.setPreferredSize(new Dimension(_width, _height));
		_canvas.setMaximumSize(new Dimension(_width, _height));
		_canvas.setMinimumSize(new Dimension(_width, _height));
		
		_frame.add(_canvas);
		_frame.pack();
	}
	
	public Canvas getCanvas()
	{
		return _canvas;
	}
}
