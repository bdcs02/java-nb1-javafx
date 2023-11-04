package grafikus.foci;

import grafikus.foci.model.SoccersModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import grafikus.foci.controller.menuDatabaseController;
import grafikus.foci.controller.menuRestController;
import grafikus.foci.controller.*;

import java.util.List;

public class SoccerController {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<SoccersModel> tableView;

    @FXML
    private TableColumn<SoccersModel, Integer> idColumn;
    @FXML
    private TableColumn<SoccersModel, String> nameColumn;
    @FXML
    private TableColumn<SoccersModel, String> rateColumn;


    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("playerRate"));

    }


    @FXML
    private Label resultLabel; // Adjunk hozzá egy Label-t

    @FXML
    private Menu adatbazisMenu; // Az adatbázis menü

    @FXML
    private MenuItem olvasMenuItem; // Az Olvas menüpont
    public void menuDatabaseRead(ActionEvent actionEvent) {
        Session session = SoccerApplication.getSession();
        Transaction t = session.beginTransaction();
        List<SoccersModel> lista = session.createQuery("FROM SoccersModel ORDER BY id ")
                .setMaxResults(10)
                .list();

        // Töröld a TableView előző tartalmát
        tableView.getItems().clear();

        // Add hozzá a lista elemeket a TableView-hoz
        tableView.getItems().addAll(lista);

        t.commit();
    }


    public void menuDatabaseRead2(ActionEvent actionEvent) {
    }

    public void menuDatabaseCreate(ActionEvent actionEvent) {
    }

    public void menuDatabaseUpdate(ActionEvent actionEvent) {
    }

    public void menuDatabaseDelete(ActionEvent actionEvent) {
    }


    public void menuDataDecision(ActionEvent actionEvent) {
    }

    public void menuDataMoreA(ActionEvent actionEvent) {
    }

    public void menuDataMoreA2(ActionEvent actionEvent) {
    }

    public void menuParalell(ActionEvent actionEvent) {
    }

    public void menuStream(ActionEvent actionEvent) {
    }

    public void menuSoapDownload(ActionEvent actionEvent) {
    }

    public void menuSoapDownload2(ActionEvent actionEvent) {
    }

    public void menuSoapStat(ActionEvent actionEvent) {
    }

    public void menuRest1Create(ActionEvent actionEvent) {
    }

    public void menuRest1Read(ActionEvent actionEvent) {
    }

    public void menuRest1Update(ActionEvent actionEvent) {
    }

    public void menuRest1Delete(ActionEvent actionEvent) {
    }
}