package com.kingaspx.main;


import java.io.*;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * Author Mehmet Emin Han (tosunPasha) Bıyık
 * Date: 29/07/2020
 */



public class Menu extends javax.swing.JFrame  implements Runnable, ThreadFactory {
    private Webcam webcam = null;

    private static final long serialVersionUID = 6441489157408381878L;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    public Menu() {
        initComponents();
        initWebcam();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        result_field = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        result_field.setBorder(null);
        jPanel1.add(result_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 470, 20));

        jSeparator1.setForeground(new java.awt.Color(126, 167, 206));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 470, 10));

        jLabel1.setForeground(new java.awt.Color(105, 105, 105));
        jLabel1.setText("QR Kod Çıktısı");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 300));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField result_field;
    // End of variables declaration//GEN-END:variables




    private void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0); //0 is default webcam // Important
        webcam.setViewSize(size);

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

        executor.execute(this);
    }




    @Override
    public void run() {

        int sayac = 1; //Günde kaç kişinin girip girmediğini görmek için sayaç

        /** Giriş ve çıkışları yönlendirmek için gerekli sayaçlar*/
        int girisCikisMehmet = 0;
        int girisCikisAhmet = 0;


        //Çalışanlar
        Calisan mehmet = new Calisan("Mehmet Emin Han ", "Bıyık","Stajyer" );
        Calisan ahmet = new Calisan("Ahmet", "Kazak","Stajyer");

        final JFrame showPictureFrame = new JFrame("Personel Fotoğraf");


        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                //No result...
            }



            // hangi dosya üzerinde işlem yapacağımızı seçiyoruz.
            File file = new File("C:\\Users\\Mehmet Bıyık\\Desktop\\Text\\dosya.txt");

            int i = 0;


            if (result != null) {

                Date d1 = new Date();
                SimpleDateFormat ft1 = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                ft1.format(d1);



                /** Burada sistem zamanını kullanarak ard arda okuma yapmasını önlemek için zamanlayıcı kullanıyoruz*/
               /* double startTime = System.currentTimeMillis();
                double seconds = (double)(startTime/1000); // saniyeye çevirmek için 1000'e bölüyoruz.
                System.out.println(seconds);*/




                /** MEHMET'İN KONTROLÜ ----------------------------------------------------------------------------*/
                if (result.getText().equals(mehmet.getAd()) && girisCikisMehmet != 3) {

                    if (girisCikisMehmet == 0) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        System.out.println("\nTarih: " + dtf.format(now) + "\nGiriş Yapan: " + mehmet.getAd() + " " + mehmet.getSoyad()
                                + "\nÜnvan: " + mehmet.getUnvan() + "\nSıra: " + sayac);
                        System.out.println("\n\n");// Düzgün Görünmesi için iki adet aşşağı boşluk
                        result_field.setText(result.getText());
                        sayac++;
                        girisCikisMehmet++;


                        // Giriş Yapanın Fotoğrafı buradan açılacak
                        try {
                            // this is a new frame, where the picture should be shown
                            // we will put the picture into this label                                                                  //Center ortaya alıyormu? çalışıyor mu?
                            JLabel pictureLabel = new JLabel(mehmet.getAd()+mehmet.getSoyad()+"    --------Giriş Başarılı-------",SwingConstants.CENTER);
                            showPictureFrame.setLocationRelativeTo(null);  // *** Uygulamanın Ekranda Ortada Açılmasını sağlıyan kod bloğu ***
                            showPictureFrame.setVisible(true);
                            /* The following will read the image */
                            // you should get your picture-path in another way. e.g. with a JFileChooser
                            String path = "C:\\Users\\Mehmet Bıyık\\Desktop\\QR KOD Personel Takip Programı\\Foto\\k.png";
                            URL url = new File(path).toURI().toURL();
                            BufferedImage img = ImageIO.read(url);
                            /* until here */
                            // add the image as ImageIcon to the label
                            pictureLabel.setIcon(new ImageIcon(img));
                            // add the label to the frame
                            showPictureFrame.add(pictureLabel);
                            // pack everything (does many stuff. e.g. resizes the frame to fit the image)
                            showPictureFrame.pack();

                            //this is how you should open a new Frame or Dialog, but only using showPictureFrame.setVisible(true); would also work.
                            java.awt.EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                    showPictureFrame.setVisible(true);
                                }
                            });

                        } catch (IOException ex) {
                            System.err.println("Some IOException accured (did you set the right path?): ");
                            System.err.println(ex.getMessage());
                        }

                        // Programı 5 saniyelik bir uykuya alıyorum burada çünkü art arda qr okumasını istemiyorum
                        try
                        {
                            Thread.sleep(5000);
                        }
                        catch(InterruptedException ex)
                        {
                            Thread.currentThread().interrupt();
                        }
                    }

                    else if (girisCikisMehmet == 1){
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        System.out.println("Tarih: " + dtf.format(now) + "\nÇıkış Yapan: " + mehmet.getAd() + " " + mehmet.getSoyad()
                                + "\nÜnvan: " + mehmet.getUnvan() + "\nSıra: " + sayac);
                        System.out.println("\n\n");// Düzgün Görünmesi için iki adet aşşağı boşluk
                        result_field.setText(result.getText());
                        sayac++;
                        girisCikisMehmet++;
                    }
                }




                /** AHMET KONTROLÜ------------------------------------------------------------------------------------*/
                else if (result.getText().equals(ahmet.getAd()) && girisCikisAhmet !=3 ) {

                    if (girisCikisAhmet== 0) {
                        System.out.println(result.hashCode());
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        System.out.println("Tarih: " + dtf.format(now) + "\nGiriş Yapan: " + ahmet.getAd() + " " + ahmet.getSoyad()
                                + "\nÜnvan: " + ahmet.getUnvan() + "\nSıra: " + sayac);
                        System.out.println("\n\n");// Düzgün Görünmesi için iki adet aşşağı boşluk
                        result_field.setText(result.getText());
                        sayac++;
                        girisCikisAhmet++;
                        // Giriş Yapanın Fotoğrafı buradan açılacak
                        try {
                            // this is a new frame, where the picture should be shown
                                                                   //Center ortaya alıyormu? çalışıyor mu?
                            JLabel pictureLabel = new JLabel(ahmet.getAd()+ahmet.getSoyad()+"    --------Giriş Başarılı-------",SwingConstants.CENTER);
                            showPictureFrame.setLocationRelativeTo(null);  // *** Uygulamanın Ekranda Ortada Açılmasını sağlıyan kod bloğu ***
                            showPictureFrame.setVisible(true);
                            /* The following will read the image */
                            // you should get your picture-path in another way. e.g. with a JFileChooser
                            String path = "C:\\Users\\Mehmet Bıyık\\Desktop\\QR KOD Personel Takip Programı\\Foto\\ahmet.jpg";
                            URL url = new File(path).toURI().toURL();
                            BufferedImage img = ImageIO.read(url);
                            /* until here */
                            // add the image as ImageIcon to the label
                            pictureLabel.setIcon(new ImageIcon(img));
                            // add the label to the frame
                            showPictureFrame.add(pictureLabel);
                            // pack everything (does many stuff. e.g. resizes the frame to fit the image)
                            showPictureFrame.pack();

                            //this is how you should open a new Frame or Dialog, but only using showPictureFrame.setVisible(true); would also work.
                            java.awt.EventQueue.invokeLater(new Runnable() {
                                public void run() {
                                    showPictureFrame.setVisible(true);
                                }
                            });

                        } catch (IOException ex) {
                            System.err.println("Some IOException accured (did you set the right path?): ");
                            System.err.println(ex.getMessage());
                        }

                        // Programı 5 saniyelik bir uykuya alıyorum burada çünkü art arda qr okumasını istemiyorum
                        try
                        {
                            Thread.sleep(5000);
                        }
                        catch(InterruptedException ex)
                        {
                            Thread.currentThread().interrupt();
                        }
                    }

                    else if (girisCikisAhmet == 1){
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        System.out.println("Tarih: " + dtf.format(now) + "\nÇıkış Yapan: " + ahmet.getAd() + " " + ahmet.getSoyad()
                                + "\nÜnvan: " + ahmet.getUnvan() + "\nSıra: " + sayac);
                        System.out.println("\n\n");// Düzgün Görünmesi için iki adet aşşağı boşluk
                        result_field.setText(result.getText());
                        sayac++;
                        girisCikisAhmet++;
                    }
                }
            }
        } while (true);
    }




    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
}


