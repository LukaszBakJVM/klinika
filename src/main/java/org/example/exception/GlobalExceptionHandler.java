package org.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalExceptionHandler {
    private  final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public  void handle(Exception e) {

        logger.error("Globalny handler złapał wyjątek", e);

    }
}
