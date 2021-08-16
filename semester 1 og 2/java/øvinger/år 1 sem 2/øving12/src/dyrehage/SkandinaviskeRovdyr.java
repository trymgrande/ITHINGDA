package dyrehage;

public interface SkandinaviskeRovdyr{
    String getNavn();
    int getFdato();
    int getAlder();
    String getAdresse();
    void flytt(String nyAdresse);
    String skrivUtInfo();
}