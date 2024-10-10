package com.example.springdataredis.controller;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdataredis.utils.QRCodeUtils;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

@RestController
@RequestMapping ("/qrcode")
public class GenController {
    @GetMapping ("/gen")
    public void gen(@RequestParam ("type") String type, HttpServletResponse response) throws Exception {
        int version = 20;
        String json1 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee dad";
        String json2 = "{\"data\":{\"relnNumber\":\"HYS-FYD-170224-01\",\"projId\":\"HYS\",\"poName\":\"海阳核电3/4号机组主泵装卸工具订货合同\",\"companyName\":\"中联重科股份有限公司\",\"relnId\":5277,\"poNumber\":\"HYS-MH17-G8-0014\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";
        String [] strings = {json1, json2};
        // 创建PDF文档并添加二维码
        PdfWriter writer = new PdfWriter("QRCode.pdf");
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        for (String json : strings){
            // 加载二维码图像
            ByteArrayOutputStream byteArrayOutputStream = QRCodeUtils.CreateQRCode(json, version, null, QRCodeUtils.ErrorCorrect.H, QRCodeUtils.EncodeMode.B);
            ImageData imageData = ImageDataFactory.create(byteArrayOutputStream.toByteArray());
            Image qrCodeImg = new Image(imageData);
            // 设置图像大小
            // 设置图像大小为相同
            qrCodeImg.setWidth(100);
            qrCodeImg.setHeight(100);
            document.add(qrCodeImg);
        }

        document.close();

    }


}
