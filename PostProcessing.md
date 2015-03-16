# Post-processing modules #

ThunderSTORM provides a set of post-processing methods to correct and optimize the results of the analysis. This step is optional but highly recommended. The order of processing steps is user-specified. with a possibility to undo the last change or to restore the original values. All of the post-processing methods are described in detail in the [Supplementary Note "Methodology and Algorithms"](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/paper/SupplementaryNote.pdf). Guidelines for the choice of parameters are described [here](Guidelines.md).

List of the built-in post-processing modules:



## Filter ##
It is a common practice to remove molecules localized with poor precision, or with unrealistic parameters. The filtering criteria can be formulated in the `Filter` text field as an expression combining mathematical and logical functions and operators with parameters from the table of results obtained from previous data analysis.

The syntax and semantics rules for the formula interpreter are similar to the ones used in the threshold selection described in [Thresholding](Thresholding.md). The variables are scalars and vectors, where vectors are columns in the table of results specified by their names without units, e.g., `id`, `frame`, `x`, `y`, `sigma`, `intensity`, `offset`, `bkgstd`, `uncertainty`. The result of the formula must be a vector of booleans (true or false) for every molecule in the table. Only molecules with the condition equal to true are accepted. Built-in functions are the same as in [Thresholding](Thresholding.md), plus there are two logical functions `a & b` (AND) and `a | b` (OR).

_Examples of filter expressions_
  * `sigma > 15`
  * `frame < 1000 | frame > 2000`
  * `intensity > 100 & uncertainty < 20`
  * `(x-10000)^2 + (y-10000)^2 < 2000^2`

_Example of creating a filter expression using a histogram_
  1. Select Plot histogram and select the parameter of choice (e.g., `sigma`).
  1. Draw a rectangular selection in the histogram specifying the minimum and the maximum accepted value and press `Apply ROI to filter`. This inserts the formula into the `Filter` text field automatically.
  1. Press `Apply` to apply the filtering rule and to update the image preview.

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-postprocessing-filter.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-postprocessing-filter.png)

## Local density filtering ##
Local density filtering effectively removes noise created by, e.g., isolated localizations. The parameters set by users are the radius for neighbors searching, and the minimum numbers of neighbors necessary to keep the localization event. Localization event is discarded if the number of neighbors is below this threshold.

## Remove duplicates ##
Repeated localizations of single molecules in one frame may occur due to overlapping fitting sub-regions when using multiple-emitter analysis approach. ThunderSTORM allows users to specify a radius below which molecules in close proximity are grouped together. Only the molecule with the smallest localization uncertainty in the group is kept, other molecules are removed. The radius can be specified as a mathematical expression which yields a scalar (same radius for all molecules) or a vector (different radius for every molecule).

_Examples of expressions_
  * `50`
  * `5*mean(uncertainty)`
  * `3*uncertainty`


## Merging ##
A single photo-activated molecule may appear in several sequential images, then disappear for several frames, appear again, and finally bleach completely. Such molecules can be combined into one single molecule based on a user-specified distance within which molecules are merged together in the subsequent frames, and the allowed number of frames in which the molecule can disappear. Users can also specify a maximum number of consecutive frames such that a repeating event is still considered a single molecule, see the timing diagram. After merging, a new column called detections appears in the table of results, showing the number of merged molecules. This new column can also be used for [filtering](PostProcessing#Filter.md).

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-postprocessing-merging.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-postprocessing-merging.png)

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/thunderstorm_merge.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/thunderstorm_merge.png)


## Drift correction ##
ThunderSTORM supports lateral drift correction using the cross-correlation method and by tracking of fiducial markers. The drift trajectory can also be saved/loaded to/from a file.

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-postprocessing-drift.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-postprocessing-drift.png)


## Z-stage offset ##
The axial field of view in 3D experiments can be extended by acquiring the data in multiple Z-stage positions. This method corrects the absolute axial position of each molecule.


## Coordinate based co-localization ##
ThunderSTORM provides a module for analysis of dual-color super-resolution images which applies coordinate based co-localization. The input data for channels 1 and 2 are provided as a table of results and a ground-truth table, respectively. Computed values with the co-localization coefficient, with the distance to the nearest neighbor, and with the number of neighbors within the user-specified radius, are displayed in the table of results as new columns.

`Plugins → ThunderSTORM → Colocalization → CBC`

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-cbc.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-cbc.png)

## Visualizing and exporting molecular localizations from a region of interest ##
  1. Select a rectangular region of interest (ROI) in the live preview image. Note that more regions can be selected at a time while holding a `Shift key` on the keyboard.
  1. Press `Restrict to ROI`. A logical expression defining the ROI will be included in the Filter.
  1. To resize the preview, or to visualize the data in the selected region, press `Visualization`.
  1. Press `Auto size by results` to define the image coordinates based on ROI.
  1. Set the visualization method and the desired magnification.
  1. Press `Use for preview` to crop the live preview image based on ROI.
  1. Or, press `OK` to visualize the final super-resolution image from the selected ROI.
  1. To export data from the ROI, press `Export`.

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-postprocessing-ROI.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-postprocessing-ROI.png)