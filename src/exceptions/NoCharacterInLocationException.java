package exceptions;

import characters.Human;

public class NoCharacterInLocationException extends RuntimeException {
  public NoCharacterInLocationException(Human human, String location) {
    super("В локации " + location + " не найдено персонажа " + human);
  }
}