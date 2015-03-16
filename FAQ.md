# Frequently asked questions #



## Approximate localization of molecules ##
  * _What is_ "`Peak intensity threshold`" _and what are the units?_<br>This can be a number or a mathematical expression, see the <a href='Thresholding.md'>threshold documentation</a>. The units are A/D counts but note that the <b>threshold is applied to the filtered image!</b> Use the preview button in <code>Run Analysis → Preview</code> to see the filtered image and its values.</li></ul>


<h2>Data analysis ##
  * _Message_ "`Camera base level is set higher than values in the image!`" _during the data analysis._<br>This is just a warning that the camera base level (digitizer offset) is set too high. For such a frame, a minimum intensity value of the current image is subtracted from the image instead of using the value specified in the <a href='Guidelines#Camera.md'>camera setup</a>. Data are processed but the estimated photon count will be probably wrong. Please, set the camera base level according to the specification sheet supplied by the camera manufacturer.</li></ul>


<h2>Table with localization results ##
  * _Histogram of variables seems to be empty._<br>Automatic binning uses minimum and maximum value of the variable. This might not work if there are outliers. Try to use manual binning instead.</li></ul>


<h2>Post-processing ##
  * _How to display/export only molecules around a specified center?_<br>Use a <a href='PostProcessing#Filter.md'>filter rule</a> with definition of a disc, e.g.,   <code>(x-10000)^2 + (y-10000)^2 &lt; 2000^2</code>.</li></ul>


<h2>Visualization ##
  * _Regular grid artefacts in the image?_<br>These are false positive detections. To remove them, use higher threshold value for approximate localization of molecules and process the data again, or use a <a href='PostProcessing#Filter.md'>filter post-processing</a> to remove molecules with small sigma and high intensity.<br>
<ul><li><i>Image coordinates shown in ImageJ vs. <code>x</code> and <code>y</code> columns shown in the table with localization results.</i><br>These coordinates match only if <code>Left top x = 0</code> and <code>Left top y = 0</code> in the Visualization ROI setup.<br>
</li><li><i>What is visualized?</i><br>This depends on what post-processing steps were applied. Visualized are values in the current table with localization results.</li></ul>


<h2>Import/Export</h2>
<ul><li><i>CSV</i><br>This is a text file with comma separated values. For import/export with, e.g., MS Excel, please make sure that the system localization settings use a point to separate decimal numbers (not comma as it is in some countries) and that comma is used to separate numbers.<br>
</li><li><i>XLS</i><br>This is a text file with values separated by a tabulator and not a MS Excel spread sheet.<br>
</li><li><i>What is exported?</i><br>This depends on what post-processing steps were applied. Exported are values in the current table with localization results.<br>
</li><li><i>Message</i> "<code>X and Y columns not found in Results table....</code>" <i>during the results import.</i><br>Colum names are case sensitive. Make sure that columns <code>x</code> and <code>y</code> (small letters) are present in the file header.</li></ul>


<h2>Update</h2>
<ul><li>Due to recent changes on Google Drive, previous versions of ThunderSTORM (<1.3) cannot be updated automatically using the build-in updater. This has been fixed but you need to install the <a href='https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/builds/stable/latest.html'>latest stable version</a> manually.</li></ul>


<h2>Report a bug</h2>
<ul><li>See <a href='Installation#Report_a_bug.md'>Installation instructions</a>