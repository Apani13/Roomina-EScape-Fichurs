package cat.itacademy.service;

import cat.itacademy.model.Client;
import cat.itacademy.observer.Observer;
import cat.itacademy.observer.Subject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NotificationService implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
