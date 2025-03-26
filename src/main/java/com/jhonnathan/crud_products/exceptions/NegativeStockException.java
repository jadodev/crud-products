package com.jhonnathan.crud_products.exceptions;

public class NegativeStockException extends RuntimeException {
    public NegativeStockException(String message) {
        super(message);
    }
}
