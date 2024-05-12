package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;


public abstract class ChaosGameObservable {
    private List<Observer> observers;

    public ChaosGameObservable(){
        this.observers = new ArrayList<>();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach (Observer observer){
        observers.remove(observer);
    }
    public void notifyObservers(){
        //observers.forEach(observer -> observer.update(,null));
    }
}
