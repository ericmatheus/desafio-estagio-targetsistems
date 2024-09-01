
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodigoFonte {
    public static void main(String[] args) {

        // ========================== QUESTÃO 1 ==========================

        int indice = 13;
        int soma = 0;
        int k = 0;
        while (k < indice) {
            k++;
            soma = soma + k;
        }
        System.out.print("QUESTÃO 1: ");
        System.out.println(soma); // RESPOSTA: 91


        // ========================== QUESTÃO 2 ==========================

        int numero = 25; // <-- NÚMERO PREVIAMENTE DEFINIDO. PARA SER ANALISADO SE ESTÁ CONTIDO NA SEQUÊNCIA
        int numero1 = 0;
        int numero2 = 1;
        int numeroFibonacci = 0;
        System.out.println("");
        System.out.println("QUESTÃO 2: ");

        if (numero == numero1 || numero == numero2) {
            System.out.println(numero + " pertence à sequência de Fibonacci.");
        } else {
            while (numeroFibonacci < numero) {
                numeroFibonacci = numero1 + numero2;
                numero1 = numero2;
                numero2 = numeroFibonacci;
            }

            if (numeroFibonacci == numero) {
                System.out.println(numero + " pertence à sequência de fibonacci.");
            } else {
                System.out.println(numero + " não pertence à sequência de Fibonacci.");
            }
        }


        // ========================== QUESTÃO 3 ==========================
        System.out.println("");
        System.out.println("QUESTÃO 3: ");
        List<Double> faturamentos = lerFaturamentoDoArquivo("src/faturamento.json");

        if (faturamentos.isEmpty()) {
            System.out.println("Não há dados de faturamento disponíveis.");
            return;
        }

        double menorFaturamento = calcularMenorFaturamento(faturamentos);
        double maiorFaturamento = calcularMaiorFaturamento(faturamentos);
        double mediaFaturamento = calcularMediaFaturamento(faturamentos);
        int diasAcimaDaMedia = contarDiasAcimaDaMedia(faturamentos, mediaFaturamento);

        System.out.println("Menor valor de faturamento: R$ " + menorFaturamento);
        System.out.println("Maior valor de faturamento: R$ " + maiorFaturamento);
        System.out.println("Número de dias com faturamento acima da média: " + diasAcimaDaMedia);

        // ========================== QUESTÃO 4 ==========================
        System.out.println("");
        System.out.println("QUESTÃO 4: ");
        double sp = 67836.43;
        double rj = 36678.66;
        double mg = 29229.88;
        double es = 27165.48;
        double outros = 19849.53;

        double totalFaturamento = sp + rj + mg + es + outros;

        double percentualSP = (sp / totalFaturamento) * 100;
        double percentualRJ = (rj / totalFaturamento) * 100;
        double percentualMG = (mg / totalFaturamento) * 100;
        double percentualES = (es / totalFaturamento) * 100;
        double percentualOutros = (outros / totalFaturamento) * 100;


        System.out.printf("SP: %.2f%%\n", percentualSP);
        System.out.printf("RJ: %.2f%%\n", percentualRJ);
        System.out.printf("MG: %.2f%%\n", percentualMG);
        System.out.printf("ES: %.2f%%\n", percentualES);
        System.out.printf("OUTROS: %.2f%%\n", percentualOutros);

        // ========================== QUESTÃO 5 ==========================

        System.out.println("");
        System.out.println("QUESTÃO 5: ");
        String string = "desenvolvedor";

        char[] caracteres = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            caracteres[i] = string.charAt(string.length() - 1 - i);
        }

        System.out.println("STRING INVERTIDA: " + new String(caracteres));

    }


    // ========================== FUNÇÕES QUESTÃO 3 ==========================

    public static List<Double> lerFaturamentoDoArquivo(String caminhoArquivo) {
        List<Double> faturamentos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = br.readLine()) != null) {
                sb.append(linha);
            }

            String json = sb.toString();
            String[] dias = json.split("\\{");

            for (String dia : dias) {
                if (dia.contains("valor")) {
                    String valorStr = dia.split("valor\":")[1].split("}")[0].trim();
                    double valor = Double.parseDouble(valorStr.replace(",", "."));
                    if (valor > 0) {
                        faturamentos.add(valor);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return faturamentos;
    }

    public static double calcularMenorFaturamento(List<Double> faturamentos) {
        double menorFaturamento = faturamentos.get(0);
        for (double faturamento : faturamentos) {
            if (faturamento < menorFaturamento) {
                menorFaturamento = faturamento;
            }
        }
        return menorFaturamento;
    }

    public static double calcularMaiorFaturamento(List<Double> faturamentos) {
        double maiorFaturamento = faturamentos.get(0);
        for (double faturamento : faturamentos) {
            if (faturamento > maiorFaturamento) {
                maiorFaturamento = faturamento;
            }
        }
        return maiorFaturamento;
    }

    public static double calcularMediaFaturamento(List<Double> faturamentos) {
        double soma = 0;
        for (double faturamento : faturamentos) {
            soma += faturamento;
        }
        return soma / faturamentos.size();
    }

    public static int contarDiasAcimaDaMedia(List<Double> faturamentos, double media) {
        int count = 0;
        for (double faturamento : faturamentos) {
            if (faturamento > media) {
                count++;
            }
        }
        return count;
    }
}