package bdn.code.client.message;

public enum ApiMessages {

    CREATED("Created new client with id %d"),
    UPDATED("Updated client with id %d"),
    NOT_FOUND("Unable to find client with id %d"),
    INTERNAL_SERVER_ERROR("Unable to complete the request");

    private final String message;

    ApiMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
