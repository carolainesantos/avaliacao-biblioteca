package avaliacao_biblioteca;

import java.util.ArrayList;

public class Filme extends Midia {
  protected String genero;
  protected static ArrayList<Filme> filmes = new ArrayList<>();

  // Criar construtor 
  public Filme(String titulo, String genero, boolean disponivel) {
    super(titulo, genero, disponivel);
    filmes.add(this);
  }

// Criar método get
  public String getTitulo() {
    return this.titulo;
  }
  public String getGenero() {
    return this.genero;
  }
  public boolean getDisponivel() {
    return this.disponivel;
  }
  public static ArrayList<Filme> getFilmes() {
    return filmes;
  }

  // Criar método set 
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public void setGenero(String genero) {
    this.genero = genero;
  }
  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }
  public void setFilmes(Filme filme) {
    filmes.add(filme);
  }

  // Mostrar Filmes
  public void mostrarFilmes() {
    int i = 0;
    for (Filme filme : filmes) {
      System.out.println(i + " - " + filme.toString());
      i++;
    }
  }

  // Emprestar Filme
  public void emprestarF() throws Exception {
    if (!this.disponivel) {
      throw new Exception("Lamento, filme indisponível!");
    }
    this.disponivel = false;
    System.out.println("\u001B[32m" + "\nFilme emprestado com sucesso!" + "\u001B[00m");
  }

  // Método devolver filme
  public void devolverF() {
    if (this.disponivel) {
      System.out.println("Filme já foi devolvido.");
    }
    this.disponivel = true;
    System.out.println("\u001B[32m" + "\nFilme devolvido com sucesso!" + "\u001B[00m");
  }

  // Criar método toString pra filme
  public String toString() {
    return "Titulo do Filme: " + this.titulo + ", " + this.genero + ", Disponibilidade: " + (this.disponivel ? "Disponivel" : "Idisponivel");
  }
}
