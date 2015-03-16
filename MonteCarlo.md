# Simulation engine #

This page provides information on:


## Generator of simulated data ##
ThunderSTORM is capable of generating a realistic sequence of SMLM-like images in which the ground-truth positions of the molecules are known. Users can specify the sequence stack size, PSF function used to create the imaged molecules (including 3D models), and ranges for the intensity and FWHM of the imaged molecules. The spatial density of the molecules and the mean photon background intensity can be varied according to user-supplied grayscale images (masks).

`Plugins → ThunderSTORM → Performance testing → Generator of simulated data`
![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-data_generator.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-data_generator.png)


## Performance evaluation ##
If ground truth molecular positions are available, ThunderSTORM can evaluate the performance of the processing methods. Ground truth data can be created by the generator of simulated data described above, or imported from a file by selecting `Plugins → ThunderSTORM → Import/Export → Import ground-truth`. To start evaluation, select `Plugins → ThunderSTORM → Performance testing → Performance evaluation`, enter the tolerance radius for accepting true-positive detections, and click OK. The results indicate true-positive detections (green), false positive detections (red), false negatives (orange), and related statistical measures (e.g., recall, precision, F1 score).

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-performance_evaluation.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-performance_evaluation.png)


## Monte-Carlo simulations - an example using the ImageJ Macro Language ##
Any procedure in ThunderSTORM can be automated using the ImageJ Macro Language. Here we show an example of a Monte-Carlo simulation which searches for an optimal threshold value for different configurations of various localization algorithms. This example can be easily extended to any other combination of functions, such as automated sequential processing of several different datasets.

To get more information about the ImageJ Macro Language, see the [official documentation](http://rsb.info.nih.gov/ij/developer/macro/macros.html).

Note that results of the simulation can be further improved by using a more complex threshold for approximate molecular localizations, or by more complex post-processing rules.

```
requires("1.46c");	// because of `Array.concat` and `Array.slice` functions

run("Generator of simulated data", "psf=[Integrated Gaussian] maskpath= height=256 "+
    "density=0.3 frames=1000 width=256 addpoisssonvar=30.0 driftangle=0.0 driftdist=0.0 "+
    "intensityrange=700:900 singlefixed=false");

filters = newArray("[Lowered Gaussian filter] sigma=1.6",
                   "[Difference-of-Gaussians filter] sigma1=1.3 sigma2=1.8",
                   "[Wavelet filter (B-Spline)] scale=2.0 order=3");
colors = newArray("red","blue","magenta");
labelX = newArray(0.05, 0.33, 0.7);
labelText = newArray("Lowered Gaussian","Difference Of Gaussians","Wavelet");

xvals = newArray();
yvals = newArray();

len = 9;
fmax = 3;

for(f = 0; f < fmax; f += 1) {
    run("Clear Results");
    for(thr = 1.0, r = 0; thr <= 5.0; thr += 0.5, r += 1) {
        selectWindow("Artificial dataset");
        run("Run analysis", "filter="+filters[f]+
            " detector=[Local maximum] connectivity=8-neighbourhood threshold="+thr+
            "*std(F) estimator=[PSF: Integrated Gaussian] sigma=1.6 "+
            "method=[Least squares] fitradius=3 mfaenabled=false renderer=[No Renderer]");
        run("Performance evaluation","tolerance=50");
        setResult("Threshold multiplier",r,thr);
        xvals = Array.concat(xvals, thr);
        yvals = Array.concat(yvals,getResult("F1-measure",r));
    }
}

Plot.create("Simulation results", "threshold multiplier", "F1-measure");
Plot.setLimits(1.0,5.0,0.0,1.0);
for(f = 0; f < fmax; f += 1) {
    x = Array.slice(xvals,f*len,(f+1)*len);
    y = Array.slice(yvals,f*len,(f+1)*len);
    Plot.setColor(colors[f]);
    Plot.add("line",x,y);
    Plot.add("x",x,y);
    Plot.addText(labelText[f], labelX[f], 0.1);
}
Plot.show();
```
![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/simulation_result.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/simulation_result.png)