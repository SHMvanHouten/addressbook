package com.github.shmvanhouten.addressbook.address;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER;

@Service
public class AddressHandler {
    PDDocument createPdfOfAllAddresses() {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(COURIER, 12);
            contentStream.beginText();
            contentStream.showText("Hello world!");
            contentStream.endText();
            contentStream.close();
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }
}
