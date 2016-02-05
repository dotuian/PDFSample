package com.dotuian;

public class App {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello World!");

		String filename = "test_" + System.currentTimeMillis() + ".pdf";
		PDFFactory.createPDF(filename);

		Runtime rt = Runtime.getRuntime();
		rt.exec("C:\\Program Files (x86)\\Adobe\\Acrobat Reader DC\\Reader\\AcroRd32.exe " + filename);

//		BarCodeFactory.createBarCode();
//		QRCodeFactory.createQRCode();
	}

}
