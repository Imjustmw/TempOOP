package autograder.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class PDFGenerator {
    public static void generatePDF(String filePath, String content) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath + "/results.pdf"));
            document.open();
            document.add(new Paragraph(content));
            document.close();
        } catch (Exception e) {
            System.err.println("Error generating PDF: " + e.getMessage());
        }
        
    }
}