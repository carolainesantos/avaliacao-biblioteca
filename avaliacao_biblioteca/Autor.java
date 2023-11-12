package avaliacao_biblioteca;
/*  Autor:
  Esta classe deve representar um autor 
  de livros e deve conter um atributo nome. 
  Ela deve ter um construtor para inicializar 
  o nome do autor e um método toString para 
  representar a classe como uma string. */

import java.util.ArrayList;

  public class Autor extends Pessoa {
  private static ArrayList<Autor> autores = new ArrayList<>();

  // Consrtutor
  public Autor(String nome, int idade) {
    super(nome, idade);
    autores.add(this);
  }

  // Criar método set 
  public void setNome(String nome) {
    this.nome = nome;
  }
  public void setAutores(Autor autor) {
    autores.add(autor);
  }

  // Criar método get 
  public String getNome() {
    return this.nome;
  }
  public static ArrayList<Autor> getAutores() {
    return autores;
  }

  // Método toString
  public String toString() {
    return nome; 
  }
}
