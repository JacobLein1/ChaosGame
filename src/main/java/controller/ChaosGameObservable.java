package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public abstract class ChaosGameObservable {
    private List<ChaosGameObserver> chaosGameObservers;

    public ChaosGameObservable(){
        this.chaosGameObservers = new ArrayList<>();
    }

    public void attach(ChaosGameObserver observer){
        chaosGameObservers.add(observer);
    }
    public void detach (ChaosGameObserver chaosGameObserver){
        chaosGameObservers.remove(chaosGameObserver);
    }

    public void notifyObservers(){
        chaosGameObservers.forEach(observer -> observer.updateGame());
    }
}
