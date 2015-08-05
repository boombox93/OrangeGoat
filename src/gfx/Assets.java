package gfx;
import java.awt.image.BufferedImage;

public class Assets 
{
	
	private static final int width = 128, height = 128;
	
	public static BufferedImage player, dirt, grass, stone, tree;

	public static void init()
	{
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
		
		
		dirt = sheet.crop(0, 0, width, height);
		grass = sheet.crop(width, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, height, width, height);
		player = spriteSheet.crop(0, 0, width/2, height/2);
	}
	
}
