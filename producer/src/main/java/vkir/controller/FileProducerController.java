package vkir.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@RestController
public class FileProducerController {

    private final RestTemplate restTemplate;

    public FileProducerController() {
        restTemplate = new RestTemplate();
    }

    @PostMapping(value = "/uploadFile")
    public ResponseEntity<String> uploadFile() {
        String filePath = "sample.txt";
        String endpoint = "http://localhost:8080/uploadFile";
        String path = "/some/path";
        String userId = "123";
        String accName = "testAccount";

        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileSystemResource);
        body.add("path", path);
        body.add("userId", userId);
        body.add("accName", accName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity(endpoint, request, String.class);
    }
}
