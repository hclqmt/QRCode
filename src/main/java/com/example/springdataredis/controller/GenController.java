package com.example.springdataredis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping ("/qrcode")
public class GenController {
    @GetMapping ("/gen")
    public void gen(@RequestParam ("url") String url, HttpServletResponse response) throws Exception {
        String json1 = "{\"data\":{\"relnNumber\":\"HYS-FYD-230510-0001\",\"projId\":\"HYS\",\"transportMethodDesc\":\"国内陆运\",\"companyName\":\"沈阳鼓风机集团核电泵业有限公司（原沈阳透平机械股份有限公司）\",\"}";
        String json2 = "{\"data\":{\"relnNumber\":\"HYS-FYD-170224-01\",\"projId\":\"HYS\",\"poName\":\"海阳核电3/4号机组主泵装卸工具订货合同\",\"companyName\":\"中联重科股份有限公司\",\"relnId\":5277,\"poNumber\":\"HYS-MH17-G8-0014\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";
        if ("1".equals(url)){
//            QRCodeGenerator.generateQRCode(json1, response, 10);
//            QrCodeUtil.generateBlackAndWhiteQRCode(json1, response);
        }else if ("2".equals(url)){
//            QRCodeGenerator.generateQRCode(json2, response, 10);
//            QrCodeUtil.generateBlackAndWhiteQRCode(json2, response);
        }

    }


}
