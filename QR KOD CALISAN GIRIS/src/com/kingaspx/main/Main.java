package com.kingaspx.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



/**
 * Author Mehmet Emin Han (tosunPasha) Bıyık
 * Date: 29/07/2020
 */




public class Main {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);


        // Zaman
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();




        while(true) {
            System.out.println("-------------------------------------------------------------------" +
                    "-----------------------------------------------------------");
            String islemler = "1- Kamerayı calistir" +
                    "\n2- Çıkış";
            System.out.println("Programın Çalışma Tarihi: "+dtf.format(now));
            System.out.println("INFO");
            System.out.println(islemler);
            System.out.print("Yapmak istediğiniz işlemi numpad yardımı ile giriniz: ");
            System.out.println("\n-------------------------------------------------------------------" +
                    "-----------------------------------------------------------");

            int islemNo = scan.nextInt();
            if (islemNo == 1){
                System.out.println("\n---------------------------------------------------------------" +
                        "---------------------------------------------------------------");
                System.out.println("\n\t----Yönetici Paneli----");
                System.out.print("ŞİFRE:");
                Sifre sifre= new Sifre();
                int sifreGirilen = scan.nextInt();

                if (sifreGirilen==sifre.getSifre()) {
                    System.out.println("PROGRAM BAŞARI İLE ÇALIŞTI");
                    /* Set the Nimbus look and feel */
                    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
                    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
                     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
                     */
                    try {
                        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                            if ("Windows".equals(info.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                break;
                            }
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
                        java.util.logging.Logger.getLogger(com.kingaspx.main.Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    //</editor-fold>

                    //</editor-fold>

                    /* Create and display the form */
                    java.awt.EventQueue.invokeLater(() -> new Menu().setVisible(true));
                }
                else{
                    System.out.println("\nHatalı şifre girişi!");
                    System.out.println("1- Ana menüye dön\n" +
                            "2- Çıkış yap");
                    System.out.print("\nYapmak istediğiniz işlemi numpad yardımı ile giriniz:");
                    int durum = scan.nextInt();
                    if (durum==1) {
                        continue;
                    }
                    else if (durum == 2) break;
                    else System.out.println("Hatalı tuş girişi. Ana menüye yönlendiriliyorsunuz.");


                }
            }
            else if (islemNo == 2){
                break;
            }

        }

    }

}

