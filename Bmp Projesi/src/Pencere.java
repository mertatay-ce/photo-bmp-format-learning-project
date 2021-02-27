/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Mert Atay
 */
public class Pencere extends javax.swing.JFrame {

    /**
     * Creates new form Pencere
     */
    public static String[] file_paths;
    public static File[] files;
    public static ImageIcon ikon1;
    public static String secilen_yol;
    public static ArrayList <ImageIcon> dosyalar;
    
    public Pencere() {
        initComponents();
        yollari_olustur();
    }
    
    
    
    public static int dosya_sayisi(){
        File dir = new File("src\\");
        int dosya_sayisi =0;
        for (File listFile : dir.listFiles()) {
            if(listFile.toString().endsWith(".bmp")){
                dosya_sayisi++;
            }
        }
        
        System.out.println(dosya_sayisi);
       return dosya_sayisi;
    }
    public static void yollari_olustur(){
        int boyut = dosya_sayisi();
        file_paths = new String[boyut];
        System.out.println(file_paths.length);
        File dir = new File("src\\");
        int i=0;
        for (File listFile : dir.listFiles()) {
            if(listFile.toString().endsWith(".bmp")){
                
                file_paths[i] = listFile.toString();
                i++;
            }
        }
        files = new File[boyut];
        dosyalar = new ArrayList<>(boyut);
        for (i=0;i<file_paths.length;i++) {
           //System.out.println(file_paths[i]);
            files[i] = new File(file_paths[i]);
            dosyalar.add(new ImageIcon(files[i].getPath()));
           //System.out.println(files[i].isFile());
           // System.out.println("dosyalar = " + dosyalar.get(i).getPath());
           bmp_chooser.addItem(files[i].getPath());
        }   
      
      
        
       
        
    }
   
    public static Image resim_olarak_oku(int index) throws IOException{
        Image img = ImageIO.read(files[index]);
        BufferedImage im= new BufferedImage(200,300,BufferedImage.TYPE_INT_ARGB);
         Graphics2D g = im.createGraphics();
         g.fillRect(0, 0, 200, 300);
         g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
         g.drawImage(img,0,0,200,300,null);
         
        return im;
    }
    public static Image resim_olarak_oku2(int index) throws IOException{
        Image img = ImageIO.read(new File("src\\outputs\\photo"+index+".bmp"));
        return img;
    }
  
    public static void txt_olustur(int index) throws IOException {
        File output = new File("src\\outputs\\output"+index+".txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(output));
        BufferedImage img;
        try {
            img = ImageIO.read(files[index]);
            int yukseklik = img.getHeight();
            int genislik = img.getWidth();
       
        int [][] red_matris = new int[yukseklik][genislik];
        
        bw.write("R kanali:");
        bw.newLine();
        
        for (int h = 0; h<yukseklik; h++)
        {
            for (int w = 0; w<genislik; w++)
            {
               Color c = new Color(img.getRGB(w, h));
               int r=c.getRed();
               red_matris[h][w]=r;
               
            }
        }
        int sayi=0;
        int sayac=0;
        
        while(true){
             for (int h = 0; h<yukseklik; h++){
              for (int w = 0; w<genislik; w++){
                if(sayi == red_matris[h][w]){
                    sayac++;   
                }
              }
            }
             
             String text = sayi+" dan "+sayac+" kadar bulundu.";
             bw.write(text);
             bw.newLine();
             sayi=sayi+1;
             sayac=0;
             if(sayi==256)
                 break;
        }
        
        int [][] green_matris = new int[yukseklik][genislik];
        bw.newLine();
        bw.write("G kanali:");
        bw.newLine();
        
        for (int h = 0; h<yukseklik; h++)
        {
            for (int w = 0; w<genislik; w++)
            {
               Color c = new Color(img.getRGB(w, h));
               int g=c.getGreen();
               green_matris[h][w]=g;
               
            }
        }
        int sayi2=0;
        int sayac2=0;
        
        while(true){
             for (int h = 0; h<yukseklik; h++){
              for (int w = 0; w<genislik; w++){
                if(sayi2 == green_matris[h][w]){
                    sayac2++;   
                }
              }
            }
             
             String text = sayi2+" dan "+sayac2+" kadar bulundu.";
             bw.write(text);
             bw.newLine();
             sayi2=sayi2+1;
             sayac2=0;
             if(sayi2==256)
                 break;
        }
        
        int [][] blue_matris = new int[yukseklik][genislik];
        bw.newLine();
        bw.write("B kanali:");
        bw.newLine();
        
        for (int h = 0; h<yukseklik; h++)
        {
            for (int w = 0; w<genislik; w++)
            {
               Color c = new Color(img.getRGB(w, h));
               int b=c.getBlue();
               blue_matris[h][w]=b;
               
            }
        }
        int sayi3=0;
        int sayac3=0;
        
        while(true){
             for (int h = 0; h<yukseklik; h++){
              for (int w = 0; w<genislik; w++){
                if(sayi3 == blue_matris[h][w]){
                    sayac3++;   
                }
              }
            }
             
             String text = sayi3+" dan "+sayac3+" kadar bulundu.";
             bw.write(text);
             bw.newLine();
             sayi3=sayi3+1;
             sayac3=0;
             if(sayi3==256)
                 break;
        }
      
        } catch (IOException ex) {
            Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          bw.close();
    }
    
   
    
    public static void rgb_ayarla(int index) throws IOException{
         BufferedImage img = ImageIO.read(files[index]);
         int yukseklik = img.getHeight();
         int genislik = img.getWidth();
         
         int [][] red_matris = new int[yukseklik][genislik];
        
       
        
        for (int h = 0; h<yukseklik; h++)
        {
            for (int w = 0; w<genislik; w++)
            {
               Color c =new Color(img.getRGB(w, h));
               Color n =new Color(0,c.getGreen(),c.getBlue(),c.getAlpha());
               Color n2 = new Color(1,c.getGreen(),c.getBlue(),c.getAlpha());
               if(h==yukseklik && w == genislik -1){
                   img.setRGB(w, h, n2.getRGB());
               }else{
                   img.setRGB(w, h,n.getRGB());
               }
               
            }
        }
        BufferedImage img2 = new BufferedImage(200,300,BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = img2.createGraphics();
        g.fillRect(0, 0, 200, 300);
        g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        g.drawImage(img,0,0,200,300,null);
        
        ImageIO.write(img2, "BMP", new File("src\\outputs\\photo1.bmp"));
        Photo_r.setIcon(new ImageIcon(resim_olarak_oku2(1)));
           
        
        int [][] green_matris = new int[yukseklik][genislik];
        
        
        for (int h = 0; h<yukseklik; h++)
        {
            for (int w = 0; w<genislik; w++)
            {
               Color c =new Color(img.getRGB(w, h));
               Color n =new Color(c.getRed(),0,c.getBlue(),c.getAlpha());
               Color n2 = new Color(c.getRed(),1,c.getBlue(),c.getAlpha());
               if(h==yukseklik && w == genislik -1){
                   img.setRGB(w, h, n2.getRGB());
               }else{
                   img.setRGB(w, h,n.getRGB());
               }
               
            }
        }
        BufferedImage img3= new BufferedImage(200,300,BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2 = img3.createGraphics();
        g2.fillRect(0, 0, 200, 300);
        g2.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        g2.drawImage(img,0,0,200,300,null);
        ImageIO.write(img3, "BMP", new File("src\\outputs\\photo2.bmp"));
        Photo_g.setIcon(new ImageIcon(resim_olarak_oku2(2)));
        
        int [][] blue_matris = new int[yukseklik][genislik];
        
        
        for (int h = 0; h<yukseklik; h++)
        {
            for (int w = 0; w<genislik; w++)
            {
               Color c =new Color(img.getRGB(w, h));
               Color n =new Color(c.getRed(),c.getGreen(),0,c.getAlpha());
               Color n2 = new Color(c.getRed(),c.getGreen(),1,c.getAlpha());
               if(h==yukseklik && w == genislik -1){
                   img.setRGB(w, h, n2.getRGB());
               }else{
                   img.setRGB(w, h,n.getRGB());
               }
               
            }
        }
         BufferedImage img4= new BufferedImage(200,300,BufferedImage.TYPE_BYTE_GRAY);
         Graphics2D g3 = img4.createGraphics();
         g3.fillRect(0, 0, 200, 300);
         g3.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
         g3.drawImage(img,0,0,200,300,null);
         ImageIO.write(img4, "BMP", new File("src\\outputs\\photo3.bmp"));
         Photo_b.setIcon(new ImageIcon(resim_olarak_oku2(3)));
        
         
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new javax.swing.JPanel();
        Photo_original = new javax.swing.JLabel();
        bmp_chooser = new javax.swing.JComboBox<>();
        Photo_r = new javax.swing.JLabel();
        Photo_g = new javax.swing.JLabel();
        Photo_b = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bmp Projesi");

        background1.setBackground(new java.awt.Color(255, 153, 51));

        bmp_chooser.setBackground(new java.awt.Color(255, 0, 0));
        bmp_chooser.setSelectedItem(bmp_chooser);
        bmp_chooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmp_chooserActionPerformed(evt);
            }
        });

        jLabel1.setText("R:");

        jLabel2.setText("G:");

        jLabel3.setText("B:");

        jLabel4.setText("Dosya Seciniz:");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGap(329, 329, 329)
                                .addComponent(Photo_original, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(bmp_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(Photo_r, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(223, 223, 223))
                            .addGroup(background1Layout.createSequentialGroup()
                                .addComponent(Photo_g, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Photo_b, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Photo_original, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(bmp_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Photo_g, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Photo_r, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Photo_b, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bmp_chooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmp_chooserActionPerformed
        // TODO add your handling code here:
        secilen_yol = bmp_chooser.getSelectedItem().toString();
        System.out.println("secilen: " + secilen_yol);
        for(int i=0;i<dosyalar.size();i++){
            if(dosyalar.get(i).getImage().equals(new ImageIcon(secilen_yol).getImage())){
                try {
                    Image ikon = resim_olarak_oku(i);
                    Photo_original.setIcon(new ImageIcon(ikon));
                } catch (IOException ex) {
                    Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        try {
            txt_olustur(bmp_chooser.getSelectedIndex());
        } catch (IOException ex) {
            Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rgb_ayarla(bmp_chooser.getSelectedIndex());
        } catch (IOException ex) {
            Logger.getLogger(Pencere.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }//GEN-LAST:event_bmp_chooserActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pencere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pencere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pencere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pencere.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pencere().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel Photo_b;
    public static javax.swing.JLabel Photo_g;
    public static javax.swing.JLabel Photo_original;
    public static javax.swing.JLabel Photo_r;
    private javax.swing.JPanel background1;
    public static javax.swing.JComboBox<String> bmp_chooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
