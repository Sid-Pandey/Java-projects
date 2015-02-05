import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class Encryption extends JFrame implements ActionListener
{
    JButton encrypt,send,retrieve,decrypt;                        
    JTextArea message;
    JTextField path;
    JPanel panel;
    String encrypted,decrypted;
    JLabel encr,snd,decr;
    JScrollPane scroll;
    
    public static void main(String args[]){
    
        Encryption encryptor = new Encryption();
    }
    
    public Encryption(){
        createwindow();
        encrypt.addActionListener(this);
        send.addActionListener(this);
        decrypt.addActionListener(this);
    }
    
   void createwindow(){
    
        panel = new JPanel();
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(3,2));
        
        initialize();
        add(panel);
        setVisible(true);
    }
    void initialize(){
        
        message = new JTextArea(10,10);
        add(message,BorderLayout.NORTH);
        
        encrypt = new JButton("encrypt");
        send = new JButton("send");
        path = new JTextField();
        retrieve = new JButton("retrieve");
        decrypt = new JButton("decrypt");
        
        decrypt.setPreferredSize(new Dimension(10,10));

        encr = new JLabel("for encrypting data");
        decr = new JLabel("for decrypting data");
        snd = new JLabel("write encrypted data to a file");
        path = new JTextField();
        
        panel.add(encr);
        panel.add(encrypt);
        panel.add(snd);
        panel.add(send);
        panel.add(decr);
        panel.add(decrypt);
        
        path.setText("Enter path of file to be decrypted here");
         add(path,BorderLayout.SOUTH);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == encrypt){             //encrypting the message
            
            cryptomain crypt = null;
            try {
                crypt = new cryptomain("myencryptionkey");
            } catch (Exception ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            encrypted = crypt.encrypt(message.getText()); 
            
            JFrame mesg = new JFrame();
            mesg.setSize(200,200);
            JTextArea t1 = new JTextArea();
            t1.setText(encrypted);
            mesg.add(t1);
            mesg.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(null,"The box shows the encrypted message!");
            mesg.setVisible(true);
        }
        
        if(ae.getSource() == send){
            
            message.setText(" ");
            
            try {
                
                File output = new File("C:\\Users\\Siddharth\\Desktop\\imgholder\\Encrypted.txt");
                FileWriter write = null;
                
                try {
                    write = new FileWriter(output);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                
                try (PrintWriter print = new PrintWriter(write)) {
                print.printf("%s",encrypted);
                }
                
                JOptionPane.showMessageDialog(null,"Check the following file for the encrypted data:\nC:/Users/Siddharth/Desktop/imgholder/Encrypted.txt");
            } 
            
            catch (IOException ex) {}
            
        }
        
        if(ae.getSource() == decrypt){
                
                File input = new File(path.getText());          //getting the file location and creating file object.
                FileInputStream fin;
                String str="";
                String s;
                
            try {
              
                fin = new FileInputStream(input);
                BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                String line;
                
                try {       
                   while((line = br.readLine())!=null)
                   {
                       str+=line;
                   }
                }
                catch (IOException ex) {};
            
                cryptomain crypt=null;
            
            try {
               crypt = new cryptomain("myencryptionkey");
               decrypted = crypt.decrypt(str);
            } 
            catch (Exception ex) {};
                
            } catch (FileNotFoundException ex) {} catch (IOException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            };
            
            message.setText(decrypted);
            JOptionPane.showMessageDialog(null, "Check the decrypted message in text box!");
        }
}
}
