package exceptions;

import things.Clothes;

public class NoClothesInLocationException extends RuntimeException {
    public NoClothesInLocationException(Clothes clothes, String location) {
        super("В локации " + location + " не найдено одежды " + clothes);
    }
}
