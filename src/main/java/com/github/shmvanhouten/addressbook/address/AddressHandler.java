package com.github.shmvanhouten.addressbook.address;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.COURIER;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.TIMES_ROMAN;

@Service
public class AddressHandler {

    private final AddressStorageAdapter addressStorageAdapter;

    @Autowired
    public AddressHandler(AddressStorageAdapter addressStorageAdapter) {
        this.addressStorageAdapter = addressStorageAdapter;
    }


    File createPdfOfAllAddresses() {
        List<Address> addresses = addressStorageAdapter.getAllAddresses();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        File file = new File("D:\\tempAddressPdf\\addressList.pdf");
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)){
            contentStream.beginText();
            contentStream.setFont(TIMES_ROMAN, 12);
            contentStream.newLineAtOffset(50, 750);
            for (Address address : addresses) {
                contentStream.showText(address.getStreetName() + ", " + address.getHouseNumber());
                contentStream.setLeading(12);
                contentStream.newLine();
            }

            contentStream.endText();
            contentStream.close();
            document.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
