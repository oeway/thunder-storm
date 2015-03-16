# Dowloads #

You can browse all downloads in our [Google Drive directory](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/).


## ThunderSTORM ##
  * version 1.3 _(2014-11-08)_: [download](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/builds/stable/1.3-2014-11-08.jar), [browse code](https://code.google.com/p/thunder-storm/source/browse/?name=v1.3)
  * version 1.2 _(2014-04-09)_: [download](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/builds/stable/1.2-2014-04-09.jar), [browse code](https://code.google.com/p/thunder-storm/source/browse/?name=v1.2)
  * version 1.1 _(2014-02-03)_: [download](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/builds/stable/1.1-2014-02-03.jar), [browse code](https://code.google.com/p/thunder-storm/source/browse/?name=v1.1)
  * version 1.0 _(2013-12-03)_: [download](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/builds/stable/1.0-2013-12-03.jar), [browse code](https://code.google.com/p/thunder-storm/source/browse/?name=v1.0)

Or download the latest daily build version [here](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/builds/daily/latest.html).

Please, **rename the downloaded file** to `Thunder_STORM.jar` and see the [installation guide](Installation.md) for further instructions.

If you already have ThunderSTORM installed, use the built-in updater for further updates.


## Additional resources ##
  * [Supplementary Note "Methodology and Algorithms"](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/paper/SupplementaryNote.pdf)
  * [User's Guide](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/paper/UsersGuide.pdf)
  * [Poster](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/paper/FOM2014-poster-A4.pdf) presented on [Focus on Microscopy 2014](http://www.focusonmicroscopy.org/2014/index.html)
  * [Sample data](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/data/12%20+%20cyl%20lens.zip)
  * [Siemens star template](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/extras/siemens_star.tif)
  * [Macro](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/extras/montecarlo.ijm) from the [example](MonteCarlo#Monte-Carlo_simulations_-_an_example_using_the_ImageJ_Macro_Lang.md)
  * Tagged Spot Format: [TSF.proto](http://www.micro-manager.org/wiki/Tagged_Spot_File_(tsf)_format#TSF.proto_file:)
  * Our Google Protocol Buffer file format: [results.proto](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/extras/results.proto)


## Changelog ##

#### Version 1.3 ####
  * New module for **Data analysis**: Coordinate based co-localization (CBC) of dual-color images.
  * New module for **Sub-pixel localization of molecules**: option to use z-position estimation based on 3D-DAOSTORM model (astigmatic imaging) including calibration module.
  * New module for **Post-processing**: filtering based on local density of molecules.
  * Update of **Sub-pixel localization of molecules module**: enhanced accuracy of estimate of z-position.
  * Update of **Post-processing**: merging molecules in subsequent frames can be limited by maximum number of frames.
  * Update of **Table of results**: table shows Chi2 error for LSQ methods.
  * Update of **Visualization** module: support for z-uncertainty, ROI can be specified as a float number, automatic estimation of z-range for 3D rendering.
  * Update of **Performance evaluation** module: tolerance radius can be specified as a circle or sphere.
  * Migrating to Maven repository, cleaning up the repository, reducing the number of dependencies.
  * Update of help files + script for their automatic compilation.
  * Some bug fixes.


#### Version 1.2 ####
  * The pixel size and units of images are set automatically (`ImageJ → Image → Properties`).
  * Table with molecular localizations (results and/or ground-truth coordinates) is shown in analog units after data analysis and/or file import.
  * Update of **Image filtering** module: automatic kernel size for filters based on Gaussian kernel, wavelet filter is adjustable (kernel is generated from the definition of B-spline basis functions), 3rd wavelet level is removed, macro command has changed for the wavelet filter.
  * Update of **Approximate localization of molecules** module: local maximum is default method, non-maximum suppression is using radius instead of size.
  * New **Post-processing** module: drift correction using fiducial markers, drift trajectory can be saved/loaded to/from a file.
  * Update of **Post-processing** module: drift correction using cross-correlation is more robust, merging of reappearing molecules in subsequent frames allows molecules to disappear for a user-specified number of frames, ROI selection in the live preview supports more regions.
  * Update of **Visualization** module: negative coordinates are allowed in ROI (left top x and y).
  * Update of **Import results/ground-truth** module: file format is recognized by file extension.
  * Update of **Generator of simulated data** module: imaged molecules can be modeled by any of the implemented PSF, range can be specified by a scalar, mean photon background intensity can be varied by a user-specified gray-scale mask.
  * Update of **Performance evaluation** module: using Gale-Shapley algorithm instead of greedy approach.
  * **GUI changes:** Import/Export dialog (macro commands have changed), post-processing (merging and drift correction module).
  * New sections added to User's guide and Tutorials: Guidelines for the choice of parameters, Comparison to other software packages.
  * Update of ThunderSTORM's help.
  * Some bug fixes.


#### Version 1.1 ####
  * New **Import/Export** module: Tagged spot file (tsf) format.
  * New **Post-processing** module: Visualization and export of data in ROI selection.
  * Efficient import of CSV file format.
  * Some bug fixes in filters, calibration, and table with results.
  * Better compatibility with Java 6.
  * New sections added to User's guide and Tutorials: Visualizing a rotating 3D projection, Visualizing and exporting molecular localizations from a region of interest, Analyzing super-resolution images with ImageJ, Batch Processing, For Developers.


#### Version 1.0 ####
  * **Feature enhancement filters:** no filter (use raw data), low-pass filters (Averaging, Gaussian, Median), and band-pass filters (Lowered Gaussian, Difference-of-Gaussians filter, Difference of averaging filters, Wavelet filter).
  * **Approximate localization of molecules** (on filtered images): detection of local maxima, non-maximum suppression, and calculation of the centroid of connected components of segmented objects. Threshold is defined as an expression combining mathematical functions with quantities based on raw or filtered images.
  * **Sub-diffraction localization of molecules** (on raw data): no estimator (use approximate localizations), computing the centroid of local neighborhoods, radial symmetry approach, and fitting (standard or weighted non-linear least-squares methods, maximum-likelihood estimation) of a suitable PSF model (Gaussian, integrated Gaussian, elliptical Gaussian) .
  * **3D super-resolution imaging:** astigmatism approach with an integrated calibration tool.
  * **Multiple-emitter fitting analysis:** both 2D and 3D with an option of  PSF model selection together with a fitting method for sub-diffraction localization.
  * **Post-processing methods:** filtering molecules with poor localization or other user-defined criteria, merging molecules reappearing in subsequent frames, removing duplicated molecules obtained in multiple emitter analysis, correction of molecular coordinates for lateral drift of the sample, support for multiple Z-stage data acquisition, possibility to undo the last change or to restore the original values.
  * **Visualization methods:** scatter plot, histogram with jittering option, average shifted histogram, Gaussian rendering; all methods 2D or slice-by-slice 3D.
  * **Import/export** of the localization results and ground-truth data: CSV, XLS, XML, YAML, JSON, Google protocol buffer.
  * **Generator of simulated data:** molecules are modeled by integrated Gaussian with a user-specified mean background level and range of intensity and FWHM, variable spatial density of molecules by means of a user-specified grayscale mask, linear drift of a sample, simulation of the photon counting process of CCD and EMCCD cameras.
  * **Performance evaluation** based on the ground-truth data: counting # of TP, # of FP, # of FN, precision, recall, F1-score, Jaccard index, RMSE distance.
  * **Parallel processing** of the input data.
  * **Support for batch processing** using ImageJ's Macro Language.