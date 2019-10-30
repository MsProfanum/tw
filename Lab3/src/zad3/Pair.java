package zad3;

public class Pair {
    private Person person1, person2;

    public Pair(WaiterMonitor waiterMonitor, int id){
        person1 = new Person(waiterMonitor, id);
        person2 = new Person(waiterMonitor, id);
    }

    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }
}
