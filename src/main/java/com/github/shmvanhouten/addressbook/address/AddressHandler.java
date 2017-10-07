package com.github.shmvanhouten.addressbook.address;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER;

@Service
public class AddressHandler {
    


    File createPdfOfAllAddresses() {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        File file = new File("D:\\tempAddressPdf\\addressList.pdf");
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(COURIER, 12);
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Hello world!");
            contentStream.endText();
            contentStream.close();
            document.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
