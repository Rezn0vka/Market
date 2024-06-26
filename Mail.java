import java.util.LinkedList;
import java.util.Queue;

// Интерфейс QueueBehaviour, определяющий методы работы с очередью
interface QueueBehaviour {
    void enqueue(Person person); // Добавить человека в очередь
    Person dequeue(); // Удалить человека из очереди
    boolean isQueueEmpty(); // Проверить, пуста ли очередь
}

// Интерфейс MarketBehaviour, определяющий методы для добавления и удаления людей из очереди
interface MarketBehaviour {
    void acceptOrder(); // Принять заказ
    void releaseOrder(); // Отдать заказ
    void update(); // Обновить состояние магазина
}

// Класс Person, представляющий человека в очереди
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Класс Market, реализующий оба интерфейса
public class Market implements QueueBehaviour, MarketBehaviour {
    private Queue<Person> queue;

    public Market() {
        queue = new LinkedList<>();
    }

    // Реализация метода из интерфейса QueueBehaviour для добавления человека в очередь
    @Override
    public void enqueue(Person person) {
        queue.add(person);
        System.out.println(person.getName() + " вошел в очередь.");
    }

    // Реализация метода из интерфейса QueueBehaviour для удаления человека из очереди
    @Override
    public Person dequeue() {
        Person person = queue.poll();
        if (person != null) {
            System.out.println(person.getName() + " покинул очередь.");
        }
        return person;
    }

    // Проверка, пуста ли очередь
    @Override
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }

    // Реализация метода из интерфейса MarketBehaviour для принятия заказа
    @Override
    public void acceptOrder() {
        if (!isQueueEmpty()) {
            Person person = queue.peek();
            System.out.println("Заказ принят у " + person.getName());
        } else {
            System.out.println("Очередь пуста. Заказ не у кого принимать.");
        }
    }

    // Реализация метода из интерфейса MarketBehaviour для отдачи заказа
    @Override
    public void releaseOrder() {
        if (!isQueueEmpty()) {
            Person person = dequeue();
            System.out.println("Заказ отдан " + person.getName());
        } else {
            System.out.println("Очередь пуста. Некому отдавать заказ.");
        }
    }

    // Обновление состояния магазина (прием и отдача заказов)
    @Override
    public void update() {
        acceptOrder();
        releaseOrder();
    }

    // Основной метод для тестирования работы класса
    public static void main(String[] args) {
        Market market = new Market();

        Person person1 = new Person("Иван");
        Person person2 = new Person("Мария");

        market.enqueue(person1);
        market.enqueue(person2);

        market.update();
        market.update();
        market.update(); // Очередь пуста на этом этапе
    }
}
