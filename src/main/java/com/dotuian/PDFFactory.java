package com.dotuian;

import java.awt.Color;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfLayer;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFFactory {
	
    public static final String IMAGE = "resources/images/bg.jpg";
	
	public static void createPDF(String filename) throws Exception {

		// step 1
		Document document = new Document();
		
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.setPageSize(PageSize.A4);
		//document.setMargins(36, 72, 108, 180);
		//document.setMarginMirroring(true);
		
		// step 3
		document.open();
		
		// step 4
		// 添加背景图片
        Image image = Image.getInstance(IMAGE);
        //image.scaleAbsolute(PageSize.A4.rotate());
        image.scaleAbsolute(595.0F, 842.0F);
        image.setAbsolutePosition(0.0F, 0.0F);
        image.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
        document.add(image);

        // 添加不打印的图片
        image = Image.getInstance(BarCodeFactory.barCode("1234567890", 200, 50));
        image.scaleAbsolute(200f, 50f);
        image.setAbsolutePosition(200, 600);
        
        
        PdfContentByte cb = writer.getDirectContent();
        PdfLayer background = new PdfLayer("Background Layer", writer);
        background.setPrint("Print", false); // 设置不打印
        background.setOnPanel(false);
        cb.beginLayer(background);
        cb.addImage(image);
		cb.endLayer();
        
		// step 4
		// 添加有颜色的文字
        Font red = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.RED);
        Chunk redText = new Chunk("This text is red. ", red);
        Font blue = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
        Chunk blueText = new Chunk("This text is blue and bold. ", blue);
        Font green = new Font(FontFamily.HELVETICA, 12, Font.ITALIC, BaseColor.GREEN);
        Chunk greenText = new Chunk("This text is green and italic. ", green);
 
        Paragraph p1 = new Paragraph(redText);
        document.add(p1);
        Paragraph p2 = new Paragraph();
        p2.add(blueText);
        p2.add(greenText);
        document.add(p2);
 
        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(new Rectangle(36, 600, 144, 760));
        ct.addElement(p1);
        ct.addElement(p2);
        ct.go();

        // 带有背景颜色的文字
        Chunk fox = new Chunk("quick brown fox");
        // 背景色を指定して、表示位置を上にあげます
        float superscript = 8.0f;
        fox.setTextRise(superscript);
        fox.setBackground(new BaseColor(0xFF, 0xDE, 0xAD));
        document.add(fox);
        
        // 普通的文字
        Chunk jumps = new Chunk(" jumps over ");
        document.add(jumps);
        
        // 带有下划线的文字
        Chunk dog = new Chunk("the lazy dog");
        float subscript = -8.0f;
        dog.setTextRise(subscript);
        dog.setUnderline(new BaseColor(0xFF, 0x00, 0x00), 3.0f, 0.0f, -5.0f + subscript, .0f, PdfContentByte.LINE_CAP_ROUND);
        document.add(dog);
        
		document.close();

	}
}
