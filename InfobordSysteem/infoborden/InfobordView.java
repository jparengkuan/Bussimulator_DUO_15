package infoborden;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class InfobordView{

    private ArrayList<Text> infoRegels;
    private Text tijdLabel;
    private GridPane pane;

    public InfobordView(){
        this.infoRegels = new ArrayList<>();
        this.infoRegels.add(new Text("De eestevolgende bus"));
        this.infoRegels.add(new Text("De tweede bus"));
        this.infoRegels.add(new Text("De derde bus"));
        this.infoRegels.add(new Text("De vierde bus"));
        this.tijdLabel = new Text("00:00:00");
        this.pane = new GridPane();
        createLayout();
    }

    public void createLayout(){
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add(new Label("Voor het laatst bijgewerkt op :"), 0, 0);
        pane.add(tijdLabel, 1, 0);
        for(int regel = 1; regel <= 4; regel++){
            pane.add(new Label(regel + ":"), 0, regel);
            pane.add(infoRegels.get(regel - 1), 1, regel);
        }
    }

    public ArrayList<Text> getInfoRegels(){
        return infoRegels;
    }

    public Text getTijdLabel() {
        return tijdLabel;
    }

    public Parent asParent(){
        return pane;
    }
}
