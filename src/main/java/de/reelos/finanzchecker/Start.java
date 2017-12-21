package de.reelos.finanzchecker;

import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

	private static String EXECUTIONPATH; 
	
    @Override
    public void start( Stage primaryStage ) throws Exception {
        primaryStage.setScene( new Scene( FXMLLoader.load( Start.class.getResource( "/MainFrame.fxml" ) ) ) );
        primaryStage.setTitle( "Finanzchecker" );
        primaryStage.show();
    }

    public static void main( String[] args ) {
    	try {
			EXECUTIONPATH = Start.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().substring(1);
			EXECUTIONPATH = EXECUTIONPATH.substring(0, EXECUTIONPATH.length() - 1);
			EXECUTIONPATH = EXECUTIONPATH.substring(0, EXECUTIONPATH.lastIndexOf("/"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        launch( args );
    }

    public String getExecutionPath() {
    	return String.copyValueOf(EXECUTIONPATH.toCharArray());
    }
}
