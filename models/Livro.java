package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import banco_conect.ConexaoBanco;
public class Livro {
  private int id;   
  private String titulo;
  private Autor autor;
  private boolean disponivel;

  // Construtor
  public Livro() {}
  public Livro(String titulo, Autor autor, boolean disponivel) {
    this.titulo = titulo;
    this.autor = autor;
    this.disponivel = disponivel;
    // Adiciona livros 
    criarNoBanco(this);
  }


  public int criarNoBanco(Livro livro){
    try{
      Connection conexao = ConexaoBanco.conectar();
      System.out.println(livro.autor);
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO livro (titulo, disponivel, id_autor) values (?, ?, ?)");
      ps.setString(1, livro.titulo);
      ps.setBoolean(2, livro.disponivel);
      ps.setInt(3, livro.autor.getIdAutor());
      int rs = ps.executeUpdate(); // desta linha pulou pra 45
      System.out.println(rs);
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  public static Livro getLivroById(int id){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM livro where id_livro = ?");
      query.setInt(1, id);
      ResultSet resultado = query.executeQuery();
      Livro livro = new Livro();
      while (resultado.next()) {
        livro.id = resultado.getInt("id_livro");
        livro.titulo = resultado.getString("titulo");
      }
      conexao.close();
      return livro;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }
  
    // Criar método Get 
  public int getIdLivro() {
    return this.id;
  }
  public String getTitulo() {
    return this.titulo;
  }
  public Autor getAutor() {
    return this.autor;
  }
  public boolean getDisponivel() {
    return this.disponivel;
  }
  
//====================================================\\

    // Criar método Set 
  public void setIdLivro(int idLivro) {
    this.id = idLivro;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public void setAutor(Autor autor) {
    this.autor = autor;
  }
  public void setDisponivel(boolean disponivel) {
    this.disponivel = disponivel;
  }
  

  // Mostrar Livros 
  public static void mostrarLivros() {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("SELECT * FROM livro");
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        System.out.println("\nID: " +resultado.getInt("id_livro") + "\nTitulo: "+resultado.getString("titulo") + "\nDisponibilidade: " +resultado.getBoolean("disponivel"));
      }
      conexao.close();
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Metodo emprestar 
  public static void emprestar(int id_biblioteca, int id_livro) throws Exception {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM Livro_Bibli WHERE id_livro = ? AND id_biblioteca = ?");
      query.setInt(1, id_livro);
      query.setInt(2, id_biblioteca);
      ResultSet resultado = query.executeQuery();
      Livro livro = new Livro();

      while(resultado.next()) {
        livro.id = resultado.getInt("id_livro");
        livro.disponivel = resultado.getBoolean("disponivel");
      } 
      if (!livro.disponivel) {
        throw new Exception("Lamento, livro indisponível!");
      }
      PreparedStatement queryUpd = conexao.prepareStatement("UPDATE Livro_Bibli SET disponivel = 0 WHERE id_livro = ? AND id_biblioteca = ?");
      queryUpd.setInt(1, id_livro);
      queryUpd.setInt(2, id_biblioteca);
      queryUpd.executeUpdate();
      conexao.close();
      System.out.println("\nLivro emprestado com sucesso!");
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
}

  // Metodo devolver 
  public static void devolver(int id_biblioteca, int id_livro) throws Exception {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM Livro_Bibli WHERE id_livro = ? AND id_biblioteca = ?");
      query.setInt(1, id_livro);
      query.setInt(2, id_biblioteca);
      ResultSet resultado = query.executeQuery();
      Livro livro = new Livro();
      
      while(resultado.next()) {
        livro.id = resultado.getInt("id_livro");
        livro.disponivel = resultado.getBoolean("disponivel");
      } 
      if (livro.disponivel) {
        throw new Exception("\nLivro ja foi devolvido!");
      }
      PreparedStatement queryUpd = conexao.prepareStatement("UPDATE Livro_Bibli SET disponivel = 1 WHERE id_livro = ? AND id_biblioteca = ?");
      queryUpd.setInt(1, id_livro);
      queryUpd.setInt(2, id_biblioteca);
      queryUpd.executeUpdate();
      conexao.close();
      System.out.println("\nLivro devolvido com sucesso!");
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Método toString 
  public String toString() {
    return "Título: " + this.titulo + ", " 
    + "Autor: " + this.autor + ", " 
    + ", Disponibilidade: " + (this.disponivel ? "Disponível" : "Indisponível"); 
  }
}