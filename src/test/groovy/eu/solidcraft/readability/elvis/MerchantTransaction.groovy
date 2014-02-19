package eu.solidcraft.readability.elvis

class MerchantTransaction {
    /*
        Requirements:
        To do a transaction we need to:
         - calculate what money transfers need to be done
         - check validation rules fot this phone number
         - send money to internal bank system
         - save our transaction
         - notify customer about success
         - update information about transaction attempt
     */

    void calculateTransfers() {}
    boolean verifyValidation(terminalPhoneNumber) {}
    void payInCyclone(terminalPhoneNumber, outletName) {}
    void saveTransaction(terminalPhoneNumber) {}
    void notifyCustomerAboutSuccess(terminalPhoneNumber) {}
    void finishSuccessfulPaymentByUpdatingAttempt() {}


    TransactionStatus performPayment() {
        try {
            calculateTransfers()
        } catch (Exception e) {
            return new TransactionFailed(failureType: FailureType.AMOUNT_DOES_NOT_MATCH_PRICE)
        }

        if(!verifyValidation(terminalPhoneNumber)) {
            return new TransactionFailed(failureType: FailureType.CUSTOMER_NOT_ALLOWED_TO_PAY)
        }

        try {
            payInCyclone(terminalPhoneNumber, outletName)
        } catch (Exception e) {
            return new TransactionFailed(failureType: FailureType.MOBILE_MONEY_REJECTED_TRANSACTION)
        }

        try {
            saveTransaction(terminalPhoneNumber)
        } catch (Exception e) {
            return new TransactionFailed(failureType: FailureType.TRANSACTION_SAVING_ERROR)
        }

        try {
            notifyCustomerAboutSuccess(terminalPhoneNumber)
        } catch (Exception e) {
            return new TransactionFailed(failureType: FailureType.INTERNAL_ERROR)
        }

        try {
            finishSuccessfulPaymentByUpdatingAttempt()
        } catch (Exception e) {
            return new TransactionFailed(failureType: FailureType.TRANSACTION_CREATION_ERROR)
        }

        return new TransactionSuccessful()
    }
}

class TransactionStatus {}
class TransactionSuccessful extends TransactionStatus {}
class TransactionInProgress extends TransactionStatus {}

class TransactionFailed extends TransactionStatus {
    FailureType failureType
    String failureDetails

    static constraints = {
        failureType(nullable: false)
        failureDetails(nullable: true)
    }

    static mapping = {
        failureType(fetch: 'join', cascade: 'all')
        failureDetails(type: "text")
    }
}

enum FailureType {
    BAD_INPUT_PARAMS,
    BAD_SHORTCODE,
    WRONG_PAYMENT_METHOD,
    AMOUNT_DOES_NOT_MATCH_PRICE,
    PRODUCT_NOT_FOUND,
    CUSTOMER_NOT_ALLOWED_TO_PAY,
    TRANSACTION_CREATION_ERROR,
    MOBILE_MONEY_REJECTED_TRANSACTION,
    TRANSACTION_SAVING_ERROR,
    BAD_FEE,
    EXTERNAL_BILLER_ERROR,
    BILLER_NOT_FOUND,
    AMOUNT_TOO_LOW,
    INTERNAL_ERROR
}
