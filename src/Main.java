import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato:");
        System.out.print("Numero: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), fmt);
        System.out.print("Valor do contrato: ");
        double value = sc.nextDouble();
        System.out.print("Entre com o numero de parcelas: ");
        int installment = sc.nextInt();

        Contract obj = new Contract(number, date, value);
        ContractService service = new ContractService(new PaypalService());
        service.processContract(obj, installment);

        System.out.println("PARCELAS: ");
        for(Installment i : obj.getInstallments()) {
            System.out.println(i);
        }

        sc.close();

    }
}