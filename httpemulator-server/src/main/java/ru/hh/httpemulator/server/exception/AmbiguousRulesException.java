package ru.hh.httpemulator.server.exception;

public class AmbiguousRulesException extends Exception {

  public AmbiguousRulesException(String message, Throwable cause) {
    super(message, cause);
  }

  public AmbiguousRulesException(String message) {
    super(message);
  }

}
