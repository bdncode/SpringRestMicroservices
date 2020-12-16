package bdn.code.trade.message;

public enum ApiMessages {

    CLIENT_HAS_NOT_ENOUGH_FUNDS("Client has not enough funds for this trade"),
    CLIENT_HAS_NOT_ENOUGH_QUANTITY("Client has not enough products for this trade"),
    CLIENT_NOT_UPDATED("Unable to update client"),
    CLIENT_NOT_FOUND("Unable to find client"),
    PRODUCT_NOT_UPDATED("Unable to update products"),
    PRODUCT_NOT_FOUND("Unable to find products with these options"),
    PRODUCT_HAS_NOT_ENOUGH_QUANTITY("Not enough products in this market"),
    INTERNAL_SERVER_ERROR("Unable to complete the request"),
    UNKNOWN_TRADE("Unknown trade type");

    private final String message;

    ApiMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
