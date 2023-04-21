package com.capstone.Capstone.project.client;

import com.capstone.Capstone.project.entities.Employee;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Fetcher {
    public static void main(String[] args) {
        Fetcher fetcher = new Fetcher();
        System.out.println(fetcher.fetchEmployeeById(1L));
        System.out.println(fetcher.fetchEmployeeById(2L).getDateOfBirth());
        System.out.println(fetcher.fetchEmployeeById(3L).getDateOfBirth());
        System.out.println(fetcher.fetchEmployeeById(4L));
    }

    public Employee fetchEmployeeById(Long id) {
        String url = "http://localhost:8080/employees/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new EmployeeErrorHandler());
        Employee employee = restTemplate.getForObject(url, Employee.class);
        if (employee!=null&&employee.getDateOfBirth() != null)
            try {
                employee.setDateOfBirth(decrypt(employee.getDateOfBirth()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        return employee;
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
        String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);

        return decryptedText;
    }
}
