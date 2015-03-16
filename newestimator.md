# Introduction #

In this tutorial we will extend ThunderSTORM by creating a simple estimator that uses least-squares fitting of a paraboloid PSF model. To create a new module, implementation of two abstract classes/interfaces must be provided: IEstimatorUI and IEstimator. IEstimatorUI handles reading parameters from GUI or macro and creates the IEstimator. The IEstimator implements the actual localization.


# Writing an implementation of estimator module #

We have to provide implementation of the IEstimator interface. The IEstimator interface has a single method to implement: `estimateParameters`. It takes an image and pixel coordinates of local maxima found during the detection step and returns a list of Molecules. The returned molecules must have at least x and y coordinates and can have other additional parameters.

In our example we will reuse some code for least squares fitting already contained in ThunderSTORM. The class MultipleLocationsFitter is an implementation of the IEstimator interface. It extracts sub-regions of specified size around each local maxima, calls a fitter for each sub-region and collects the results. LSQFitter performs least-squares fitting of a PSFModel on a sub-region. So, to implement least-squares fitting of paraboloids, we only need an implementation of PSFModel for the paraboloid.

## Implementing paraboloid PSFModel ##
We will create subclass of PSFModel. PSFModel has many methods that can be overriden, but only three of them are really needed for our least-squares fitting example:

The `getValue` method should return the PSFModel value at coordinates (`x`,`y`) with fitted parameters `params`. So we provide the formula for our paraboloid model:

![http://chart.apis.google.com/chart?cht=tx&chco=000000&chl=PSF(x,y)=b-\frac{(x-x_0)^2+(y-y_0)^2}{a^2}&nonsense=endswith.png](http://chart.apis.google.com/chart?cht=tx&chco=000000&chl=PSF(x,y)=b-\frac{(x-x_0)^2+(y-y_0)^2}{a^2}&nonsense=endswith.png)


The `newInstanceFromParams` method creates a new Molecule object from the fitted parameters. Molecule object wraps the fitted values with units and a string label for each value.

The `getInitialParams` returns initial values of parameters for optimization.

Other methods are not used in single molecule least-squares fitting.
The full code for our PSFModel implementation looks like this:

```java

public class ParaboloidPSF extends PSFModel{
private static final String[] parameterNames = new  String[]{PSFModel.Params.LABEL_X, PSFModel.Params.LABEL_Y, "a", "b"};

@Override
public double getValue(double[] params, double x, double y) {
return params[3] - ((x-params[0])*(x-params[0]) + (y - params[1])*(y - params[1]))/(params[2]*params[2]);
}

@Override
public double getDoF() {
//used only in multiple-emitter fitting
return 4;
}

@Override
public double[] getInitialSimplex() {
//used only in MLE
return new double[]{0.1,0.1,0.1,0.1};
}

@Override
public double[] getInitialParams(OneLocationFitter.SubImage subImage) {
return new double[]{0,0,1,0};
}

@Override
public Molecule newInstanceFromParams(double[] params, MoleculeDescriptor.Units subImageUnits) {
return new Molecule(new MoleculeDescriptor(parameterNames), params);
}
}
```

There are other methods that can be overridden. For example, a method for analytic jacobian calculation could be provided, instead of the default implementation using numerical calculation. Or a log-likelihood function could be provided for use with MLE fitter.

# Writing GUI handling class #
For the new module to appear in the ThunderSTORM GUI an implementation of the abstract IEstimatorUI has to be created for the module. This class should provide implementation of parsing parameter values from GUI, parsing and recording macros. By default modules use [ParameterTracker](https://googledrive.com/host/0B5BHzluxJ2uFb1hZRy11dV9SSUU/cz/cuni/lf1/lge/thunderstorm/util/macroui/ParameterTracker.html) from [MacroAwareUI](https://bitbucket.org/pepa_borkovec/macroawareui) to handle parameters. You do not have to use it in your module but you would have to implement macro functionality yourself by overriding the relevant methods of IEstimatorUI.

We will add a single integer parameter for the size of the fitting region.
```
ParameterKey.Integer boxSize = parameters.createIntField("boxSize", IntegerValidatorFactory.positiveNonZero(), 1);
```
The above line creates an int parameter with a default value of 1 and a validator that checks that the value is bigger than zero. The returned key is used to access the parameter(e.g. getting its value).

Next, we have to create the GUI to enter the parameters value. In the `getOptionsPanel` method we return a simple JPanel with a JLabel and a JTextField in it. We have to register the text field with our parameter, so that the parameter value is automatically parsed from the text in the text field. The `ParameterTracker#loadPrefs()` call is used to initialize the text field value to the last-used value.

In the `getImplementation` method we return our IEstimator instance as described above.

Here is the code for the UI:
```java

public class ParaboloidFittingUI extends IEstimatorUI {

ParameterKey.Integer boxSize = parameters.createIntField("boxSize", IntegerValidatorFactory.positiveNonZero(), 1);

@Override
public String getName() {
return "Paraboloid Fitting";
}

@Override
public JPanel getOptionsPanel() {
JPanel optionsPane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
optionsPane.add(new JLabel("Box size"));
JTextField textField = new JTextField(20);
optionsPane.add(textField);
boxSize.registerComponent(textField);

parameters.loadPrefs();     //fill in last used values or defaults
return optionsPane;
}

@Override
public IEstimator getImplementation() {
return new MultipleLocationsImageFitting(boxSize.getValue(), new LSQFitter(new ParaboloidPSF(), false));
}
}
```

# Creating service provider configuration file #
The java [ServiceLoader API](http://docs.oracle.com/javase/tutorial/ext/basics/spi.html) is used to load available modules at runtime from classpath. To load your module a service provider configuration file has to be added to the module jar. In the package META-INF/services create a text file named after the fully qualified name of the class you are implementing (`cz.cuni.lf1.lge.ThunderSTORM.estimators.ui.IEstimatorUI` for our example). In that file add a line with fully qualified name of the implementation class (`cz.cuni.lf1.lge.thunderstorm.extensions.ParaboloidFittingUI for our example`). See [ServiceLoader](http://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html) for more information on the configuration file.

# Deploy #
Now just compile and package all the created files in a jar and put it in the directory where ImageJ looks for plugins. On next run, the new estimator should appear as an option for the "Run analysis" command.

# Download files #
You can download source code and the created jar for this example at our [Google drive](https://googledrive.com/host/0BzOGc-xMFyDYR1JaelZYQmJsaUE/extras/extend/).