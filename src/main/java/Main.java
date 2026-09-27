import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML de la vista principal
            URL fxmlLocation = getClass().getResource("/view/main.fxml");
            if (fxmlLocation == null) {
                System.err.println("Error: No se encontró el archivo /view/main.fxml. Verifica que esté en src/main/resources/view/.");
                return;
            }

            Parent root = FXMLLoader.load(fxmlLocation);

            // Configurar la escena y ventana principal
            Scene scene = new Scene(root, 920, 680);
            primaryStage.setTitle("RentCar - Sistema de Administración y Alquiler");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar la interfaz gráfica: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}