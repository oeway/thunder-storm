package cz.cuni.lf1.lge.ThunderSTORM.rendering.ui;

import cz.cuni.lf1.lge.ThunderSTORM.util.GridBagHelper;
import cz.cuni.lf1.rendering.HistogramRendering;
import cz.cuni.lf1.rendering.IncrementalRenderingMethod;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Josef Borkovec <josef.borkovec[at]lf1.cuni.cz>
 */
public class HistogramRenderingUI extends AbstractRenderingUI {

  JTextField avgTextField;
  int avg;

  public HistogramRenderingUI() {
  }

  public HistogramRenderingUI(int sizeX, int sizeY) {
    super(sizeX, sizeY);
  }

  @Override
  public String getName() {
    return "Histogram Renderer";
  }

  @Override
  public JPanel getOptionsPanel() {
    JPanel panel = super.getOptionsPanel();

    avgTextField = new JTextField("0", 20);
    panel.add(new JLabel("Averages:"), GridBagHelper.pos(0, GridBagConstraints.RELATIVE));
    panel.add(avgTextField, GridBagHelper.pos(1, GridBagConstraints.RELATIVE));

    return panel;
  }

  @Override
  public void readParameters() {
    avg = Integer.parseInt(avgTextField.getText());
    super.readParameters();
  }

  @Override
  public IncrementalRenderingMethod getMethod() {
    return new HistogramRendering.Builder().roi(0, sizeX, 0, sizeY).resolution(resolution).average(avg).build();
  }

}