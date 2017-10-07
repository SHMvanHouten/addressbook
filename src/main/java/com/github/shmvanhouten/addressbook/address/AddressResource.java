package com.github.shmvanhouten.addressbook.address;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/address")
public class AddressResource {

    private final AddressHandler addressHandler;

    @Autowired
    public AddressResource(AddressHandler addressHandler) {
        this.addressHandler = addressHandler;
    }

    @RequestMapping(method = GET)
    public PDDocument getAddressBookPdf(@RequestParam String userName){
        //Todo: user specific addressbooks to be added later

        return addressHandler.createPdfOfAllAddresses();
    }
}
