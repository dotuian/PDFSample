package com.dotuian;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

public class BarCodeFactory {

	public static void createBarCode() {
		try {
			String contents = "12345678901231abce";

			int width = 200;
			int height = 50;

			Code128Writer writer = new Code128Writer();
			BarcodeFormat format = BarcodeFormat.CODE_128;

			BitMatrix bitMatrix = writer.encode(contents, format, width, height);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

			ImageIO.write(image, "png", new File("barcode.png"));

			Runtime rt = Runtime.getRuntime();
			rt.exec("mspaint barcode.png");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}

	public static Image barCode(String contents, int width, int height) {

		Image image = null;
		try {
			Code128Writer writer = new Code128Writer();
			BarcodeFormat format = BarcodeFormat.CODE_128;
			BitMatrix bitMatrix = writer.encode(contents, format, width, height);
			BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);

			Toolkit toolkit = Toolkit.getDefaultToolkit();
			image = Image.getInstance(toolkit.createImage(bi.getSource()), null);

		} catch (WriterException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}

}
