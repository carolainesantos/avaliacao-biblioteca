package avaliacao_biblioteca;

public class Pessoa {
  protected String nome;
  protected int idade;
  
  // Construtor 
  public Pessoa(String nome, int idade) {
    this.nome = nome;
    this.idade = idade; 
  }

  // Criar métodos get 
  public String getNome() {
    return nome;
  }
  public int getIdade() {
    return idade; 
  }

  // Criar métodos set
  public void setNome(String nome) {
    this.nome = nome; 
  }
  public void setIdade(int idade) {
    this.idade = idade;
  } 

  public String toString() {
    return "Nome: " + this.nome;
  }
}