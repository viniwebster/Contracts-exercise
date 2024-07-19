package services;

import entities.Contract;
import entities.Installment;

public class ContractService {
    private final OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {
        int monthOfInstallment = 1;
        while (monthOfInstallment <= months) {
            double valuePerMonth = contract.getTotalValue() / months;
            double paymentFee = onlinePaymentService.paymentFee(valuePerMonth);
            double interest = onlinePaymentService.interest(paymentFee, monthOfInstallment);
            double totalPerMonth = interest + paymentFee;
            contract.getInstallments().add(new Installment(contract.getDate().plusMonths(monthOfInstallment), totalPerMonth));
            monthOfInstallment++;
        }
    }
}
