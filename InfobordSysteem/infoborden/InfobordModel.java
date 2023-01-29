package infoborden;

public class InfobordModel {
    private String[] infoRegels;
    private String tijd;

    public InfobordModel(){
        this.infoRegels = new String[4];
        this.tijd = "";
    }

    public String[] getInfoRegels() {
        return infoRegels;
    }

    public void setInfoRegels(String[] regels){
        this.infoRegels = regels;
    }

    public String getTijd() {
        return tijd;
    }

    public void setTijd(String tijd) {
        this.tijd = tijd;
    }
}
