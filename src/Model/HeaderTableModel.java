/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hd STORE
 */
public class HeaderTableModel extends DefaultTableModel{
    
    private final String[] cols = {"NO.","Date","Customer","Total"};
    ArrayList<InvoiceHeader> invoices;
    
    public HeaderTableModel(ArrayList<InvoiceHeader> invoices){
        this.invoices = invoices;
    }

    @Override
    public int getColumnCount() {
        return cols.length; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getColumnName(int column) {
        return cols[column]; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int getRowCount() {
        if(this.invoices == null){
            invoices = new ArrayList<>();
        }
        return invoices.size(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object getValueAt(int row, int column) {
      
        InvoiceHeader inv = invoices.get(row);
        switch (column) {
            case 0 -> {
                return inv.getNum();
            }
            case 1 -> {
                return inv.getDate();
            }
            case 2 -> {
                return inv.getName();
            } 
            case 3 -> {
                return inv.getInvTotal();
            }
        }
        return null;
    }

    public ArrayList<InvoiceHeader> getInvoices() {
        return invoices;
    }

    @Override
    public void removeRow(int row) {
       
        invoices.remove(row);
    }
    
    
    
    
}
