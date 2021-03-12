/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_panel;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import javax.swing.JOptionPane;

/**
 *
 * @author risingstar
 */
public class Panel extends javax.swing.JFrame {
    
    private static final long serialVersionUID = 122221213;
            
    ArrayList<String> clients  = new ArrayList<String>();
    ArrayList<Client> users = new ArrayList<Client>();
    
    int START_PORT = 2222;
    int nServerCnt = 0;
    int nClientCnt = 0;
    
    public BlockingQueue<String> queue;
    /**
     * Creates new form Panel
     */
    public Panel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ta_Nodes = new javax.swing.JScrollPane();
        ta_Node = new javax.swing.JTextArea();
        btnServer = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_Process = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_Result = new javax.swing.JTextArea();
        btnConnectUser = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        ta_Node.setEditable(false);
        ta_Node.setColumns(20);
        ta_Node.setRows(5);
        ta_Node.setEnabled(false);
        ta_Nodes.setViewportView(ta_Node);

        btnServer.setText("Start Servers");
        btnServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServerActionPerformed(evt);
            }
        });

        btnConnect.setText("Start Clients");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        btnSend.setText("Send Message");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        ta_Process.setEditable(false);
        ta_Process.setColumns(20);
        ta_Process.setRows(5);
        jScrollPane1.setViewportView(ta_Process);

        ta_Result.setEditable(false);
        ta_Result.setColumns(20);
        ta_Result.setRows(5);
        jScrollPane2.setViewportView(ta_Result);

        btnConnectUser.setText("Connect User");
        btnConnectUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectUserActionPerformed(evt);
            }
        });

        btnShow.setText("Show Result");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ta_Nodes)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConnectUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSend)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnShow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ta_Nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnServer)
                    .addComponent(btnConnect)
                    .addComponent(btnSend)
                    .addComponent(btnConnectUser)
                    .addComponent(btnShow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        String strServerCnt = JOptionPane.showInputDialog("Please input number of servers: ");
        ta_Node.append("Number of Servers: " + strServerCnt + "\n");
        ta_Node.append("---------------------------------------------\n");

        nServerCnt = Integer.parseInt(strServerCnt);
        int total = 0;
        for (int i = 0; i < nServerCnt; i++)
        {
            String strClientCnt = JOptionPane.showInputDialog("Input number of clients for Server" + Integer.toString(i + 1));
            ta_Node.append("Number of Clients for Server" + Integer.toString(i + 1) + ": " + strClientCnt + "\n");
            
            clients.add(strClientCnt);
            total += Integer.parseInt(strClientCnt);
        }
        ta_Node.append("---------------------------------------------\n");
        queue = new ArrayBlockingQueue<>(total + nServerCnt);
    }//GEN-LAST:event_formWindowOpened

    private void btnServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServerActionPerformed
        for (int i = 0; i < nServerCnt; i++)
        {
            Server server = new Server(START_PORT + i, this);
            server.start();
        }
    }//GEN-LAST:event_btnServerActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        for (int i = 0; i < nServerCnt; i++)
        {
            int clients_num = Integer.parseInt(clients.get(i));
            for (int j = 0; j < clients_num; j++)
            {
                Client client = new Client(START_PORT + i, "Client" + Integer.toString(i+1) + "-" + Integer.toString(j+1), this);
                client.connect();
            }
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        for (int i = 0; i < nServerCnt; i++)
        {
            Client user = users.get(i);
            user.send();
        }
        btnSend.setEnabled(false);
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnConnectUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectUserActionPerformed
        users.removeAll(users);
        for (int i = 0; i < nServerCnt; i++)
        {
            
            Client user = new Client(START_PORT + i, "User" + Integer.toString(i+1), this);
            users.add(user);
            user.connect();
            
        }
        btnConnectUser.setEnabled(false);
    }//GEN-LAST:event_btnConnectUserActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        Object[] strArr = queue.toArray();
        int size = strArr.length;
        long total = 0;
        int min=100, max = 0;
        double avg = 0.0;
        
        for (int i=0; i<size; i++)
        {
            int v = Integer.parseInt((String) strArr[i]);
            if (v > max)
            {
                max = v;
            }
            if (v < min)
            {
                min = v;
            }
            total += v;
        }
        avg = total / size;
        showTime("Min: " + Integer.toString(min) + " ms\n");
        showTime("Max: " + Integer.toString(max) + " ms\n");
        showTime("Average: " + Double.toString(avg) + " ms\n");
    }//GEN-LAST:event_btnShowActionPerformed

    public void logProcess(String process)
    {
        ta_Process.append(process);
    }
    
    public void showTime(String time)
    {
        ta_Result.append(time);
    }
    
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
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Panel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnConnectUser;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnServer;
    private javax.swing.JButton btnShow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea ta_Node;
    private javax.swing.JScrollPane ta_Nodes;
    private javax.swing.JTextArea ta_Process;
    private javax.swing.JTextArea ta_Result;
    // End of variables declaration//GEN-END:variables
}