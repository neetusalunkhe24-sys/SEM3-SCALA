package Prac2

object StatisticCalculator {
  def main(args: Array[String]): Unit = {

    val numbers = List(15, 25, 35, 45, 25, 55, 25)
    println("Numbers: " + numbers)

    val mean = numbers.sum.toDouble / numbers.length
    val sorted = numbers.sorted
    val n = sorted.length

    val median =
      if (n % 2 == 0)
        (sorted(n / 2 - 1) + sorted(n / 2)).toDouble / 2
      else
        sorted(n / 2)

    val frequency = numbers.groupBy(x => x).mapValues(_.size)
    val mode = frequency.maxBy(_._2)._1

    println("Mean = " + mean)
    println("Median = " + median)
    println("Mode = " + mode)

  }
}

