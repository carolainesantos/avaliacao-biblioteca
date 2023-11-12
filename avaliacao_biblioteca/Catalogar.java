package avaliacao_biblioteca;
/* No método main da classe principal, deverão ser descritas as seguintes operações: - Criar autor - Criar livro- Criar biblioteca
- Adicionar livro à biblioteca
- Emprestar livro
- Devolver livro
Certifique-se de que seu programa seja capaz de lidar com casos em que um livro já está emprestado e tentativas de devolução de livros que já estão disponíveis. */

import java.util.Scanner;

public class Catalogar {
  public static void main(String[] args) {
    Scanner sc = new Scanner (System.in);

    int opcao = 0;
    boolean temErro = false;

    do {
        System.out.println("\n" + "\u001B[35m");
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("|                 Gerenciamento de Biblioteca                 |");
        System.out.println("|                                                             |");
        System.out.println("|   [0] - Encerrar  Programa    |  [9]  - Add Album           |");
        System.out.println("|   [1] - Registrar Autor       |  [10] - Add AudioBook       |");
        System.out.println("|   [2] - Registrar Livro       |  [11] - Emprestar Livro     |");
        System.out.println("|   [3] - Registrar Filme       |  [12] - Emprestar Filme     |");
        System.out.println("|   [4] - Registrar Album       |  [13] - Emprestar Album     |"); 
        System.out.println("|   [5] - Registrar AudioBook   |  [14] - Comprar AudioBook   |");
        System.out.println("|   [6] - Registrar Biblioteca  |  [15] - Devolver  Livro     |");
        System.out.println("|   [7] - Add Livro             |  [16] - Devolver  Filme     |");
        System.out.println("|   [8] - Add Filme             |  [17] - Devolver  Album     |");
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("\nEscolha uma opção: \n" + "\u001B[00m");

        try {
          opcao = sc.nextInt();
        } catch (Exception e) {
          opcao = 18;
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
                System.out.println("Nome do autor: " + "\u001B[32m" + nome + "\u001B[00m");
                System.out.println("Número na lista: " + "\u001B[32m" + Autor.getAutores().size() + "\u001B[00m");
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
  
                System.out.println("\nDigite o número do autor: ");
                int numero = sc.nextInt() - 1;
                if (numero < 0 || numero >= Autor.getAutores().size()) {
                  throw new Exception("Autor inexistente");
                }
                Autor autor = Autor.getAutores().get(numero);

                System.out.println("\nO livro está disponivel? [1] - Sim | [2] - Não");
                int disponivel = sc.nextInt();
                boolean aux = false;
                if(disponivel == 1){
                  aux = true;
                }

                temErro = false;
                new Livro(titulo, autor, aux); // Criar um novo livro

                System.out.println("\n\u001B[36mLivro cadastrado com sucesso!\u001B[00m \n");
                System.out.println("Nome do livro: " + "\u001B[32m" + titulo + "\u001B[00m");
                System.out.println("Número na lista: " + "\u001B[32m" + Livro.getLivros().size() + "\u001B[00m");
              } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
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

                System.out.println("\nO Filme está disponivel? [1] - Sim | [2] - Não");
                int disponivel = sc.nextInt();

                boolean aux = false;
                if(disponivel == 1){
                  aux = true;
                }

                temErro = false;
                new Filme(titulo, genero, aux); // Criar um novo filme

                System.out.println("\n\u001B[36mFilme cadastrado com sucesso! \u001B[00m");
                System.out.println("Nome do filme: " + "\u001B[32m" + titulo + "\u001B[00m");
                System.out.println("Número na lista: " + "\u001B[32m" + Filme.getFilmes().size() + "\u001B[00m");
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
                if (titulo.length() <= 1) { 
                  throw new Exception("\nO titulo do Album não é válido!");
                }

                System.out.println("\nQual a data de lançamento do album? ");
                String dtLancamento = sc.next();
                if (dtLancamento.length() <=1) {
                  throw new Exception("\n Data incorreta");
                }

                System.out.println("\nQual a quantidade de faixas? ");
                int qtdFaixa = sc.nextInt();
                if (qtdFaixa <=1) {
                  throw new Exception("\nInsira a quantidade!");
                }

                System.out.println("\nO Album está disponivel? [1] - Sim | [2] - Não");
                int disponivel = sc.nextInt();

                boolean aux = false;
                if(disponivel == 1){
                  aux = true;
                }

                temErro = false;
                new Album(titulo, dtLancamento, aux, qtdFaixa); 

                System.out.println("\n\u001B[36mAlbum cadastrado com sucesso! \u001B[00m");
                System.out.println("Nome do Album: " + "\u001B[32m" + titulo + "\u001B[00m");
                System.out.println("Número na lista: " + "\u001B[32m" + Album.getAlbums().size() + "\u001B[00m");
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
                if (titulo.length() <= 1) { 
                  throw new Exception("\nO titulo do AudioBook não é válido!");
                }

                System.out.println("\nQual a data de lançamento? ");
                String dtLancamento = sc.next();
                if (dtLancamento.length() <=1) {
                  throw new Exception("\nData incorreta");
                }

                System.out.println("\nNome do narrador? ");
                String narrador = sc.next();
                if (narrador.length() <=1) {
                  throw new Exception("\nNome inválido!");
                }

                System.out.println("\nO AudioBook está disponivel? [1] - Sim | [2] - Não");
                int disponivel = sc.nextInt();

                boolean aux = false;
                if(disponivel == 1){
                  aux = true;
                }

                temErro = false;
                new AudioBook(titulo, dtLancamento, aux, narrador); // Criar um novo AudioBook

                System.out.println("\n\u001B[36mAudioBook cadastrado com sucesso! \u001B[00m");
                System.out.println("Nome do AudioBook: " + "\u001B[32m" + titulo + "\u001B[00m");
                System.out.println("Número na lista: " + "\u001B[32m" + AudioBook.getAudioBooks().size() + "\u001B[00m");
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
                 if (nome.length() <= 1) { // Verificar se digitou o nome correto 
                  throw new Exception("\nO nome não é válido");
                }
                new Biblioteca(nome);

                System.out.println("\n\u001B[36mBiblioteca cadastrada com sucesso! \u001B[00m");
                System.out.println("Biblioteca: " + "\u001B[32m" + nome + "\u001B[00m");
                System.out.println("Número na lista: " + "\u001B[32m" + Biblioteca.getBibliotecas().size() + "\u001B[00m");
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
                System.out.println("\nDigite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;
                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("\nBiblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("\nDigite o número do livro: ");
                int numLivro = sc.nextInt() - 1;

                if (numLivro < 0 || numLivro >= Livro.getLivros().size()) {
                  throw new Exception("\nLivro inexistente");
                }
                Livro livro = Livro.getLivros().get(numLivro);

                biblioteca.setLivros(livro);
                System.out.println("\n\u001B[36mLivro adicionado a biblioteca " + Biblioteca.getBibliotecas().get(numBiblioteca) + "\u001B[00m");
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
                System.out.println("\nDigite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;
                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("\nDigite o número do filme: ");
                int numFilme = sc.nextInt() - 1;
              
                if (numFilme < 0 || numFilme >= Filme.getFilmes().size()) {
                  throw new Exception("Filme inexistente");
                }
                Filme filme = Filme.getFilmes().get(numFilme);

                biblioteca.setFilmes(filme);
                System.out.println("\n\u001B[36mFilme adicionado a biblioteca " + Biblioteca.getBibliotecas().get(numBiblioteca));
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

                System.out.println("\nDigite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;
                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("\nDigite o número do Album: ");
                int numAlbum = sc.nextInt() - 1;
              
                if (numAlbum < 0 || numAlbum >= Album.getAlbums().size()) {
                  throw new Exception("Album inexistente");
                }
                Album album = Album.getAlbums().get(numAlbum);

                biblioteca.setAlbums(album);
                System.out.println("\n\u001B[36mAlbum adicionado a biblioteca " + Biblioteca.getBibliotecas().get(numBiblioteca) + "\u001B[00m");
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
                System.out.println("\n Digite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;
                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("\nDigite o número do AudioBook: ");
                int numAudio = sc.nextInt() - 1;
              
                if (numAudio < 0 || numAudio >= AudioBook.getAudioBooks().size()) {
                  throw new Exception("AudioBook inexistente");
                }
                AudioBook audioBook = AudioBook.getAudioBooks().get(numAudio);

                biblioteca.setAudioBooks(audioBook);
                System.out.println("\n\u001B[36mAudioBook adicionado a biblioteca " + Biblioteca.getBibliotecas().get(numBiblioteca) + "\u001B[00m");
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
                int numBiblioteca = sc.nextInt() - 1;

                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("Digite o número do livro: ");
                int numLivro = sc.nextInt() - 1;

                if (numLivro < 0 || numLivro >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Livro inexistente");
                }
                Livro livro = biblioteca.getLivros().get(numLivro);

                livro.emprestar();
              } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
              }
            } while(temErro);
            break;
          } 
          case 12: {
            do {
              try {
                System.out.println("\n\t \u001B[36m Emprestando Filme ... \u001B[00m\n");
                System.out.println("Digite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;

                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("Digite o numero do Filme: ");
                int numFilme = sc.nextInt() - 1; 

                if (numFilme < 0 || numFilme >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Fime Inexistente!");
                } 
                Filme filme = biblioteca.getFilmes().get(numFilme);

                filme.emprestarF();
              } catch(Exception e) {
                System.out.println("Erro: " + e.getMessage());
              }
            } while(temErro);
            break;
          }
          case 13: {
            do {
              try {
                System.out.println("\n\t \u001B[36m  Emprestando Album... \u001B[00m\n");

                System.out.println("Digite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;
                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("Digite o numero do Album: ");
                int numAlbum = sc.nextInt() - 1; 

                if (numAlbum < 0 || numAlbum >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("\u001B[31m Fime Inexistente!\u001B[00m");
                } 
                Album album = biblioteca.getAlbums().get(numAlbum);

                album.emprestarAlbum();
              } catch(Exception e) {
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
                int numBiblioteca = sc.nextInt() - 1;
                
                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);
                
                System.out.println("Digite o numero do AudioBook: ");
                int numAudio = sc.nextInt() - 1; 
                
                if (numAudio < 0 || numAudio >= Biblioteca.getBibliotecas().size()) {
                throw new Exception("AudioBook Inexistente!");
              } 
                AudioBook audioBook = biblioteca.getAudioBooks().get(numAudio);
              
                audioBook.comprar();
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
                System.out.println("\nDigite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;

                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("\n Digite o número do livro para devolver: ");
                int numLivro = sc.nextInt() - 1;
               
                Livro livro = biblioteca.getLivros().get(numLivro);

                livro.devolver();
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

                System.out.println("\nDigite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;

                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("\nDigite o número do filme para devolver: ");
                int numFilme = sc.nextInt() - 1;
                  
                Filme filme = biblioteca.getFilmes().get(numFilme);

                filme.devolverF();
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
                int numBiblioteca = sc.nextInt() - 1;

                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("\nDigite o número do album para devolver: ");
                int numAlbum = sc.nextInt() - 1;
                  
                Album album = biblioteca.getAlbums().get(numAlbum);

                album.devolverAlbum();
              } catch (Exception e) {
                  System.out.println("Erro: " + e.getMessage());
                }
            } while(temErro);
              break;
          }
        }
    } while (opcao != 0);
    sc.close(); 
  }
}
