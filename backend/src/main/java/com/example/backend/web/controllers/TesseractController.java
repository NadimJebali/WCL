package com.example.backend.web.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.business.services.TesseractService;
import org.springframework.http.MediaType;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sourceforge.tess4j.TesseractException;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/extract")
@Tag(name = "TesseractController" , description = "For Extract text from image")
public class TesseractController {

    @Autowired
    private TesseractService tesseractService ;
    
    @PostMapping(value = "/french" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> extractTextFrench(@Parameter(description = "Fichier à uploader", required = true, 
                                                                content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, 
                                                                schema = @Schema(type = "string", format = "binary")))@RequestParam("file") MultipartFile multipartFile) {
        
         try {
            return new ResponseEntity<>(this.tesseractService.extractTextFrench(multipartFile) , HttpStatus.OK);
        } catch (IOException | TesseractException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'extraction du texte");
        }
    }

    @PostMapping(value = "/arabic" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> extractTextArabic(@Parameter(description = "Fichier à uploader", required = true, 
                                                                content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, 
                                                                schema = @Schema(type = "string", format = "binary")))@RequestParam("file") MultipartFile multipartFile) {
        
         try {
            return new ResponseEntity<>(this.tesseractService.extractTextArabic(multipartFile) , HttpStatus.OK);
        } catch (IOException | TesseractException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'extraction du texte");
        }
    }

    @PostMapping(value = "/english" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> extractTextEnglish(@Parameter(description = "Fichier à uploader", required = true, 
                                                                content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, 
                                                                schema = @Schema(type = "string", format = "binary")))@RequestParam("file") MultipartFile multipartFile) {
        
         try {
            return new ResponseEntity<>(this.tesseractService.extractTextEnglish(multipartFile) , HttpStatus.OK);
        } catch (IOException | TesseractException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'extraction du texte");
        }
    }
    
}
