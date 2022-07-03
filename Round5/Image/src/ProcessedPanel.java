import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ProcessedPanel extends JPanel{
	private int frameWidth;
	private int frameHeight;
	private int buttonPanelWidth;
	private int imageHeight;
	private int imageWidth;
	static BufferedImage image = null;
	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public void setButtonPanelWidth(int buttonPanelWidth) {
		this.buttonPanelWidth = buttonPanelWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	
	public void doFrame() {
		// TODO Auto-generated method stub
		setBounds(frameWidth/2 + 5 + buttonPanelWidth,frameHeight/2 - Frame.imageProcessSize/2 ,Frame.imageProcessSize*4/3,Frame.imageProcessSize);
		setBackground(Color.BLUE);
		setOpaque(true);
		setVisible(true);	
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		System.out.println("ProcessedPanel : " + imageHeight +" " +imageWidth);
		if (image != null) {
			double ratio = 0.0;
			int w = 0;
			int h = 0;
			if (imageWidth> imageHeight) {
				System.out.println("1");
				ratio = ((double) Frame.imageProcessSize) / ((double) imageWidth);
				w = (int) (imageWidth * ratio);
				h = (int) (imageHeight * ratio);
			} else {
				System.out.println("2");
				ratio = ((double) Frame.imageProcessSize) / ((double) imageHeight);
				w = (int) (imageWidth * ratio);
				h = (int) (imageHeight * ratio);
			}
			Image resizeImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
//			BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			if (imageWidth > imageHeight) {
				g2.drawImage(resizeImage, 0, Frame.imageProcessSize / 2 - h / 2, this);
			} else {
				g2.drawImage(resizeImage, Frame.imageProcessSize / 2 - w / 2, 0, this);
			}
		}
	}
	
}
