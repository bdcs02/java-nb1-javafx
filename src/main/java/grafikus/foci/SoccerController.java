package grafikus.foci;

import com.oanda.v20.account.AccountID;
import com.oanda.v20.account.AccountSummary;
import grafikus.foci.data.GépiTanulás1;
import grafikus.foci.controller.ParalellController;
import grafikus.foci.data.GépiTanulás2CrossValidation;
import grafikus.foci.model.SoccersModel;
import grafikus.oanda.v20.Config;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Utils;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.Query;
import java.io.*;
import java.net.URL;
import java.util.*;

import static com.oanda.v20.instrument.CandlestickGranularity.H1;

public class SoccerController {
    @FXML
    private Label accountDetails;


    @FXML
    private Label welcomeText;

    @FXML
    private TableView<SoccersModel> tableView;

    @FXML
    private VBox read_two;

    @FXML
    private GridPane gp1;
    @FXML
    private TextField  tfMez;
    @FXML
    private TextField  tfClub;
    @FXML
    private TextField  tfPoszt;
    @FXML
    private TextField  tfUtónév;
    @FXML
    private TextField  tfVezetéknév;
    @FXML
    private TextField  tfSzulido;
    @FXML
    private RadioButton  tfMagyar;
    @FXML
    private RadioButton  tfKüldölfi;
    @FXML
    private TextField  tfÉrték;
    @FXML
    private TextField  tfID;
    @FXML
    private Label  tfIDLabel;
    @FXML
    private Label  Result;
    @FXML
    private Button updateButton;
    @FXML
    private Button createButton;

    @FXML
    private GridPane deleteForm;
    @FXML
    private TextField  deleteID;
    @FXML
    private Label  deleteResult;


    @FXML
    private GridPane restForm;
    @FXML
    private GridPane restPostPutForm;
    @FXML
    private TextField  restName;
    @FXML
    private TextField  restGender;
    @FXML
    private TextField  restEmail;
    @FXML
    private TextField  restStatus;
    @FXML
    private TextField  restID;
    @FXML
    private Label  restIDLabel;
    @FXML
    private Label  restResult;
    @FXML
    private Button restGetButton;
    @FXML
    private Button restDeleteButton;
    @FXML
    private Button restPostButton;
    @FXML
    private Button restPutButton;

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

    @FXML
    private Label historicalPrice;

    @FXML
    private GridPane tobbAlgoritmusGrid;

    @FXML
    private Label tobbAlgoritmus;

    ChoiceBox DataMoreA2CB = new ChoiceBox();


    static String token = "e455a8a36e6c012a6714e438908d8ba40561925e2074b2064af403b50149e976";
    static HttpsURLConnection connection;


    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("playerRate"));
        //currencyPairComboBox.getItems().addAll("EUR/USD", "USD/JPY", "GBP/USD");
        //quantityComboBox.getItems().addAll(1, 5, 10, 50, 100);
        ElemekTörlése();

    }

    @FXML
    private VBox pos_open;

    @FXML
    private Label postionList;
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

        historicalPrice.setVisible(false);
        pricesNow.setManaged(false);

        //pos_open.setVisible(false);
        //pos_open.setManaged(false);

        postionList.setVisible(false);
        postionList.setManaged(false);

        deleteForm.setVisible(false);
        deleteForm.setManaged(false);

        restForm.setVisible(false);
        restForm.setManaged(false);

        restPostPutForm.setVisible(false);
        restPostPutForm.setManaged(false);

        tobbAlgoritmus.setVisible(false);
        tobbAlgoritmus.setManaged(false);

        tobbAlgoritmusGrid.setVisible(false);
        tobbAlgoritmusGrid.setManaged(false);

        DataMoreA2CB.setVisible(false);
        DataMoreA2CB.setManaged(false);
    }

    public void RestFxReset()
    {
        restForm.setVisible(true);
        restForm.setManaged(true);

        restPostPutForm.setVisible(false);
        restPostPutForm.setManaged(false);

        restID.setVisible(false);
        restID.setManaged(false);
        restIDLabel.setVisible(false);
        restIDLabel.setManaged(false);

        restGetButton.setVisible(false);
        restGetButton.setManaged(false);

        restPostButton.setVisible(false);
        restPostButton.setManaged(false);

        restPutButton.setVisible(false);
        restPutButton.setManaged(false);

        restDeleteButton.setVisible(false);
        restDeleteButton.setManaged(false);

        restResult.setVisible(false);
        restResult.setManaged(false);
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
        gp1.setVisible(true);
        gp1.setManaged(true);
        createButton.setVisible(true);
        createButton.setManaged(true);
        updateButton.setVisible(false);
        updateButton.setManaged(false);
        tfID.setVisible(false);
        tfID.setManaged(false);
        tfIDLabel.setVisible(false);
        tfIDLabel.setManaged(false);

    }

    public void Insert()
    {
        Session session = SoccerApplication.getSession();
        Transaction t = session.beginTransaction();

        SoccersModel soccersModel = new SoccersModel();
        soccersModel.playerNumber = Integer.parseInt(tfMez.getText());
        soccersModel.clubId = Integer.parseInt(tfClub.getText());
        soccersModel.playerPost = Integer.parseInt(tfPoszt.getText());
        soccersModel.firstName = tfUtónév.getText();
        soccersModel.lastName = tfVezetéknév.getText();
        soccersModel.birthDate = tfSzulido.getText();
        soccersModel.isHungarian = String.valueOf(tfMagyar.isSelected());
        soccersModel.isForeign = String.valueOf(tfKüldölfi.isSelected());
        soccersModel.playerRate = tfÉrték.getText();

        session.save(soccersModel);

        Result.setText("Sikeres beillesztés!");

        t.commit();
        session.close();
    }

    public void menuDatabaseUpdate(ActionEvent actionEvent) {
        ElemekTörlése();
        gp1.setVisible(true);
        gp1.setManaged(true);
        updateButton.setVisible(true);
        updateButton.setManaged(true);
        createButton.setVisible(false);
        createButton.setManaged(false);
        tfID.setVisible(true);
        tfID.setManaged(true);
        tfIDLabel.setVisible(true);
        tfIDLabel.setManaged(true);
    }

    public void Update()
    {
        Session session = SoccerApplication.getSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("UPDATE SoccersModel set mezszam = :mez, klubid = :club, posztid = :posztid, utonev = :utonev, vezeteknev = :vezeteknev, szulido = :szulido, magyar = :magyar, kulfoldi = :kulfoldi, ertek = :ertek WHERE id = :id");
        query.setParameter("mez", tfMez.getText());
        query.setParameter("club", tfClub.getText());
        query.setParameter("posztid", tfPoszt.getText());
        query.setParameter("utonev",  tfUtónév.getText());
        query.setParameter("vezeteknev", tfVezetéknév.getText());
        query.setParameter("szulido", tfSzulido.getText());
        query.setParameter("magyar", tfMagyar.getText());
        query.setParameter("kulfoldi",  tfKüldölfi.getText());
        query.setParameter("ertek", tfÉrték.getText());
        query.setParameter("id", Integer.parseInt(tfID.getText()));
        int result = query.executeUpdate();
        if(result == 0)
        {
            Result.setText("Sikertelen módosítás!");
        }
        else
        {
            Result.setText("Sikeres módosítás!");
        }
        t.commit();
        session.close();
    }

    public void menuDatabaseDelete(ActionEvent actionEvent) {
        ElemekTörlése();
        deleteForm.setVisible(true);
        deleteForm.setManaged(true);
    }

    public void Delete() {
        Session session = SoccerApplication.getSession();
        Transaction t = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM SoccersModel WHERE id = :id");
        query.setParameter("id", Integer.parseInt(deleteID.getText()));
        int result = query.executeUpdate();
        if(result == 0)
        {
            deleteResult.setText("Sikertelen törlés!");
        }
        else
        {
            deleteResult.setText("Sikeres törlés!");
        }
        t.commit();
        session.close();
    }


    public void menuDataDecision(ActionEvent actionEvent) {
        ElemekTörlése();
        String fájlNév = "src/main/java/grafikus/foci/data/diabetes.arff";
        int classIndex=8;	// 20. oszlopot kell előre jelezni
        new GépiTanulás1(fájlNév, classIndex);

    }

    public void menuDataMoreA(ActionEvent actionEvent) throws Exception {
        ElemekTörlése();

        tobbAlgoritmus.setVisible(true);
        tobbAlgoritmus.setManaged(true);
        tobbAlgoritmus.setText("");
        tobbAlgoritmusGrid.setVisible(true);
        tobbAlgoritmusGrid.setManaged(true);
        String fájlNév = "src/main/java/grafikus/foci/data/diabetes.arff";
        int classIndex=8;	// 20. oszlopot kell előre jelezni
        var j48 = new GépiTanulás2CrossValidation(fájlNév, classIndex, new J48());
        var smo = new GépiTanulás2CrossValidation(fájlNév, classIndex, new SMO());
        var naiveBayes = new GépiTanulás2CrossValidation(fájlNév, classIndex, new NaiveBayes());
        IBk classifier = new IBk();
// 10 legközelebbi szomszéd:
        classifier.setOptions(Utils.splitOptions("-K 10"));
        var ibk = new GépiTanulás2CrossValidation(fájlNév, classIndex, classifier);
        var randomForest = new GépiTanulás2CrossValidation(fájlNév, classIndex, new RandomForest());


        PrintWriter kiir = new PrintWriter("Gépi tanulás.txt");
        j48.WriteToFile(kiir);
        smo.WriteToFile(kiir);
        naiveBayes.WriteToFile(kiir);
        ibk.WriteToFile(kiir);
        randomForest.WriteToFile(kiir);
        kiir.close();

        Map<String, Double> map = new HashMap<String, Double>();
        map.put(j48.classifier.getClass().getName(), j48.CCI);
        map.put(smo.classifier.getClass().getName(), smo.CCI);
        map.put(naiveBayes.classifier.getClass().getName(),naiveBayes.CCI );
        map.put(ibk.classifier.getClass().getName(), ibk.CCI);
        map.put(randomForest.classifier.getClass().getName(), randomForest.CCI);

        double bestCCI = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getValue();
        String best = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
        tobbAlgoritmus.setText("A legjobb eredmény: " + best + "\nCorrectly Classified Instances: " + bestCCI);
    }

    public void menuDataMoreA2(ActionEvent actionEvent) throws Exception {
        ElemekTörlése();
        tobbAlgoritmus.setVisible(true);
        tobbAlgoritmus.setManaged(true);
        tobbAlgoritmus.setText("Válasszon ki egy elemet!");

        tobbAlgoritmusGrid.setVisible(true);
        tobbAlgoritmusGrid.setManaged(true);

        String st[] = { "J48", "SMO", "NaiveBayes", "IBK", "RandomForest" };

        // create a choiceBox
        DataMoreA2CB = new ChoiceBox(FXCollections.observableArrayList(st));

        tobbAlgoritmusGrid.getChildren().add(DataMoreA2CB);
        DataMoreA2CB.setVisible(true);
        DataMoreA2CB.setManaged(true);

        // add a listener
        DataMoreA2CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                if (new_value != null) {

                    String fájlNév = "src/main/java/grafikus/foci/data/diabetes.arff";
                    int classIndex = 8;    // 20. oszlopot kell előre jelezni
                    switch (st[new_value.intValue()]) {
                        case "J48": {
                            var object = new GépiTanulás2CrossValidation(fájlNév, classIndex, new J48());
                            tobbAlgoritmus.setText("A kiválasztott algoritmus: "+ st[new_value.intValue()]
                                    + "\nCorrectly Classified Instances: " + object.CCI
                                    + "\nIncorrectly Classified Instances: " + object.ICI
                                    + "\nTP: " + object.TP
                                    + "\nTN: " + object.TN
                                    + "\nFP: " + object.FP
                                    + "\nFN: " + object.FN);
                            break;
                        }
                        case "SMO": {
                            var object = new GépiTanulás2CrossValidation(fájlNév, classIndex, new SMO());
                            tobbAlgoritmus.setText("A kiválasztott algoritmus: "+ st[new_value.intValue()]
                                    + "\nCorrectly Classified Instances: " + object.CCI
                                    + "\nIncorrectly Classified Instances: " + object.ICI
                                    + "\nTP: " + object.TP
                                    + "\nTN: " + object.TN
                                    + "\nFP: " + object.FP
                                    + "\nFN: " + object.FN);
                            break;
                        }
                        case "NaiveBayes": {
                            var object = new GépiTanulás2CrossValidation(fájlNév, classIndex, new NaiveBayes());
                            tobbAlgoritmus.setText("A kiválasztott algoritmus: "+ st[new_value.intValue()]
                                    + "\nCorrectly Classified Instances: " + object.CCI
                                    + "\nIncorrectly Classified Instances: " + object.ICI
                                    + "\nTP: " + object.TP
                                    + "\nTN: " + object.TN
                                    + "\nFP: " + object.FP
                                    + "\nFN: " + object.FN);
                            break;
                        }
                        case "IBK": {
                            IBk classifier = new IBk();
                            try {
                                classifier.setOptions(Utils.splitOptions("-K 10"));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            var object = new GépiTanulás2CrossValidation(fájlNév, classIndex, classifier);
                            tobbAlgoritmus.setText("A kiválasztott algoritmus: "+ st[new_value.intValue()]
                                    + "\nCorrectly Classified Instances: " + object.CCI
                                    + "\nIncorrectly Classified Instances: " + object.ICI
                                    + "\nTP: " + object.TP
                                    + "\nTN: " + object.TN
                                    + "\nFP: " + object.FP
                                    + "\nFN: " + object.FN);
                            break;
                        }
                        case "RandomForest": {
                            var object = new GépiTanulás2CrossValidation(fájlNév, classIndex, new RandomForest());
                            tobbAlgoritmus.setText("A kiválasztott algoritmus: "+ st[new_value.intValue()]
                                    + "\nCorrectly Classified Instances: " + object.CCI
                                    + "\nIncorrectly Classified Instances: " + object.ICI
                                    + "\nTP: " + object.TP
                                    + "\nTN: " + object.TN
                                    + "\nFP: " + object.FP
                                    + "\nFN: " + object.FN);
                            break;
                        }
                    }

                } else {

                    tobbAlgoritmus.setText("Válasszon ki egy elemet!");
                }
            }
        });
    }

    public void menuParalell(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("paralell.fxml"));
        Parent root = loader.load();
        ParalellController controller = loader.getController();

        Scene scene = new Scene(root);


        Stage stage = new Stage();


        stage.setTitle("Párhuzamos Végrehajtás");


        stage.setScene(scene);

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
        ElemekTörlése();
        RestFxReset();
        restPostButton.setVisible(true);
        restPostButton.setManaged(true);
        restPostPutForm.setVisible(true);
        restPostPutForm.setManaged(true);
    }

    public void RestPost() throws IOException
    {
        System.out.println("\nPOST...");
        URL postUrl = new URL("https://gorest.co.in/public/v1/users");
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("POST");
        segéd1();
        String params = "{\"name\":\""+restName.getText()+"\", \"gender\":\""+restGender.getText()+"\", \"email\":\""+restEmail.getText()+"\", \"status\":\""+restStatus.getText()+"\"}";
        segéd2(params);
        String result = segéd3(HttpsURLConnection.HTTP_CREATED);
        restResult.setText(result);
        restResult.setVisible(true);
        restResult.setManaged(true);
    }

    static void segéd1(){
        // Setting Header Parameters
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
    }

    static void segéd2(String params) throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        wr.write(params);
        wr.close();
        connection.connect();
    }


    static String segéd3(int code) throws java.io.IOException {

        int statusCode = connection.getResponseCode();   // Getting response code
        System.out.println("statusCode: "+statusCode);
        String result = "";
        if (statusCode == code) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            while ((readLine = in.readLine()) != null)
                jsonResponseData.append(readLine);
            in.close();
            result = "List of users: " + jsonResponseData.toString();
        } else {
            result = "Hiba! \nStatuscode: " + statusCode;
        }
        connection.disconnect();
        return result;
    }


    public void menuRest1Read(ActionEvent actionEvent) {
        ElemekTörlése();
        RestFxReset();
        restGetButton.setVisible(true);
        restGetButton.setManaged(true);
        restID.setVisible(true);
        restID.setManaged(true);
        restIDLabel.setVisible(true);
        restIDLabel.setManaged(true);
    }

    public void RestGet() throws java.io.IOException
    {
        String url = "https://gorest.co.in/public/v1/users";
        if(restID.getText()!=null)
            url=url+"/"+restID.getText();
        System.out.println(url);
        URL usersUrl = new URL(url); // Url for making GET request
        connection = (HttpsURLConnection) usersUrl.openConnection();
        connection.setRequestMethod("GET");  // Set request method
        if(restID.getText()!=null)
            connection.setRequestProperty("Authorization", "Bearer " + token);
        String result = segéd3(HttpsURLConnection.HTTP_OK);
        restResult.setText(result);
        restResult.setVisible(true);
        restResult.setManaged(true);
    }

    public void menuRest1Update(ActionEvent actionEvent)  {
        ElemekTörlése();
        RestFxReset();
        restPutButton.setVisible(true);
        restPutButton.setManaged(true);
        restID.setVisible(true);
        restID.setManaged(true);
        restIDLabel.setVisible(true);
        restIDLabel.setManaged(true);
        restPostPutForm.setVisible(true);
        restPostPutForm.setManaged(true);
    }

    public void RestPut() throws IOException {
        System.out.println("\nPUT...");
        String url = "https://gorest.co.in/public/v1/users";
        if(restID.getText()!=null)
            url=url+"/"+restID.getText();

        System.out.println(url);
        URL postUrl = new URL(url);
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("PUT");
        segéd1();
        String params = "{\"name\":\""+restName.getText()+"\", \"gender\":\""+restGender.getText()+"\", \"email\":\""+restEmail.getText()+"\", \"status\":\""+restStatus.getText()+"\"}";
        segéd2(params);
        String result = segéd3(HttpsURLConnection.HTTP_OK);
        restResult.setText(result);
        restResult.setVisible(true);
        restResult.setManaged(true);
    }

    public void menuRest1Delete(ActionEvent actionEvent)  {
        ElemekTörlése();
        RestFxReset();
        restForm.setVisible(true);
        restForm.setManaged(true);
        restDeleteButton.setVisible(true);
        restDeleteButton.setManaged(true);
        restID.setVisible(true);
        restID.setManaged(true);
        restIDLabel.setVisible(true);
        restIDLabel.setManaged(true);
    }

    public void RestDelete() throws IOException {
        System.out.println("\nDELETE...");
        String url = "https://gorest.co.in/public/v1/users";
        if(restID.getText()!=null)
            url=url+"/"+restID.getText();

        System.out.println(url);
        URL postUrl = new URL(url);
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("DELETE");
        segéd1();
        String result = segéd3(HttpsURLConnection.HTTP_NO_CONTENT);
        restResult.setText(result);
        restResult.setVisible(true);
        restResult.setManaged(true);
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

        // Indíts egy új szálat az árlekérdezéshez
        Thread pricePollingThread = new Thread(() -> {
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
                    for (com.oanda.v20.pricing.ClientPrice price : resp.getPrices()) {
                        // Az árat a Label-be történő beállítás a Platform.runLater() segítségével
                        Platform.runLater(() -> {
                            pricesNow.setText("Price: " + price.getBids()); // Itt a bid árat használom példaként
                        });
                    }
                    since = resp.getTime();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        pricePollingThread.setDaemon(true);
        pricePollingThread.start();
    }


    @FXML
    public void menuHistorical(ActionEvent event) {
        ElemekTörlése();
        historicalPrice.setManaged(true);
        historicalPrice.setVisible(true);
        com.oanda.v20.Context ctx = new com.oanda.v20.ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("HistorikusAdatok").build();
        try {
            com.oanda.v20.instrument.InstrumentCandlesRequest request = new com.oanda.v20.instrument.InstrumentCandlesRequest(new com.oanda.v20.primitives.InstrumentName("EUR_USD"));
            request.setGranularity(H1);
            request.setCount(10L);
            com.oanda.v20.instrument.InstrumentCandlesResponse resp = ctx.instrument.candles(request);

            StringBuilder historicalData = new StringBuilder();
            for(com.oanda.v20.instrument.Candlestick candle: resp.getCandles()) {
                historicalData.append(candle.getTime()).append("\t").append(candle.getMid().getC()).append("\n");
            }

            historicalPrice.setText(historicalData.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private ComboBox<String> currencyPairComboBox;

    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML
    private ComboBox<String> directionComboBox;

    @FXML
    private Button openPositionButton;
    @FXML
    public void menuOpenPos(ActionEvent event) {
       /* ElemekTörlése();
        pos_open.setManaged(true);
        pos_open.setManaged(true);

        com.oanda.v20.Context ctx = new com.oanda.v20.ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("StepByStepOrder").build();
       AccountID accountId = Config.ACCOUNTID;
        if(true) {
            System.out.println("Place a Market Order");
            String selectedInstrument = currencyPairComboBox.getValue();
            com.oanda.v20.primitives.InstrumentName instrument = new com.oanda.v20.primitives.InstrumentName(selectedInstrument);
            try {
                com.oanda.v20.order.OrderCreateRequest request = new com.oanda.v20.order.OrderCreateRequest(accountId);
                com.oanda.v20.order.MarketOrderRequest marketorderrequest = new com.oanda.v20.order.MarketOrderRequest();
                marketorderrequest.setInstrument(instrument);
// Ha pozitív, akkor LONG, ha negatív, akkor SHORT:
                String selectedDirection = directionComboBox.getValue();
                double units = 10.0; // Alapértelmezett érték
                if ("Eladás".equals(selectedDirection)) {
                    units = -10.0; // Ha "Eladás" irányt választott, akkor negatív "units" érték
                }
                marketorderrequest.setUnits(units);
                request.setOrder(marketorderrequest);
                com.oanda.v20.order.OrderCreateResponse response = ctx.order.create(request);
                System.out.println("tradeId: "+response.getOrderFillTransaction().getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Done");

        */
    }

    @FXML
    public void menuClosePos(ActionEvent event) {
        // Ide írd meg a Pozíció zárás kezelőkódot

    }

    @FXML
    public void menuPositions(ActionEvent event) throws com.oanda.v20.ExecuteException, com.oanda.v20.RequestException {
        // Ide írd meg a Nyitott pozíciók kezelőkódot
        ElemekTörlése();
        postionList.setVisible(true);
        postionList.setManaged(true);
        com.oanda.v20.Context ctx = new com.oanda.v20.ContextBuilder(Config.URL).setToken(Config.TOKEN).setApplication("StepByStepOrder").build();
        AccountID accountId = Config.ACCOUNTID;
        postionList.setText("Nyitott tradek:\n");
        List<com.oanda.v20.trade.Trade> trades = ctx.trade.listOpen(accountId).getTrades();
        for (com.oanda.v20.trade.Trade trade : trades) {
            String tradeInfo = trade.getId() + "\t" + trade.getInstrument() + "\t" + trade.getOpenTime() + "\t" + trade.getCurrentUnits() + "\t" + trade.getPrice() + "\t" + trade.getUnrealizedPL() + "\n";
            postionList.setText(postionList.getText() + tradeInfo);
        }

    }
}
