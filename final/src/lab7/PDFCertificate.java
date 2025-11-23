/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import javax.swing.JFileChooser;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Malak Mokhtar
 */



public class PDFCertificate {

    private Certificate cert;

    public PDFCertificate(Certificate cert) {
        this.cert = cert;
    }

    public void createPDF() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save Certificate as PDF");
        chooser.setSelectedFile(new java.io.File("certificate.pdf"));

        int option = chooser.showSaveDialog(null);
        if (option != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String filePath = chooser.getSelectedFile().getAbsolutePath();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Certificate of Completion\n\n"));
            document.add(new Paragraph("Certificate ID: " + cert.getCertificateId()));
            document.add(new Paragraph("Student ID: " + cert.getStudentId()));
            document.add(new Paragraph("Course ID: " + cert.getCourseId()));
            document.add(new Paragraph("Issued on: " + cert.getIssueDate()));

            document.close();

            javax.swing.JOptionPane.showMessageDialog(null, "Certificate saved as PDF!");

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
