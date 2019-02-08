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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
//import java.util.GregorianCalendar;


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

        jProgressBar1 = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        readDataButton = new javax.swing.JButton();
        latDegTextField = new javax.swing.JTextField();
        latitudeDegreesLabel = new javax.swing.JLabel();
        lonDegreeLabel = new javax.swing.JLabel();
        lonDegTextField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        clockLabel = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        hrsTextField = new javax.swing.JTextField();
        generateButton = new javax.swing.JButton();
        minTextField = new javax.swing.JTextField();
        colonLabel = new javax.swing.JLabel();
        starsCheckBox = new javax.swing.JCheckBox();
        planetsCheckBox = new javax.swing.JCheckBox();
        constellationsCheckBox = new javax.swing.JCheckBox();
        messierCheckBox = new javax.swing.JCheckBox();
        unselectCheckBox = new javax.swing.JCheckBox();
        latitudeMinLabel = new javax.swing.JLabel();
        latMinTextField = new javax.swing.JTextField();
        lonMinTextField = new javax.swing.JTextField();
        lonMinLabel = new javax.swing.JLabel();
        selectAllButton = new javax.swing.JButton();
        enterDataButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("StarMapGenerator");
        setResizable(false);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane2.setViewportView(textArea);

        readDataButton.setText("Read Data File");
        readDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readDataButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249)
                .addComponent(readDataButton)
                .addGap(0, 299, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(readDataButton)))
                .addGap(424, 498, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        latDegTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latDegTextFieldActionPerformed(evt);
            }
        });

        latitudeDegreesLabel.setText("Latitude (in Degrees):");

        lonDegreeLabel.setText("Longitude (in Degrees):");

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

        starsCheckBox.setText("Stars");
        starsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                starsCheckBoxActionPerformed(evt);
            }
        });

        planetsCheckBox.setText("Major Planets");
        planetsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planetsCheckBoxActionPerformed(evt);
            }
        });

        constellationsCheckBox.setText("Major Constellations");
        constellationsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                constellationsCheckBoxActionPerformed(evt);
            }
        });

        messierCheckBox.setText("Messier Objects");
        messierCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messierCheckBoxActionPerformed(evt);
            }
        });

        unselectCheckBox.setText("Unselect All");
        unselectCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unselectCheckBoxActionPerformed(evt);
            }
        });

        latitudeMinLabel.setText("Latitude (in Minutes):");

        latMinTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                latMinTextFieldKeyTyped(evt);
            }
        });

        lonMinLabel.setText("Longitude (in Minutes):");

        selectAllButton.setText("Select All");
        selectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllButtonActionPerformed(evt);
            }
        });

        enterDataButton.setText("Enter Data");
        enterDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterDataButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lonMinLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lonMinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lonDegreeLabel)
                                        .addComponent(latitudeDegreesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(latDegTextField)
                                        .addComponent(lonDegTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(latitudeMinLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(latMinTextField))))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(clockLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(hrsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(colonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(minTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(dateLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dateTextField)))
                            .addComponent(selectAllButton)
                            .addComponent(enterDataButton))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(planetsCheckBox)
                            .addComponent(constellationsCheckBox)
                            .addComponent(messierCheckBox)
                            .addComponent(unselectCheckBox)
                            .addComponent(starsCheckBox))
                        .addGap(18, 18, 18)
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {latDegTextField, latMinTextField, lonDegTextField, lonMinTextField});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(latitudeDegreesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(latDegTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel)
                            .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(starsCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(latitudeMinLabel)
                            .addComponent(latMinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clockLabel)
                            .addComponent(hrsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colonLabel)
                            .addComponent(minTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(planetsCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lonDegreeLabel)
                                .addComponent(lonDegTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(enterDataButton))
                            .addComponent(constellationsCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lonMinLabel)
                                .addComponent(lonMinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(selectAllButton))
                            .addComponent(messierCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unselectCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(generateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void latDegTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latDegTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_latDegTextFieldActionPerformed

    private void hrsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hrsTextFieldActionPerformed

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        // TODO add your handling code here:
        //jPanel1.setText("You clicked Generate Image button!");
    }//GEN-LAST:event_generateButtonActionPerformed
    private void readDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readDataButtonActionPerformed
        for (int j = 0; j < starMapData.length; j++)
        {
            String output = "";
           
            for (int k = 0; k < starMapData[0].length; k++)
            {
                output += starMapData[j][k] + ",";
            }
            
            System.out.println(output);
        }
    }//GEN-LAST:event_readDataButtonActionPerformed
    /*
    * METHOD: starsCheckBoxActionPerformed()
    *
    * DESCRIPTION: this method prints a message when the stars check box has
    *               been selected.
    */
    private void starsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_starsCheckBoxActionPerformed
        // TODO add your handling code here:
        String stars = "";
        if(starsCheckBox.isSelected())
        {
            stars = stars + " " + starsCheckBox.getText() + '\n';
            textArea.setText(stars);
        }
        else
            textArea.setText("");
    }//GEN-LAST:event_starsCheckBoxActionPerformed
    /*
    * METHOD: planetsCheckBoxActionPerformed
    *
    * DESCRIPTION: this method prints a message when the planets check box has
    *               been selected.
    */
    private void planetsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planetsCheckBoxActionPerformed
        // TODO add your handling code here:
        String planets = "";
        if(planetsCheckBox.isSelected())
        {
            planets = planets + " " + planetsCheckBox.getText() + '\n';
            textArea.setText(planets);
        }
        else
            textArea.setText("");
    }//GEN-LAST:event_planetsCheckBoxActionPerformed
    /*
    * METHOD: constellationsCheckBoxActionPerformed
    *
    * DESCRIPTION: this method prints a message when the constellations check 
    *               box has been selected.
    */
    private void constellationsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_constellationsCheckBoxActionPerformed
        // TODO add your handling code here:
        String constellations = "";
        if(constellationsCheckBox.isSelected())
        {
            constellations = constellations + " " + constellationsCheckBox.getText() + '\n';
            textArea.setText(constellations);
        }
        else
            textArea.setText("");
    }//GEN-LAST:event_constellationsCheckBoxActionPerformed
    /*
    * METHOD: messierCheckBoxActionPerformed
    *
    * DESCRIPTION: this method prints a message when the Messier check box has
    *               been selected.
    */
    private void messierCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messierCheckBoxActionPerformed
        // TODO add your handling code here:
        String messier = "";
        if(messierCheckBox.isSelected())
        {
            messier = messier + " " + messierCheckBox.getText() + '\n';
            textArea.setText(messier);
        }
        else
            textArea.setText("");
    }//GEN-LAST:event_messierCheckBoxActionPerformed
    /*
    * METHOD: unselectCheckBoxActionPerformed
    *
    * DESCRIPTION: this method prints a message when the unselect check box has
    *               been selected.
    */
    private void unselectCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unselectCheckBoxActionPerformed
        // TODO add your handling code here:
        String unselect = "";
        if(unselectCheckBox.isSelected())
        {
            unselect = unselect + " " + unselectCheckBox.getText() + '\n';
            textArea.setText(unselect);
        }
        else
            textArea.setText("");
    }//GEN-LAST:event_unselectCheckBoxActionPerformed
    /*
    * METHOD: unselectCheckBoxActionPerformed
    *
    * DESCRIPTION: this method prints a message when the unselect check box has
    *               been selected.
    */
    private void selectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllButtonActionPerformed
        // TODO add your handling code here:
        String s1 = "";
        
        if(starsCheckBox.isSelected())
        {
            //starsCheckBox.setSelected(true);
            s1 = s1 + " " + starsCheckBox.getText() + '\n';  
        }
//        else
//        {
//            starsCheckBox.setSelected(false);
//            s1 = s1 + "not selected";
//        }
        
        if(planetsCheckBox.isSelected())
        {
            planetsCheckBox.setSelected(true);
            s1 = s1 + " " + planetsCheckBox.getText() + '\n';
        }
        else
            planetsCheckBox.setSelected(false);
        
        if(constellationsCheckBox.isSelected())
        {
            s1 = s1 + " " + constellationsCheckBox.getText() + '\n';
        }
        
        if(messierCheckBox.isSelected())
        {
            s1 = s1 + " " + messierCheckBox.getText() + '\n';
        }
        
        if(unselectCheckBox.isSelected())
        {
            s1 = s1 + " " + unselectCheckBox.getText() + '\n';
        }
        
        textArea.setText(s1);
    }//GEN-LAST:event_selectAllButtonActionPerformed

    private void enterDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterDataButtonActionPerformed
        // Get latitiude in degrees and print to textArea.
        //String infoMsg = "Please enter a Latitude value between " + "[-90, +90]";
        float latDegree = Float.parseFloat(latDegTextField.getText());
        textArea.append(latDegree + " degrees\n");
        

        double latMinute = Double.parseDouble(latMinTextField.getText());
        textArea.append(latMinute + " minutes\n");


//        
//        float lonDegree = Float.parseFloat(lonDegTextField.getText());
//        textArea.append(lonDegree + " degrees\n");
//        
//        double lonMinute = Double.parseDouble(lonMinTextField.getText());
//        textArea.append(lonMinute + " minutes\n");
        
//        if((latDegree < -90) || (latDegree > 90))
//            JOptionPane.showMessageDialog(null, infoMsg);
//        else
//            textArea.append(latDegree + " degrees\n");
        
        //Get latitude in minutes and print to textArea.
        //double latMinute = Double.parseDouble(latMinTextField.getText());
        //textArea.append(latMinute + " minutes\n");
        
        //Get longitude in degrees and print to textArea.
        //String infoMsg2 = "Please enter a Longitude value between " + "[-180, +180]";
        //float lonDegree = Float.parseFloat(lonDegTextField.getText());
        //textArea.append(lonDegree + " degrees\n");
        
//        if((lonDegree < -180) || (lonDegree > 180))
//            JOptionPane.showMessageDialog(null, infoMsg2);
//        else
//            textArea.append(lonDegree + " degrees\n");
        
        //Get longitude in minutes and print to textArea
        //double lonMinute = Double.parseDouble(lonMinTextField.getText());
        //textArea.append(lonMinute + " minutes\n");
        
        
//        dateChooser.getDate();
//        textArea.setText(dateChooser);
        
    }//GEN-LAST:event_enterDataButtonActionPerformed

    private void latMinTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_latMinTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_latMinTextFieldKeyTyped

    
    
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
                readData();
            }
        };
        
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(r);
        
        try 
        {
            System.out.println("Current Local Sidereal Time: " + Calculation.getLocalSiderealTime(86, 38, 47, "West"));
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readData()
    {
        System.out.println("Reading data from file");
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        System.out.println("Working directory: ");
        
        System.out.println(System.getProperty("user.dir"));
        
        String filePath = "./src/resources/hyg.csv";
        
        File file = new File(filePath);
        System.out.println(filePath);
        
        try
        {
            String lineFromFile;
            
            BufferedReader reader = new BufferedReader(new FileReader(file));            
            int rowCount = 0;
            
            while ((reader.readLine()) != null)
            {
                rowCount++;
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
    private javax.swing.JCheckBox constellationsCheckBox;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton enterDataButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JTextField hrsTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField latDegTextField;
    private javax.swing.JTextField latMinTextField;
    private javax.swing.JLabel latitudeDegreesLabel;
    private javax.swing.JLabel latitudeMinLabel;
    private javax.swing.JTextField lonDegTextField;
    private javax.swing.JLabel lonDegreeLabel;
    private javax.swing.JLabel lonMinLabel;
    private javax.swing.JTextField lonMinTextField;
    private javax.swing.JCheckBox messierCheckBox;
    private javax.swing.JTextField minTextField;
    private javax.swing.JCheckBox planetsCheckBox;
    private javax.swing.JButton readDataButton;
    private javax.swing.JButton selectAllButton;
    private javax.swing.JCheckBox starsCheckBox;
    private javax.swing.JTextArea textArea;
    private javax.swing.JCheckBox unselectCheckBox;
    // End of variables declaration//GEN-END:variables
}
