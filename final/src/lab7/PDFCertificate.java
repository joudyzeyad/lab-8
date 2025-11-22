/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.awt.*;
import java.awt.print.*;

/**
 *
 * @author Malak Mokhtar
 */
public class PDFCertificate implements Printable{

    private Certificate cert;

    public PDFCertificate(Certificate cert) {
        this.cert = cert;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());

        g2.setFont(new Font("Serif", Font.BOLD, 24));
        g2.drawString("Certificate of Completion", 100, 100);

        g2.setFont(new Font("Serif", Font.PLAIN, 18));
        g2.drawString("Certificate ID: " + cert.getCertificateId(), 100, 160);
        g2.drawString("Student ID: " + cert.getStudentId(), 100, 190);
        g2.drawString("Course ID: " + cert.getCourseId(), 100, 220);
        g2.drawString("Issued on: " + cert.getIssueDate(), 100, 250);

        return PAGE_EXISTS;
    }

    public void createPDF() throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        // This opens a dialog for saving as PDF
        if (job.printDialog()) {
            job.print();
        }
    }
    
}
