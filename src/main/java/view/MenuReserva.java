package view;

import controller.ControladorQuarto;
import controller.ControladorReserva;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Quarto;
import model.Reserva;
import utilities.ConversorData;
import utilities.ConversorDataLocalString;
import utilities.ConversorDataString;

public class MenuReserva extends javax.swing.JInternalFrame {

    ControladorReserva controlaReserva = new ControladorReserva();
    ControladorQuarto controlaQuarto = new ControladorQuarto();
    ConversorData conversordata = new ConversorData();
    ConversorDataString conversordatastring = new ConversorDataString();
    ConversorDataLocalString converteParaDataLocal = new ConversorDataLocalString();
// VARIAVEL GLOBAL!!!!!!!!!!!!!!!!!

    public MenuReserva() {
        initComponents();
        try {
            preencheCombo();
        } catch (SQLException ex) {
            Logger.getLogger(MenuReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel retornaModeloTabela() {
        DefaultTableModel tbReservas = new DefaultTableModel();
        tbReservas.addColumn("Cliente");
        tbReservas.addColumn("Numero");
        tbReservas.addColumn("ID Reserva");
        tbReservas.addColumn("Data Inicio");
        tbReservas.addColumn("Data Fim");
        return tbReservas;
    }

    public void fazReserva() {
        Date dateDataInicio = conversordata.conversorDataParaDateSQL(dataInicio.getText());
        Date dateDataFim = conversordata.conversorDataParaDateSQL(dataFim.getText());
        String SdateDataInicio = conversordatastring.conversorDataParaDateSQL(dataInicio.getText());
        String SdateDataFim = conversordatastring.conversorDataParaDateSQL(dataFim.getText());

        int numeroQuarto = Integer.parseInt(jComboBoxNumeroQuarto.getSelectedItem().toString());
        Reserva reserva = new Reserva(jTextFieldCPFCliente.getText(), numeroQuarto, SdateDataInicio, SdateDataFim);

        if (!isIntervaloNoFuturo(dateDataInicio, dateDataFim)) {
            System.out.println("As datas devem estar no futuro.");
        } else {
            boolean reservasNoPeriodo = controlaReserva.existeReservaNoPeriodo(numeroQuarto, SdateDataInicio, SdateDataFim);

            if (!reservasNoPeriodo) {
                // Realizar a lógica para gravar a nova reserva no banco de dados
                //System.out.println("Reserva gravada com sucesso.");
                controlaReserva.novaReserva(reserva);
                JOptionPane.showMessageDialog(null, "Reserva realizada!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //System.out.println("Já existe uma reserva no período informado.");
                JOptionPane.showMessageDialog(null, "Já existe uma reserva no periodo desejado, consulte o calendario ao lado", "Conflito!", JOptionPane.QUESTION_MESSAGE);
            }
        }
    }

    private boolean isIntervaloNoFuturo(Date dataInicio, Date dataFim) {
        Date data = new Date();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtualS = formatador.format(data);
        Date dataAtual = conversordata.conversorDataParaDateSQL(dataAtualS);
        return (dataInicio.equals(dataAtual) || dataInicio.after(dataAtual)) && (dataFim.equals(dataAtual) || dataFim.after(data));
    }

    public void preencheCombo() throws SQLException {
        List<Quarto> numeros = controlaQuarto.retornarTodos();

        for (Quarto numero : numeros) {
            jComboBoxNumeroQuarto.addItem(String.valueOf(numero.getNumero()));
        }
    }

    public void buscaReservas(int opcao) {
        int numeroQuarto = Integer.parseInt(jComboBoxNumeroQuarto.getSelectedItem().toString());
        List<Reserva> reservas = controlaReserva.buscaReservas(numeroQuarto, jTextFieldBuscaPorCPF.getText(), opcao);
        DefaultTableModel tbReservas = retornaModeloTabela();
        for (Reserva reserva : reservas) {
            Object[] row = {reserva.getCPF(), reserva.getNumeroQuarto(), reserva.getIdReserva(), converteParaDataLocal.conversorDataParaDataLocal(reserva.getDataInicio()), converteParaDataLocal.conversorDataParaDataLocal(reserva.getDataFim())};
            tbReservas.addRow(row);
        }
        jTableReservas.setModel(tbReservas);
    }

    public void cancelaReserva() {
        int idReserva = Integer.parseInt(jTextFieldCancelaReserva.getText());
        System.out.println(idReserva);
        if(controlaReserva.cancelaReserva(idReserva)){
            
            JOptionPane.showMessageDialog(null, "Reserva cancelada");
        }else{
          JOptionPane.showMessageDialog(null, "Verifique o ID da reserva!", "Erro", JOptionPane.QUESTION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReservas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCPFCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxNumeroQuarto = new javax.swing.JComboBox<>();
        dataInicio = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dataFim = new javax.swing.JFormattedTextField();
        btnReserva = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldBuscaPorCPF = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCancelaReserva = new javax.swing.JTextField();
        jButtonPesquisaPorCPF = new javax.swing.JButton();
        jButtonCancelaReserva = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setText("AMBIENTE DE CADASTRO/CONSULTA DE RESERVAS");

        jTableReservas.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jTableReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Numero", "ID_Reserva", "Data Inicio", "Data Fim"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableReservas.setRowHeight(20);
        jTableReservas.setRowMargin(3);
        jTableReservas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableReservas);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Numero");

        jTextFieldCPFCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldCPFCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCPFClienteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("CPF");

        jComboBoxNumeroQuarto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxNumeroQuarto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jComboBoxNumeroQuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNumeroQuartoActionPerformed(evt);
            }
        });

        try {
            dataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Data inicio");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Data fim");

        try {
            dataFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataFim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnReserva.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnReserva.setText("Reserva");
        btnReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Ou CPF:");

        jTextFieldBuscaPorCPF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldBuscaPorCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscaPorCPFActionPerformed(evt);
            }
        });
        jTextFieldBuscaPorCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldBuscaPorCPFKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Busca/Reserva Quarto");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel2)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        jLabel7.setText("Cancelar reserva");

        jLabel8.setText("ID reserva");

        jTextFieldCancelaReserva.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButtonPesquisaPorCPF.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPesquisaPorCPF.setText("Pesquisar");
        jButtonPesquisaPorCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisaPorCPFActionPerformed(evt);
            }
        });

        jButtonCancelaReserva.setText("Cancelar");
        jButtonCancelaReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelaReservaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(294, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(231, 231, 231))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(100, 100, 100)
                                                .addComponent(jLabel8))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnReserva)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jComboBoxNumeroQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldBuscaPorCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jButtonPesquisaPorCPF))
                            .addComponent(jButtonCancelaReserva)
                            .addComponent(jTextFieldCancelaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBoxNumeroQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldCPFCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldBuscaPorCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisaPorCPF)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(dataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldCancelaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(btnReserva))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButtonCancelaReserva)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jTextFieldCPFClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCPFClienteActionPerformed

    }//GEN-LAST:event_jTextFieldCPFClienteActionPerformed

    private void jComboBoxNumeroQuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNumeroQuartoActionPerformed
        buscaReservas(1);
    }//GEN-LAST:event_jComboBoxNumeroQuartoActionPerformed

    private void btnReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservaActionPerformed
        fazReserva();
        buscaReservas(1);
    }//GEN-LAST:event_btnReservaActionPerformed

    private void jTextFieldBuscaPorCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscaPorCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscaPorCPFActionPerformed

    private void jTextFieldBuscaPorCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscaPorCPFKeyPressed
        if (evt.getKeyCode() == 10) {
            buscaReservas(1);
        }
    }//GEN-LAST:event_jTextFieldBuscaPorCPFKeyPressed

    private void jButtonPesquisaPorCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisaPorCPFActionPerformed
        buscaReservas(2);
    }//GEN-LAST:event_jButtonPesquisaPorCPFActionPerformed

    private void jButtonCancelaReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelaReservaActionPerformed
        cancelaReserva();
        buscaReservas(2);
    }//GEN-LAST:event_jButtonCancelaReservaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReserva;
    private javax.swing.JFormattedTextField dataFim;
    private javax.swing.JFormattedTextField dataInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancelaReserva;
    private javax.swing.JButton jButtonPesquisaPorCPF;
    private javax.swing.JComboBox<String> jComboBoxNumeroQuarto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReservas;
    private javax.swing.JTextField jTextFieldBuscaPorCPF;
    private javax.swing.JTextField jTextFieldCPFCliente;
    private javax.swing.JTextField jTextFieldCancelaReserva;
    // End of variables declaration//GEN-END:variables

}
