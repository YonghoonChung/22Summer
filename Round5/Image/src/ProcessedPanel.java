import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ProcessedPanel extends JPanel {
	static MagnifiedPanel magnifiedPanel = new MagnifiedPanel();
	DeepCopy dCopy = new DeepCopy();
//	JPanel magnifiedPanel = new JPanel();
	private int frameWidth;
	private int frameHeight;
	private int buttonPanelWidth;
	private int imageHeight;
	private int imageWidth;
	private int count = 0;
	private int mouseX = 0;
	private int mouseY = 0;
	private boolean scope;
	private BufferedImage temp;
	private BufferedImage croppedTemp;
	static BufferedImage image = null;

	public void doFrame() {
		// TODO Auto-generated method stub
		setBounds(frameWidth / 2 + 5 + buttonPanelWidth, frameHeight / 2 - Frame.imageProcessSize / 2,
				Frame.imageProcessSize * 4 / 3, Frame.imageProcessSize);
		setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		setVisible(true);
		setLayout(null);

		magnifiedPanel.draw();
		this.add(magnifiedPanel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (image != null) {
			double ratio = 0.0;
			int w = 0;
			int h = 0;
			if (imageWidth > imageHeight) {
				ratio = ((double) Frame.imageProcessSize) / ((double) imageWidth);
				w = (int) (imageWidth * ratio);
				h = (int) (imageHeight * ratio);
			} else {
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
		if (scope) {
			
			magnifiedPanel.setBounds((mouseX - 15), (mouseY - 15), 30, 30);
			if (ImagePanel.image != null && scope) {
				System.out.println("123");
				temp = dCopy.deepCopy(ImagePanel.image);
				croppedTemp = temp.getSubimage(mouseX - 20, mouseY - 20, 40, 40);
				Image resizeImage = croppedTemp.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

				g2.drawImage(resizeImage, mouseX - 15, mouseY - 15, this);

			}
//			g2.draw(new Ellipse2D.Double((mouseX-50), (mouseY-50), 100, 100))
		}
	}

	public void setScope(boolean scope) {
		this.scope = scope;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

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
}
