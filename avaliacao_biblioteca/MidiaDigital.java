package avaliacao_biblioteca;
import java.util.ArrayList;

public class MidiaDigital {
  protected String titulo; 
  protected String dtLancamento;
  protected Boolean disponivel;
  protected static ArrayList<MidiaDigital> midiaDigitais = new ArrayList<>();

  // construtor 
  public MidiaDigital(String titulo, String dtLacamento, Boolean disponivel) {
    this.titulo = titulo;
    this.dtLancamento = dtLacamento;
    this.disponivel = disponivel;
    midiaDigitais.add(this);
  }

  // metodos get
  public static ArrayList<MidiaDigital> getMidiaDigitais() {
    return midiaDigitais;
  }

  // metodos set
  public void setMidiaDigitais(MidiaDigital midiaDigital) {
    midiaDigitais.add(midiaDigital);
  }

   // Criar  toString
   public String toString() {
    return "Titulo: " + this.titulo;
  }
}
