package com.example.springdataredis.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    // 生成二维码的方法
    public static void generateQRCode(String url, HttpServletResponse response) {
       try {
          // 准备二维码属性
          Map<EncodeHintType, Object> hints = new HashMap<>();
          hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
          hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
          hints.put(EncodeHintType.MARGIN, 1);

          // 创建MultiFormatWriter对象
          MultiFormatWriter writer = new MultiFormatWriter();
          // 生成二维码的位矩阵
          BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 300, 300, hints);

          // 获取位矩阵的宽度和高度
          int width = bitMatrix.getWidth();
          int height = bitMatrix.getHeight();

          // 创建BufferedImage对象
          BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
          // 遍历位矩阵
          for (int x = 0; x < width; x++) {
             for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
             }
          }

          // 设置响应内容类型
          response.setContentType("image/png");
          ServletOutputStream out = response.getOutputStream();
          ImageIO.write(image, "png", out);
          out.flush();
          out.close();
       } catch (WriterException | IOException e) {
          e.printStackTrace();
       }
    }

   public static void generateQRCode(String url, HttpServletResponse response, int version) {
      try {
         // 准备二维码属性
         Map<EncodeHintType, Object> hints = new HashMap<>();
         hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
         hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
         hints.put(EncodeHintType.MARGIN, 1); // 边距

         // 创建QRCodeWriter对象
         QRCodeWriter writer = new QRCodeWriter();

         // 生成二维码的位矩阵，指定版本
         BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 300, 300, hints);

         // 直接调整版本
         QRCode qrCode = new QRCode();
         // 创建BufferedImage对象
         BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
         for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
               image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
         }

         // 设置响应内容类型
         response.setContentType("image/png");
         ServletOutputStream out = response.getOutputStream();
         ImageIO.write(image, "png", out);
         out.flush();
         out.close();
      } catch (WriterException | IOException e) {
         e.printStackTrace();
      }
   }

}
