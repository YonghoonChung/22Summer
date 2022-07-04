import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private int frameHeight;
	private int imageHeight;
	private int imageWidth;
	private boolean scope;


	public void setScope(boolean scope) {
		this.scope = scope;
	}

	static BufferedImage image = null;

	public void setFrameHeight(int frameHeight) {
		this.frameHeight = frameHeight;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	Color[][] imageColor;

	public void doFrame() {
		// TODO Auto-generated method stub
		setBounds(5, frameHeight / 2 - Frame.imageProcessSize / 2, Frame.imageProcessSize, Frame.imageProcessSize);
		setBackground(Color.LIGHT_GRAY);
		setOpaque(true);
		setVisible(true);
		addMouseMotionListener(new MouseMotionListener() {
			

			@Override
			public void mouseMoved(MouseEvent e) {
				if(scope) {
					Frame.processedPanel.setMouseX(e.getX());
					Frame.processedPanel.setMouseY(e.getY());
					ProcessedPanel.magnifiedPanel.setMouseX(e.getX());
					ProcessedPanel.magnifiedPanel.setMouseY(e.getY());
					Frame.processedPanel.repaint();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
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
