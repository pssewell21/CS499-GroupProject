/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author pssew
 */
public class Application extends javax.swing.JFrame {
    
    private String[][] starMapData;

    /**
     * Creates new form Application
     */
    public Application() {
        initComponents();
        
        load();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        latTextField = new javax.swing.JTextField();
        latitudeDegreesLabel = new javax.swing.JLabel();
        longitudeLabel = new javax.swing.JLabel();
        lonTextField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        clockLabel = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        hrsTextField = new javax.swing.JTextField();
        generateButton = new javax.swing.JButton();
        minTextField = new javax.swing.JTextField();
        colonLabel = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        latitudeMinLabel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("StarMapGenerator");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 913, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        latTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latTextFieldActionPerformed(evt);
            }
        });

        latitudeDegreesLabel.setText("Latitude (in Degrees):");

        longitudeLabel.setText("Longitude:");

        dateLabel.setText("Date:");

        clockLabel.setText("Clock:");

        hrsTextField.setText("hr");
        hrsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrsTextFieldActionPerformed(evt);
            }
        });

        generateButton.setBackground(java.awt.Color.green);
        generateButton.setText("Generate Image");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        minTextField.setText("min");

        colonLabel.setText(":");

        jCheckBox1.setText("Stars");

        jCheckBox2.setText("Major Planets");

        jCheckBox3.setText("Major Constellations");

        jCheckBox4.setText("Messier Objects");

        jCheckBox5.setText("Unselect All");

        latitudeMinLabel.setText("Latitude (in minutes):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(longitudeLabel)
                            .addComponent(latitudeDegreesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(latTextField)
                            .addComponent(lonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(latitudeMinLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clockLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hrsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateLabel)
                        .addGap(18, 18, 18)
                        .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox5))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(jScrollPane1)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jTextField1, latTextField, lonTextField});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(latitudeDegreesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(latTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel)
                            .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(latitudeMinLabel)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clockLabel)
                                    .addComponent(hrsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(longitudeLabel)
                                    .addComponent(minTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(colonLabel)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBox2)
                                    .addComponent(jCheckBox5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void latTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_latTextFieldActionPerformed

    private void hrsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hrsTextFieldActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        // TODO add your handling code here:
        //jPanel1.setText("You clicked Generate Image button!");
    }//GEN-LAST:event_generateButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Application().setVisible(true);
            }
        });
    }
    
    private void load()
    {
        Runnable r = new Runnable() 
        {
            public void run() 
            {
                runTask();
            }
        };
        
//        Runnable r = new Runnable() 
//        {
//            public void run() 
//            {
//                readData();
//            }
//        };
        
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(r);
    }
    
    private void runTask()
    {
        try 
        {
            System.out.println("Task Started...");
            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            Thread.sleep(5000);
            
            System.out.println("Task completed");
        } 
        catch (InterruptedException ex) 
        {
            System.out.println("Task was interrupted\n" + ex.toString());
        }
        finally
        {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void readData()
    {
        System.out.println("Reading data from file");
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        String filePath = ".\\src\\resources\\hyg.csv";
        
        File file = new File(filePath);
        System.out.println(filePath);
        
        try
        {
            String lineFromFile;
            
            BufferedReader reader = new BufferedReader(new FileReader(file));            
            int rowCount = 0;
            
            while ((lineFromFile = reader.readLine()) != null)
            {
                rowCount++;
                //System.out.println("Line: " + lineFromFile);
            }
            
            System.out.println(rowCount + " rows found");
            
            reader = new BufferedReader(new FileReader(file));
            int columnCount = 0;
            
            if ((lineFromFile = reader.readLine()) != null)
            {
                int commas = 0;
                        
                for(int i = 0; i < lineFromFile.length(); i++) 
                {
                    if (lineFromFile.charAt(i) == ',') 
                    {
                        commas++;
                    }
                }
                
                columnCount = commas + 1;
                System.out.println(columnCount + " columns found");
            }
            
            starMapData = new String[rowCount][columnCount];
            
            reader = new BufferedReader(new FileReader(file));
            int i = 0;
            
            while ((lineFromFile = reader.readLine()) != null)
            {
                String[] lineItems = lineFromFile.split(",");
                starMapData[i] = lineItems;
                i++;
            }
            
            for (int j = 0; j < i; j++)
            {
                String output = "";
                
                for (int k = 0; k < columnCount; k++)
                {
                    output += starMapData[j][k] + ",";
                }
                
                System.out.println(output);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found at: " + filePath + "\n" + e.toString());
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clockLabel;
    private javax.swing.JLabel colonLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton generateButton;
    private javax.swing.JTextField hrsTextField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField latTextField;
    private javax.swing.JLabel latitudeDegreesLabel;
    private javax.swing.JLabel latitudeMinLabel;
    private javax.swing.JTextField lonTextField;
    private javax.swing.JLabel longitudeLabel;
    private javax.swing.JTextField minTextField;
    // End of variables declaration//GEN-END:variables
}
