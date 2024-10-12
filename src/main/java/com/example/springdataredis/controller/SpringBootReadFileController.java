package com.example.springdataredis.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springdataredis.utils.IOUtil;

@Controller
@RequestMapping ("/")
public class SpringBootReadFileController {

    @Autowired
    private IOUtil ioUtil;

    @Value("classpath:/file/text.txt")
    private Resource resource;

    @RequestMapping ("/resourceLoader")
    @ResponseBody
    public String resourceLoader() throws IOException {
        return ioUtil.readResource("/file/text.txt");
    }
    @RequestMapping ("/classpathResource")
    @ResponseBody
    public String classpathResource() throws IOException {
        return ioUtil.readFile("/file/text.txt");
    }

    /**
     * 打成jar包读取无效
     * @return {@link String }
     * @throws IOException
     */
    @RequestMapping ("/filesRead")
    @ResponseBody
    public String filesRead() throws IOException {
        return new String(Files.readAllBytes(Paths.get(resource.getURI())));
    }

    @RequestMapping ("/classLoaderResource")
    @ResponseBody
    public String classLoaderResource() throws IOException {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("file/text.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
