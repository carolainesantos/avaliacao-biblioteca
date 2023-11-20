package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import banco_conect.ConexaoBanco;

public class Filme extends Midia {
  private int id;
  protected String genero;
  protected static ArrayList<Filme> filmes = new ArrayList<>();

  // Criar construtor 
  public Filme() {}
  public Filme(String titulo, String genero, boolean disponivel) {
    super(titulo, genero, disponivel);
    this.titulo = titulo;
    this.genero = genero;
    this.disponivel = disponivel;
    criarNoBanco(this);
  }
  public int criarNoBanco(Filme filme){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO filme (titulo, genero, disponivel) values (?, ?, ?)");
      ps.setString(1, filme.titulo);
      ps.setString(2, filme.genero);
      ps.setBoolean(3, filme.disponivel);
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  public static Filme getFilmeById(int id){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM filme where id_filme = ?");
      query.setInt(1, id);
      ResultSet resultado = query.executeQuery();
      Filme filme = new Filme();
      while (resultado.next()) {
        filme.id = resultado.getInt("id_filme");
        filme.titulo = resultado.getString("titulo");
      }
      conexao.close();
      return filme;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

//=====================================================//
// Criar método get
  public int getIdFilme() {
    return this.id;
  }
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
//=====================================================//

  // Criar método set 
  public void setIdFilme(int idFilme) {
    this.id = idFilme;
  }
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
//=====================================================//


  // Mostrar Filmes
  public static void mostrarFilmes() {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("SELECT * FROM filme");
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        System.out.println("\nID: " + resultado.getInt("id_filme") + " - " + "\nTitulo: " + resultado.getString("titulo") + " - " + "\nGenero: " + resultado.getString("genero")  + " - " + "\nDisponibilidade: " + resultado.getBoolean("disponivel"));
      }
      conexao.close();
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

   // Metodo emprestar 
   public static void emprestar(int id_biblioteca, int id_filme) throws Exception {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM Filme_Bibli WHERE id_filme = ? AND id_biblioteca = ?");
      query.setInt(1, id_filme);
      query.setInt(2, id_biblioteca);
      ResultSet resultado = query.executeQuery();
      Filme filme = new Filme();
      while(resultado.next()) {
        filme.id = resultado.getInt("id_filme");
        filme.disponivel = resultado.getBoolean("disponivel");
      } 
      if (!filme.disponivel) {
        throw new Exception("\nLamento, filme indisponível!");
      }
      PreparedStatement queryUpd = conexao.prepareStatement("UPDATE Filme_Bibli SET disponivel = 0 WHERE id_filme = ? AND id_biblioteca = ?");
      queryUpd.setInt(1, id_filme);
      queryUpd.setInt(2, id_biblioteca);
      queryUpd.executeUpdate();
      conexao.close();
      System.out.println("\nFilme emprestado com sucesso!");
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
}

  // Método devolver filme
  public static void devolver(int id_biblioteca, int id_filme) throws Exception {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM Filme_Bibli WHERE id_filme = ? AND id_biblioteca = ?");
      query.setInt(1, id_filme);
      query.setInt(2, id_biblioteca);
      ResultSet resultado = query.executeQuery();
      Filme filme = new Filme();
      
      while(resultado.next()) {
        filme.id = resultado.getInt("id_filme");
        filme.disponivel = resultado.getBoolean("disponivel");
      } 
      if (filme.disponivel) {
        throw new Exception("\nFilme ja foi devolvido!");
      }
      PreparedStatement queryUpd = conexao.prepareStatement("UPDATE Filme_Bibli SET disponivel = 1 WHERE id_filme = ? AND id_biblioteca = ?");
      queryUpd.setInt(1, id_filme);
      queryUpd.setInt(2, id_biblioteca);
      queryUpd.executeUpdate();
      conexao.close();
      System.out.println("\nFilme devolvido com sucesso!");
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Criar método toString pra filme
  public String toString() {
    return "Titulo do Filme: " + this.titulo + ", " + this.genero + ", Disponibilidade: " + (this.disponivel ? "Disponivel" : "Idisponivel");
  }
}
