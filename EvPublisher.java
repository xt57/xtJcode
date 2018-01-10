//
//
//

package net.xt57;

import java.util.Observable;

//import java.util.Observer;


public
/////////////////////////////////////////////////////////////////////
class
EvPublisher extends Observable {
/////////////////////////////////////////////////////////////////////
	public
	void
	changeSomething() {
	///////////////////
		// Notify observers of change
		setChanged();
		notifyObservers();
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





//////////////////////////////////////////////////////////////////////////





import java.util.Observable;
import java.util.Observer;



  // The Observer normally maintains a view on the data
  class MyView implements Observer {
    // For now, we just print the fact that we got notified.
    public void update(Observable obs, Object x) {
      System.out.println("update(" + obs + "," + x + ");");
    }
  }

	// The Observable normally maintains the data
  class MyModel extends Observable {
    public void changeSomething() {
      // Notify observers of change
      setChanged();
      notifyObservers();
    }
  }
}



public class ObservDemo extends Object {
  MyView view;

  MyModel model;

  public ObservDemo() {

    view = new MyView();

    model = new MyModel();
    model.addObserver(view);

  }

  public static void main(String[] av) {
    ObservDemo me = new ObservDemo();
    me.demo();
  }

  public void demo() {
    model.changeSomething();
  }


*/