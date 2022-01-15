package locations;

import java.util.HashMap;
import characters.*;
import exceptions.NoCharacterInLocationException;
import things.*;

public class Sky extends Place {
  // Для персонажа нужно переопределить hashCode чтобы положить его в Hashmap
  // Можно изменить, чтобы сохранялось значение над какой локацией находится персонаж
  private HashMap<Human, Integer> charactersHeights = new HashMap<>();
  private HashMap<Human, Place> charactersLocationBelow = new HashMap<>();
  private Wind wind = new Wind();

  public Sky(String name) {
    super(name);
  }

  @Override
  public void addClothes(Clothes newClothes) {
    System.out.println("Вы не можете добавлять одежду в локацию типа Sky");
  }

  @Override
  public void removeClothes(Clothes rmClothes) {
    System.out.println("Вы не можете удалять одежду в локацию типа Sky");
  }

  public boolean willWindMoveInNextLocation(Human human, Place nextLocation) {
      if (wind.windSpeed > 10){
          if(nextLocation != null) {
            System.out.printf("%s теперь летит над локацией %s\n", human.toString(), nextLocation.toString());
            charactersLocationBelow.put(human, nextLocation);
          }
        return true;
      } else return false;
  }

  public void willCharacterStartFly(Human human){
    if(human.containsParachute()){
      Parachute parachute = human.getParachuteName();
      human.dropItem(parachute);
      parachute.open();
      human.fly();
    } else {
      this.removeHuman(human);
      System.out.printf("Ваш %s разбился, потому что у него не было парашюта. Какая жалость\n", human.toString());
    }
  }

  public void countWind(){
    wind.changeSpeed();
    System.out.println("Текущая скорость ветра в локации " + wind.getSpeed());
  }

  public class Wind {
    private int windSpeed = 10;
    
    public int getSpeed() {
      return this.windSpeed;
    }
    
    public void changeSpeed() {
      this.windSpeed = (int) (Math.random() * 50);
    }
  }

  public void addHuman(Human newHuman, Integer height, Place locationBelow) {
    super.addHuman(newHuman);
    System.out.println(newHuman.toString() + " падает в локации " + this.toString());
    charactersHeights.put(newHuman, height);
    charactersLocationBelow.put(newHuman, locationBelow);
  }

  public void removeHuman(Human rmHuman) throws NoCharacterInLocationException {
    if (this.containsHumans(rmHuman)) {
      super.removeHuman(rmHuman);
      System.out.printf("%s презмляется \n", rmHuman);
      getCharacterLocationBelow(rmHuman).addHuman(rmHuman);
      charactersHeights.remove(rmHuman);
      charactersLocationBelow.remove(rmHuman);
    }
    else throw new NoCharacterInLocationException(rmHuman, this.toString());
  }

  // Описать насколько высоко находится персонаж
  public int getCharacterHeight(Human character) {
    Integer height = charactersHeights.get(character);
    return height;
  }
  
  public Place getCharacterLocationBelow(Human character) {
    Place locationBelow = charactersLocationBelow.get(character);
    return locationBelow;
  }
  
  public void changeCharacterHeight(Human character, int changeByValue) {
    if (getCharacterHeight(character) + changeByValue <= 0){
      this.removeHuman(character);
    } else charactersHeights.put(character, getCharacterHeight(character) + changeByValue);
  }
}