/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class ConversorData {
    public Date conversorDataParaDateSQL(String data) {
    // Substitua pela data do seu TextField

        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        formatoBrasileiro.setLenient(false);
        java.sql.Date dataSQL = null;
        try {
            dataSQL = new java.sql.Date(formatoBrasileiro.parse(data).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Erro na conversão da data.");
        }
        return dataSQL;
    }
}
