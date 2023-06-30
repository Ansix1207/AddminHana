package hana.teamfour.addminhana.service;

import hana.teamfour.addminhana.DAO.TransactionDAO;
import hana.teamfour.addminhana.DTO.DepositDTO;
import hana.teamfour.addminhana.DTO.TransferDTO;
import hana.teamfour.addminhana.DTO.WithdrawDTO;
import hana.teamfour.addminhana.Exception.BalanceInsufficientException;
import hana.teamfour.addminhana.entity.TransactionEntity;

public class TransactionService {
    private TransactionDAO transactionDAO;

    public TransactionService() {
        this.transactionDAO = new TransactionDAO();
    }

    private TransactionEntity DepositDTOToEntity(DepositDTO depositDTO) {
        //t_id 와 t_status , t_date는 제외하고 만든다. t_balance 또한
        //입금이기 때문에 t_counterpart_id또한 제외
        return TransactionEntity.builder()
                .t_accid(depositDTO.getAcc_id())
                .t_type('+')
                .t_amount(depositDTO.getT_amount())
                .t_description(depositDTO.getT_description())
                .build();
    }

    private TransactionEntity WithdrawDTOToEntity(WithdrawDTO withdrawDTO) {
        //t_id 와 t_status , t_date는 제외하고 만든다. t_balance 또한
        //입금이기 때문에 t_counterpart_id또한 제외
        return TransactionEntity.builder()
                .t_accid(withdrawDTO.getAcc_id())
                .t_type('-')
                .t_amount(withdrawDTO.getT_amount())
                .t_description(withdrawDTO.getT_description())
                .build();
    }

    private TransactionEntity TransferDTOToEntity(TransferDTO transferDTO) {
        //t_id 와 t_status , t_date는 제외하고 만든다. t_balance 또한
        //입금이기 때문에 t_counterpart_id또한 제외
        return TransactionEntity.builder()
                .t_accid(transferDTO.getAcc_id())
                .t_counterpart_id(transferDTO.getT_counterpart_id())
                .t_amount(transferDTO.getT_amount())
                .t_description(transferDTO.getT_description())
                .build();
    }

    //입금 메소드 , 성공 실패 여부만 반환
    public String doDeposit(DepositDTO depositDTO) {
        TransactionEntity transactionEntity = DepositDTOToEntity(depositDTO);
        int p_id = transactionDAO.insertDeposit(transactionEntity);
        if (p_id == 2) {
            return "계좌 없음";
        } else if (p_id == 0) {
            return "입금 실패";
        } else {
            return "입금 성공";
        }
    }

    //출금 메소드, 성공 실패 여부만 반환
    public WithdrawDTO doWithdraw(WithdrawDTO withdrawDTO) {
        TransactionEntity transactionEntity = WithdrawDTOToEntity(withdrawDTO);
        int t_id = -99;
        String message = "출금 실패 사유(오류)";
        boolean isValid = false;
        WithdrawDTO responseWithdrawDTO = null;
        try {
            if (transactionDAO.checkAccountByPassword(transactionEntity, withdrawDTO.getAcc_password()) != null) {
                //비밀번호가 틀렸을 경우 널을 반환한다.
                transactionDAO.insertWithdraw(transactionEntity);
                isValid = true;
            } else {
                isValid = false;
            }
            //insertWithdraw가 실행되며 사용자예외인 (BalanceInsufficientException)이 던져진다.
        } catch (BalanceInsufficientException e) {
            //해당 메시지를 찾아서 출력(성공, 실패(사유))
            t_id = e.getT_id();
            System.out.println("e.getMessage() = " + e.getMessage());
            message = e.getMessage();
        } finally {
            if (isValid) { //출금 요청중에 비밀번호가 동일한 경우.
                responseWithdrawDTO = WithdrawDTO.from(transactionDAO.findById(t_id));
            } else { //출금 요청중에 비밀번호가 틀린 경우
                responseWithdrawDTO = new WithdrawDTO();
            }
            responseWithdrawDTO.setMessage(message);
            System.out.println("IN SERVICE : t_id = " + t_id);
            return responseWithdrawDTO;
        }
    }

    public int doTransfer(TransferDTO transferDTO) {
        System.out.println("Transfer Service : 들어옴");
        TransactionEntity transactionEntity = TransferDTOToEntity(transferDTO);
        int result = transactionDAO.doTransfer(transactionEntity);
        return result;
    }

    public WithdrawDTO verifyAccountPassword(WithdrawDTO withdrawDTO) {
        System.out.println("verifyAccountPassword Service : 들어옴");
        try {
            TransactionEntity requestEntity = TransactionEntity.builder()
                    .t_accid(withdrawDTO.getAcc_id())
                    .build();
            TransactionEntity responseEntity = transactionDAO.checkAccountByPassword(requestEntity, withdrawDTO.getAcc_password());
//            WithdrawDTO responseWithdrawDTO;
            if (responseEntity.getT_balance() != null) { //값이 배정되었으면 잔고만 더해서 컨트롤러로 던져줌
                withdrawDTO.setAcc_balance(responseEntity.getT_balance());
            }
            return withdrawDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "verifyAccountPassword (패스워드가 없습니다)");
//            e.printStackTrace();
        } finally {
            System.out.println("Service : verifyAccountPassword  - " + withdrawDTO.toString());
            return withdrawDTO;
        }
    }
}
