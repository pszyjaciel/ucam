// Allows to locate the LayerViewer class.
import com.barco.ets.ucam.hypertool.Ucamapp;
import com.barco.ets.ucam.dtl.Ucamaction;
import com.company.ucam.layer.LayerViewer;

/**
 * Class loaded by Ucam.
 * Only its static block is executed.
 */
public class UcamActions {

	// Static block executed when the class is loaded.
	static {

		// Calls the showDialog() method from the
		// com.company.ucam.layer.LayerViewer class.
		Ucamaction act = new Ucamaction() {
			public void action() {
				LayerViewer.showDialog();
			}
		};

		// Adds a button called 'layer_viewer' with label
		// 'View Layers' to the hypertool menu.
		Ucamapp.cO.add_pb("layer_viewer", "View Layers", null, act, "hypertool_menu", true);

	}
}