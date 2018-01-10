//
//
//

package com.xt57;


import java.util.Observable;
import java.util.Observer;


/////////////////////////////////////////////////////////////////////
class
evStreamReader extends Observable {
/////////////////////////////////////////////////////////////////////
	public
	void
	changeSomething() {
	///////////////////

		// data is read here from the post'd input

		// Notify observers of change
		setChanged();
		notifyObservers();
	}
}


///////////////////////////////////////////////////////////////////
class
evStreamDigester implements Observer {
///////////////////////////////////////////////////////////////////

	@Override
	public
	void
	update(Observable obs, Object x) {
	//////////////////////////////////


		// digest the read and modify the session accordingly

		 System.out.println("evStreamDigester update() call (" + obs + "," + x + ");" );
	}
}





/*

// from tutorialsPoint's addObserver() tutorial

class ObservedObject extends Observable {

	private String watchedValue;

	public ObservedObject(String value) {
		watchedValue = value;
	}

	public void setValue(String value) {

		// if value has changed notify observers
		if( ! watchedValue.equals(value) ) {

			System.out.println("Value changed to new value: "+value);
			watchedValue = value;

			// mark as value changed
			setChanged();

			// trigger notification
			notifyObservers(value);
		}//if
	}
}

public class ObservableDemo implements Observer {
	public static void main(String[] args) {

		// create watched and watcher objects
		ObservedObject watched = new ObservedObject("Original Value");

		// watcher object listens to object change
		ObservableDemo watcher = new ObservableDemo();

		// trigger value change
		watched.setValue("New Value");

		// add observer to the watched object
		watched.addObserver(watcher);

		// trigger value change
		watched.setValue("Latest Value");
	}

	public void update(Observable obj, Object arg) {
		System.out.println( "Update called with Arguments: " + arg);
	}
}

*/








/*

	//	this example should be relatively easy to follow and translate!

	//	the "subject" object is the host object
	//		the "observers" of the subjects "updates" (changes in state) are
	//		monitored so that the updated number may be displayed in the
	//		base that corresponds to the observer's responsibility

  import java.util.ArrayList;
  import java.util.List;

  public class Subject {

  private List<Observer> observers
        = new ArrayList<Observer>();
  private int state;

  public int getState() {
    return state;
  }

 public void setState(int state) {
   this.state = state;
   notifyAllObservers();
 }

   public void attach(Observer observer){
     observers.add(observer);
   }

  public void notifyAllObservers(){
    for (Observer observer : observers) {
     observer.update();
  }
}
}

Step 2

Create Observer class.

Observer.java

public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}
Step 3

Create concrete observer classes

BinaryObserver.java

public class BinaryObserver extends Observer{

  public BinaryObserver(Subject subject){
     this.subject = subject;
     this.subject.attach(this);
  }

  @Override
  public void update() {
     System.out.println( "Binary String: "
     + Integer.toBinaryString( subject.getState() ) );
  }
}

HexaObserver.java

public class HexaObserver extends Observer{

  public HexaObserver(Subject subject){
    this.subject = subject;
    this.subject.attach(this);
 }

  @Override
  public void update() {
     System.out.println( "Hex String: "
    + Integer.toHexString( subject.getState() ).toUpperCase() );
}
}

Step 4

Use Subject and concrete observer objects.

ObserverPatternDemo.java

 public class ObserverPatternDemo {
    public static void main(String[] args) {
       Subject subject = new Subject();

		new HexaObserver(subject);
		new BinaryObserver(subject);

       System.out.println("First state change: 15");
       subject.setState(15);
       System.out.println("Second state change: 10");
       subject.setState(10);
 }
}

Step 5

Verify the output.

First state change: 15

Hex String: F

Octal String: 17

Binary String: 1111

Second state change: 10

Hex String: A

Octal String: 12

Binary String: 1010

*/






































/*



//	look into the "observer" example, posted below, after this example


// here is an example of "self-generated" events in Java!!!



import java.util.*;

// An interface to be implemented by everyone interested in "Hello" events
interface HelloListener {
    void someoneSaidHello();
}

// Someone who says "Hello"
class Initiater {
    private List<HelloListener> listeners = new ArrayList<HelloListener>();

    public void addListener(HelloListener toAdd) {
        listeners.add(toAdd);
    }

    public void sayHello() {
        System.out.println("Hello!!");

        // Notify everybody that may be interested.
        for (HelloListener hl : listeners)
            hl.someoneSaidHello();
    }
}

// Someone interested in "Hello" events
class Responder implements HelloListener {
    @Override
    public void someoneSaidHello() {
        System.out.println("Hello there...");
    }
}


class Test {
    public static void main(String[] args) {
        Initiater initiater = new Initiater();
        Responder responder = new Responder();

        initiater.addListener(responder);

        initiater.sayHello();  // Prints "Hello!!!" and "Hello there..."
    }
}








//	observer / observable example


package com.journaldev.design.observer;

import java.util.ArrayList;
import java.util.List;

public class MyTopic implements Subject {

	private List<Observer> observers;
	private String message;
	private boolean changed;
	private final Object MUTEX= new Object();

	public MyTopic(){
		this.observers=new ArrayList<>();
	}
	@Override
	public void register(Observer obj) {
		if(obj == null) throw new NullPointerException("Null Observer");
		if(!observers.contains(obj)) observers.add(obj);
	}

	@Override
	public void unregister(Observer obj) {
		observers.remove(obj);
	}

	@Override
	public void notifyObservers() {
		List<Observer> observersLocal = null;
		//synchronization is used to make sure any observer registered after message is received is not notified
		synchronized (MUTEX) {
			if (!changed)
				return;
			observersLocal = new ArrayList<>(this.observers);
			this.changed=false;
		}
		for (Observer obj : observersLocal) {
			obj.update();
		}

	}

	@Override
	public Object getUpdate(Observer obj) {
		return this.message;
	}

	//method to post message to the topic
	public void postMessage(String msg){
		System.out.println("Message Posted to Topic:"+msg);
		this.message=msg;
		this.changed=true;
		notifyObservers();
	}

}


*/



