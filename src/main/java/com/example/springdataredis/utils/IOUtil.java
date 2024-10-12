package com.example.springdataredis.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class IOUtil {
    @Autowired
    private ResourceLoader resourceLoader;

    public String readResource(String path) throws IOException {
        InputStream inputStream = resourceLoader.getResource("classpath:" + path).getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new java.io.InputStreamReader(inputStream, "UTF-8"));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
    public String readFile(String path) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(path);
        InputStream inputStream = classPathResource.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new java.io.InputStreamReader(inputStream, "UTF-8"));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return stringBuilder.toString();
    }
}
