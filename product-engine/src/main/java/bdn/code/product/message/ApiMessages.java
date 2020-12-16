package bdn.code.product.message;

public enum ApiMessages {

    UPDATED("Updated product with id %d"),
    NOT_FOUND("Unable to find products for %s market with these options"),
    INTERNAL_SERVER_ERROR("Unable to complete the request");

    private final String message;

    ApiMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
