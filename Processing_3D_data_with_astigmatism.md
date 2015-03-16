# Processing 3D data (astigmatism method) #

This is an example of the workflow for processing 3D SMLM data in ThunderSTORM in which the axial molecular position is estimated from astigmatism introduced by a cylindrical lens. The procedure is basically the same as in the case of processing 2D data. The only difference is that calibration of the system needs to be performed prior to processing the data in order to estimate the axial positions. An example of (a fragment of) one of our 3D real datasets is provided [here](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/data/12%20+%20cyl%20lens.zip).




## Step 1 - Calibration of the system ##
Load the calibration data and select `Plugins → ThunderSTORM → 3D calibration → Cylindrical lens calibration`. The default settings for processing the data should be fine to use. Specify the `Z stage step` (10 nm in our example dataset), `Z range limit` (range to limit the fitted polynomial models on each side of the in-focus position), and a location where the result of the calibration will be saved. Then click OK to run the calibration. All necessary information for 3D fitting is saved in the YAML file format. Guidelines for the choice of parameters are described [here](Guidelines#3D_calibration.md).

Note that calibration can be limited to a user-specified region of interest in the input images.

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-3D_calibration.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-3D_calibration.png)

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-astigmatism-calibration.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-astigmatism-calibration.png)


## Step 2 - Processing the data ##
Load the image sequence acquired with the cylindrical lens and select `Plugins → ThunderSTORM → Run analysis`. In the panel `Sub-pixel localization of molecules`, select `PSF: Elliptical Gaussian (3D astigmatism)` and specify the path to the `Calibration file` generated in Step 1. Click `OK` to run the analysis. Guidelines for the choice of parameters are described [here](Guidelines#Processing_the_data.md).


## Step 3 - Visualization ##
Make sure that the 3D option is enabled in the visualization dialog to get 3D super-resolution images, otherwise only a 2D image will be visualized. Specify the `Z range` to set the axial range and the step between slices. Enable the `Colorize z-stack` option to get a color-coded z-position (implemented as an ImageJ hyper-stack of channels with different lookup tables). Then Click `OK`. Guidelines for the choice of parameters are described [here](Guidelines#Visualization.md).

Note that variable uncertainty of the localized molecules in an axial direction can be visualized if the results table contains a column called `uncertainty_z [nm]`. The uncertainty is not calculated automatically at the moment and must to be imported with the data.

## Step 4 - Visualizing a rotating 3D projection (optional) ##
A rotating 3D projection of the data can be generated using ImageJ feature such as `3D Project ...`. The steps are as follows:

  1. Create a slice-by-slice 3D visualization of the data.
  1. Set optimal image contrast using the command `Image → Adjust → Brightness/Contrast (Ctrl+Shift+C)`. Press `Set` from the `B&C` menu to propagate the required minimum and maximum intensity value through the whole Z-stack.
  1. Prior to creating a 3D projection, convert the image stack to 8-bits in the case of grayscale visualization, or to RGB, if the `Colorize z-stack` option was selected. Use commands `Image → Type → 8-bit` or `Image → Type → RGB Color`.
  1. Set units and appropriate size of pixels for the final super-resolution image using the command: `Image → Properties ... (Ctrl+Shift+P)`.
  1. A rotating 3D projection is created using the command `Image → Stacks → 3D Project ...`.![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-3Dproject.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-3Dproject.png)
  1. Text with the rotation angle can be added: `Image → Stacks → Label ...`. In the `Label stack` dialog, set the starting value, step interval, text location, and `Use Overaly`. The text color might need to be changed first using: `Image → Color → Color Picker ... (Ctrl+Shift+K)`.
  1. To add a scale bar, use the command `Analyze → Tools → Scale Bar ...`. Set scale bar length, text size, location, and select `Overlay`.
![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-3Dresult.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-3Dresult.png)