# Guidelines for the choice of parameters #



## Camera ##
As ThunderSTORM uses real units (photons, nanometers), accurate camera parameters need to be supplied by the user. The effective pixel size can be determined from the actual pixel pitch of the camera sensor and from the total magnification of the optical setup, or by performing a spatial calibration using a stage micrometer. The detector response (photoelectrons per A/D count and base level offset of the camera digitizer) can be obtained from the specification sheet supplied by the camera manufacturer or from photon transfer curve measurements (the dependence of the variance on the mean value of pixel intensities), see e.g., [[1, 2](Guidelines#References.md)]. Correct pixel size is important for proper spatial calibration of the rendered images. Camera conversion gain and offset influence simulated images and the estimates of localization precision.

## Processing the data ##
**Image filtering:** The best detection performance is achieved by band-pass filters (Lowered Gaussian, Difference-of-Gaussians, Difference of averaging filters, or Wavelet B-Spline filter). Filter parameters should be set such that the spatial frequency corresponding to the imaged size of the molecules passes through the filter and other frequencies are suppressed. This will depend on the pixel pitch of the camera sensor and on the objective. We recommend using the Wavelet filter (order 3, scale 2) as it achieves good detection results and can be easily used to select a suitable threshold value, see the next step. The default parameters for the Wavelet filter generally work for imaged molecules with sizes (FWHM) in the range of 2 to 5 pixels.

**Approximate localization of molecules and threshold selection:** Our experiments indicate that the local maximum approach with 8-connected neighborhoods provides the highest F1-score compared to other methods. The suggested threshold applied to the filtered images, in the case of the Wavelet filter, usually ranges from 0.5 to 2 times the standard deviation of the 1st wavelet level, e.g., `1*std(Wave.F1)`. Such a value is recommended by Izeddin et al. [[3](Guidelines#References.md)] and works well for typical SMLM data. Increasing the threshold value will lead to less false positive detections at the expense of more missed molecules and vice versa.

**Sub-pixel localization of molecules:** Fitting PSF models by maximum likelihood estimation generally gives slightly better results than fitting by least square methods, particularly when the photon counts are low. Maximum likelihood estimation requires correct setup of the camera parameters (photoelectrons per A/D count and the digitizer offset level). The recommended PSF model, which generally works well, is the integrated Gaussian. The initial size of sigma can be found by running ThunderSTORM on few images of the data sequence. A histogram of the fitted sizes of sigma (in pixels) can help to find the initial value. The size of the fitting radius should be an integer number close to 3\*sigma.

**Multiple emitter fitting analysis:** Multiple emitter fitting analysis (MFA) can be used in high-density data to estimate the number of molecules detected as a single blob. We recommend setting 3 to 5 molecules per fitting region. The stability of the algorithm is improved if the molecular brightness is limited to a realistic range (perhaps 300 to 5000 photons), depending on the sample. This range can be estimated by ThunderSTORM by processing a few frames of the data and evaluating the number of detected photons from each molecule using the histogram function (this relies on correct entry of the camera parameters). The algorithm can also be more stable by forcing all molecules to have the same intensity. Unfortunately, multiple emitter analysis is a computationally costly method, and may run quite slowly depending on the user-specified maximum number of molecules within the fitting area. Note that the size of the fitting radius might need to be increased slightly to accommodate larger blobs.

## Post-processing ##
The order of post-processing steps is user-specified. However, we recommend the following order: Remove duplicates (if MFA was enabled), filtering (to remove outliers), Z-stage offset (if applicable), drift correction, and finally merging.

**Filtering:** Outliers can be removed by using a histogram feature, where a region of interest can be selected and applied to the [filter rules](PostProcessing#Filter.md). Users may need to use a manual range and binning, if outliers are too far from the rest of the data (e.g., one very large value which distorts the automatic histogram function). Check histograms especially for fields: `sigma`, `intensity`, `offset`, and `uncertainty`. More complex filtering rules for minimizing detection errors can be found in [[4](Guidelines#References.md)].

_Examples of filter expressions_
  * `frame<1000 | frame>2000`
  * `sigma>15 & intensity>100 & uncertainty<20`
  * `(x-10000)^2 + (y-10000)^2 < 2000^2`

**Drift correction by cross-correlation:** The parameter “Number of bins” controls the time resolution of the drift trajectory by splitting the image sequence into an appropriate number of bins. Molecular localizations from each bin are used to reconstruct one super-resolution image. “Magnification” controls the lateral resolution of the drift trajectory through the magnification of the reconstructed images. A small number of localized molecules requires a smaller number of bins so that there will be enough data in each sub-sequence. This decreases the time resolution of the drift estimation. A smaller magnification setting can also help to obtain resolvable peaks in cross-correlation images created from images with less data or with unclear structures. Cross-correlation images with detected peaks can be viewed by checking the "Show cross-correlations" checkbox.

**Drift correction by tracking fiducial markers:** Fiducial markers are automatically detected as molecules that stay in the “on” state at one position for a substantial amount of time. The lateral tolerance for identification of a marker is controlled by the setting “Max distance”. The parameter “Min marker visibility ratio” controls the fraction of frames wherein the molecule must be detected to be considered as a fiducial marker. The ratio should be set higher than the longest “on” state for a regular blinking molecule. Values higher than 0.5 might not work due to possibility of missed detections. “Trajectory smoothing factor” controls smoothness of the drift trajectory and ranges from 0 (no smoothing) to 1 (highest smoothing).

Note that analyzing samples with fiducial markers yields localizations of both the blinking fluorophores and the fiducial markers. This may slow down the merging algorithm used for automatic identification of the markers. For faster marker identification, the merging process can be limited to regions containing only the fiducial markers. The drift trajectory can then be saved to a file and applied later to the whole dataset.

## Visualization ##
The proposed average shifted histogram method provides similar results as Gaussian rendering with a fixed sigma, but is orders of magnitude faster. Both methods result in a molecular density map. The Gaussian rendering approach can further show the calculated localization accuracy for each molecule.

## 3D calibration ##
**Approximate localization of molecules and threshold selection:** As the signal to noise ratio is usually higher in the 3D calibration data with fluorescent beads, users should set the threshold, in the case of the wavelet filter, to 5 to 8 times the standard deviation of the 1st wavelet level, e.g., `6*std(Wave.F1)`. The rest of the settings are the same as in 2D data analysis. Use the Preview button to see detections of the calibration beads with the current settings.

## References ##
  1. L. J. Van Vliet, F. R. Boddeke, D. Sudar, I. T. Young. Image Detectors for Digital Image Microscopy, in Digital Image Analysis of Microbes, Modern Microbiological Methods, M. H. F. Wilkinson and F. Schut, Eds. Chichester, United Kingdom: John Wiley & Sons, pp. 37-64 (1998).
  1. J. Janesick, K. Klaasen, T. Elliott. CCD charge collection efficiency and the photon transfer technique, Proc. SPIE, Solid State Imaging Arrays, vol. 570, pp. 7-19 (1985).
  1. I. Izeddin, J. Boulanger, V. Racine, C. G. Specht, A. Kechkar, D. Nair, A. Triller, D. Choquet, M. Dahan, J. B. Sibarita. Wavelet analysis for single molecule localization microscopy. Optics Express 20(3), pp. 2081-95 (2012).
  1. P. Křížek, I. Raška, G. M. Hagen. Minimizing detection errors in single molecule localization microscopy. Optics Express 19(4), pp. 3226-35 (2011).