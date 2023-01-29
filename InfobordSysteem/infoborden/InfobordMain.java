package InfobordSysteem.infoborden;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InfobordMain extends Application {

    private String halte;
    private String richting;

    public InfobordMain(String halte, String richting){
        this.halte = halte;
        this.richting = richting;
    }
    @Override
    public void start(Stage stage) {
        InfobordModel model = new InfobordModel();
        InfobordView view = new InfobordView();
        InfobordController controller = new InfobordController(model, view);
        Scene scene = new Scene(view.asParent(), 500, 150);
        stage.setTitle("Bushalte " + halte + " in richting " + richting);
        stage.setScene(scene);
        stage.show();
        controller.updateView();
    }
}
