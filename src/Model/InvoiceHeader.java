/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author hd STORE
 */
public class InvoiceHeader {
    
    private int num;
    private String date;
    private String Name;
    private double invTotal;
    private ArrayList<InvoiceLine> lines;
    
    public InvoiceHeader(int num, String date, String Name) {
        this.num = num;
        this.date = date;
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(double invTotal) {
        this.invTotal = invTotal;
    }

    public ArrayList<InvoiceLine> getLines() {
        if(lines == null){
            lines = new ArrayList<>(); 
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public void addline(InvoiceLine line){
        getLines().add(line);
        setInvTotal( getInvTotal() + line.getItemTotal() );
             
    }
}
