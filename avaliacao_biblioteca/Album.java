package avaliacao_biblioteca;

import java.util.ArrayList;

public class Album extends MidiaDigital {
  protected int qtdFaixa;
  protected static ArrayList<Album> albums = new ArrayList<>();

  // Construtor
  public Album(String titulo, String dtLacamento, Boolean disponivel, int qtdFaixa) {
    super(titulo, dtLacamento, disponivel);
    this.qtdFaixa = qtdFaixa;
    albums.add(this);
  }

  // Metodo set
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public void setDtLancamento(String dtLancameto) {
    this.dtLancamento = dtLancameto;
  }
  public void setDisponivel(Boolean disponivel) {
    this.disponivel = disponivel;
  }
  public void setQtdFaixa(int qtdFaixa) {
    this.qtdFaixa = qtdFaixa;
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
  public int getQtdFaixa() {
    return this.qtdFaixa;
  }
  public static ArrayList<Album> getAlbums() {
    return albums;
  }

  // Mostrar Albums
  public void mostrarAlbums() {
    int i = 0;
    for (Album album : albums) {
      System.out.println(i + " - " + album.toString());
      i++;
    }
  }

  // Emprestar Album
  public void emprestarAlbum() throws Exception {
    if (!this.disponivel) {
      throw new Exception("Lamento, album indisponível!");
    }
    this.disponivel = false;
    System.out.println("\u001B[32m" + "\nAlbum emprestado com sucesso!" + "\u001B[00m");
  }

  // Método devolver album
  public void devolverAlbum() {
    if (this.disponivel) {
      System.out.println("Album já foi devolvido.");
    }
    this.disponivel = true;
    System.out.println("\u001B[32m" + "\nAlbum devolvido com sucesso!" + "\u001B[00m");
  }

  // Metodo toString
  public String toString() {
    return "Titulo do AudioBook: " + this.titulo + ", " + ", Disponibilidade: " + (this.disponivel ? "Disponivel" : "Idisponivel");
  }
}
