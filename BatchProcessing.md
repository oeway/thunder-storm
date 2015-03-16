To demonstrate how easy it is to work with ImageJ Macro Language we show you how to perform another automated task such like batch processing. First of all we simply record a macro from a sequence of actions we want to perform. Then we place the macro in a loop and run the macro.




## Step 1 - Record a macro ##
In ImageJ’s menu go to `Plugins → Macros → Record...`. Then perform an action you want to make run in a batch, e.g., open an image, run the analysis, and save the results.

This is how the recorded macro might look like:
```
open("D:\\blinking\\data\\storm28.tif");
run("Run analysis", "filter=[Wavelet filter (B-Spline)] scale=2.0 order=3 detector=[Local maximum] connectivity=8-neighbourhood threshold=std(Wave.F1) estimator=[PSF: Integrated Gaussian] sigma=1.6 method=[Weighted Least squares] fitradius=3 mfaenabled=false renderer=[Averaged shifted histograms] magnification=5.0 colorizez=true shifts=2 repaint=50 threed=false");
run("Export results", "filepath=[D:\\blinking\\data\\storm28-results.csv] file=[CSV (comma separated)] frame=true x=true y=true sigma=true intensity=true offset=true bkgstd=true uncertainty=true");
```
Finally, in the `Recorder` window click on `Create` which opens the macro editor.

## Step 2 - Modify the macro ##
Now there are two options. First is simply to copy/paste this macro several times into the macro editor and change the data and results paths. The other options is to make it little more sophisticated and run the macro in a loop which might look like the following:
```
datapaths = newArray("D:\\blinking\\data\\storm28.tif","D:\\blinking\\data\\storm29.tif");
respaths = newArray("D:\\blinking\\data\\storm28.csv","D:\\blinking\\data\\storm29.csv");

for(i = 0; i < datapaths.length; i++) {
	open(datapaths[i]);
	run("Run analysis", "filter=[Wavelet filter (B-Spline)] scale=2.0 order=3 detector "+
	    "detector=[Local maximum] connectivity=8-neighbourhood threshold=std(Wave.F1) "+
	    "estimator=[PSF: Integrated Gaussian] sigma=1.6 method=[Weighted Least squares] fitradius=3 mfaenabled=false "+
	    "renderer=[Averaged shifted histograms] magnification=5.0 colorizez=true shifts=2 "+
	    "repaint=50 threed=false");
	run("Export results", "filepath=["+respaths[i]+"] file=[CSV (comma separated)] "+
	    "frame=true x=true y=true sigma=true intensity=true offset=true bkgstd=true uncertainty=true");
}
```
Then adding more datasets to process is really simple since you only need to add paths into the arrays in the two top lines of the macro.

## Step 3 - Run the macro ##
When the macro is complete you can just save it (in macro editor go to `File → Save`) and run it (press `Ctrl+R` or in macro editor go to `Macros → Run Macro`).