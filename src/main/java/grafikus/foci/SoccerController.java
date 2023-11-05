package grafikus.foci;

import grafikus.foci.model.SoccersModel;
import grafikus.foci.controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import grafikus.foci.controller.menuDatabaseController;
import grafikus.foci.controller.menuRestController;
import grafikus.foci.controller.*;

import java.io.IOException;
import java.util.List;

public class SoccerController {
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<SoccersModel> tableView;

    @FXML
    private VBox read_two;

    @FXML
    private GridPane gp1;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private RadioButton radioButton;

    @FXML
    private CheckBox checkBox;


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
        ElemekTörlése();

    }

    public void ElemekTörlése() {
        tableView.setVisible(false);
        tableView.setManaged(false);

        read_two.setVisible(false);
        read_two.setManaged(false);

        gp1.setVisible(false);
        gp1.setManaged(false);


    }


    @FXML
    private Label resultLabel;

    @FXML
    private Menu adatbazisMenu;

    public void menuDatabaseRead(ActionEvent actionEvent) {
        ElemekTörlése();
        tableView.setVisible(true);
        tableView.setManaged(true);
        Session session = SoccerApplication.getSession();
        Transaction t = session.beginTransaction();
        List<SoccersModel> lista = session.createQuery("FROM SoccersModel ORDER BY id ")
                .setMaxResults(10)
                .list();

        tableView.getItems().clear();

        tableView.getItems().addAll(lista);

        t.commit();
        session.close();
    }


    public void menuDatabaseRead2(ActionEvent actionEvent) {
        ElemekTörlése();
        read_two.setVisible(true);
        read_two.setManaged(true);
        tableView.setManaged(true);
        tableView.setVisible(true);
        tableView.getItems().clear();

        String searchText = searchField.getText();
        boolean isHungarian = radioButton.isSelected();
        boolean isForeign = checkBox.isSelected();

        Session session = SoccerApplication.getSession();
        Transaction t = session.beginTransaction();

        // Készítse el a HQL lekérdezést csak azokkal a feltételekkel, amelyeket a felhasználó megadott
        String hql = "FROM SoccersModel WHERE 1=1"; // Alapértelmezett feltétel

        if (!searchText.isEmpty()) {
            hql += " AND (id LIKE :searchText OR name LIKE :searchText OR value LIKE :searchText)";
        }

        if (isForeign) {
            hql += " AND kulfoldi = true";
        }

        org.hibernate.query.Query<SoccersModel> query = session.createQuery(hql, SoccersModel.class);

        if (!searchText.isEmpty()) {
            query.setParameter("searchText", "%" + searchText + "%");
        }

        List<SoccersModel> lista = query
                .setMaxResults(10)
                .list();

        tableView.getItems().addAll(lista);

        t.commit();
        session.close();

    }
    public void menuDatabaseCreate(ActionEvent actionEvent) {
        ElemekTörlése();
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

    public void menuParalell(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("paralell.fxml"));
        Parent root = loader.load();
        ParalellController controller = loader.getController();

        Scene scene = new Scene(root);

        // Hozzon létre egy új Stage objektumot (ablak)
        Stage stage = new Stage();

        // Állítsa be az ablak címét
        stage.setTitle("Párhuzamos Végrehajtás");

        // Állítsa be az ablak Scene-jét
        stage.setScene(scene);

        // Mutassa be az ablakot
        stage.show();
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