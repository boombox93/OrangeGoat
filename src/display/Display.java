package display;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display 
{

	private JFrame _frame;
	private Canvas _canvas;
	
	private String _title;
	private int _width;
	private int _height;
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
		_frame.setResizable(false);
		_frame.setLocationRelativeTo(null);
		_frame.setVisible(true);
		
		_canvas = new Canvas();
		_canvas.setPreferredSize(new Dimension(_width, _height));
		_canvas.setMaximumSize(new Dimension(_width, _height));
		_canvas.setMinimumSize(new Dimension(_width, _height));
		_canvas.setFocusable(false);
		
		_frame.add(_canvas);
		_frame.pack();
	}

	public Canvas getCanvas(){
		return _canvas;
	}
	
	public JFrame getFrame(){
		return _frame;
	}
	
}
