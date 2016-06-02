package de.reelos.finanzchecker;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Start extends Application implements Initializable {

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
    public void initialize( URL location, ResourceBundle resources ) {
        ListChangeListener<Double> lcl = new ListChangeListener<Double>() {

            @Override
            public void onChanged( javafx.collections.ListChangeListener.Change<? extends Double> c ) {
                double v = verlustListe.getItems().stream().mapToDouble( Double::doubleValue ).sum();
                double e = ertragListe.getItems().stream().mapToDouble( Double::doubleValue ).sum();
                bilanzFeld.setText( MessageFormat.format( "{0,number,currency}", e - v ) );
            }
        };
        ertragListe.getItems().addListener( lcl );
        verlustListe.getItems().addListener( lcl );
    }

    @Override
    public void start( Stage primaryStage ) throws Exception {
        primaryStage.setScene( new Scene( FXMLLoader.load( Start.class.getResource( "/MainFrame.fxml" ) ) ) );
        primaryStage.setTitle( "Finanzchecker" );
        primaryStage.show();
    }

    @FXML
    void onErtagButton( ActionEvent event ) {
        if ( ertragFeld.getText().matches( "\\d+([.,]\\d+)?" ) ) {
            String ertrag = ertragFeld.getText().replace( ',', '.' );
            ertragListe.getItems().add( Double.valueOf( ertrag ) );
        }
    }

    @FXML
    void onVerlustButton( ActionEvent event ) {
        if ( verlustFeld.getText().matches( "\\d+([.,]\\d+)?" ) ) {
            String verlust = verlustFeld.getText().replace( ',', '.' );
            verlustListe.getItems().add( Double.valueOf( verlust ) );
        }
    }

    public static void main( String[] args ) {
        launch( args );
    }

}
