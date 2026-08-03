package Prac15

import breeze.linalg._
import breeze.plot._
import com.github.tototoshi.csv._
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Practical15 {

  def main(args: Array[String]): Unit = {

    // Read CSV file
    val reader = CSVReader.open(new File("day.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    // Date format
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    // Parse date and rental count
    val parsedData = data.flatMap { row =>
      try {
        val date = LocalDate.parse(row("dteday"), formatter)
        val rentals = row("cnt").toDouble
        Some((date, rentals))
      } catch {
        case _: Throwable => None
      }
    }.sortBy(_._1)

    // Debugging Output
    println("Rows read: " + data.length)
    println("Valid rows: " + parsedData.length)

    println("First 5 parsed rows:")
    parsedData.take(5).foreach(println)

    // X-axis: Day Number
    val x = DenseVector((0 until parsedData.length).map(_.toDouble).toArray)

    // Y-axis: Rental Count
    val y = DenseVector(parsedData.map(_._2).toArray)

    // Create Figure
    val fig = Figure("Bike Rental Trend")
    val plt = fig.subplot(0)

    plt += plot(x, y, name = "Daily Rentals", colorcode = "blue")

    plt.xlabel = "Time (Days)"
    plt.ylabel = "Number of Bike Rentals"
    plt.title = "Daily Bike Rentals Over Time"

    fig.refresh()
  }
}