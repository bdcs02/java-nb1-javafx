package grafikus.foci;

import grafikus.foci.model.ClubModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.lang.module.Configuration;
import java.sql.*;
import java.util.List;

public class SoccerApplication extends Application {
    private static SessionFactory factory;
    private static Session session;


    public static Session getSession() {
        if (session == null || !session.isOpen()) {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).buildMetadata();
            factory = metadata.getSessionFactoryBuilder().build();
            session = factory.openSession();
        }
        return session;
    }

    public static void closeSession() {
        if (session != null && session.isOpen()) {
            session.close();
        }
        if (factory != null) {
            factory.close();
        }
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SoccerApplication.class.getResource("app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("NB-1 Foci");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
       getSession();
        launch();
    }


}