package com.capstone.project.client;

import com.capstone.project.entities.Employee;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Fetcher {
    public Employee fetchEmployeeById(Long id) throws Exception {
        String url = "http://localhost:8080/employees/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = restTemplate.getForObject(url, Employee.class);
        employee.setDateOfBirth(decrypt(employee.getDateOfBirth()));
        return employee;
    }

    public static void main(String... args) throws Exception {
        Fetcher fetcher = new Fetcher();
        System.out.println(fetcher.fetchEmployeeById(1L));
        System.out.println(fetcher.fetchEmployeeById(2L));
        System.out.println(fetcher.fetchEmployeeById(3L));
        System.out.println(fetcher.fetchEmployeeById(4L));
        System.out.println(fetcher.fetchEmployeeById(5L));


    }

    public static String decrypt(String encryptedText) throws Exception {
        String ALGORITHM = "AES/CBC/PKCS5Padding";
        String KEY = "0123456789abcdef0123456789abcdef";
        String IV = "0123456789abcdef";
        byte[] keyBytes = KEY.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        byte[] ivBytes = IV.getBytes(StandardCharsets.UTF_8);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}

