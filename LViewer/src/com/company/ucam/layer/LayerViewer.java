/*
 * LayerViewer.java
 *
 * Copyright (c) 1999 BARCO n.v. All rights reserved.
 */

// Defines the package this class resides in.
// This should be the first line of code in the source file.
package com.company.ucam.layer;

// Standard Ucam packages.
import com.barco.ets.ucam.dtl.*;
import com.barco.ets.ucam.ui.*;
import com.barco.ets.ucam.hypertool.*;

// Additional User Interface packages needed.
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Implements a JDialog with an Udwaplane to display a layer selected in a
 * JComboBox.
 */
public class LayerViewer extends JDialog {
	// Only one dialog can be created. Once created, it is
	// recycled for later use.
	private static LayerViewer dialog = null;

	// The combobox containing the names of all layers.
	private JComboBox comboBox;

	// The drawing area.
	private Udwaplane dwa;

	// The javaPeer of the drawing area.
	private JPanel panel;

	// The DisplayPar of plane 1 of the drawing area.
	private Displaypar dsp;

	// The Graphics to draw directly to the screen.
	private Graphics panelGraphics;

	// The name of the currently selected layer.
	private String layerName;

	// The toggle for the skeleton setting.
	private JToggleButton skeletonButton;

	// The last dragged rectangle.
	private int[] dragRect;

	/**
	 * Constructs a new dialog window and initializes all values.
	 */
	public LayerViewer() {
		// Creates and initializes the JDialog with the
		// Ucam main window as parent and 'Layer Viewer'
		// as title.
		super(Uiobj.getFrame(), "Layer Viewer");
		getContentPane().setLayout(new BorderLayout());

		JPanel interior = new JPanel(new BorderLayout(5, 5));
		interior.setBorder(new EmptyBorder(5, 5, 5, 5));
		((JPanel) getContentPane()).add(interior, BorderLayout.CENTER);

		// The changeLayer() method is called when an item
		// is selected in the layer combobox.
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeLayer();
			}
		});
		interior.add(comboBox, BorderLayout.NORTH);

		// The changeLayer() method is called when the drawing
		// is resized, or a repaint is requested by the OS.
		dwa = new Udwaplane(null, "layer_viewer_dwa");
		Ucamaction act = new Ucamaction() {
			public void action() {
				changeLayer();
			}
		};
		dwa.setpaintaction(act);
		dwa.setresizeaction(act);

		// The dragRectangle() method is called when the user
		// moves the mouse over the drawing area with a mouse
		// button held down.
		dwa.setmoveaction(new Ucamaction() {
			public void action() {
				dragRectangle();
			}
		});

		// The zoom() method is called when the user releases
		// the mouse button over the drawing area.
		dwa.setreleaseaction(new Ucamaction() {
			public void action() {
				zoom();
			}
		});

		panel = (JPanel) dwa.getJavaPeer();
		panel.setPreferredSize(new Dimension(320, 240));
		interior.add(panel, BorderLayout.CENTER);

		// Create a panel to display the control buttons.
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		buttonPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

		// The refresh button.
		JButton button = new JButton("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		buttonPanel.add(button);

		// The cancel button.
		button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.hide();
			}
		});
		buttonPanel.add(button);

		interior.add(buttonPanel, BorderLayout.SOUTH);

		// Create a toolbar for the totalView and skeleton buttons.
		JToolBar toolBar = new JToolBar();

		// The totalView button.
		button = new JButton(new ImageIcon(getClass().getResource("/icons/nozoom.gif")));
		button.setMargin(new Insets(0, 0, 0, 0));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.totalView();
			}
		});
		toolBar.add(button);

		toolBar.addSeparator();

		// The skeleton button.
		skeletonButton = new JToggleButton(new ImageIcon(getClass().getResource("/icons/skeleton0.gif")));
		skeletonButton.setMargin(new Insets(0, 0, 0, 0));
		skeletonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.skeleton();
			}
		});
		skeletonButton.setSelectedIcon(new ImageIcon(getClass().getResource("/icons/skeleton1.gif")));
		toolBar.add(skeletonButton);

		getContentPane().add(toolBar, BorderLayout.NORTH);

		// Resize the dialog to its preferred size.
		pack();
	}

	/**
	 * Checks which layer is selected, loads the layer and displays it. When the
	 * layer is changed, a total view is displayed.
	 * 
	 * Called when an item is selected in the layer combobox, or when anything
	 * needs to be repainted.
	 */
	public void changeLayer() {
		// Check if there is a job loaded.
		if (Ucamv6.ucam_job == null) {
			return;
		}

		// Check if the drawing area is already initialized.
		dsp = dwa.dsp[1];
		if (dsp == null) {
			return;
		}

		// Initialize the Graphics object for direct drawing
		// to the screen.
		panelGraphics = panel.getGraphics();
		panelGraphics.setXORMode(Color.white);

		// Get the currently selected layer.
		String name = (String) comboBox.getSelectedItem();

		Ulayer layer = Ucamv6.ucam_job.getlayerbyname(name);
		if (layer == null) {
			return;
		}

		layer.load();

		// If a new layer is selected, initialize the dsp
		// to display a total view.
		if (!name.equals(layerName)) {
			layerName = name;
			dsp.totalview(layer.enclosingbox());
		}

		// Draw the layer in the dsp.
		layer.display(dsp, null);

		// Clear the previous rubber rectangle.
		dragRect = null;

		// Draw to the screen.
		dwa.updateBitmap();
		dwa.repaint();
	}

	/**
	 * Reloads the layer names from the current job and puts them in the
	 * combobox. Select the first item.
	 * 
	 * Called when opening the dialog or pressing the refresh button.
	 */
	public void refresh() {
		if (Ucamv6.ucam_job == null) {
			return;
		}

		// Clear the combobox.
		comboBox.removeAllItems();

		// Fill up the combobox.
		for (int i = 1; i <= Ucamv6.ucam_job.numlayers(); ++i) {
			comboBox.addItem(Ucamv6.ucam_job.getlayer("all", null, i).name());
		}

		// Select the first item, resulting in a totalview
		// of the first layer.
		comboBox.setSelectedIndex(0);
	}

	/**
	 * Implements rubberbanding functionality.
	 * 
	 * Called when moving the mouse over the drawing area with a button held
	 * down.
	 */
	public void dragRectangle() {
		if (panelGraphics == null) {
			return;
		}

		// We are not interested in the right mouse button.
		if (Uiobj.cO.state.getInt("mb") == 4) {
			return;
		}

		int x = Uiobj.cO.state.getInt("x");
		int y = Uiobj.cO.state.getInt("y");
		int pressX = Uiobj.cO.state.getInt("pressx");
		int pressY = Uiobj.cO.state.getInt("pressy");

		// Erase the old rectangle when there was one.
		// Erasing is done by drawing the rectangle a second
		// time. The XOR setting in panelGraphics makes
		// sure this erases the previous rectangle.
		if (dragRect != null) {
			panelGraphics.drawRect(dragRect[0], dragRect[1], dragRect[2], dragRect[3]);
		}

		dragRect = new int[4];
		if (x > pressX) {
			dragRect[0] = pressX;
			dragRect[2] = x - pressX;
		} else {
			dragRect[0] = x;
			dragRect[2] = pressX - x;
		}
		if (y > pressY) {
			dragRect[1] = pressY;
			dragRect[3] = y - pressY;
		} else {
			dragRect[1] = y;
			dragRect[3] = pressY - y;
		}

		// Draw the new rectangle.
		panelGraphics.drawRect(dragRect[0], dragRect[1], dragRect[2], dragRect[3]);
	}

	/**
	 * Zoom in or out.
	 * 
	 * Called when releasing a mouse button.
	 */
	public void zoom() {
		if (dsp == null) {
			return;
		}

		if (panelGraphics == null) {
			return;
		}

		// When the right mouse button was released, zoom
		// out with a factor 2.
		if (Uiobj.cO.state.getInt("mb") == 4) {
			dsp.zoomout();
		}
		// Zoom in.
		else {
			// Remove the drag rectangle.
			if (dragRect != null) {
				panelGraphics.drawRect(dragRect[0], dragRect[1], dragRect[2], dragRect[3]);
			}
			dragRect = null;

			// Get the zoom coordinates.
			int x = Uiobj.cO.state.getInt("x");
			int y = Uiobj.cO.state.getInt("y");
			int pressX = Uiobj.cO.state.getInt("pressx");
			int pressY = Uiobj.cO.state.getInt("pressy");

			// The mouse was not moved, zoom with factor 2.
			if ((x == pressX) && (y == pressY)) {
				int width = dwa.width();
				int height = dwa.height();
				dsp.zoomin(x - width / 4, y - height / 4, x + width / 4, y + height / 4);
			} else {
				dsp.zoomin(pressX, pressY, x, y);
			}
		}

		// Redraw the layer with the new settings.
		changeLayer();
	}

	/**
	 * Restore the total view of the layer.
	 * 
	 * Called when the totalview button was clicked.
	 */
	public void totalView() {
		if (dsp == null) {
			return;
		}

		layerName = null;
		changeLayer();
	}

	/**
	 * Change the skeleton setting.
	 * 
	 * Called when the skeleton button was clicked.
	 */
	public void skeleton() {
		if (dsp == null) {
			return;
		}

		if (skeletonButton.isSelected()) {
			dsp.setoptions("skeleton");
		} else {
			dsp.setoptions("solid");
		}

		changeLayer();
	}

	/**
	 * Refreshes and shows the window.
	 * 
	 * Called when the menu item is selected.
	 */
	public static void showDialog() {
		if (dialog == null) {
			dialog = new LayerViewer();
		}

		dialog.refresh();
		dialog.show();
	}
}