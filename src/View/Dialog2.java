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
public class Dialog2 extends JDialog{
    
    private JLabel Itemname;
    private JLabel count;
    private JLabel price;
    private JTextField ItemnameTF;
    private JTextField countTF;
    private JTextField priceTF;
    private JButton btn1;
    private JButton btn2;
    
    MyFrame frame = new MyFrame();
    
    public Dialog2( MyFrame f){
        setLayout(new FlowLayout());
        
        Itemname = new JLabel("Item Name: ");
        ItemnameTF = new JTextField(15);
        count = new JLabel("Count:       ");
        countTF = new JTextField(15);
        price = new JLabel("price:       ");
        priceTF = new JTextField(15);
        btn1 = new JButton("Add");
        btn2 = new JButton("Cancel");
        
        btn1.addActionListener(f);
        btn1.setActionCommand("AddItemOK");
        
        btn2.addActionListener(f);
        btn2.setActionCommand("CancelItem");
        
        add(Itemname);
        add(ItemnameTF);
        add(count);
        add(countTF);
        add(price);
        add(priceTF);
        add(btn1);
        add(btn2);
        
        setSize(300, 300);
        setLocation(200,200);
    }

    public JTextField getItemnameTF() {
        return ItemnameTF;
    }

    public JTextField getCountTF() {
        return countTF;
    }

    public JTextField getPriceTF() {
        return priceTF;
    }


    
}
