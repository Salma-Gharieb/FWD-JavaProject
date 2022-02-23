/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author hd STORE
 */
public class Dialog extends JDialog{
    
    private JLabel custname;
    private JLabel date;
    private JTextField custnameTF;
    private JTextField dateTF;
    private JButton btn1;
    private JButton btn2;
    
    public Dialog( MyFrame f){
        setLayout(new FlowLayout());
        
        custname = new JLabel("Customer Name: ");
        custnameTF = new JTextField(15);
        date = new JLabel("Invoice Date:       ");
        dateTF = new JTextField(15);
        btn1 = new JButton("OK");
        btn2 = new JButton("Cancel");
        
        btn1.addActionListener(f);
        btn1.setActionCommand("createinvOK");
        
        btn2.addActionListener(f);
        btn2.setActionCommand("Cancelcreateinv");
        
        add(custname);
        add(custnameTF);
        add(date);
        add(dateTF);
        add(btn1);
        add(btn2);
        
        setSize(340, 300);
        setLocation(200,200);
    }

    public JTextField getCustnameTF() {
        return custnameTF;
    }

    public JTextField getDateTF() {
        return dateTF;
    }
}
