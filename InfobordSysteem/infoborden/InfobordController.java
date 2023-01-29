package infoborden;

import javafx.application.Platform;

import java.util.ArrayList;

public class InfobordController {

    private InfobordView infobordView;
    private InfobordModel infobordModel;
    private Berichten berichten;

    public InfobordController(InfobordModel model, InfobordView view){
        this.berichten = new Berichten();
        initializeModel(model);
        initializeView(view);
    }

    public void initializeModel(InfobordModel model){
        if(this.infobordModel != null){
            throw new IllegalStateException("Model is already initialized.");
        }

        this.infobordModel = model;

    }

    public void initializeView(InfobordView view){
        if(this.infobordView != null){
            throw new IllegalStateException("View is already initialized.");
        }

        this.infobordView = view;
        thread(new ListenerStarter("JSONBerichten", this, berichten), false);
    }

    public String[] getInfoRegels(){
        return infobordModel.getInfoRegels();
    }

    public void setInfoRegels(String[] infoRegels){
        infobordModel.setInfoRegels(infoRegels);
    }

    public String getTijd(){
        return infobordModel.getTijd();
    }

    public void setTijd(String tijd){
        infobordModel.setTijd(tijd);
    }

    public void updateView(){
        Runnable updater = new Runnable() {
            @Override
            public void run() {
                if(berichten.hetBordMoetVerverst()){
                    String[] huidigeRegels = berichten.repaintInfoBordValues();
//                    InfobordTijdFuncties tijdFuncties = new InfobordTijdFuncties();
//                    String tijd = tijdFuncties.getCentralTime().toString();
//                    setTijd(tijd);
//                    infobordView.getTijdLabel().setText(tijd);
                    String[] infoRegels = getInfoRegels();
                    setInfoRegels(huidigeRegels);
                    for(int i = 0; i < 4; i++){
                        infobordView.getInfoRegels().get(i).setText(infoRegels[i]);
                    }
                }
            }
        };
        Platform.runLater(updater);
    }

    public void thread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }
}
