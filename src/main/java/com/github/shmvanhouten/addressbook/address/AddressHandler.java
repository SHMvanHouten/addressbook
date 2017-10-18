package com.github.shmvanhouten.addressbook.address;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressHandler {

    private final AddressRepository addressRepository;
    private final TemplateEngine pdfTemplateEngine;

    @Autowired
    public AddressHandler(AddressRepository addressRepository, TemplateEngine pdfTemplateEngine) {
        this.addressRepository = addressRepository;
        this.pdfTemplateEngine = pdfTemplateEngine;
    }


    byte[] createPdfOfAllAddresses() {
        List<Address> addresses = addressRepository.getAllAddresses();
        List<String> addressesStringified = new ArrayList<>();
        for (Address address : addresses) {
            addressesStringified.add(address.getFirstName() + " " + address.getSurName() + ", " + address.getStreetName() + " " + address.getHouseNumber());
        }
        final Context context = new Context();
        //userName would be gotten from input or something
        final String userName = "John Doe";
        context.setVariable("name", userName);
        context.setVariable("addresses", addresses);
        String addressList = pdfTemplateEngine.process("templates/addresslist-template.html", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {

            PdfRendererBuilder pdfRendererBuilder = new PdfRendererBuilder();
            pdfRendererBuilder.withHtmlContent(addressList, "/root.htm");
            pdfRendererBuilder.toStream(outputStream);
            pdfRendererBuilder.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
