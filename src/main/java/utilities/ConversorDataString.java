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
public class ConversorDataString {

    public String conversorDataParaDateSQL(String data) {
        // Substitua pela data do seu TextField

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");

        String dataFormatoSQL = null;
        try {
            Date dataConvertida = formatoData.parse(data);
            dataFormatoSQL = formatoSQL.format(dataConvertida);
            System.out.println(dataFormatoSQL);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Erro na conversão da data.");
        }
        return dataFormatoSQL;
    }
}
