## Installation ##
ThunderSTORM is written in Java and is distributed under the terms of the GNU GPLv3 license as a plugin for ImageJ. The official download site for the latest version of ImageJ is accessible at http://rsbweb.nih.gov/ij/download.html. Note that ImageJ requires the Java Runtime Environment (JRE) to be installed. If you do not have JRE installed, download a bundled version of ImageJ & JRE for your platform. We recommend using the 64-bit version of Java for a 64-bit operating system.

To install the ThunderSTORM plugin for the first time, [download](Downloads.md) the latest version, rename the file to `Thunder_STORM.jar`, and copy it into the plugins subfolder of your ImageJ installation. The location on a Windows system is `C:\Program Files\ImageJ\plugins\Thunder_STORM.jar`, for example.

To verify successful installation of ThunderSTORM, run ImageJ. There should be an item called `ThunderSTORM` in the `Plugins` menu of ImageJ. If the item does not exist, make sure that the file name `Thunder_STORM.jar` is correct as it has to contain the underscore (`_`) character.


## Compatibility notes ##
  * ThunderSTORM has been tested on Windows XP (32b), Windows 7 (32b/64b), Debian and Ubuntu Linux (32b/64b), and MacOS X 10.8.5.
  * ThunderSTORM is compatible with ImageJ based applications such as Fiji and μManager.
  * ThunderSTORM is compiled for Java 1.6 and is compatible with ImageJ 1.45s or later.


## Input / Output ##
  * Images – all image formats supported by ImageJ and its plugins.
  * Molecular localizations and ground truth data – CSV, XSL, XML, YAML, JSON, Google protocol buffer, and Tagged Spot File (TSF) format.


## Help with ImageJ ##
The official ImageJ documentation is available at http://rsbweb.nih.gov/ij/docs/guide/user-guide.pdf.


## Help with ThunderSTORM ##
To get help with using ThunderSTORM, please consult [User's Guide](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/paper/UsersGuide.pdf) or the project [tutorials](Tutorials.md). To get help related to a specific function, click on a blue icon with a question mark ![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/question-button-icon.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/question-button-icon.png) next to a particular control panel. A detailed description of algorithms used for the data analysis is also available in the [Supplementary Note "Methodology and Algorithms"](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/paper/SupplementaryNote.pdf).


## Initial settings of ImageJ ##
Since data acquired by Single Molecule Localization Microscopy (SMLM) are usually very large (several GB) and processing the data is computationally demanding, we recommend setting the number of processing threads equal to the number of available CPU cores. It is also crucial to set the memory limit to the highest possible value, but at the same time to keep enough memory (~2 GB) for the operating system.

For example, our setup uses a computer with an Intel i5 (4 cores) processor and 32 GB of RAM. We set the number of processing threads to 4 and the memory limit to 30 GB. To decrease memory consumption, load your data as a virtual stack (see ImageJ documentation).

To set the number of processing threads and the memory limit in ImageJ, select `Edit → Options → Memory & Threads`.


## Updating ThunderSTORM ##
ThunderSTORM has an integrated updater. From ImageJ, select `Plugins → ThunderSTORM → Check for updates...` and select the version you want to upgrade (or downgrade) to, and click OK. If the updater cannot be used for some reason, download the latest `Thunder_STORM.jar` file from the project website, and copy it into the plugins subfolder of your ImageJ installation.

Please restart ImageJ before running the updated version of ThunderSTORM.


## Report a bug ##
Prior to submitting a bug report, please upgrade to the latest version of ThunderSTORM and see if the error still occurs. Note that due to recent changes on Google Drive, previous versions of ThunderSTORM (<1.3) cannot be updated automatically using the build-in updater. This has been fixed but you need to install the [latest stable version](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/builds/stable/latest.html) manually.

If you receive an exception that ImageJ ran out of memory, try to increase the memory limit or to load the SMLM data as a virtual stack (see [ImageJ documentation](http://rsb.info.nih.gov/ij/docs/guide/146-8.html)).

In the case of a malfunction, please let us know by using the [Google Code issue tracking system](https://code.google.com/p/thunder-storm/issues/list). Specify the malfunction, and describe the procedure to reproduce the error. If an exception occurs, copy the stacktrace into the description box and specify your platform, i.e., your version of Java, ImageJ, ThunderSTORM, and operating system.