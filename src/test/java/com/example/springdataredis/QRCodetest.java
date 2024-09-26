package com.example.springdataredis;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class QRCodetest {
    public static void writeQRCodeImage(String text, int width, int height, OutputStream outputStream) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
    }

    public static void main(String[] args) {
        try {
            String text = "Hello, World!";
            String json1 = "{\"data\":{\"relnNumber\":\"HYS-FYD-230510-0001\",\"projId\":\"HYS\",\"poName\":\"海阳3/4号机组MP10卧式单级离心泵设备采购合同\",\"transportMethodDesc\":\"国内陆运\",\"companyName\":\"沈阳鼓风机集团核电泵业有限公司（原沈阳透平机械股份有限公司）\",\"relnId\":7839,\"poNumber\":\"HYS-MP10-G8-0108\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";
            String json2 = "{\"data\":{\"relnNumber\":\"HYS-FYD-170224-01\",\"projId\":\"HYS\",\"poName\":\"海阳核电3/4号机组主泵装卸工具订货合同\",\"companyName\":\"中联重科股份有限公司\",\"relnId\":5277,\"poNumber\":\"HYS-MH17-G8-0014\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";
            int size = 300; // 设置二维码的大小
            writeQRCodeImage(json2, size, size, Files.newOutputStream(Paths.get("qrcode1.png"))); // 输出到控制台
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }
}
