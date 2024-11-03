# PDF Generator API

## Problem statement

* REST API to accept data and generate a PDF based on the received data.
* Ability to download the above-generated PDF
* Store the above-generated PDF on the local storage and redownload it when the
same data is provided instead of generating it again.

## Input format

```
{
    "seller": "XYZ Pvt. Ltd.",
    "sellerGstin": "29AABBCCDD121ZD",
    "sellerAddress": "New Delhi, India",
    "buyer": "Vedant Computers",
    "buyerGstin": "29AABBCCDD131ZD",
    "buyerAddress": "New Delhi, India",
    "items": 
    [{
        "name": "Product 1",
        "quantity": "12 Nos",
        "rate": 123.00,
        "amount": 1476.00
    }
    ]
}
```

## Output
[Expected output](https://github.com/Varun-Choudhry/PDFGen/blob/master/expectedoutput.png)

## Steps to run as a standalone
* Download PDFGen-0.0.1-SNAPSHOT.jar
* Run the jar in cmd with Java 21
* Port is 8080, use POST in Postman with URL http://localhost:8080/pdf/generate
* Add a body to the post request in the format of the input


## Notes
* Uses H2 database for persistance
* Uses SHA-256 hash to identify duplicate requests
