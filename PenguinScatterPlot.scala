package Prac13

import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._

import java.io.File

object PenguinScatterPlot {

  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("penguins_dataset - penguins.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Separate data by species
    val adelie = data.filter(_("species") == "Adelie")
    val chinstrap = data.filter(_("species") == "Chinstrap")
    val gentoo = data.filter(_("species") == "Gentoo")

    // Function to extract X and Y values
    def extractXY(rows: List[Map[String, String]]) = {
      val x = DenseVector(rows.map(_("culmen_length_mm").toDouble).toArray)
      val y = DenseVector(rows.map(_("flipper_length_mm").toDouble).toArray)
      (x, y)
    }

    val (xAdelie, yAdelie) = extractXY(adelie)
    val (xChinstrap, yChinstrap) = extractXY(chinstrap)
    val (xGentoo, yGentoo) = extractXY(gentoo)

    // Create Scatter Plot
    val fig = Figure()
    val plt = fig.subplot(0)

    plt.title = "Penguin Species Scatter Plot"
    plt.xlabel = "Culmen Length (mm)"
    plt.ylabel = "Flipper Length (mm)"

    plt += plot(xAdelie, yAdelie, '.', name = "Adelie", colorcode = "blue")
    plt += plot(xChinstrap, yChinstrap, '.', name = "Chinstrap", colorcode = "green")
    plt += plot(xGentoo, yGentoo, '.', name = "Gentoo", colorcode = "red")

    plt.legend = true

    fig.refresh()
  }
}
