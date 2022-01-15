package locations;

import characters.*;
import exceptions.NoMorePlaceInInventoryException;
import things.*;

public class Shore extends Place {
    public Shore(String name) {
        super(name);
    }

    @Override
    public void addHuman(Human newHuman) {
        super.addHuman(newHuman);
        System.out.printf("%s выбрался в локацию %s\n",newHuman,this.toString());
    }

    public static class SaltCrystal{
        public static void grindSaltCrystal(Human whoGrind) throws NoMorePlaceInInventoryException {
            System.out.printf("%s толчет соляные кристаллы в локации %s чтобы получить соль\n",whoGrind, whoGrind.getPlace().toString());
            Item salt = new Item("Солька", 1);
            whoGrind.takeItem(salt);
        }
    }
}
