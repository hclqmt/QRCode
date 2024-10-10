package com.example.springdataredis.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/**
 * 二维码工具类
 * 实现输入字符串(文本，网址)生成对应的二维码二维码实质是01代码，具有4个等级的容错能力
 * QRCodeUtils
 * @author lipw
 * @date   2018年8月1日下午4:52:01
 */
public class QRCodeUtils {

    /**
     *  二维码可包含的内容
     * EncodeMode
     * @author lipw
     * @date   2018年8月1日下午4:47:15
     */
    public class EncodeMode{
        /**
         * N代表的是数字
         */
        public final static char N = 'N';
        /**
         * A代表的是a-z
         */
        public final static char A= 'A';
        /**
         * B代表的是其他字符
         */
        public final static char B = 'B';
    }

    /**
     * 二维码的容错能力等级
     * 二维码容错率用字母表示，容错能力等级分为：L、M、Q、H四级
     * 二维码具有容错功能，当二维码图片被遮挡一部分后，仍可以扫描出来。
     * 容错的原理是二维码在编码过程中进行了冗余，就像是123被编码成123123，这样只要扫描到一部分二维码图片，
     * 二维码内容还是可以被全部读到。
     * 二维码容错率即是指二维码图标被遮挡多少后，仍可以被扫描出来的能力。容错率越高，则二维码图片能被遮挡的部分越多。
     * ErrorCorrect
     * @author lipw
     * @date   2018年8月1日下午4:49:17
     */
    public class ErrorCorrect{
        /**
         * 低,最大 7% 的错误能够被纠正
         */
        public final static char L = 'L';
        /**
         * 中，最大 15% 的错误能够被纠正
         */
        public final static char M = 'M';
        /**
         * 中上，最大 25% 的错误能够被纠正
         */
        public final static char Q = 'Q';
        /**
         * 高，最大 30% 的错误能够被纠正
         */
        public final static char H = 'H';
    }

    /**
     * 基于 QRCode 创建二维码
     * @author lipw
     * @date   2018年8月2日上午10:21:57
     * @param content 要写入二维码的内容
     * @param savePath 完整的保存路径
     * @param version 版本
     * @param logoPath 完整的logo路径，可以为：null
     * @return
     */
    public static boolean CreateQRCode(String content, String savePath, int version, String logoPath,char errorCorrect,char encodeMode){
        // 创建生成二维码的对象
        Qrcode qrcode = new Qrcode();
        // 设置二维码的容错能力等级
        qrcode.setQrcodeErrorCorrect(errorCorrect);
        // N代表的是数字，A代表的是a-z,B代表的是其他字符
        qrcode.setQrcodeEncodeMode(encodeMode);
        // 版本
        qrcode.setQrcodeVersion(version);

        // 设置验证码的大小
        int width = 67 + 12 * (version - 1);
        int height = 67 + 12 * (version - 1);
        // 定义缓冲区图片
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 设置画图工具
        Graphics2D gs = bufferedImage.createGraphics();
        // 设置二维码背景颜色
        gs.setBackground(Color.white);//lightGray
        // 设置颜色
        gs.setColor(Color.black);//cyan,green,red,black,pink
        // 清除画板内容
        gs.clearRect(0, 0, width, height);
        // 定义偏移量
        int pixoff = 2;

        // 填充的内容转化为字节数
        byte[] ctt;
        try {
            ctt = content.getBytes("utf-8");
            // 设置编码方式
            if (ctt.length > 0 && ctt.length < 2953 ) {
                boolean[][] s = qrcode.calQrcode(ctt);
                for (int i = 0; i < s.length; i++) {
                    for (int j = 0; j < s.length; j++) {
                        if (s[j][i]) {
                            // 验证码图片填充内容
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            }

            /* 判断是否需要添加logo图片 */
            if(logoPath != null){
                File icon = new File(logoPath);
                if(icon.exists()){
                    int width_4 = width / 4;
                    int width_8 = width_4 / 2;
                    int height_4 = height / 4;
                    int height_8 = height_4 / 2;
                    Image img = ImageIO.read(icon);
                    gs.drawImage(img, width_4 + width_8, height_4 + height_8,width_4,height_4, null);
                }else{
                    System.out.println("Error: login图片不存在！");
                }

            }

            // 结束写入
            gs.dispose();
            // 结束内存图片
            bufferedImage.flush();
            // 保存生成的二维码图片
            ImageIO.write(bufferedImage, "png", new File(savePath));

            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String content = "{\"data\":{\"relnNumber\":\"HYS-FYD-230510-0001\",\"projId\":\"HYS\",\"poName\":\"海阳3/4号机组MP10卧式单级离心泵设备采购合同\",\"transportMethodDesc\":\"国内陆运\",\"companyName\":\"沈阳鼓风机集团核电泵业有限公司（原沈阳透平机械股份有限公司）\",\"relnId\":7839,\"poNumber\":\"HYS-MP10-G8-0108\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";
        content = "{\"data\":{\"relnNumber\":\"HYS-FYD3/4号机组主泵3/4号机组主泵3/4号机组主泵3/4号机组主泵3/4号机组主泵3/4号机组主泵3/4号机组主泵3/4号机组主泵3/4号机组主泵3/4号机组主泵3/4号机组主泵-170224-01\",\"projId\":\"HYS\",\"poName\":\"海阳核电3/4号机组主泵装卸工具订货合同\",\"companyName\":\"中联重科股份有限公司\",\"relnId\":5277,\"poNumber\":\"HYS-MH17-G8-0014\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";
        String savePath = "C:\\Users\\huangchengliu\\Desktop\\qrcode2.png";
        int version = 15;
        String logoPath = null;
        boolean result = CreateQRCode(content, savePath, version, logoPath,ErrorCorrect.H,EncodeMode.B);
        if (result){
            System.out.println("\n二维码图片生成成功！");
        }else{
            System.out.println("\n二维码图片生成失败！");
        }
    }
}