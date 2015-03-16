# Comparison to other software packages #




## Performance evaluation on simulated data ##

To quantitatively evaluate the performance of ThunderSTORM compared to other software packages, we used a publicly available simulated dataset “Tubulins I” from the [Single-Molecule Localization Microscopy Challenge](http://bigwww.epfl.ch/smlm/) [[1](Comparison#References.md)]. Because the ground-truth positions of the molecules are known in this data, it is possible to calculate the accuracy of the localization algorithms used to process the data. Please see Section 9 of the Supplementary Note “ThunderSTORM: Methodology and Algorithms” for an explanation of the performance evaluation methods available in ThunderSTORM, and also Section 9 of the User’s Guide for help in using the [performance evaluation](MonteCarlo#Performance_evaluation.md) feature.

The analysis of the “[Tubulins I](http://bigwww.epfl.ch/smlm/datasets/index.html)” dataset was performed by ThunderSTORM, QuickPALM [[2](Comparison#References.md)], and rapidSTORM [[3](Comparison#References.md)]. ThunderSTORM was used with the default settings. RapidSTORM was used with a “difference of averages” filter, “local relative threshold” set to 12, and “compute two kernel improvement” active. The other settings were the defaults. For QuickPALM, we used “local threshold” set to 10% otherwise we used the default settings. The localization accuracy was evaluated by the F1 score, which was calculated as a function of the detection tolerance radius. The results in the figure below indicate that ThunderSTORM achieves the highest F1 score (i.e., the best balance between precision and recall) in every case in this experiment.

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-detection_rate.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-detection_rate.png)

## Performance evaluation on real data ##

The performance of ThunderSTORM, compared to other software packages, was also evaluated on the real dataset “[Tubulin AF647](http://bigwww.epfl.ch/smlm/datasets/index.html?p=tubulin-af647)”, which is publicly available from the [Single-Molecule Localization Microscopy Challenge](http://bigwww.epfl.ch/smlm/) website [[1](Comparison#References.md)]. [ThunderSTORM](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/rendered-thunderstorm-1280x1280-ASH2.png) was used with the default settings. Results for [Octane](http://bigwww.epfl.ch/smlm/datasets/tubulin-af647/rendered-octane-1280x1280.png) [[4](Comparison#References.md)], [QuickPALM](http://bigwww.epfl.ch/smlm/datasets/tubulin-af647/rendered-quickpalm-1280x1280.png) [[2](Comparison#References.md)], and [rapidSTORM](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/rendered-rapidstorm-1280x1280.png) [[3](Comparison#References.md)] are publicly available at the Challenge website. The reconstructed super-resolution images are shown in the figure below.

As the ground-truth data is not available, we compared the results based on the number of detections and based on the final super-resolution images. [Octane](http://bigwww.epfl.ch/smlm/datasets/tubulin-af647/localization-octane.csv) detected 30,831 molecules, [QuickPALM](http://bigwww.epfl.ch/smlm/datasets/tubulin-af647/localization-quickpalm.csv) 37,528 molecules, [rapidSTORM](http://bigwww.epfl.ch/smlm/datasets/tubulin-af647/localization-rapidstorm.csv) 84,157 molecules, and [ThunderSTORM](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/TubulinAF647-resutls.csv.zip) 65,259 molecules. The number of localized molecules indicate low detection rates provided by Octane and QuickPALM. On the other hand, rapidSTORM appears to have too many false positive detections, which are visible in the bottom right corner of the reconstructed super-resolution image. ThunderSTORM lies in between. Note that additional post-processing can be applied to further enhance the results.

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/comparevisual.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/comparevisual.png)

## New Features in ThunderSTORM in relation to existing software solutions ##

ThunderSTORM introduces several new features and concepts for super-resolution experiments. Although some of the algorithms implemented in ThunderSTORM have been published previously in the literature, ThunderSTORM combines them into one comprehensive environment. The main features unique to ThunderSTORM in comparison to QuickPALM ver. 1.1 [[2](Comparison#References.md)], rapidSTORM ver. 3.3 [[3](Comparison#References.md)], Octane ver. 1.5.1 [[4](Comparison#References.md)], GraspJ ver. 1.1.2 [[5](Comparison#References.md)], and a few other software packages we have tested, are as follows.

#### Highly flexible data analysis ####
One of the main contributions is the ability to use any combination of the implemented feature-enhancing filters and spot detection methods for approximate localization of the molecules. Each possible combination represents an algorithm for detection of molecules in the raw data. Many of these algorithms have not been reported in the literature as unique solutions and are therefore novel in ThunderSTORM. Moreover, some of the existing software solutions for SMLM data analysis offer little or no details about whether or how these critical steps are performed. A feature exclusively unique to ThunderSTORM is the possibility to specify the threshold for detection of molecules using a mathematical expression. This allows computing the threshold value systematically for unknown input images, where the global intensity may slowly fluctuate.

Sub-pixel localization of molecules allows a choice between LSQ, WLSQ and MLE fitting methods in combination with any of the implemented PSF models. Available are also much faster PSF model-free methods, and an option not to use sub-pixel localization. To process high density SMLM data, multiple emitter fitting analysis can be performed with any of the implemented PSF models.

#### Highly flexible post-processing with a live preview ####
ThunderSTORM offers a much higher degree of user-interactivity during data post-processing compared to other SMLM software packages we have evaluated. Users can define the order of post-processing methods arbitrarily with an option to undo the last operation, or to restore the original values. Advanced filtering methods allow one to remove molecules from the results by selecting ranges of the variables in the relevant histograms. Users can also enter mathematical expressions directly into the filter dialog. Other post-processing methods can, for example, remove molecules with small local density, merge molecules in subsequent frames, wherein molecules can disappear for few frames, or remove duplicated molecules based on a mathematical expression. ThunderSTORM also supports lateral drift correction with an option to load/save the drift trajectory from/to a file. A live preview is available so users can see the effect of the post-processing settings immediately. Post-processing incorporates also methods for coordinate based co-localization of dual-color images.

#### Visualization of super-resolution images ####
ThunderSTORM offers several visualization techniques, including the proposed method based on averaged shifted histograms, and a previously published method based on jittered histograms [[6](Comparison#References.md)].

#### Import/export of molecular localizations ####
Molecular localization can be imported/exported in several file formats. This allows post-processing and visualization of data obtained also by other software packages.

#### Sophisticated simulation engine ####
ThunderSTORM can generate realistic sequences of SMLM-like images in which the ground-truth positions of the molecules are known. Molecular density and the mean photon background intensity can be varied in different parts of image by user-specified gray-scale masks. Noise in the data simulates photon counting properties of CCD or EMCCD cameras. When the localization data and the ground-truth positions of molecules are available, ThunderSTORM can evaluate the performance of localization algorithms by several statistical measures. Monte-Carlo simulations can be performed using the ImageJ macro language.

#### Extensive documentation ####
All algorithms and processing steps are described in detail in the Supplementary Note “ThunderSTORM: Methodology and Algorithms”. Also this User’s Guide provides overall information about how to use ThunderSTORM and how to set the parameters.

## References ##

  1. Benchmarking of Single-Molecule Localization Microscopy Software (2013). Biomedical Imaging Group, Ecole Polytechnique Fédérale de Lausanne (EPFL), Lausanne, Switzerland. Available at http://bigwww.epfl.ch/smlm/.
  1. R. Henriques, M. Lelek, E. F. Fornasiero, F. Valtorta, C. Zimmer, M. M. Mhlanga. QuickPALM: 3D real-time photoactivation nanoscopy image processing in ImageJ. Nature Methods 7, pp. 339-340 (2010). QuickPALM is available at https://code.google.com/p/quickpalm/.
  1. S. Wolter, M. Schüttpelz, M. Tscherepanow, S. van de Linde, M. Heilemann, M. Sauer. Real-time computation of subdiffraction-resolution fluorescence images. Journal of Microscopy 237(1), pp. 12-22 (2010). RapidSTORM is available at	http://www.super-resolution.biozentrum.uni-wuerzburg.de/research_topics/rapidstorm/.
  1. L. Niu and J. Yu. Investigating intracellular dynamics of FtsZ cytoskeleton with photoactivation single-molecule tracking. Biophysical Journal 4 (2008). Octane is available at https://github.com/jiyuuchc/Octane.
  1. N. Brede and M. Lakadamyali. GraspJ: an open source, real-time analysis package for super-resolution imaging. Optical Nanoscopy 1, 2012. GraspJ is available at https://code.google.com/p/graspj/.
  1. P. Křížek, I. Raška, G. M. Hagen. Minimizing detection errors in single molecule localization microscopy. Optics Express 19(4), pp. 3226-35 (2011).