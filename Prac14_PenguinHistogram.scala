package Prac14

import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File

object PenguinHistogram {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("penguins_dataset - penguins.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Extract body_mass_g as a vector of doubles
    val bodyMass = DenseVector(
      data.map(_("body_mass_g").toDouble).toArray
    )

    // Create figure and subplots with different bin sizes
    val fig = Figure("Histogram of Penguin Body Mass")

    val binSizes = List(5, 10, 20)

    for ((bins, idx) <- binSizes.zipWithIndex) {

      val plt = fig.subplot(1, binSizes.length, idx)

      plt += hist(bodyMass, bins)

      plt.title = s"Histogram with $bins bins"
      plt.xlabel = "Body Mass (g)"
      plt.ylabel = "Frequency"
    }

    fig.refresh()
  }
}
