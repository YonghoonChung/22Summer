import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class DeepCopy {
	public  BufferedImage deepCopy(BufferedImage image) {
		ColorModel colorModel= image.getColorModel();
		boolean isAlphaPremultiplied = colorModel.isAlphaPremultiplied();
		WritableRaster raster = image.copyData(image.getRaster().createCompatibleWritableRaster());

		return new BufferedImage(colorModel, raster, isAlphaPremultiplied, null);
	}
}
