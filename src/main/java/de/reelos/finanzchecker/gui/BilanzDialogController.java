package de.reelos.finanzchecker.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BilanzDialogController implements Initializable {

    @FXML
    private TableView<?> viewTable;

    @FXML
    private TableColumn<?, ?> describtionColumn;

    @FXML
    private TableColumn<?, ?> gainColumn;

    @FXML
    private TableColumn<?, ?> lossColumn;

    @FXML
    private MenuItem openItem;

    @FXML
    private MenuItem expoItem;

    @FXML
    private MenuItem closItem;

    @FXML
    private MenuItem addItem;

    @FXML
    private MenuItem remItem;

    @FXML
    private Label gainLabel;

    @FXML
    private Label lossLabel;

    @FXML
    private Label diffLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
