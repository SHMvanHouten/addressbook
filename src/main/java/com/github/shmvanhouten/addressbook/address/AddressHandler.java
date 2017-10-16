package com.github.shmvanhouten.addressbook.address;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.pdfbox.pdmodel.font.PDType1Font.TIMES_ROMAN;

@Service
public class AddressHandler {

    private final AddressStorageAdapter addressStorageAdapter;
    private final TemplateEngine emailTemplateEngine;

    @Autowired
    public AddressHandler(AddressStorageAdapter addressStorageAdapter, TemplateEngine emailTemplateEngine) {
        this.addressStorageAdapter = addressStorageAdapter;
        this.emailTemplateEngine = emailTemplateEngine;
    }


    File createPdfOfAllAddresses() {
        List<Address> addresses = addressStorageAdapter.getAllAddresses();

        final Context context = new Context();
        //userName would be gotten from input or something
        final String userName =  "John Doe";
        context.setVariable("name", userName);
        context.setVariable("addresses", addresses);
        String addressList = emailTemplateEngine.process("templates/addresslist-template.html", context);
        addressList = addressList.replace("\n", "").replace("\r", "");

        final String fileName = buildFileName(addresses);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        //TODO: dynamische naam en weggooien na 5 minuten
        File file = new File(fileName);
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)){
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText(addressList);
//            for (Address address : addresses) {
//                contentStream.showText(address.getStreetName() + ", " + address.getHouseNumber());
//                contentStream.setLeading(12);
//                contentStream.newLine();
//            }

            contentStream.endText();
            contentStream.close();
            document.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    private String buildFileName(List<Address> addresses) {
        //TODO: when unique users can make unique address lists use the user's id
        StringBuilder fileNameBuilder = new StringBuilder("D:\\tempAddressPdf\\addressList");
        for (Address address : addresses) {
            fileNameBuilder.append(address.getId());
        }
        return fileNameBuilder.append(".pdf").toString();
    }
}
