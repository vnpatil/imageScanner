package com.amudabadmus.awfa.extractor;

import java.awt.Graphics2D; 
import net.sourceforge.tess4j.*; 
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.*; 
import java.io.*;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream; 
  
public class ScanedImage { 
  
    public static void
    processImg(BufferedImage ipimage, 
               float scaleFactor, 
               float offset) 
        throws IOException, TesseractException 
    { 
        // Making an empty image buffer 
        // to store image later 
        // ipimage is an image buffer 
        // of input image 
        BufferedImage opimage 
            = new BufferedImage(1050, 
                                1024, 
                                ipimage.getType()); 
  
        // creating a 2D platform 
        // on the buffer image 
        // for drawing the new image 
        Graphics2D graphic 
            = opimage.createGraphics(); 
  
        // drawing new image starting from 0 0 
        // of size 1050 x 1024 (zoomed images) 
        // null is the ImageObserver class object 
        graphic.drawImage(ipimage, 0, 0, 
                          1050, 1024, null); 
        graphic.dispose(); 
  
        // rescale OP object 
        // for gray scaling images 
        RescaleOp rescale 
            = new RescaleOp(scaleFactor, offset, null); 
  
        // performing scaling 
        // and writing on a .png file 
        BufferedImage fopimage 
            = rescale.filter(opimage, null); 
        ImageIO 
            .write(fopimage, 
                   "jpg", 
                   new File("E:/ImgToText/1.jpg")); 
  
        // Instantiating the Tesseract class 
        // which is used to perform OCR 
        Tesseract it = new Tesseract(); 
  
        it.setDatapath("E:/ImgToText/tessdata"); 
  
        // doing OCR on the image 
        // and storing result in string str 


File outputfile = new File("E:/ImgToText/11.jpg");
ImageIO.write(fopimage, "jpg", outputfile);


        String str = it.doOCR(fopimage); 
        System.out.println(str); 
    } 
  
    public static  void main(String args[]) throws Exception 
    { 
      
        File fdest 
        = new File( 
            "E://ImgToText/111.jpg");
        ScanedImage.resizeUsingJavaAlgo("E://ImgToText/1.jpg",fdest,1050/2,1024/2);
        File f 
        = new File( 
            "E://ImgToText/111.jpg");
        BufferedImage ipimage = ImageIO.read(f); 
  
        // getting RGB content of the whole image file 
        double d 
            = ipimage 
                  .getRGB(ipimage.getTileWidth() / 2, 
                          ipimage.getTileHeight() / 2); 
  
        // comparing the values 
        // and setting new scaling values 
        // that are later on used by RescaleOP 
        if (d >= -1.4211511E7 && d < -7254228) { 
            processImg(ipimage, 3f, -10f); 
        } 
        else if (d >= -7254228 && d < -2171170) { 
            processImg(ipimage, 1.455f, -47f); 
        } 
        else if (d >= -2171170 && d < -1907998) { 
            processImg(ipimage, 1.35f, -10f); 
        } 
        else if (d >= -1907998 && d < -257) { 
            processImg(ipimage, 1.19f, 0.5f); 
        } 
        else if (d >= -257 && d < -1) { 
            processImg(ipimage, 1f, 0.5f); 
        } 
        else if (d >= -1 && d < 2) { 
            processImg(ipimage, 1f, 0.35f); 
        } 
    } 
    public static boolean resizeUsingJavaAlgo(String source, File dest, int width, int height) throws IOException {
    	  BufferedImage sourceImage = ImageIO.read(new FileInputStream(source));
    	  double ratio = (double) sourceImage.getWidth()/sourceImage.getHeight();
    	  if (width < 1) {
    	    width = (int) (height * ratio + 0.4);
    	  } else if (height < 1) {
    	    height = (int) (width /ratio + 0.4);
    	  }

    	  Image scaled = sourceImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
    	  BufferedImage bufferedScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null), BufferedImage.TYPE_INT_RGB);
    	  Graphics2D g2d = bufferedScaled.createGraphics();
    	  g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    	  g2d.drawImage(scaled, 0, 0, width, height, null);
    	  dest.createNewFile();
    	  writeJpeg(bufferedScaled, dest.getCanonicalPath(), 1.0f);
    	  return true;
    	}


    	/**
    	* Write a JPEG file setting the compression quality.
    	*
    	* @param image a BufferedImage to be saved
    	* @param destFile destination file (absolute or relative path)
    	* @param quality a float between 0 and 1, where 1 means uncompressed.
    	* @throws IOException in case of problems writing the file
    	*/
    	private static void writeJpeg(BufferedImage image, String destFile, float quality)
    	      throws IOException {
    	  ImageWriter writer = null;
    	  FileImageOutputStream output = null;
    	  try {
    	    writer = ImageIO.getImageWritersByFormatName("jpeg").next();
    	    ImageWriteParam param = writer.getDefaultWriteParam();
    	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    	    param.setCompressionQuality(quality);
    	    output = new FileImageOutputStream(new File(destFile));
    	    writer.setOutput(output);
    	    IIOImage iioImage = new IIOImage(image, null, null);
    	    writer.write(null, iioImage, param);
    	  } catch (IOException ex) {
    	    throw ex;
    	  } finally {
    	    if (writer != null) {
    	      writer.dispose();
    	    }
    	    if (output != null) {
    	      output.close();
    	    }
    	  }
    	}
} 
