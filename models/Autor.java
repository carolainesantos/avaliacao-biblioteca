package models;

import banco_conect.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

  public class Autor extends Pessoa {
    private int id;    

  // Consrtutor
  public Autor() {};
  public Autor(String nome, int idade) {
    super(nome, idade);
    criarNoBanco(this);
  }

  public int criarNoBanco(Autor autor){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO autor (nome, idade) values (?, ?)");
      ps.setString(1, autor.nome);
      ps.setInt(2, autor.idade);
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Criar método set 
  public void setIdAutor(int idAutor) {
    this.id = idAutor;
  }
  public void setNome(String nome, int idade) {
    this.nome = nome;
    this.idade = idade;
  }

  // Criar método get 
  public int getIdAutor() {
    return this.id;
  }
  public String getNome() {
    return this.nome + " (" + this.idade + " anos)";
  }
  
  public static Autor getAutorById(int id) {
     try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM autor where id_autor = ?");
      query.setInt(1, id);
      ResultSet resultado = query.executeQuery();
      Autor autor = new Autor();
      while (resultado.next()) {
        autor.id = resultado.getInt("id_autor");
        autor.idade = resultado.getInt("idade");
        autor.nome = resultado.getString("nome");
      }
      conexao.close();
      return autor;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Método toString
  public String toString() {
    return nome; 
  }
}
