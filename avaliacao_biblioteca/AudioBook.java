package avaliacao_biblioteca;

import java.util.ArrayList;

public class AudioBook extends MidiaDigital {
  protected String narrador;
  protected static ArrayList<AudioBook> audioBooks = new ArrayList<>();

  // construtor 
  public AudioBook(String titulo, String dtLacamento, Boolean disponivel, String narrador) {
    super(titulo, dtLacamento, disponivel);
    this.narrador = narrador;
    audioBooks.add(this);
  }

  // metodo get
  public String getTitulo() {
    return this.titulo; 
  }
  public String getDtLancamento() {
    return this.dtLancamento;
  }
  public Boolean getDisponivel() {
    return this.disponivel;
  }
  public String getNarrador() {
    return this.narrador;
  }
  public static ArrayList<AudioBook> getAudioBooks() {
    return audioBooks;
  }

  // metodo set 
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public void setDtLancamento(String dtLancameto) {
    this.dtLancamento = dtLancameto;
  }
  public void setDisponivel(Boolean disponivel) {
    this.disponivel = disponivel;
  }
  public void setNarrador(String narrador) {
    this.narrador = narrador;
  }
  public void setAudioBooks(AudioBook audioBook) {
    audioBooks.add(audioBook);
  }

  // Mostrar AudioBooks
  public void mostrarAudioBooks() {
    int i = 0;
    for (AudioBook audioBook : audioBooks) {
      System.out.println(i + " - " + audioBook.toString());
      i++;
    }
  }

  // Comprar
  public void comprar() throws Exception {
    if (!this.disponivel) {
      throw new Exception("Lamento, AudioBook indisponível!");
    }
    this.disponivel = false;
    System.out.println("\u001B[32m" + "\n Oba, você acaba de comprar seu novo AudioBook!" + "\u001B[00m");
  }

  public String toString() {
    return "Titulo do AudioBook: " + this.titulo + ", " + ", Disponibilidade: " + (this.disponivel ? "Disponivel" : "Idisponivel");
  }
}
