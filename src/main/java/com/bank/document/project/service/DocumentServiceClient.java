package com.bank.document.project.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="documentService",url = "http://localhost:8080")
public interface DocumentServiceClient {
    @GetMapping("/documents/checkDocumentId/{docId}")
    public String getDocumentId(@PathVariable String docId);
}
