package view;

import controller.Database;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private Connection con;
    private DatePicker datePickerInicio;
    private DatePicker datePickerFim;

    public App() {
        con = Database.getInstance().getConnection();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calendário de Reservas");

        VBox vbox = new VBox(20);

        // Criando DatePicker para a data de início
        datePickerInicio = new DatePicker();
        datePickerInicio.setDayCellFactory(getDayCellFactory());

        // Criando DatePicker para a data de fim
        datePickerFim = new DatePicker();
        datePickerFim.setDayCellFactory(getDayCellFactory());

        // Botão para gravar a reserva
        Button btnGravarReserva = new Button("Gravar Reserva");
        btnGravarReserva.setOnAction(e -> gravarReserva());

        vbox.getChildren().addAll(datePickerInicio, datePickerFim, btnGravarReserva);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    private Callback<DatePicker, DateCell> getDayCellFactory() {
        return new Callback<>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Desabilitar datas anteriores ao dia atual
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Cor de fundo para datas indisponíveis
                        } else {
                            int numeroQuarto = obterNumeroQuarto(); // Substitua isso pela lógica correta

                            // Consultar o banco de dados para obter as datas reservadas
                            List<Reserva> reservas = obterDatasReservadas(numeroQuarto);

                            // Verificar se a data fornecida está dentro do intervalo de alguma reserva
                            boolean isDataReservada = reservas.stream()
                                    .anyMatch(reserva -> item.isAfter(reserva.getDataInicio().minusDays(1))
                                    && item.isBefore(reserva.getDataFim().plusDays(1)));

                            if (isDataReservada) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;"); // Cor de fundo para datas indisponíveis
                            }
                        }
                    }
                };
            }
        };
    }

    private void gravarReserva() {
        LocalDate dataInicio = datePickerInicio.getValue();
        LocalDate dataFim = datePickerFim.getValue();

        if (dataInicio != null && dataFim != null && !dataFim.isBefore(dataInicio)) {
            int numeroQuarto = obterNumeroQuarto(); // Substitua isso pela lógica correta

            // Verificar se o intervalo está disponível antes de gravar
            if (!isIntervaloReservado(numeroQuarto, dataInicio, dataFim)) {
                // Implemente a lógica para gravar a reserva no banco de dados
                System.out.println("Reserva gravada: " + dataInicio + " a " + dataFim);
            } else {
                System.out.println("Intervalo já reservado.");
            }
        } else {
            System.out.println("Selecione um intervalo válido.");
        }
    }

    private List<Reserva> obterDatasReservadas(int numeroQuarto) {
        List<Reserva> reservas = new ArrayList<>();

        try {
            String sql = "SELECT data_inicio, data_fim FROM reserva "
                    + "WHERE numero_quarto = ?";

            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, numeroQuarto);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String dataInicioString = resultSet.getString("data_inicio");
                        String dataFimString = resultSet.getString("data_fim");

                        LocalDate dataInicioReserva = LocalDate.parse(dataInicioString);
                        LocalDate dataFimReserva = LocalDate.parse(dataFimString);

                        reservas.add(new Reserva(dataInicioReserva, dataFimReserva));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Lide com exceções adequadamente em um ambiente de produção
        }

        return reservas;
    }

    private boolean isIntervaloReservado(int numeroQuarto, LocalDate dataInicio, LocalDate dataFim) {
        List<Reserva> reservas = obterDatasReservadas(numeroQuarto);

        for (Reserva reserva : reservas) {
            if (!(dataFim.isBefore(reserva.getDataInicio()) || dataInicio.isAfter(reserva.getDataFim()))) {
                // Se houver sobreposição de datas, o intervalo está reservado
                return true;
            }
        }

        return false;
    }

    private int obterNumeroQuarto() {
        // Implemente a lógica para obter o número do quarto (pode ser um input do usuário ou lógica específica do seu aplicativo)
        return 1; // Substitua isso pela lógica correta
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Reserva {

        private final LocalDate dataInicio;
        private final LocalDate dataFim;

        public Reserva(LocalDate dataInicio, LocalDate dataFim) {
            this.dataInicio = dataInicio;
            this.dataFim = dataFim;
        }

        public LocalDate getDataInicio() {
            return dataInicio;
        }

        public LocalDate getDataFim() {
            return dataFim;
        }
    }
}
