package com.amudabadmus.awfa.extractor;
import java.io.*;
import net.sourceforge.tess4j.*;
 
 
public class ImageExtractor
{
    private String imgText;
    public String getImgText(String imageLocation) {
        File imageFile = new File(imageLocation);
        ITesseract instance = new Tesseract();
        instance.setDatapath("E:/ImgToText/tessdata"); 
        instance.setLanguage("eng");
        try {
            imgText = instance.doOCR(imageFile);
            System.out.println("imgtxt="+imgText);
            return imgText;
        } catch (TesseractException e) {
            e.getMessage();
            return "Error while reading image";
        }
    }
}
