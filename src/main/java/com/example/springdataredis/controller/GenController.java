package com.example.springdataredis.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdataredis.utils.QRCodeUtils;

@RestController
@RequestMapping ("/qrcode")
public class GenController {
    @GetMapping ("/gen")
    public void gen(@RequestParam ("type") String type, HttpServletResponse response) throws Exception {
        String json1 = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee dad w哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇哇";
        String json2 = "{\"data\":{\"relnNumber\":\"HYS-FYD-170224-01\",\"projId\":\"HYS\",\"poName\":\"海阳核电3/4号机组主泵装卸工具订货合同\",\"companyName\":\"中联重科股份有限公司\",\"relnId\":5277,\"poNumber\":\"HYS-MH17-G8-0014\"},\"type\":\"ITEM_RETURN_FAYUNDAN\"}";
        if ("1".equals(type)){
            String savePath = "C:\\Users\\hcl\\Desktop\\qrcode1.png";
            int version = 20;
            QRCodeUtils.CreateQRCode(json1, savePath, version, null, QRCodeUtils.ErrorCorrect.H, QRCodeUtils.EncodeMode.B);
        }else if ("2".equals(type)){
            String savePath = "C:\\Users\\hcl\\Desktop\\qrcode2.png";
            int version = 40;
            QRCodeUtils.CreateQRCode(json2, savePath, version, null, QRCodeUtils.ErrorCorrect.L, QRCodeUtils.EncodeMode.B);
        }

    }


}
