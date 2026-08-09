import scala.io.Source
import breeze.linalg._
import breeze.plot._

object p2 {

  def main(args: Array[String]): Unit = {

    val stream = getClass.getResourceAsStream("/TCS.NS.csv")

    if (stream == null) {
      println("CSV file not found!")
      return
    }

    val file = Source.fromInputStream(stream)

    val closePrice = file.getLines().drop(1).flatMap { line =>

      val cols = line.split(",")

      if (cols.length >= 5)
        cols(4).trim.toDoubleOption
      else
        None

    }.toList

    file.close()

    val window = 5

    // SMA
    val sma = closePrice.sliding(window)
      .map(_.sum / window)
      .toList

    // WMA
    val weights = (1 to window).toList
    val totalWeight = weights.sum.toDouble

    val wma = closePrice.sliding(window).map { values =>
      values.zip(weights).map {
        case (v, w) => v * w
      }.sum / totalWeight
    }.toList

    // EMA
    val alpha = 2.0 / (window + 1)

    var ema = List(closePrice.head)

    for (price <- closePrice.tail) {
      val next = alpha * price + (1 - alpha) * ema.last
      ema = ema :+ next
    }

    // Graph
    val f = Figure()

    val p = f.subplot(0)

    p += plot(
      DenseVector((window - 1 until closePrice.length)
        .map(_.toDouble).toArray),
      DenseVector(sma.toArray),
      name = "SMA"
    )

    p += plot(
      DenseVector((window - 1 until closePrice.length)
        .map(_.toDouble).toArray),
      DenseVector(wma.toArray),
      name = "WMA"
    )

    p += plot(
      DenseVector((0 until ema.length)
        .map(_.toDouble).toArray),
      DenseVector(ema.toArray),
      name = "EMA"
    )

    p.xlabel = "Days"
    p.ylabel = "Close Price"
    p.legend = true

    f.refresh()

    println("========= RESULT =========")
    println(s"Total Records : ${closePrice.length}")
    println("Graph displayed successfully.")
  }
}
