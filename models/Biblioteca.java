package models;
/* Biblioteca: 
  Esta classe deve representar a biblioteca 
  e deve conter uma lista de livros. 
  Ela deve ter métodos para adicionar livros
  à biblioteca e listar todos os livros 
  disponíveis na biblioteca. */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import banco_conect.ConexaoBanco;

public class Biblioteca {
  private int id;
  private String nome;
  private ArrayList<Livro> livros;
  private ArrayList<Filme> filmes = new ArrayList<>();
  protected static ArrayList<Album> albums = new ArrayList<>();
  protected static ArrayList<AudioBook> audioBooks = new ArrayList<>();

  // Criar lista bibliotecas
  private static ArrayList<Biblioteca> bibliotecas = new ArrayList<>();

  // Criar construtor 
  public Biblioteca() {}
  public Biblioteca(String nome) {
    this.nome = nome;
    this.livros = new ArrayList<>();
    criarNoBanco(this); 
  }
  public int criarNoBanco(Biblioteca biblioteca){
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO biblioteca (nome) values (?)");
      ps.setString(1, biblioteca.nome);
      
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
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
  public ArrayList<Album> getAlbums() {
    return albums;
  }
  public ArrayList<AudioBook> getAudioBooks() {
    return audioBooks;
  }
  public static ArrayList<Biblioteca> getBibliotecas() {
    return bibliotecas;
  }

  // Criar método set
  public void setIdBiblioteca(int idBiblioteca) {
    this.id = idBiblioteca;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public int setLivros(Livro livro) {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO Livro_Bibli (id_biblioteca, id_livro, disponivel) values (?, ?, ?)");
      ps.setInt(1, this.id);
      ps.setInt(2, livro.getIdLivro());
      ps.setBoolean(3, livro.getDisponivel());

      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }
  public int setFilmes(Filme filme) {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO Filme_Bibli (id_biblioteca, id_filme, disponivel) values (?, ?, ?)");
      ps.setInt(1, this.id);
      ps.setInt(2, filme.getIdFilme());
      ps.setBoolean(3, filme.getDisponivel());
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }
  public int setAlbums(Album album) {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO Album_Bibli (id_biblioteca, id_album, disponivel) values (?, ?, ?)");
      ps.setInt(1, this.id);
      ps.setInt(2, album.getIdAlbum());
      ps.setBoolean(3, album.getDisponivel());
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  public int setAudioBooks(AudioBook audioBook) {
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("INSERT INTO AudioB_Bibli (id_biblioteca, id_audioBook) values (?, ?)");
      ps.setInt(1, this.id);
      ps.setInt(2, audioBook.getIdAudioBook());
      
      int rs = ps.executeUpdate();
      conexao.close();
      return rs;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }
  public void setBibliotecas(Biblioteca biblioteca) {
    bibliotecas.add(biblioteca);
  }

  // Criar metodo toString
  public int getIdBiblioteca() {
    return this.id;
  }
  public String toString() {
    return this.nome;
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
    try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement ps = conexao.prepareStatement("SELECT * FROM biblioteca");
      ResultSet resultado = ps.executeQuery();
      while (resultado.next()) {
        System.out.println("\nID: " + resultado.getInt("id_biblioteca") + "\nNome: " + resultado.getString("nome"));
      }
      conexao.close();
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

  public static Biblioteca getBibliotecaById(int id){
     try{
      Connection conexao = ConexaoBanco.conectar();
      PreparedStatement query = conexao.prepareStatement("SELECT * FROM biblioteca where id_biblioteca = ?");
      query.setInt(1, id);
      ResultSet resultado = query.executeQuery();
      Biblioteca biblioteca = new Biblioteca();
      while (resultado.next()) {
        biblioteca.id = resultado.getInt("id_biblioteca");
        biblioteca.nome = resultado.getString("nome");
      }
      conexao.close();
      return biblioteca;
    } catch (SQLException exception) {
      throw new Error(exception.getMessage());
    }
  }

}