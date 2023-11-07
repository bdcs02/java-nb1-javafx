<<<<<<< HEAD
package grafikus.foci.controller;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ParalellController {
    @FXML
    private Button startButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    public ParalellController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void startButtonClicked(ActionEvent actionEvent) {
        label1.setText("Label 1: -");
        label2.setText("Label 2: -");
        startParallelTasks();
    }

    private void startParallelTasks() {
        Task<Void> firstTask = createTask(1, label1);
        Task<Void> secondTask = createTask(2, label2);

        new Thread(firstTask).start();
        new Thread(secondTask).start();
    }

    private Task<Void> createTask(int sleepSeconds, Label label) {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(sleepSeconds * 1000);
                    final int finalI = i;
                    Platform.runLater(() -> {
                        label.setText("Label " + (label == label1 ? 1 : 2) + ": " + finalI);
                    });
                }
                return null;
            }
        };
    }
}
=======
package grafikus.foci.controller;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ParalellController {
    @FXML
    private Button startButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    public ParalellController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void startButtonClicked(ActionEvent actionEvent) {
        label1.setText("Label 1: -");
        label2.setText("Label 2: -");
        startParallelTasks();
    }

    private void startParallelTasks() {
        Task<Void> firstTask = createTask(1, label1);
        Task<Void> secondTask = createTask(2, label2);

        new Thread(firstTask).start();
        new Thread(secondTask).start();
    }

    private Task<Void> createTask(int sleepSeconds, Label label) {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(sleepSeconds * 1000);
                    final int finalI = i;
                    Platform.runLater(() -> {
                        label.setText("Label " + (label == label1 ? 1 : 2) + ": " + finalI);
                    });
                }
                return null;
            }
        };
    }
}
>>>>>>> 1b60a021d8ee0a46b6f9803e6cf6b66e7314ef1b
