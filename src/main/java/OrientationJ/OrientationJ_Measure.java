//=============================================================================================================
//
// Project: Directional Image Analysis - OrientationJ plugins
// 
// Author: Daniel Sage
// 
// Organization: Biomedical Imaging Group (BIG)
// Ecole Polytechnique Federale de Lausanne (EPFL), Lausanne, Switzerland
//
// Information: 
// OrientationJ: http://bigwww.epfl.ch/demo/orientation/
// MonogenicJ: http://bigwww.epfl.ch/demo/monogenic/
//  
// Reference on methods and plugins
// Z. Püspöki, M. Storath, D. Sage, M. Unser
// Transforms and Operators for Directional Bioimage Analysis: A Survey 
// Advances in Anatomy, Embryology and Cell Biology, vol. 219, Focus on Bio-Image Informatics 
// Springer International Publishing, ch. 33, 2016.
//
//
// Reference the application measure of coherency
// R. Rezakhaniha, A. Agianniotis, J.T.C. Schrauwen, A. Griffa, D. Sage, 
// C.V.C. Bouten, F.N. van de Vosse, M. Unser, N. Stergiopulos
// Experimental Investigation of Collagen Waviness and Orientation in the Arterial Adventitia 
// Using Confocal Laser Scanning Microscopy
// Biomechanics and Modeling in Mechanobiology, vol. 11, no. 3-4, 2012.

// Reference the application direction of orientation
// E. Fonck, G.G. Feigl, J. Fasel, D. Sage, M. Unser, D.A. Ruefenacht, N. Stergiopulos 
// Effect of Aging on Elastin Functionality in Human Cerebral Arteries
// Stroke, vol. 40, no. 7, 2009.
//
// Conditions of use: You are free to use this software for research or
// educational purposes. In addition, we expect you to include adequate
// citations and acknowledgments whenever you present or publish results that
// are based on it.
//
//=============================================================================================================
package OrientationJ;
import gui_orientation.MeasureDialog;
import ij.IJ;
import ij.ImagePlus;
import ij.Macro;
import ij.WindowManager;
import ij.plugin.PlugIn;

public class OrientationJ_Measure implements PlugIn {

	public static void main(String arg[]) {
		new OrientationJ_Test_Stack_Image_Small().run("");
		new OrientationJ_Measure().run("");
	}

	public void run(String arg) {
	
		ImagePlus imp = WindowManager.getCurrentImage();
		if (imp == null) {
			IJ.noImage();
			return;
		}
		
		if (imp.getType() != ImagePlus.GRAY8 && imp.getType() != ImagePlus.GRAY16 && imp.getType() != ImagePlus.GRAY32) {
			IJ.error("Only process 8-bits, 16 bits or 32 bits image.");
			return;
		}
		
		if (Macro.getOptions() == null) {
			MeasureDialog dialog = new MeasureDialog(imp, false, 0.0);
			dialog.showDialog();
		}
		else {
			String options = Macro.getOptions();
			double sigma = Double.parseDouble(Macro.getValue(options, "sigma", "0.0"));
			MeasureDialog dialog = new MeasureDialog(imp, true, sigma);
			dialog.run();	
		}
	}
}