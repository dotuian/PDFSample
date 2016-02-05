package com.dotuian;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeFactory {

	public static void createQRCode() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("今天是个好天气！");
		sb.append("\r\n");
		sb.append("今日は天気がいいね！");
		sb.append("\r\n");
		sb.append("Today is a good weather");
		sb.append("\r\n");

		String contents = sb.toString();

		int width = 320;
		int height = 200;

		@SuppressWarnings("rawtypes")
		Hashtable<EncodeHintType, Comparable> hints = new Hashtable<EncodeHintType, Comparable>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		Writer writer = new QRCodeWriter();
		BarcodeFormat format = BarcodeFormat.QR_CODE;

		// Writer writer = new PDF417Writer();
		// BarcodeFormat format = BarcodeFormat.PDF_417;

		// Writer writer = new DataMatrixWriter();
		// BarcodeFormat format = BarcodeFormat.DATA_MATRIX;

		BitMatrix bitMatrix = writer.encode(contents, format, width, height, hints);

		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
		ImageIO.write(image, "png", new File("qrcode.png"));

		Runtime rt = Runtime.getRuntime();
		rt.exec("mspaint qrcode.png");
	}
}
