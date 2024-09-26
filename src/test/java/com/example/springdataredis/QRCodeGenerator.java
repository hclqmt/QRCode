package com.example.springdataredis;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

import java.io.ByteArrayOutputStream;

public class QRCodeGenerator {

    public static void main(String[] args) throws Exception {
        String json1 = "{\"data\":{\"relnNumber\":\"HYS-FYD-230510-0001\",\"projId\":\"HYS\",\"poName\":\"海阳3/4号机组MP10卧式单级离心泵设备采购合同\",\"transportMethodDesc\":\"国内陆运\",\"companyName\":\"沈阳鼓风机集团核电泵业有限公司（原沈阳透平机械股份有限公司）\",\"relnId\":7839,\"poNumber\":\"HYS-MP10-G8-0108\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";
        String json2 = "{\"data\":{\"relnNumber\":\"HYS-FYD-170224-01\",\"projId\":\"HYS\",\"poName\":\"海阳核电3/4号机组主泵装卸工具订货合同\",\"companyName\":\"中联重科股份有限公司\",\"relnId\":5277,\"poNumber\":\"HYS-MH17-G8-0014\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";

        String [] strings = {json1, json2};
        // 创建PDF文档并添加二维码
        PdfWriter writer = new PdfWriter("QRCode.pdf");
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        for (String json : strings){
            // 加载二维码图像
            ImageData imageData = ImageDataFactory.create(generateQRCode(json));
            Image qrCodeImg = new Image(imageData);

            // 设置图像大小
            // 设置图像大小为相同
            qrCodeImg.setWidth(100);
            qrCodeImg.setHeight(100);
            document.add(qrCodeImg);
        }

        document.close();
    }

    public static byte[] generateQRCode(String qrCodeText) throws Exception {
        int width = 200; // 设定二维码宽度
        int height = 200; // 设定二维码高度

        // 生成二维码
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height);

        // 将二维码转换为图像
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
        byte[] qrCodeImage = baos.toByteArray();

        return qrCodeImage;
    }
}
