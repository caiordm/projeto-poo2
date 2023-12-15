package aula.orientacao.enumarator.aplicacao;

import java.time.Month;
import java.util.List;
import java.util.Scanner;

import aula.orientacao.enumarator.modelo.Cliente;
import aula.orientacao.enumarator.modelo.ContaCorrente;
import aula.orientacao.enumarator.modelo.ContaPoupanca;
import aula.orientacao.enumarator.modelo.IConta;
import aula.orientacao.enumarator.persistencia.PersistenciaEmArquivo;

public class App {


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int op;

        do {
            System.out.println("--- BEM VINDO AO BANCO SANTA CRUZ ---");
            System.out.println("-------------------------------------");
            System.out.println("-- O QUE DESEJA FAZER? --");
            System.out.println("-------------------------------------");
            System.out.println("-- 1.  CADASTRAR CLIENTE --");
            System.out.println("-- 2.  LISTAR TODOS OS CLIENTES --");
            System.out.println("-- 3.  LISTAR CLIENTE POR CPF --");
            System.out.println("-- 4.  REMOVER CLIENTE --");
            System.out.println("-- 5.  CRIAR CONTA E ASSOCIAR AO CLIENTE --");
            System.out.println("-- 6.  LISTAR CONTAS DO CLIENTE --");
            System.out.println("-- 7.  REMOVER CONTA DE UM CLIENTE --");
            System.out.println("-- 8.  DEPOSITAR --");
            System.out.println("-- 9.  SACAR --");
            System.out.println("-- 10. TRANSFERIR PARA OUTRA CONTA --");
            System.out.println("-- 11. VER EXTRATO POR MES E ANO --");
            System.out.println("-- 12. CONSULTAR SALDO --");
            System.out.println("-- 13. VER BALANÇO DAS CONTAS --");
            System.out.println("----------------------------------");
            System.out.println("-- PARA SAIR DIGITE 0 --");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1: {
                    System.out.println("Digite o nome do cliente");
                    String nome = scanner.next();
                    System.out.println("Digite o CPF do cliente");
                    String cpf = scanner.next();

                    Cliente cliente = new Cliente(cpf, nome);

                    PersistenciaEmArquivo.getInstance().salvarCliente(cliente);
                    System.out.println("Cliente cadastrado!");
                    System.out.println("------------------");
                    break;

                }
                case 2: {
                    System.out.println("-- Lista de clientes --");
                    System.out.println(PersistenciaEmArquivo.getInstance().getClienteCadastrados());
                    System.out.println("------------------");
                    break;

                }
                case 3: {
                    System.out.println("Digite o CPF do cliente");
                    String cpf = scanner.next();

                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        System.out.println("------------------");
                        System.out.println(PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf));
                        System.out.println("------------------");
                        break;
                    }

                }
                case 4: {
                    System.out.println("Digite o cpf do cliente que deseja remover");
                    String cpf = scanner.next();

                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("Esse cliente não existe!");
                        break;
                    } else {
                        Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        PersistenciaEmArquivo.getInstance().removerCliente(cli);
                        System.out.println("Cliente removido!");
                        System.out.println("------------------");
                        break;
                    }
                }
                case 5: {
                    System.out.println("Qual o tipo da conta que deseja criar?");
                    System.out.println("1 - CONTA CORRENTE");
                    System.out.println("2 - CONTA POUPANÇA");
                    int key = scanner.nextInt();

                    System.out.println("Digite o numero da conta que deseja criar");
                    String numero = scanner.next();

                    IConta conta;

                    switch (key) {
                        case 1: {
                            conta = new ContaCorrente(numero);
                            System.out.println("Conta corrente criada com sucesso!");
                            System.out.println("-----------------");
                            System.out.println("Digite o CPF do cliente que deseja associar a essa conta");
                            String cpf = scanner.next();
                            if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                                System.out.println("------------------");
                                System.out.println("Nenhum cliente encontrado!");
                                System.out.println("------------------");
                                break;
                            } else {
                                Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                                cli.adicionarConta(conta);
                                PersistenciaEmArquivo.getInstance().atualizarCliente(cli);
                                System.out.println("Conta adicionada com sucesso!");
                                break;
                            }
                        }
                        case 2: {
                            conta = new ContaPoupanca(numero);
                            System.out.println("Conta corrente criada com sucesso!");
                            System.out.println("-----------------");
                            System.out.println("Digite o CPF do cliente que deseja associar a essa conta");
                            String cpf = scanner.next();
                            if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                                System.out.println("------------------");
                                System.out.println("Nenhum cliente encontrado!");
                                System.out.println("------------------");
                                break;
                            } else {
                                Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                                cli.adicionarConta(conta);
                                PersistenciaEmArquivo.getInstance().atualizarCliente(cli);
                                System.out.println("Conta adicionada com sucesso!");
                                break;
                            }
                        }
                        default:
                            throw new IllegalArgumentException("Valor inesperado: " + key);
                    }
                }
                case 6: {
                    System.out.println("Digite o CPF do cliente");
                    String cpf = scanner.next();
                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        for (IConta conta : cli.contas) {
                            if (conta instanceof ContaCorrente) {
                                System.out.println("Conta Corrente: " + conta);
                            } else {
                                System.out.println("Conta Poupança: " + conta);
                            }
                        }
                        break;
                    }
                }
                case 7: {
                    System.out.println("Digite o CPF do cliente");
                    String cpf = scanner.next();
                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        System.out.println(cli.contas);
                        System.out.println("Digite o numero da conta");
                        String numero = scanner.next();
                        if (cli.localizarContaPorNumero(numero) == null) {
                            System.out.println("Conta não existe");
                            break;
                        } else {
                            IConta c = cli.localizarContaPorNumero(numero);
                            cli.removerConta(c);
                            PersistenciaEmArquivo.getInstance().atualizarCliente(cli);
                            break;
                        }
                    }
                }
                case 8: {
                    System.out.println("Digite o CPF do cliente");
                    String cpf = scanner.next();

                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        System.out.println(cli.contas);
                        System.out.println("Digite o numero da conta");
                        String numero = scanner.next();
                        if (cli.localizarContaPorNumero(numero) == null) {
                            System.out.println("Conta não existe");
                            break;
                        } else {
                            System.out.println("Digite a quantia que deseja depositar");
                            float valor = scanner.nextFloat();
                            IConta c = cli.localizarContaPorNumero(numero);
                            c.depositar(valor);
                            PersistenciaEmArquivo.getInstance().atualizarCliente(cli);
                            break;
                        }
                    }
                }
                case 9: {
                    System.out.println("Digite o CPF do cliente");
                    String cpf = scanner.next();

                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        System.out.println(cli.contas);
                        System.out.println("Digite o numero da conta");
                        String numero = scanner.next();
                        if (cli.localizarContaPorNumero(numero) == null) {
                            System.out.println("Conta não existe");
                            break;
                        } else {
                            System.out.println("Digite a quantia que deseja sacar");
                            float valor = scanner.nextFloat();
                            IConta c = cli.localizarContaPorNumero(numero);
                            c.sacar(valor);
                            PersistenciaEmArquivo.getInstance().atualizarCliente(cli);
                            break;
                        }
                    }
                }
                case 10: {
                    System.out.println("Digite o CPF do cliente transferidor");
                    String cpf = scanner.next();

                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        Cliente cliOri = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        System.out.println(cliOri.contas);
                        System.out.println("Digite o numero da conta origem");
                        String numero = scanner.next();
                        if (cliOri.localizarContaPorNumero(numero) == null) {
                            System.out.println("Conta não existe");
                            break;
                        } else {
                            IConta contOrig = cliOri.localizarContaPorNumero(numero);

                            System.out.println("Digite o CPF do cliente de destino");
                            String cpfDest = scanner.next();
                            if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpfDest) == null) {
                                System.out.println("------------------");
                                System.out.println("Nenhum cliente encontrado!");
                                System.out.println("------------------");
                                break;
                            } else {
                                Cliente cliDest = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpfDest);
                                System.out.println(cliDest.contas);
                                System.out.println("Digite o numero da conta de destino");
                                String numeroDest = scanner.next();
                                if (cliDest.localizarContaPorNumero(numeroDest) == null) {
                                    System.out.println("Conta não existe");
                                    break;
                                } else {
                                    IConta contDest = cliDest.localizarContaPorNumero(numeroDest);

                                    System.out.println("Digite a quantia que deseja transferir");
                                    float valor = scanner.nextFloat();

                                    System.out.println("Resumo da operacao:" + cliOri.nome + "(conta: " + numero + ")" + " envia " + valor + " para " + cliDest.nome + "(conta: " + numeroDest + ")");

                                    contOrig.transferir(valor, contDest);
                                    PersistenciaEmArquivo.getInstance().atualizarCliente(cliDest);
                                    PersistenciaEmArquivo.getInstance().atualizarCliente(cliOri);
                                    break;
                                }
                            }
                        }
                    }
                }
                case 11: {
                    System.out.println("Digite o CPF do cliente portador da conta");
                    String cpf = scanner.next();

                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        System.out.println(cli.contas);
                        System.out.println("Digite o numero da conta a consultar o extrato");
                        String numero = scanner.next();
                        if (cli.localizarContaPorNumero(numero) == null) {
                            System.out.println("Conta não existe");
                            break;
                        } else {
                            IConta c = cli.localizarContaPorNumero(numero);
                            System.out.println("Digite o ano do extrato que quer ver");
                            int ano = scanner.nextInt();

                            System.out.println("Digite o numero equivalente ao mes do extrato que quer ver");
                            System.out.println(" 1. JANEIRO ");
                            System.out.println(" 2. FEVEREIRO ");
                            System.out.println(" 3. MARÇO ");
                            System.out.println(" 4. ABRIL ");
                            System.out.println(" 5. MAIO ");
                            System.out.println(" 6. JUNHO ");
                            System.out.println(" 7. JULHO ");
                            System.out.println(" 8. AGOSTO ");
                            System.out.println(" 9. SETEMBRO ");
                            System.out.println(" 10. OUTUBRO ");
                            System.out.println(" 11. NOVEMBRO ");
                            System.out.println(" 12. DEZEMBRO ");
                            int mes = scanner.nextInt();

                            if (mes < 1 || mes > 12) {
                                System.out.println("Mes invalido");
                                break;
                            } else {
                                Month mesFormat = Month.of(mes);
                                c.extrato(ano, mesFormat);
                                break;
                            }
                        }
                    }
                }
                case 12: {
                    System.out.println("Digite o CPF do cliente portador da conta");
                    String cpf = scanner.next();
                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        System.out.println(cli.contas);
                        System.out.println("Digite o numero da conta a consultar o extrato");
                        String numero = scanner.next();
                        if (cli.localizarContaPorNumero(numero) == null) {
                            System.out.println("Conta não existe");
                            break;
                        } else {
                            IConta c = cli.localizarContaPorNumero(numero);
                            System.out.println("O saldo é: " + c.getSaldo());
                            break;
                        }
                    }
                }
                case 13: {
                    System.out.println("Digite o CPF do cliente da conta");
                    String cpf = scanner.next();
                    if (PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf) == null) {
                        System.out.println("------------------");
                        System.out.println("Nenhum cliente encontrado!");
                        System.out.println("------------------");
                        break;
                    } else {
                        Cliente cli = PersistenciaEmArquivo.getInstance().localizarClientePorCPF(cpf);
                        float balance = 0;
                        for (IConta conta : cli.contas) {
                            balance += conta.getSaldo();
                        }

                        System.out.println("Seu balanço total é: " + balance);
                        break;
                    }
                }
                default:
                    throw new IllegalArgumentException("Unexpected value: " + op);
            }

        } while (op != 0);
    }
}
