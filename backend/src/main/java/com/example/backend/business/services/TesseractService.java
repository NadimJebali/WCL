package com.example.backend.business.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import net.sourceforge.tess4j.TesseractException;

public interface TesseractService {
    
    public String extractTextArabic(MultipartFile file)throws IOException, TesseractException;
    public String extractTextFrench(MultipartFile file)throws IOException, TesseractException;
    public String extractTextEnglish(MultipartFile file)throws IOException, TesseractException;

}
