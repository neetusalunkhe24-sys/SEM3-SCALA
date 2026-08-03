package Practical16

import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Practical16 {

  def main(args: Array[String]): Unit = {

    // Read CSV file
    val reader = CSVReader.open(new File("day.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Date format
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    // Parse Date and Bike Rental Count
    val parsedData = data.flatMap { row =>
      try {
        val date = LocalDate.parse(row("dteday"), formatter)
        val rentals = row("cnt").toDouble
        Some((date, rentals))
      } catch {
        case _: Throwable => None
      }
    }.sortBy(_._1).take(100)

    // Convert to Breeze vectors
    val x = DenseVector((0 until parsedData.length).map(_.toDouble).toArray)
    val y = DenseVector(parsedData.map(_._2).toArray)

    // Create Figure
    val fig = Figure("Bike Rental - Line + Scatter Plot")
    val plt = fig.subplot(0)

    // Line Plot
    plt += plot(x, y, name = "Daily Rentals Line", colorcode = "blue")

    // Scatter Plot
    plt += plot(x, y, '.', name = "Daily Rentals Points", colorcode = "red")

    // Labels
    plt.xlabel = "Time (Days)"
    plt.ylabel = "Number of Bike Rentals"
    plt.title = "Bike Rentals - Line and Scatter Plot"

    // Display Graph
    fig.refresh()
  }
}
