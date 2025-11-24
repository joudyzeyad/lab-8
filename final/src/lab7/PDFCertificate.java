package lab7;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import javax.swing.JFileChooser;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import lab7.Certificate;

public class PDFCertificate {

    private Certificate cert;

    public PDFCertificate(Certificate cert) {
        this.cert = cert;
    }

    public void createPDF() throws FileNotFoundException, IOException {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Certificate as PDF");
        chooser.setSelectedFile(new java.io.File("certificate"+cert.getCertificateId()+".pdf"));

        int option = chooser.showSaveDialog(null);
        if (option != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filePath = chooser.getSelectedFile().getAbsolutePath();

        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            PdfContentByte canvas = writer.getDirectContent();
            Rectangle rect = new Rectangle(36, 36, 559, 806);
            rect.setBorder(Rectangle.BOX);
            rect.setBorderWidth(3);
            canvas.rectangle(rect);

          
            Font titleFont = new Font(Font.HELVETICA, 28, Font.BOLD);
            Paragraph title = new Paragraph("Certificate of Completion", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(40);
            document.add(title);

           
            Font successFont = new Font(Font.HELVETICA, 18, Font.NORMAL);
          
            ArrayList<User> users = JsonDatabaseManager.loadUsers();
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).getUserId()==cert.getStudentId())
            {
                String name=users.get(i).getUsername();
                Paragraph success = new Paragraph(
                "This is to certify that the student "+name+" has successfully completed the course.",
                successFont
            );
                success.setAlignment(Element.ALIGN_CENTER);
            success.setSpacingAfter(40);
            document.add(success);
                break;
            }
        }
           
            Font bodyFont = new Font(Font.HELVETICA, 16, Font.NORMAL);

            document.add(new Paragraph("Certificate ID: " + cert.getCertificateId(), bodyFont));
            document.add(new Paragraph("Student ID: " + cert.getStudentId(), bodyFont));
            document.add(new Paragraph("Course ID: " + cert.getCourseId(), bodyFont));
            document.add(new Paragraph("Issued on: " + cert.getIssueDate(), bodyFont));


            document.close();

            javax.swing.JOptionPane.showMessageDialog(null, "PDF Certificate Saved!");

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
