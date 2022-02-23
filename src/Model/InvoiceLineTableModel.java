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
public class InvoiceLineTableModel extends DefaultTableModel{

    String[] cols = {"itemName","price","count","total"};
    private ArrayList<InvoiceLine> data;
    
    public InvoiceLineTableModel(ArrayList<InvoiceLine> data){
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        if(this.data == null){
            
            data = new ArrayList<>();
        }
         return data.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getColumnName(int column){
        return cols[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        InvoiceLine line = data.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {
                return line.getItemName();
            }
            case 1 -> {
                return line.getPrice();
            }
            case 2 -> {
                return line.getCount();
            }
            case 3 -> {
                return line.getItemTotal();
            } 
        }
        return null;
    }

    public ArrayList<InvoiceLine> getData() {
        return data;
    }

    @Override
    public void removeRow(int row) {
        
        data.remove(row);
    }
    
    
}
