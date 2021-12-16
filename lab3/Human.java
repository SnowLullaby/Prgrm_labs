public class Human {
    public int ManeuverAbility = 5;
    public int Stamina = 100; // количество энергии
    public boolean NowSleeping = false; // если сейчас персонаж отдыхает
    public int SleepTimer = 0; // время, которое он еще должен отдыхать

    public void Sleep(int TimeForSleep){ // погрузить в сон на хх часов
        this.Stamina = this.Stamina + TimeForSleep/2 > 100 ? 100 : this.Stamina + TimeForSleep/2;
        this.NowSleeping = true;
        this.SleepTimer = TimeForSleep;
    }

    public void WakeUp(){ // разбудит персонажа, если он уже проспал положенное время
        if (this.SleepTimer == 0)
            this.NowSleeping = false;
        else
            System.out.println("Не проснулся, попробуйте через " + this.SleepTimer);
    }
}
