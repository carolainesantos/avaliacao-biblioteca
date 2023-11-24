import java.sql.SQLException;
import java.util.Scanner;

import models.Album;
import models.AudioBook;
import models.Autor;
import models.Biblioteca;
import models.Filme;
import models.Livro;

public class Catalogar {
  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    Scanner sc = new Scanner (System.in);

    int opcao = 0;
    boolean temErro = false;

    do {
      System.out.println("\n" + "\u001B[35m");
      System.out.println("+------------------------------------------------------------------+");
      System.out.println("|                 Gerenciamento de Biblioteca                      |");
      System.out.println("|                                                                  |");
      System.out.println("|   [0] - Encerrar  Programa      |   [12] - Emprestar Filme       |");
      System.out.println("|   [1] - Registrar Autor         |   [13] - Emprestar Album       |");
      System.out.println("|   [2] - Registrar Livro         |   [14] - Comprar AudioBook     |");
      System.out.println("|   [3] - Registrar Filme         |   [15] - Devolver  Livro       |");
      System.out.println("|   [4] - Registrar Album         |   [16] - Devolver  Filme       |"); 
      System.out.println("|   [5] - Registrar AudioBook     |   [17] - Devolver  Albumm      |");
      System.out.println("|   [6] - Registrar Biblioteca    |   [18] - Listar  Livro         |");
      System.out.println("|   [7] - Add Livro               |   [19] - Listar  Filme         |"); 
      System.out.println("|   [8] - Add Filme               |   [20] - Listar  Album         |");
      System.out.println("|   [9] - Add Album               |   [21] - Listar  AudioBook     |");
      System.out.println("|  [10] - Add AudioBook           |   [22] - Listar  Biblioteca    |");
      System.out.println("|  [11] - Emprestar Livro         |   [23] - Listar  Autor         |");
      System.out.println("+------------------------------------------------------------------+");
      System.out.println("\nEscolha uma opção: \n" + "\u001B[00m");

      try {
        opcao = sc.nextInt();
      } catch (Exception e) {
        opcao = 24;
      }

      switch (opcao) {
        case 0: {
            System.out.println("Bye....");
            break;
        }
        case 1: {
          do {
            System.out.println("\n\t \u001B[36m Registrando Autores ... \u001B[00m \n");

            System.out.println("\nDigite o nome do autor: ");
            sc.nextLine();
            String nome = sc.nextLine();

            System.out.println("\nDigite a idade do autor: ");
            int idade = sc.nextInt();
            System.out.println("\u001B[00m");
            try {
              if (nome.length() <= 1 || idade <= 0) { // Verificar se digitou o nome correto 
                throw new Exception("O nome do autor não é válido");
              }
              temErro = false;
              new Autor(nome, idade); // Criar um novo autor

              System.out.println("\n\u001B[36mAutor cadastrado com sucesso! \u001B[00m");
            
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
              temErro = true;
            }
          } while(temErro);
          break;
        }
        case 2: {
          do {
            try {
              System.out.println("\n\t \u001B[36m Registrando Livros ... \u001B[00m \n");

              System.out.println("\nDigite o título do livro: ");
              String titulo = sc.next();
              if (titulo.length() <= 1) { // Verificar se digitou o titulo correto 
                throw new Exception("Título não é válido");
              }

              System.out.println("\nDigite o id do autor: ");
              int id = sc.nextInt();
              Autor autor = Autor.getAutorById(id);

              System.out.println("\nO livro está disponivel? [1] - Sim | [0] - Não");
              int disponivel = sc.nextInt();
              boolean aux = false;
              if(disponivel == 1){
                aux = true;
              }

              temErro = false;
              new Livro(titulo, autor, aux); // Criar um novo livro

              System.out.println("\n\u001B[36mLivro cadastrado com sucesso!\u001B[00m \n");
              
            } catch (Exception e) {
              System.out.println("\nErro: " + e.getMessage());
              temErro = true;
            }
          } while(temErro);
          break;
        }
        case 3: {
          do {
            try {
              System.out.println("\n\t \u001B[36m Registrando Filmes ... \u001B[00m \n");

              System.out.println("\nDigite o título do Filme: ");
              String titulo = sc.next();
              if (titulo.length() <= 1) { // Verificar se digitou o titulo correto 
                throw new Exception("\nO titulo do filme não é válido!");
              }

              System.out.println("\nQual o genero do filme? ");
              String genero = sc.next();
              if (genero.length() <=1) {
                throw new Exception("\nO genero esta incorreto");
              }

              System.out.println("\nO Filme está disponivel? [1] - Sim | [0] - Não");
              int disponivel = sc.nextInt();

              boolean aux = false;
              if(disponivel == 1){
                aux = true;
              }

              temErro = false;
              new Filme(titulo, genero, aux); // Criar um novo filme

              System.out.println("\n\u001B[36mFilme cadastrado com sucesso! \u001B[00m");
              System.out.println("Nome do filme: " + "\u001B[32m" + titulo + "\u001B[00m");
              
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
              temErro = true;
            }
          } while(temErro);
          break;
        }
        case 4: {
          do {
            try {
              System.out.println("\n\t \u001B[36m  Registrando Albums... \u001B[00m \n");

              System.out.println("\nDigite o título do Album: ");
              String titulo = sc.next();
            
              System.out.println("\nQual a data de lançamento do album?");
              String dtLancamento = sc.next();

              System.out.println("\nQual a quantidade de faixas? ");
              int qtdFaixa = sc.nextInt();
              if (qtdFaixa <=1) {
                throw new Exception("\nInsira a quantidade!");
              }

              System.out.println("\nO Album está disponivel? [1] - Sim | [0] - Não");
              int disponivel = sc.nextInt();

              boolean aux = false;
              if(disponivel == 1){
                aux = true;
              }

              temErro = false;
              new Album(titulo, dtLancamento, aux, qtdFaixa); 

              System.out.println("\n\u001B[36mAlbum cadastrado com sucesso! \u001B[00m");
              System.out.println("Nome do Album: " + "\u001B[32m" + titulo + "\u001B[00m");
              
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
              temErro = true;
            }
          } while(temErro);
          break; 
          }
        case 5: {
          do {
            try {
              System.out.println("\n\t \u001B[36m Registrando AudioBooks... \u001B[00m \n");

              System.out.println("\nDigite o título do AudioBook: ");
              String titulo = sc.next();

              System.out.println("\nQual a data de lançamento? ");
              String dtLancamento = sc.next();

              System.out.println("\nNome do narrador? ");
              String narrador = sc.next();
              if (narrador.length() <=1) {
                throw new Exception("\nNome inválido!");
              }

              System.out.println("\nO AudioBook está disponivel? [1] - Sim | [0] - Não");
              int disponivel = sc.nextInt();

              boolean aux = false;
              if(disponivel == 1){
                aux = true;
              }

              temErro = false;
              new AudioBook(titulo, dtLancamento, aux, narrador); 

              System.out.println("\n\u001B[36mAudioBook cadastrado com sucesso! \u001B[00m");
              System.out.println("Nome do AudioBook: " + "\u001B[32m" + titulo + "\u001B[00m");
            
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
              temErro = true;
            }
          } while(temErro);
          break; 
      }
        case 6: {
          do {
            try {
              System.out.println("\n\t \u001B[36m  Registrando Bibliotecas ... \u001B[00m \n");
              System.out.println("\nDigite o nome da biblioteca: ");
              String nome = sc.next();
              //   if (nome.length() <= 1) { // Verificar se digitou o nome correto 
              //   throw new Exception("\nO nome não é válido");
              // }
              new Biblioteca(nome);

              System.out.println("\n\u001B[36mBiblioteca cadastrada com sucesso! \u001B[00m");
              System.out.println("Biblioteca: " + "\u001B[32m" + nome + "\u001B[00m");
              
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
        }
        break;
        case 7: {
          do {
            try {
              System.out.println("\n\t\u001B[36m Adicionando Livro a Biblioteca ... \u001B[00m \n");

              System.out.println("\nDigite o id da biblioteca: ");
              int idBiblioteca = sc.nextInt();
              Biblioteca biblioteca = Biblioteca.getBibliotecaById(idBiblioteca);

              System.out.println("\nDigite o id do livro: ");
              int idLivro = sc.nextInt();
              Livro livro = Livro.getLivroById(idLivro);
            
              System.out.println("O livro está disponivel? 1(Sim)  0(Não)");
              int disponivel = sc.nextInt();
              livro.setDisponivel(false);
              if(disponivel == 1) {
                livro.setDisponivel(true);
              }

              biblioteca.setLivros(livro);
              System.out.println("\n\u001B[36mLivro adicionado a biblioteca " + Biblioteca.getBibliotecaById(idBiblioteca) + "\u001B[00m");
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
          break;
        }
        case 8: {
          do {
            try {
              System.out.println("\n\t\u001B[36m  Adicionando Filme a Biblioteca ... \u001B[00m \n");

              System.out.println("\nDigite o id da biblioteca: ");
              int idBiblioteca = sc.nextInt();
              Biblioteca biblioteca = Biblioteca.getBibliotecaById(idBiblioteca);

              System.out.println("\nDigite o id do filme: ");
              int idFilme = sc.nextInt();
              Filme filme = Filme.getFilmeById(idFilme);

              System.out.println("O filme está disponivel? 1(Sim)  0(Não)");
              int disponivel = sc.nextInt();
              filme.setDisponivel(false);
              if(disponivel == 1) {
                filme.setDisponivel(true);
              }

              biblioteca.setFilmes(filme);
              System.out.println("\n\u001B[36mFilme adicionado a biblioteca " + Biblioteca.getBibliotecaById(idBiblioteca) + "\u001B[00m");
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
          break;
        }
        case 9: {
          do {
            try {
              System.out.println("\n\t \u001B[36m Adicionando Album a Biblioteca...\u001B[00m \n");

              System.out.println("\nDigite o id da biblioteca: ");
              int idBiblioteca = sc.nextInt();
              Biblioteca biblioteca = Biblioteca.getBibliotecaById(idBiblioteca);
              
              System.out.println("\nDigite o número do Album: ");
              int idAlbum = sc.nextInt();
              Album album = Album.getAlbumById(idAlbum);
              
              System.out.println("O album está disponivel? 1(Sim)  0(Não)");
              int disponivel = sc.nextInt();
              album.setDisponivel(false);
              if(disponivel == 1) {
                album.setDisponivel(true);
              }

              biblioteca.setAlbums(album);
              System.out.println("\n\u001B[36mAlbum adicionado a biblioteca " + Biblioteca.getBibliotecaById(idBiblioteca) + "\u001B[00m");
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
          break;
        }
        case 10: {
          do {
            try {
              System.out.println("\n\t\u001B[36m Adicionando AudioBook a Biblioteca ... \u001B[00m\n");

              System.out.println("\nDigite o id da biblioteca: ");
              int idBiblioteca = sc.nextInt();
              Biblioteca biblioteca = Biblioteca.getBibliotecaById(idBiblioteca);

              System.out.println("\nDigite o número do AudioBook: ");
              int idAudioBook = sc.nextInt();
              AudioBook audioBook = AudioBook.getAudioBookById(idAudioBook);
            
              biblioteca.setAudioBooks(audioBook);
              System.out.println("\n\u001B[36mAudioBook adicionado a biblioteca " + Biblioteca.getBibliotecaById(idBiblioteca) + "\u001B[00m");
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);

          break;
        }
        case 11: {
          do {
            try {
              System.out.println("\n\t\u001B[36m Emprestando Livro ... \u001B[00m \n");

              System.out.println("Digite o número da biblioteca: ");
              int numBiblioteca = sc.nextInt();

              System.out.println("Digite o número do livro: ");
              int numLivro = sc.nextInt();

              Livro.emprestar(numBiblioteca, numLivro);
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
          break;
        } 
        case 12: {
          do {
              try {
              System.out.println("\n\t\u001B[36m Emprestando Filme ... \u001B[00m \n");

              System.out.println("Digite o número da biblioteca: ");
              int numBiblioteca = sc.nextInt();

              System.out.println("Digite o número do filme: ");
              int numFilme = sc.nextInt();

              Filme.emprestar(numBiblioteca, numFilme);
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
          break;
        }
        case 13: {
          do {
            try {
              System.out.println("\n\t\u001B[36m Emprestando Album ... \u001B[00m \n");

              System.out.println("Digite o número da biblioteca: ");
              int numBiblioteca = sc.nextInt();

              System.out.println("Digite o número do album: ");
              int numAlbum = sc.nextInt();

              Album.emprestar(numBiblioteca, numAlbum);
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
        break;
        }
        case 14: {
          do {
            try {
              System.out.println("\n\t \u001B[36m Realizando Compra... \u001B[00m \n");
            
              System.out.println("Digite o número da biblioteca: ");
              int numBiblioteca = sc.nextInt();
              
              System.out.println("Digite o numero do AudioBook: ");
              int numAudio = sc.nextInt(); 

              AudioBook.comprar(numBiblioteca, numAudio);
            }  catch(Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
        }
        break;
        case 15: {
          do {
            try {
              System.out.println("\n\t \u001B[36m Devolvendo Livro ... \u001B[00m \n");

              System.out.println("Digite o número da biblioteca: ");
              int numBiblioteca = sc.nextInt();

                System.out.println("Digite o número do livro: ");
              int numLivro = sc.nextInt();

              Livro.devolver(numBiblioteca,numLivro);
            } catch (Exception e) {
              System.out.println("Erro: " + e.getMessage());
            }
          } while(temErro);
          break;
        }
        case 16: {
          do {
            try {
              System.out.println("\n\t \u001B[36m Devolvendo Filme ... \u001B[00m \n");

              System.out.println("Digite o número da biblioteca: ");
              int numBiblioteca = sc.nextInt();

              System.out.println("Digite o número do filme: ");
              int numFilme = sc.nextInt();

              Filme.devolver(numBiblioteca,numFilme);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
              }
          } while(temErro);
            break;
        }
        case 17: {
          do {
            try {
              System.out.println("\n\t \u001B[36m Devolvendo Album... \u001B[00m \n");

              System.out.println("\nDigite o número da biblioteca: ");
              int numBiblioteca = sc.nextInt();

              System.out.println("\nDigite o número do album para devolver: ");
              int numAlbum = sc.nextInt();

              Album.devolver(numBiblioteca, numAlbum);
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
              }
          } while(temErro);
            break;
        }
        case 18: {
          do {
            System.out.println("\n\t \u001B[36m Visualizar Livro(s) \u001B[00m \n");
            Livro.mostrarLivros();

          } while(temErro);
          break;
        }
        case 19: {
          do {
              System.out.println("\n\t \u001B[36m Visualizar Filme(s) \u001B[00m \n");
              Filme.mostrarFilmes();
          } while(temErro);
          break;
        }
        case 20: {
          do {
              System.out.println("\n\t \u001B[36m Visualizar Album(s)\u001B[00m \n");
              Album.mostrarAlbums();
          } while(temErro);
          break;
        }
        case 21: {
          do {
              System.out.println("\n\t \u001B[36m Visualizar AudioBook(s)\u001B[00m \n");
              AudioBook.mostrarAudioBooks();
          } while(temErro);
          break;
        }
        case 22: {
          do {
              System.out.println("\n\t \u001B[36m Visualizar Biblioteca(s)\u001B[00m \n");
              Biblioteca.mostrarBibliotecas();
          } while(temErro);
          break;
        }
        case 23: {
          do {
              System.out.println("\n\t \u001B[36m Visualizar Autor(s)\u001B[00m \n");
              Autor.mostrarAutores();
          } while(temErro);
          break;
        }
      }
    } while (opcao != 0);
    sc.close(); 
  }
}
