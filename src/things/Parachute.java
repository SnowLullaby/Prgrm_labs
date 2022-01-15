package things;

public class Parachute extends Item{
  private boolean isOpened = false;

  public Parachute(String name, int size) {
    super(name, size);
  }

  public void open() {
    this.isOpened = true;
    System.out.println("Парашют открыт");
  }

  public boolean getState() {
    return isOpened;
  }
}