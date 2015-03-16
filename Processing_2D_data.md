# Processing 2D data #

This is an example of the workflow for processing 2D SMLM data in ThunderSTORM.

An example of (a fragment of) one of our real datasets is provided [here](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/data/12%20+%20cyl%20lens.zip). More datasets, both artificial and real, can be found on the [Single-Molecule Localization Microscopy Challenge website](http://bigwww.epfl.ch/smlm/).

Note that processing data with more channels should be done separately for each channel.




## Step 1 - Loading the data ##
**Small datasets:** Drag and drop the data file from the file explorer to ImageJ, or select `File → Open` in ImageJ, browse to the data file and press `Open`. This will load the entire image stack into memory.

**Large datasets**: For datasets larger than the memory allocated to ImageJ, select `File → Import → TIFF Virtual Stack`, browse to the file and press `Open`. This loads just a TIFF header and the currently displayed frame into memory.

**Sequence of images**: If the dataset is not a single file but a sequence of files (all images have to be in the same folder), select `File → Import → Image Sequence`, browse to the first image of the sequence and press `Open`. The image sequence can also be opened with the Bio-Formats Importer plugin, which is available at [Bio-Formats website](http://www.openmicroscopy.org/site/products/bio-formats). Please be sure to use the latest version of Bio-Formats. For large datasets, use the `Virtual stack` option.


## Step 2 - Camera settings ##
Set the camera pixel size in the sample plane, conversion factor between photons and digital units, base level offset of the camera digitizer, and EM gain of the camera. This is important for fitting methods and for estimation of localization accuracy, as these methods work with photon counts. Guidelines for the choice of parameters are described [here](Guidelines#Camera.md).

`Plugins → ThunderSTORM → Camera setup`

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-camera_setup.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-camera_setup.png)


## Step 3 - Processing the data ##
After opening the data, select `Plugins → ThunderSTORM → Run analysis`. There is a good deal of freedom for experienced users to set the processing parameters. However, the default options provide very good results on various datasets we have tried. Press `OK` to start the processing. An instant preview of the results achieved so far during the data analysis is shown. The table with results appears after the data analysis is finished.

Note that processing can be limited to an arbitrarily-shaped, user-specified region of interest in the input images. This can greatly speed up the processing in large data sets. The processing can be stopped at any point by pressing `Escape` on the keyboard.

Details about the algorithms used are accessible by clicking the particular help button ![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/question-button-icon.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/question-button-icon.png), or in the [Supplementary Note "Methodology and Algorithms"](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/paper/SupplementaryNote.pdf).  Guidelines for the choice of parameters are described [here](Guidelines#Processing_the_data.md).

`Plugins → ThunderSTORM → Run analysis`

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-run_analysis.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-run_analysis.png)


## Step 4 - Post-processing the data ##
The visualized super-resolution image and the table of localized molecules can be used as the final result. However, some of the fitted parameters might be estimated with poor quality due to noise in the input images, or for example, due to sample drift. [Post-processing methods](PostProcessing.md) can be used to correct for this. Guidelines for the choice of parameters are described [here](Guidelines#Post-processing.md).

`Plugins → ThunderSTORM → Import/Export → Show results table`

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-results_table.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-results_table.png)


## Step 5 - Visualization ##
Visualization involves creation of a new, high resolution image based on the previously obtained sub-diffraction molecular coordinates. Several methods have been implemented for both 2D and slice-by-slice 3D visualization. Choose one of them to visualize the data. Note that the table of results has to be open. Super-resolution images can be saved in any image format provided by ImageJ. Guidelines for the choice of parameters are described [here](Guidelines#Visualization.md).

`Plugins → ThunderSTORM → Visualization`

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-visualization.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-visualization.png)


## Step 6 - Importing / Exporting the table of results ##
ThunderSTORM can be used for post-processing and visualization of molecular localizations acquired also by other localization software. To allow easy interaction with other SMLM analysis packages, molecular localizations can be imported/exported to/from ThunderSTORM in various file formats, such as: CSV, XSL, XML, YAML, JSON, Google protocol buffer, and Tagged Spot File (TSF) format. Note that file format is recognized by the file extension.

`Plugins → ThunderSTORM → Import/Export → Import results`

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-import.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-import.png)

`Plugins → ThunderSTORM → Import/Export → Export results`

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-export.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-export.png)