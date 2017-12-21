package de.reelos.finanzchecker.gui;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class MainFrameController implements Initializable {

	@FXML
	private ListView<Double> ertragListe;

	@FXML
	private ListView<Double> verlustListe;

	@FXML
	private TextField ertragFeld;

	@FXML
	private TextField verlustFeld;

	@FXML
	private Label bilanzFeld;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ListChangeListener<Double> lcl = new ListChangeListener<Double>() {

            @Override
            public void onChanged( javafx.collections.ListChangeListener.Change<? extends Double> c ) {
                double v = verlustListe.getItems().stream().mapToDouble( Double::doubleValue ).sum();
                double e = ertragListe.getItems().stream().mapToDouble( Double::doubleValue ).sum();
                bilanzFeld.setText( MessageFormat.format( "{0,number,currency}", e - v ) );
                double diff = e - v;
                if(diff > 0)
                	bilanzFeld.setTextFill(Color.GREEN);
                else if (diff < 0)
                	bilanzFeld.setTextFill(Color.RED);
                else
                	bilanzFeld.setTextFill(Color.BLACK);
            }
        };
        ertragListe.getItems().addListener( lcl );
        verlustListe.getItems().addListener( lcl );
        //Callback<ListView<Double>,ListCell<Double>> baseCall = ertragListe.getCellFactory();
        
        ertragListe.setCellFactory( lv -> new ListCell<Double>() {
			public void updateItem(Double friend, boolean empty) {
				super.updateItem(friend, empty);
				if (empty) {
					setText(null);
				} else {
					setText(MessageFormat.format( "{0,number,currency}", friend ));
					setTextFill(Color.GREEN);
				}
			}
        });
        
        verlustListe.setCellFactory( lv -> new ListCell<Double>() {
			public void updateItem(Double friend, boolean empty) {
				super.updateItem(friend, empty);
				if(empty) {
					setText(null);
				} else {
					setText(MessageFormat.format( "{0,number,currency}", friend ));
					setTextFill(Color.RED);
				}
			}
    });
	}

	@FXML
	void onErtagButton(ActionEvent event) {
		if (ertragFeld.getText().matches("\\d+([.,]\\d+)?")) {
			String ertrag = ertragFeld.getText().replace(',', '.');
			ertragListe.getItems().add(Double.valueOf(ertrag));
		}
	}

	@FXML
	void onVerlustButton(ActionEvent event) {
		if (verlustFeld.getText().matches("\\d+([.,]\\d+)?")) {
			String verlust = verlustFeld.getText().replace(',', '.');
			verlustListe.getItems().add(Double.valueOf(verlust));
		}
	}
}
