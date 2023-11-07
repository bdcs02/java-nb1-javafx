package grafikus.foci;


import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import grafikus.foci.model.SoccersModel;
import grafikus.foci.controller.*;
import grafikus.oanda.v20.Config;
import javafx.application.Platform;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.oanda.v20.instrument.CandlestickGranularity.H1;

public class SoccerController {
    @FXML
    private Label accountDetails;

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
    private Button startButton;

    @FXML
    private Label pricesNow;

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

        startButton.setVisible(false);
        startButton.setManaged(false);

        accountDetails.setVisible(false);
        accountDetails.setManaged(false);

        pricesNow.setVisible(false);
        pricesNow.setManaged(false);


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

    @FXML
    public void menuInfo(ActionEvent event) {
        ElemekTörlése();
        accountDetails.setManaged(true);
        accountDetails.setVisible(true);
        com.oanda.v20.Context ctx = new com.oanda.v20.Context("https://api-fxpractice.oanda.com", "4b1d82ae730663b7ef67158cf1ffc363-efe9da7155f6afdb448d3e89dc81f9a9");
        try {
            AccountSummary summary = ctx.account.summary(new AccountID("101-004-27353979-001")).getAccount();

            // A leíró szövegek és az attribútumok hozzáadása
            String summaryText =
                    "Azonosító: " + summary.getId() + "\n" +
                            "Alias: " + summary.getAlias() + "\n" +
                            "Pénznem: " + summary.getCurrency() + "\n" +
                            "Egyenleg: " + summary.getBalance() + "\n" +
                            "Létrehozta a felhasználó azonosítója: " + summary.getCreatedByUserID() + "\n" +
                            "Létrehozás ideje: " + summary.getCreatedTime() + "\n" +
                            "Garantált stop loss rendszer engedélyezve: " + summary.getGuaranteedStopLossOrderMode() + "\n" +
                            "PL: " + summary.getPl() + "\n" +
                            "Visszaállítható PL: " + summary.getResettablePL() + "\n" +
                            "Visszaállítható PL időpontja: " + summary.getResettablePLTime() + "\n";

            accountDetails.setText(summaryText);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void menuPrices(ActionEvent event) {
        ElemekTörlése();
        pricesNow.setManaged(true);
        pricesNow.setVisible(true);
        com.oanda.v20.Context ctx = new com.oanda.v20.ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("PricePolling").build();
        AccountID accountId = Config.ACCOUNTID;
        List<String> instruments = new ArrayList<>(Arrays.asList("EUR_USD", "USD_JPY", "GBP_USD", "USD_CHF"));

        try {
            com.oanda.v20.pricing.PricingGetRequest request = new com.oanda.v20.pricing.PricingGetRequest(accountId, instruments);
            com.oanda.v20.primitives.DateTime since = null;
            while (true) {
                if (since != null) {
                    System.out.println("Polling since " + since);
                    request.setSince(since);
                }
                com.oanda.v20.pricing.PricingGetResponse resp = ctx.pricing.get(request);
                for (com.oanda.v20.pricing.ClientPrice price : resp.getPrices())
                    System.out.println(price);
                since = resp.getTime();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void menuHistorical(ActionEvent event) {
        com.oanda.v20.Context ctx = new com.oanda.v20.ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("HistorikusAdatok").build();
        try {
            com.oanda.v20.instrument.InstrumentCandlesRequest request = new com.oanda.v20.instrument.InstrumentCandlesRequest(new com.oanda.v20.primitives.InstrumentName("EUR_USD"));
            request.setGranularity(H1);
            request.setCount(10L);	// 10 adat	L: long adattípus
            com.oanda.v20.instrument.InstrumentCandlesResponse resp = ctx.instrument.candles(request);
            for(com.oanda.v20.instrument.Candlestick candle: resp.getCandles())
                System.out.println(candle);
            for(com.oanda.v20.instrument.Candlestick candle: resp.getCandles())
                System.out.println(candle.getTime()+"\t"+candle.getMid().getC());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void menuOpenPos(ActionEvent event) {
        // Ide írd meg a Pozíció nyitás kezelőkódot
    }

    @FXML
    public void menuClosePos(ActionEvent event) {
        // Ide írd meg a Pozíció zárás kezelőkódot
    }

    @FXML
    public void menuPositions(ActionEvent event) {
        // Ide írd meg a Nyitott pozíciók kezelőkódot
    }
}