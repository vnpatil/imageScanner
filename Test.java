package com.amudabadmus.awfa.extractor;


import java.io.File;

import net.sourceforge.tess4j.Tesseract; 
import net.sourceforge.tess4j.TesseractException; 
  
public class Test { 
    public static void main(String[] args) 
    { String text ="";
        Tesseract tesseract = new Tesseract(); 
        try { 
  
            tesseract.setDatapath("E:/ImgToText/tessdata"); 
             tesseract.setLanguage("eng");
            // the path of your tess data folder 
            // inside the extracted file 
              text = tesseract.doOCR(new File("E:/ImgToText/1.jpg")); 
  
            // path of your image file 
            System.out.print(text); 
        } 
        catch (TesseractException e) { 
            e.printStackTrace(); 
        }
        
    }
    } 