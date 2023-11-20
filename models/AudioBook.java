package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import banco_conect.ConexaoBanco;

public class AudioBook extends MidiaDigital {
  private int id;
  protected String narrador;

  // construtor 
  public AudioBook() {}
  public AudioBook(String titulo, String dtLacamento, Boolean disponivel, String narrador) {
    super(titulo, dtLacamento, disponivel);
    this.narrador = narrador;
    criarNoBanco(this);
  }

  public int criarNoBanco(AudioBook audioBook){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO audioBook (narrador, titulo, dtLancamento, disponivel) values (?, ?, ?, ?)");
      ps.setString(1, audioBook.narrador);
      ps.setString(2, audioBook.titulo);
      ps.setString(3, audioBook.dtLancamento);
      ps.setBoolean(4, audioBook.disponivel);
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  public static AudioBook getAudioBookById(int id){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM audioBook where id_audioBook = ?");
      query.setInt(1, id);
      ResultSet resultado = query.executeQuery();
      AudioBook audioBook = new AudioBook();
      while (resultado.next()) {
        audioBook.id = resultado.getInt("id_audioBook");
      }
      conexao.close();
      return audioBook;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

//==========================================================================================================//
  // metodo get
  public int getIdAudioBook() {
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
  public String getNarrador() {
    return this.narrador;
  }

  // metodo set 
  public void setIdAudioBook(int idAudioBook) {
    this.id = idAudioBook;
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
  public void setNarrador(String narrador) {
    this.narrador = narrador;
  }
//==========================================================================================================//

  // Mostrar AudioBooks
  public static void mostrarAudioBooks() {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("SELECT * FROM audiobook");
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        System.out.println("\nID: " + resultado.getInt("id_audioBook") + "\nNarrador: " + resultado.getString("narrador") + "\nTitulo: " + resultado.getString("titulo") + "\nData Lançamento: " + resultado.getString("dtLancamento")  + "\nDisponibilidade: " + resultado.getBoolean("disponivel"));
      }
      conexao.close();
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  // Comprar
  public static void comprar(int id_biblioteca, int id_audioBook) throws Exception {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM AudioB_Bibli WHERE id_audioBook = ? AND id_biblioteca = ?");
      query.setInt(1, id_audioBook);
      query.setInt(2, id_biblioteca);
      ResultSet resultado = query.executeQuery();
      AudioBook audioBook = new AudioBook();
      while(resultado.next()) {
        audioBook.id = resultado.getInt("id_audioBook");
        audioBook.disponivel = resultado.getBoolean("disponivel");
      } 
      if (audioBook.disponivel) {
        throw new Exception("\nLamento, audiobook indisponível");
      }
      PreparedStatement queryUpd = conexao.prepareStatement("UPDATE AudioB_Bibli SET disponivel = 0 WHERE id_audioBook = ? AND id_biblioteca = ?");
      queryUpd.setInt(1, id_audioBook);
      queryUpd.setInt(2, id_biblioteca);
      queryUpd.executeUpdate();
      conexao.close();
      System.out.println("\nAudioBook comprado com sucesso!");
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }
  public String toString() {
    return "Titulo do AudioBook: " + this.titulo + ", " + ", Disponibilidade: " + (this.disponivel ? "Disponivel" : "Idisponivel");
  }
}
