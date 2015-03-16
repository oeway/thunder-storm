# Specifying a threshold for approximate detection of molecules #



ThunderSTORM uses a single-valued intensity threshold which is updated for every raw input image and applied to the filtered image. The threshold value can be specified by users as an expression combining mathematical functions and operators with variables based on the current raw or filtered image. This is a powerful option, because users can specify the threshold value systematically for unknown input images, where the global intensity may slowly fluctuate over time.

Use the Preview button in the Run analysis window to see which molecules are detected with the current settings. This can be useful for fine-tuning the threshold value given by the formula.

![https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-thresholding.png](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/wiki/screenshot-thresholding.png)

The formula interpreter provides several built-in statistical functions and some predefined variables. A brief description of the syntax and semantic rules follows:


## Examples ##
  * `150`
  * `3*std(Wave.F1)`
  * `0.2+min(F)`


## Types of variables ##
  * Scalar → a number
  * Matrix → an image


## Main syntax rules ##
  * all names are case insensitive
  * use a period (`.`) as a decimal point delimiter


## Built-in operators ##
  * `a + b` → addition
  * `a – b` → subtraction
  * `a * b` → multiplication
  * `a / b` → division
  * `a % b` → modulo
  * `a ^ b` → `b`-th power of `a`

Semantics (all matrix operations are performed element-wise; matrices must have the same size):
  * `scalar + scalar = scalar`
  * `matrix + matrix = matrix`
  * `matrix + scalar = matrix` (scalar is added to each element of the matrix)


## Built-in functions ##
  * `var(x)` → variance of `x`
  * `std(x)` → standard deviation of `x`
  * `mean(x)` → mean value of `x`
  * `median(x)` → median of `x`
  * `min(x)` → minimum in `x`
  * `max(x)` → maximum in `x`
  * `sum(x)` → sum of all items in `x`
  * `abs(x)` → absolute value of `x`


## Variables provided by different feature enhancement filters ##
All the filters provide these variables:
  * `I` → input image without any changes
  * `F` → final filtered image

Variables representing the filters:
  * `Box.F` → Averaging (Box) filter
  * `Med.F` → Median filter
  * `Gauss.F` → Gaussian filter
  * `LowGauss.F` → Lowered Gaussian filter
  * `DoG.F` → Difference-of-Gaussians filter
    * `DoG.G1` → Input image filtered by 1st Gaussian function of DoG filter
    * `DoG.G2` → Input image filtered by 2nd Gaussian function of DoG filter
  * `DoB.F` → Difference of averaging filters
    * `DoB.B1` → Input image filtered by 1st Box of DoB filter
    * `DoB.B2` → Input image filtered by 2nd Box of DoB filter
  * `Wave.F` → Wavelet filter
    * `Wave.F1` → 1st Wavelet level of the input image
    * `Wave.F2` → 2nd Wavelet level of the input image