package avaliacao_biblioteca;
/* Biblioteca: 
  Esta classe deve representar a biblioteca 
  e deve conter uma lista de livros. 
  Ela deve ter métodos para adicionar livros
  à biblioteca e listar todos os livros 
  disponíveis na biblioteca. */

import java.util.ArrayList;

public class Biblioteca {
  private String nome;
  private ArrayList<Livro> livros;
  private ArrayList<Filme> filmes = new ArrayList<>();
  // Criar lista bibliotecas
  private static ArrayList<Biblioteca> bibliotecas = new ArrayList<>();

  // Criar construtor 
  public Biblioteca(String nome) {
    this.nome = nome;
    this.livros = new ArrayList<>();

    bibliotecas.add(this); // adiciona biblioteca
  }
  // Criar método get
  public String getNome() {
    return this.nome;
  }
  public ArrayList<Livro> getLivros(){
    return this.livros;
  }
  public ArrayList<Filme> getFilmes(){
    return this.filmes;
  }
  public static ArrayList<Biblioteca> getBibliotecas() {
    return bibliotecas;
  }

  // Criar método set
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setLivros(Livro livro) {
    this.livros.add(livro);
  }
  public void setFilmes(Filme filme) {
    filmes.add(filme);
  }
  public void setBibliotecas(Biblioteca biblioteca) {
    bibliotecas.add(biblioteca);
  }

  // Criar metodo toString
  public String toString() {
    return "Nome: " + this.nome;
  }

  // Mostrar todos os livros
  public void mostrarLivros() {
    int i = 0;
    for (Livro livro : this.livros) {
      System.out.println(i + " - " + livro.toString());
      i++;
    }
  }

  public static void mostrarBibliotecas() {
    for(int i = 0; i < bibliotecas.size(); i++) {
        Biblioteca biblioteca = bibliotecas.get(i);
        System.out.println(i + " - " + biblioteca.toString());
        biblioteca.mostrarLivros();
    }
  }
}