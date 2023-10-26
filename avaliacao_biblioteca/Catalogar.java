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
        System.out.println("+-------------------------------+");
        System.out.println("|  Gerenciamento de Biblioteca  |");
        System.out.println("|                               |");
        System.out.println("| [0] - Encerrar  Programa      |");
        System.out.println("| [1] - Registrar Autor         |");
        System.out.println("| [2] - Registrar Livro         |");
        System.out.println("| [3] - Registrar Filme         |");
        System.out.println("| [4] - Registrar Biblioteca    |");
        System.out.println("| [5] - Adicionar Livro         |");
        System.out.println("| [6] - Adicionar Filme         |");
        System.out.println("| [7] - Emprestar Livro         |");
        System.out.println("| [8] - Emprestar Filme         |");
        System.out.println("| [9] - Devolver  Livro         |");
        System.out.println("|[10] - Devolver  Filme         |");
        System.out.println("|[]]  -                         |");
        System.out.println("|[]   -                         |");
        System.out.println("+-------------------------------+");
        System.out.println("Escolha uma opção: \n" + "\u001B[00m");

        try {
          opcao = sc.nextInt();
        } catch (Exception e) {
          opcao = 99;
        }

        switch (opcao) {
          case 0: {
              System.out.println("Bye....");
              break;
          }
          case 1: {
            do {
              System.out.println("\n\t Registrando Autores ...\n");
              System.out.println("\nDigite o nome do autor: ");
              sc.nextLine();
              String nome = sc.nextLine();

              System.out.println("\nDigite a idade do autor: ");
              int idade = sc.nextInt();

              try {
                if (nome.length() <= 1 || idade <= 0) { // Verificar se digitou o nome correto 
                  throw new Exception("O nome do autor não é válido");
                }
                temErro = false;
                new Autor(nome, idade); // Criar um novo autor

                System.out.println("\nAutor cadastrado com sucesso!\n");
                System.out.println("Nome do autor: " + "\u001B[32m" + nome + "\u001B[00m");
                System.out.println("Número na lista: " + "\u001B[32m" + Autor.getAutores().size() + "\u001B[00m");
              } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                temErro = true;
              }
            } while(temErro);
            break;
          }
          case 2:{
            do {
              try {
                System.out.println("\n\t Registrando Livros ...\n");
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
                System.out.println("\nNome do livro: " + "\u001B[32m" + titulo + "\u001B[00m");
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
                System.out.println("\n\t Registrando Filmes ...\n");
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
                System.out.println("\nNome do filme: " + "\u001B[32m" + titulo + "\u001B[00m");
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
                System.out.println("\n\t Registrando Bibliotecas ...\n");
                System.out.println("\nDigite o nome da biblioteca: ");
                String nome = sc.next();
                 if (nome.length() <= 1) { // Verificar se digitou o nome correto 
                  throw new Exception("\nO nome não é válido");
                }
                new Biblioteca(nome);
                System.out.println("\nBiblioteca: " + "\u001B[32m" + nome + "\u001B[00m");
                System.out.println("Número na lista: " + "\u001B[32m" + Biblioteca.getBibliotecas().size() + "\u001B[00m");
              } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
              }
            } while(temErro);
          }
          break;
          case 5: {
            do {
              try {
                    // Adicionar Livro 
                System.out.println("\n\t Adicionando Livro a Biblioteca ...\n");
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
                System.out.println("\nLivro adicionado a biblioteca " + Biblioteca.getBibliotecas().get(numBiblioteca));
              } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
              }
            } while(temErro);
            break;
          }
          case 6: {
            do {
              try {
                System.out.println("\n\t Adicionando Filme a Biblioteca ...\n");
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
                System.out.println("\nFilme adicionado a biblioteca " + Biblioteca.getBibliotecas().get(numBiblioteca));
              } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
              }
            } while(temErro);
            break;
          }
          case 7: {
            do {
              try {
                System.out.println("\n\t Emprestando Livro ...\n");

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
          case 8: {
            do {
              try {
                System.out.println("\n\t Emprestando Filme ...\n");
                System.out.println("Digite o número da biblioteca: ");
                int numBiblioteca = sc.nextInt() - 1;

                if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.getBibliotecas().size()) {
                  throw new Exception("Biblioteca inexistente");
                }
                Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

                System.out.println("Digite o numero do Filme");
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
        case 9: {
            do {
              try {
                System.out.println("\n\t  \u001B[34m"  + "Devolvendo Livro ...\n" + "\u001B[00m");
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
        case 10: {
          do {
              try {
                System.out.println("\n\t Devolvendo Filme ...\n");

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
        }
    } while (opcao != 0);
    sc.close(); 
  }
}

// do {
//               try {
//                 System.out.println("Digite o número da biblioteca: ");
//                 int numBiblioteca = sc.nextInt() - 1;
//                 if (numBiblioteca < 0 || numBiblioteca >= Biblioteca.      getBibliotecas().size()) {
//                   throw new Exception("Biblioteca inexistente");
//                 }
//                 Biblioteca biblioteca = Biblioteca.getBibliotecas().get(numBiblioteca);

//                 System.out.println("Digite o número do livro para devolver: ");
//                 int numLivro = sc.nextInt() - 1;
//                 if (numLivro < 0 || numLivro >= biblioteca.getLivros().size()) {
//                   throw new Exception("Livro inexistente");
//                 }
//                 Livro livro = biblioteca.getLivros().get(numLivro);

//                 livro.devolver();
//               } catch (Exception e) {
//                 System.out.println("Erro: " + e.getMessage());
//               }
//             } while(temErro);
//             break;