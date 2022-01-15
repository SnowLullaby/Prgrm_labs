package exceptions;

import things.Clothes;

public class NoClothesOnHumanException extends RuntimeException{
    public NoClothesOnHumanException(Clothes clothes, String human) {
        super("На персонаже " + human + " нет одежды " + clothes);
    }
}
