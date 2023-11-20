package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import banco_conect.ConexaoBanco;

public class Album extends MidiaDigital {
  private int id;
  protected int qtdFaixa;

  // Construtor
  public Album() {}
  public Album(String titulo, String dtLacamento, Boolean disponivel, int qtdFaixa) {
    super(titulo, dtLacamento, disponivel);
    this.qtdFaixa = qtdFaixa;
    criarNoBanco(this);
  }

  public int criarNoBanco(Album album){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO album (titulo, dtLancamento, disponivel, qtdFaixa) values (?, ?, ?, ?)");
      ps.setString(1, album.titulo);
      ps.setString(2, album.dtLancamento);
      ps.setBoolean(3, album.disponivel);
      ps.setInt(4, album.qtdFaixa);
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  public static Album getAlbumById(int id){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM album where id_album = ?");
      query.setInt(1, id);
      ResultSet resultado = query.executeQuery();
      Album album = new Album();
      while (resultado.next()) {
        album.id = resultado.getInt("id_album");
      }
      conexao.close();
      return album;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

//=================================================================//
  // Metodo set
  public void setIdAlbum(int idAlbum) {
    this.id = idAlbum;
  }
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
//=================================================================//

  // metodo get 
  public int getIdAlbum() {
    return this.id;
  }
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
  

  // Mostrar Albums
  public static void mostrarAlbums() {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("SELECT * FROM album");
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        System.out.println("\nID: " + resultado.getInt("id_album") + "\nTitulo: " + resultado.getString("titulo") + "\nData Lançamento: " + resultado.getString("dtLancamento")  + "\nDisponibilidade: " + resultado.getBoolean("disponivel") + "\nQuantidade Faixas: " + resultado.getInt("qtdFaixa"));
      }
      conexao.close();
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

   // Metodo emprestar 
   public static void emprestar(int id_biblioteca, int id_album) throws Exception {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM Album_Bibli WHERE id_album = ? AND id_biblioteca = ?");
      query.setInt(1, id_album);
      query.setInt(2, id_biblioteca);
      ResultSet resultado = query.executeQuery();
      Album album = new Album();
      while(resultado.next()) {
        album.id = resultado.getInt("id_album");
        album.disponivel = resultado.getBoolean("disponivel");
      } 
      if (!album.disponivel) {
        throw new Exception("\nLamento, album indisponível");
      }
      PreparedStatement queryUpd = conexao.prepareStatement("UPDATE Album_Bibli SET disponivel = 0 WHERE id_album = ? AND id_biblioteca = ?");
      queryUpd.setInt(1, id_album);
      queryUpd.setInt(2, id_biblioteca);
      queryUpd.executeUpdate();
      conexao.close();
      System.out.println("\nAlbum emprestado com sucesso!");
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
}

  // Método devolver album
  public static void devolver(int id_biblioteca, int id_album) throws Exception {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM Album_Bibli WHERE id_album = ? AND id_biblioteca = ?");
      query.setInt(1, id_album);
      query.setInt(2, id_biblioteca);
      ResultSet resultado = query.executeQuery();
      Album album = new Album();
      
      while(resultado.next()) {
        album.id = resultado.getInt("id_album");
        album.disponivel = resultado.getBoolean("disponivel");
      } 
      if (album.disponivel) {
        throw new Exception("\nAlbum ja foi devolvido!");
      }
      PreparedStatement queryUpd = conexao.prepareStatement("UPDATE Album_Bibli SET disponivel = 1 WHERE id_album = ? AND id_biblioteca = ?");
      queryUpd.setInt(1, id_album);
      queryUpd.setInt(2, id_biblioteca);
      queryUpd.executeUpdate();
      conexao.close();
      System.out.println("\nAlbum devolvido com sucesso!");
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Metodo toString
  public String toString() {
    return "Titulo do AudioBook: " + this.titulo + ", " + ", Disponibilidade: " + (this.disponivel ? "Disponivel" : "Idisponivel");
  }
}
