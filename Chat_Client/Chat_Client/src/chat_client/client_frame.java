package chat_client;


import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * 
 * @author abul.kalam
 * @since 2020-07-19
 *
 */
public class client_frame extends javax.swing.JFrame {

    String gbp3 = null, getsum1 = null, vsm1 = null, groust3 = null, vatt3 = null, vsum = null, gtsum = null,
            ondate3 = null, bankreg3 = null, bankvat3 = null, banksw4 = null, k12 = null, ivD1 = null, sum1 = null,
            banka3 = null, bankIb3 = null, servername = null, portnum = null, dbname0 = null, login0 = null,
            loginpass = null, amounts = null, daddress = null, database = null, splitstor = null;
    String pass = "1234";
    String username = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2220;
    Boolean isConnected = false;

    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    //--------------------------//
    public void ListenThread() {
        Thread IncomingReader = new Thread(new IncomingReader());
        IncomingReader.start();
    }

    //--------------------------//
    public void userAdd(String data) {
        users.add(data);
    }

    //--------------------------//
    public void userRemove(String data) {
        ta_chat.append(data + " is now offline.\n");
    }

    //--------------------------//
    public void writeUsers() {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token : tempList) {
            //users.append(token + "\n");
        }
    }

    //--------------------------//
    public void sendDisconnect() {
        String bye = (username + ": :Disconnect");
        try {
            writer.println(bye);
            writer.flush();
        } catch (Exception e) {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    public void Disconnect() {
        try {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch (Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }

    void dbms() throws SQLException {
    }

    public client_frame() {
        initComponents();
        ta_chat.setCaretColor(Color.DARK_GRAY);
        ta_chat.setEditable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                Exit();
            }
        });

    }
    
    // / KeyBoardEvents
  
    public void keyTyped(KeyEvent e) {
    }
  
    public void keyReleased(KeyEvent e) {
    }
  
    public void keyPressed(KeyEvent e) {
 
        if ((e.getKeyCode() == KeyEvent.VK_ENTER) && e.isShiftDown()) {
            ta_chat1.append("\n");
 
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ta_chat1();
        }
 
        else if ((e.getKeyCode() == KeyEvent.VK_X) && e.isControlDown()) {
            System.exit(0);
        }
    }
 
    // FocusEvents 
    public void focusGained(FocusEvent e) {
 
        if (e.getSource() == ta_chat) {
            if (!(ta_chat.getText().equals("") || ta_chat.getText().equals(
                    "Connected!"))) {
 
                writer.println("\t ***" + tf_username
                        + ": The Msg is being read......***");
                writer.flush();
            }
        } else if (e.getSource() == ta_chat1) {
            // Set Mesg sending area clear
            if (ta_chat1.getText().equals("Write Message here")) {
                ta_chat1.setText("");
            } else {
                writer.println("\t ***" + tf_username
                        + ": The Msg is being typed......***");
                writer.flush();
            }
 
        }
    }
  
    public void focusLost(FocusEvent e) {
    }
 
    void Exit() {
        int a = JOptionPane.showConfirmDialog(null, "Press \"Yes\" for close", "Massage", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.YES_NO_OPTION) {
            this.dispose();
        }
    }

    

    private void ta_chat1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //--------------------------//
    public class IncomingReader implements Runnable {

        @Override
        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = reader.readLine()) != null) {
                    data = stream.split(":");

                    if (data[2].equals(chat)) {
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                    } else if (data[2].equals(connect)) {
                        ta_chat.removeAll();
                        userAdd(data[0]);
                    } else if (data[2].equals(disconnect)) {
                        userRemove(data[0]);
                    } else if (data[2].equals(done)) {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                    }
                }
            } catch (Exception ex) {
            }
        }
    }

    //--------------------------//
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        lb_password = new javax.swing.JLabel();
        tf_password = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_disconnect = new javax.swing.JButton();
        b_anonymous = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_chat1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_address.setText("Address : ");

        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });

        lb_port.setText("Port :");

        tf_port.setText("2220");
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });

        lb_username.setText("Username :");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        lb_password.setText("Password : ");

        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        b_anonymous.setText("Anonymous Login");
        b_anonymous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_anonymousActionPerformed(evt);
            }
        });

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        ta_chat.setCaretColor(new java.awt.Color(102, 102, 255));
        ta_chat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(ta_chat);

        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        ta_chat1.setColumns(20);
        ta_chat1.setRows(5);
        ta_chat1.setCaretColor(new java.awt.Color(102, 102, 255));
        ta_chat1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(ta_chat1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(b_send))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lb_username, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(lb_address, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_address, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(tf_username))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_port, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_password)
                            .addComponent(tf_port, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b_connect)
                                .addGap(2, 2, 2)
                                .addComponent(b_disconnect)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(b_anonymous, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_address)
                    .addComponent(tf_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_port)
                    .addComponent(tf_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_anonymous))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_username)
                    .addComponent(tf_password)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lb_username)
                        .addComponent(lb_password)
                        .addComponent(b_connect)
                        .addComponent(b_disconnect)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b_send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed

    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed

    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed

        if (isConnected == false) {
            username = tf_username.getText();

            tf_username.setEditable(false);
            try {
                sock = new Socket(tf_address.getText().trim(), port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect\n");
                writer.flush();
                isConnected = true;
            } catch (Exception ex) {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true) {
            ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_anonymousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_anonymousActionPerformed
        tf_username.setText("");
        if (isConnected == false) {
            String anon = "anon";
            Random generator = new Random();
            int i = generator.nextInt(999) + 1;
            String is = String.valueOf(i);
            anon = anon.concat(is);
            username = anon;

            tf_username.setText(anon);
            tf_username.setEditable(false);

            try {
                sock = new Socket(tf_address.getText().trim(), port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(anon + ":has connected.:Connect");
                writer.flush();
                isConnected = true;
            } catch (Exception ex) {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }

            ListenThread();

        } else if (isConnected == true) {
            ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_anonymousActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((ta_chat1.getText()).equals(nothing)) {
            ta_chat1.setText("");
            ta_chat1.setCaretColor(Color.yellow);
            ta_chat1.requestFocus();
        } else {
            try {
                writer.println(username + ":" + ta_chat1.getText() + ":" + "Chat");
                writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            ta_chat1.setText("");
            ta_chat1.requestFocus();
        }

        ta_chat1.setText("");
        ta_chat1.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed

    }//GEN-LAST:event_tf_portActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new client_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_anonymous;
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_password;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextArea ta_chat1;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_password;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
