package com.pc.dam;
 
import java.awt.Color; 
import java.awt.Rectangle; 
import java.awt.image.BufferedImage; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import javax.jcr.Node; 
//import javax.jcr.RepositoryException; 
import javax.jcr.Session; 
import org.apache.commons.io.IOUtils; 
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
 
import com.day.cq.dam.api.metadata.ExtractedMetadata; 
import com.day.cq.dam.commons.handler.AbstractAssetHandler;
import com.day.cq.dam.api.handler.AssetHandler;
import com.day.image.Font; 
import com.day.image.Layer; 
import com.day.cq.commons.ImageHelper; 
 
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.osgi.framework.Constants;
 
@Component
@Service 
@Properties({
  @Property(name = Constants.SERVICE_RANKING , intValue  = 100) })
 
public class MetaDataExtractHandler extends AbstractAssetHandler  { 
     
    /** * Logger instance for this class. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
     
    /** * Music icon margin */
    private static final int MARGIN = 10; 
     
    /** * @see com.day.cq.dam.api.handler.AssetHandler#getMimeTypes() */
    public String[] getMimeTypes() {
        return new String[] {"text/plain"}; 
    }
 
    public ExtractedMetadata extractMetadata(com.day.cq.dam.api.Asset asset)
    {
     ExtractedMetadata extractedMetadata = new ExtractedMetadata(); 
      
     //convert  the asset to a node to that we can get an InputStream
    Node node = asset.adaptTo(Node.class); 
    InputStream data = this.getInputStream(node); 
    long wordCount = 0;  
    try { 
        // read text data 
         
        InputStreamReader reader = new InputStreamReader(data); 
        char[] buffer = new char[4096]; 
        String text = ""; 
        while (reader.read(buffer) != -1) { 
            text += new String(buffer); 
        } 
        reader.close(); 
        wordCount = this.wordCount(text); 
        extractedMetadata.setProperty("Title", "Text File"); 
        extractedMetadata.setProperty("text", text); 
        extractedMetadata.setMetaDataProperty("Word Count",wordCount); 
        setMimetype(extractedMetadata, asset); 
    } catch (Throwable t) { 
        log.error("handling error: " + t.toString(), t); 
    } finally { 
        IOUtils.closeQuietly(data); 
        //Log the number of words in the Digial Assets uploaded to the DAM
        log.info("Here in the DAM Hander - the text asset had this many words "+wordCount);
    } 
     return extractedMetadata; 
    }
    
       
        
     
      
/**
 * This method counts the number of words in a string
 *
 * @param text the String whose words would like to be counted
 @return the number of words in the string
 */
 private long wordCount(String text) {
 // We need to keep track of the last character, if we have two white spaces in a row we dont want to double count 
 // The starting of the document is always a whitespace 
 boolean prevWhiteSpace = true;
 boolean currentWhiteSpace = true;
 char c;
 long numwords = 0;
 int j = text.length();
 int i = 0;
 while (i < j) {
     c = text.charAt(i++);
     if (c == 0) { break; }
     currentWhiteSpace = Character.isWhitespace(c);
     if (currentWhiteSpace && !prevWhiteSpace)
         { numwords++; }
     prevWhiteSpace = currentWhiteSpace;
     } // If we do not end with a white space then we need to add one extra word 
 if (!currentWhiteSpace) 
     { numwords++; }
 return numwords;
 }
  
      
       
      
     //
    // This method cuts lines if the text file is too long..
     //*
     //* @param text * text to check
     //@param height * text box height (px)
     //@param fontheight * font height (px)
     //@return the text which will fit into the box
      
     private String getDisplayText(String text, int height, int fontheight) {
         String trimmedText = text.trim();
         int numOfLines = height / fontheight;
         String lines[] = trimmedText.split("\n");
      
             if (lines.length <= numOfLines) { return trimmedText; }
             else {
                     StringBuilder cuttetTextBuilder = new StringBuilder();
                     for (int i = 0; i < numOfLines; i++)
                     { cuttetTextBuilder.append(lines[i]).append("\n"); }
             return cuttetTextBuilder.toString();
             }
     }  
       
     @Override
     protected BufferedImage getThumbnailImage(Node node) {
         ExtractedMetadata metadata = extractMetadata(node);
         final String text = (String) metadata.getProperty("text");
         // create text layer 
         final Layer layer = new Layer(500, 600, Color.WHITE);
         layer.setPaint(Color.black);
         Font font = new Font("Arial", 12);
         String displayText = this.getDisplayText(text, 600, 12);
         if (displayText != null && displayText.length() > 0)
         { // commons-gfx Font class would throw IllegalArgumentException on empty or null text layer.drawText(10, 10, 500, 600, displayText, font, Font.ALIGN_LEFT, 0, 0); }
             // create watermark and merge with text layer 
              
             Layer watermarkLayer;
             try
             { 
              final Session session = node.getSession();
              watermarkLayer = ImageHelper.createLayer(session, "/content/dam/geometrixx/icons/certificate.png"); 
              watermarkLayer.setX(MARGIN); watermarkLayer.setY(MARGIN); 
              layer.merge(watermarkLayer); 
             }
             catch (Exception e)
             {  
                 e.printStackTrace();
             }
        }
      
     layer.crop(new Rectangle(510, 600));
     return layer.getImage();
     }
      
        
}