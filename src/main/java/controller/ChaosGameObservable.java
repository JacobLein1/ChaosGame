package controller;

import java.util.ArrayList;
import java.util.List;


/**
 * This class contains a list of all observers and notifies them when called.
 */
public abstract class ChaosGameObservable {
    private List<ChaosGameObserver> chaosGameObservers;

    /**
     * Instantiates a new Chaos game observable.
     */
    public ChaosGameObservable(){
        this.chaosGameObservers = new ArrayList<>();
    }

    /**
     * Attaches a new observer.
     *
     * @param chaosGameObserver the observer
     */
    public void attach(ChaosGameObserver chaosGameObserver){chaosGameObservers.add(chaosGameObserver);
    }

    /**
     * Detaches an observer.
     *
     * @param chaosGameObserver the chaos game observer
     */
    public void detach (ChaosGameObserver chaosGameObserver){
        chaosGameObservers.remove(chaosGameObserver);
    }

    /**
     * Notify all observers.
     */
    public void notifyObservers(){
        chaosGameObservers.forEach(observer -> observer.updateGame());
    }
}
