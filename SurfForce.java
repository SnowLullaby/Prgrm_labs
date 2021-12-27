import java.util.Random;

public enum SurfForce {
    LOW("Слабый прибой", 4),
    MEDIUM("Средний прибой", 5),
    HIGH("Cильный прибой", 6);

    private String type;
    private int forceNum;
    private static Random RANDOM = new Random();

    SurfForce(String type, int forceNum) {
        this.type = type;
        this.forceNum = forceNum;
    }

    public String getType() {
        return this.type;
    }

    public int getForceNum() {
        return this.forceNum;
    }

    public static SurfForce getRandomState() {
        return SurfForce.values()[RANDOM.nextInt(3)];
    }
}