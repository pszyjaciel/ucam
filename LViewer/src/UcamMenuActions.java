
// Allows to locate the HelloWorld class.
import com.barco.ets.ucam.dtl.Ucamaction;
import com.barco.ets.ucam.hypertool.Umnbutton;
import com.company.ucam.layer.LayerViewer;

/**
 * Class loaded by Ucam.
 * Only its static block is executed.
 */
public class UcamMenuActions {
	// Static block executed when the class is loaded.
	static {
		// Action to associate with the Umnbutton.
		// Its action() method is executed when the button
		// is clicked.
		// Calls the sayHello() method from the
		// com.company.ucam.hello.HelloWorld class.
		Ucamaction act = new Ucamaction() {
			public void action() {
				LayerViewer.showDialog();
			}
		};

		// Create a new Umnbutton named 'Hello'.
		// The name can be used in the menu and toolbar resource files.
		new Umnbutton("LayerViewer", act);
	}
}