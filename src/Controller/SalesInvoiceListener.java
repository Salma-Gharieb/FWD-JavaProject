/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.HeaderTableModel;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.InvoiceLineTableModel;
import View.Dialog;
import View.Dialog2;
import View.MyFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author hd STORE
 */
public class SalesInvoiceListener implements ActionListener, ListSelectionListener{

    private MyFrame frame;
    
    public SalesInvoiceListener(MyFrame frame){
        this.frame = frame;
    }
    
    /**
     *
     * @param e
     */

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("load")){
            
            try {
                loadFiles();
            } catch (Exception ex) {
                Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getActionCommand().equals("save")){
            try {
                saveData();
            } catch (Exception ex) {
                Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getActionCommand().equals("newInv")) {
            createInv();
        }
        else if(e.getActionCommand().equals("delInv")){
            
            deleteInv();
        }
        else if(e.getActionCommand().equals("newItem")){
            Additem();
        }
        else if(e.getActionCommand().equals("delItem")){
            
            deleteItem();
        }
        else if(e.getActionCommand().equals("createinvOK")){
            
            okInv();
        }
        else if(e.getActionCommand().equals("Cancelcreateinv")){
            
            cancelInv();
        }
        else if(e.getActionCommand().equals("AddItemOK")){
            
            okItem();
        }
        else if(e.getActionCommand().equals("CancelItem")){
            
            cancelItem();
        }
    
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        selectRow();
    }
    
    private void loadFiles() throws Exception 
    {
        frame.getInvoices().clear();
        JOptionPane.showMessageDialog(frame,"Please select invoice header file", "Invoice Header", JOptionPane.WARNING_MESSAGE);
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(frame);
        
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = fc.getSelectedFile();
            FileReader fr = new FileReader(selectedFile);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
           
            while((line = br.readLine()) != null)
            {
                String[] headerFields = line.split(",");
                String numstr = headerFields[0];
                String date = headerFields[1];
                String custName = headerFields[2];
             
                int num = Integer.parseInt(numstr);
                InvoiceHeader header = new InvoiceHeader(num, date, custName);
                frame.getInvoices().add(header);
               
            }
            
            br.close();
            fr.close();
            
            JOptionPane.showMessageDialog(frame, "Show invoice lines file", "Invoice Lines", JOptionPane.WARNING_MESSAGE);
            result = fc.showOpenDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = fc.getSelectedFile();
                fr = new FileReader(selectedFile);
                br = new BufferedReader(fr);
                
                line = null;
           
                while((line = br.readLine()) != null)
                {
                    String[] LineFields = line.split(",");
                    String invnumstr = LineFields[0];
                    String ItemName = LineFields[1];
                    String pricestr = LineFields[2];
                    String countstr = LineFields[3];

                    int invnum = Integer.parseInt(invnumstr);
                    double price = Double.parseDouble(pricestr);
                    int count = Integer.parseInt(countstr);
                    InvoiceHeader header = searchHeader(invnum);
                   
                    InvoiceLine invline = new InvoiceLine(ItemName, price, count, header);
                    header.addline(invline);
                }
            
                br.close();
                fr.close();
                frame.setHeadermodel(new HeaderTableModel(frame.getInvoices()));
                frame.getHeaderTable().setModel(frame.getHeadermodel());
                frame.getHeaderTable().validate();
            }
        }
    }
    
    private void saveData() throws Exception {
         
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(null);
        
        
        if(result == JFileChooser.APPROVE_OPTION ){
            
            File myFile = fc.getSelectedFile();
            PrintWriter fw = new PrintWriter(myFile);
            BufferedWriter bw = new BufferedWriter(fw);
          
            for(InvoiceHeader header:frame.getInvoices()){
             
                fw.printf("%d,%s,%s",header.getNum(),header.getDate().toString(),header.getName());

                bw.newLine();
                fw.println();
                
            }
            JOptionPane.showMessageDialog(frame, "saved!", "information message",JOptionPane.INFORMATION_MESSAGE);
            bw.close();
            fw.close();
        }
        
        
        fc = new JFileChooser();
        result = fc.showSaveDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION ){
            
            File myFile = fc.getSelectedFile();
            PrintWriter fw = new PrintWriter(myFile);
            BufferedWriter bw = new BufferedWriter(fw);
          
            for(InvoiceHeader header:frame.getInvoices() ){
                for(InvoiceLine Lines :header.getLines()){
                    
                fw.printf("%s ,%s,%d",Lines.getItemName(),""+Lines.getPrice(),Lines.getCount());
                bw.newLine();
                fw.println();
            }
            }

            JOptionPane.showMessageDialog(frame, "saved!", "information message",JOptionPane.INFORMATION_MESSAGE);
            bw.close();
            fw.close();
        }
       }
 
       
    
   private InvoiceHeader searchHeader(int num){
       
       for(InvoiceHeader header: frame.getInvoices()){
           
           if(header.getNum() == num){
               
               return header;
           }
       }
       return null;
   }
    
    private void selectRow(){
        
        int rowIndex = frame.getHeaderTable().getSelectedRow();
        if(rowIndex >= 0 )
        {
        InvoiceHeader row = frame.getHeadermodel().getInvoices().get(rowIndex);
        
        frame.getInvNum().setText(""+row.getNum());
        frame.getInvDateTF().setText(row.getDate());
        frame.getCustNameTF().setText(row.getName());
        frame.getInvTotal().setText(""+row.getInvTotal());
        
        ArrayList<InvoiceLine> lines = row.getLines();
        frame.setLinesModel( new InvoiceLineTableModel(lines));
        frame.getLineTable().setModel(frame.getLinesModel());
        frame.getLinesModel().fireTableDataChanged();
        }
        
    }
    
    
    private void createInv(){
        frame.setDialog(new Dialog(frame));
        frame.getDialog().setVisible(true);
    }

    private void okInv(){
        
        String custName = frame.getDialog().getCustnameTF().getText();
        String Date = frame.getDialog().getDateTF().getText();
        frame.getDialog().setVisible(false);
        frame.getDialog().dispose();
        frame.setDialog(null);
        
        InvoiceHeader newheader = new InvoiceHeader(getsecondIndex(), Date, custName);
        frame.getInvoices().add(newheader);
        frame.getHeadermodel().fireTableDataChanged();
        
    }
    
    private void cancelInv(){
        
        frame.getDialog().setVisible(false);
        frame.getDialog().dispose();
        frame.setDialog(null);
    }
    
    private int getsecondIndex(){
        
        int index = 0;
        for(InvoiceHeader h: frame.getInvoices()){
            
            if(h.getNum() > index){
                
                index = h.getNum();
            }
        }
        return index+1;
    }

    private void Additem() {
        
        frame.setDialog2(new Dialog2(frame));
        frame.getDialog2().setVisible(true);
    }
    
    private void cancelItem() {
       
        frame.getDialog2().setVisible(false);
        frame.getDialog2().dispose();
        frame.setDialog2(null);
        
    }

    private void okItem() {
          
        String itemName = frame.getDialog2().getItemnameTF().getText();
        String countstr = frame.getDialog2().getCountTF().getText();
        String pricestr = frame.getDialog2().getPriceTF().getText();
        frame.getDialog2().setVisible(false);
        frame.getDialog2().dispose();
        frame.setDialog2(null);
        
        int count = Integer.parseInt(countstr);
        double price = Double.parseDouble(pricestr);
         
        InvoiceHeader header =frame.getInvoices().get(frame.getHeaderTable().getSelectedRow());
        InvoiceLine newline = new InvoiceLine(itemName, price , count ,header);
        header.addline(newline);
        
        frame.getLinesModel().fireTableDataChanged();
        frame.getHeadermodel().fireTableDataChanged();
        frame.getInvTotal().setText(""+header.getInvTotal());
    }

    private void deleteItem() {
  
        int rowIndex = frame.getLineTable().getSelectedRow();
        
        if(rowIndex != -1){
            frame.getLinesModel().removeRow(rowIndex);
        }
      
        frame.getLinesModel().fireTableDataChanged();
        frame.getHeadermodel().fireTableDataChanged();
    }

    private void deleteInv() {
        
        int rowindex = frame.getHeaderTable().getSelectedRow();
        if(rowindex != -1){
        frame.getHeadermodel().removeRow(rowindex);
        }
        
        frame.getHeadermodel().fireTableDataChanged();
    }
    
}
