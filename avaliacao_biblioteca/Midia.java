package avaliacao_biblioteca;

import java.util.ArrayList;

public class Midia {
  protected String titulo;
  protected boolean disponivel;
  protected static ArrayList<Midia> midias = new ArrayList<>();
  
  // Criar método construtor
  public Midia(String titulo, String genero, boolean disponivel) {
    this.titulo = titulo;  
    this.disponivel = disponivel;
    midias.add(this);
  }

  // Criar método get 
  public static ArrayList<Midia> getMidias() {
    return midias;
  }
  // Criar método set 
  public void setMidias(Midia midia) {
    midias.add(midia);
  }

  // Criar  toString
  public String toString() {
    return "Titulo: " + this.titulo;
  }
}