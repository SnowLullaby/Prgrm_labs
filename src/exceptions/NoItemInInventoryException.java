package exceptions;

import things.Item;

public class NoItemInInventoryException extends RuntimeException {
    public NoItemInInventoryException(Item item, String name) {
        super(name + " не имеет в инвентаре " + item);
    }
}
