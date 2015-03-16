# For developers #

This page provides information on:



## Building ThunderSTORM from source ##
To make any changes or upgrades to the code on your own, and to build ThunderSTORM, clone the Git repository by running the `git clone https://code.google.com/p/thunder-storm/` command from the Git console. The repository contains a Maven project which can be compiled in the Netbeans IDE.

In order to run ThunderSTORM directly from Netbeans, right-click on the Thunder\_STORM project and select `Properties`. Then select Run and type the ImageJ path into the `Working directory` text field, for example, `C:\Program Files\ImageJ`. Note that running ThunderSTORM directly from Netbeans will start ImageJ version 1.45s.


## ImageJ Macro Language ##
Most of the actions done in ThunderSTORM can be automated using the ImageJ macro language or other scripting languages supported by ImageJ. You can find the correct commands by recording a macro in ImageJ. Click on Plugins -> Macros -> Record... and then all actions done in ThunderSTORM will record a macro command in the recorder window.

You can find an example of batch processing with ThunderSTORM [in the tutorials section](BatchProcessing.md).

## Extending ThunderSTORM ##
New modules can be added to ThunderSTORM without modifying the distributed binary or recompiling from source. The java [ServiceLoader API](http://docs.oracle.com/javase/tutorial/ext/basics/spi.html) is used to load available modules at runtime from classpath (usually ImageJ's plugins folder).
In short, adding a module to ThunderSTORM consists of creating a jar and putting it to the ImageJ's plugins directory. The jar must contain a class implementing the module interface and a one-line configuration file.

See [Extending ThunderSTORM](newestimator.md) tutorial for an example of creating a new estimator module.



## Interface of ThunderSTORM Modules ##
You can currently add six types of modules by extending one of these classes:

  * `IImportExport` - importing and exporting the results table data
  * `IFilterUI` - feature enhancement filters
  * `IDetectorUI` - rough molecule localization
  * `IEstimatorUI` - sub-pixel precision localization
  * `IRendererUI` - visualization of the results
  * `PostProcessingModule` - manipulating the results table


## Reusing ThunderSTORM Features ##
TODO

  * ImageJ Macro Language
  * From Java (can we simply add Maven support into the repository?)
  * From Matlab
  * ... or similarly in any other language supporting Java ...


## Hidden Macro Commands ##
Here we list macro commands that are not available from GUI. These are mainly for debugging and testing.

So far we offer the following hidden macro commands:

  * In the **Generator of Simulated Data** there is an option called **singleFixed**. If it is set to `true`, the generator outputs a sequence of frames with a single molecule with a fixed position in the center of each frame. Example: `run("Generator of simulated data", "psf=[Integrated Gaussian] height=32 density=1 frames=100 width=32 addpoisssonvar=30 intensityrange=900 singlefixed=true");`
  * In the **Run Analysis** there is an option called **full\_image\_fitting** which interprets the whole image as a fitting region and runs multi-emitter fitting analysis (MFA) on it, thus MFA must be enabled for this feature in order to work properly. The fitting always starts with an assumption of a single molecule model which is at the position of detection with largest intensity. Note that the parameter `fitradius` is ignored in this use-case. Example: `run("Run analysis", "filter=[Wavelet filter (B-Spline)] scale=2.0 order=3 detector=[Local maximum] connectivity=8-neighbourhood threshold=std(Wave.F1) estimator=[PSF: Integrated Gaussian] sigma=1.3 method=[Weighted Least squares] full_image_fitting=true fitradius=3 fixed_intensity=true expected_intensity=500:900 nmax=10 pvalue=1.0E-6 mfaenabled=true keep_same_intensity=false renderer=[No Renderer]");`