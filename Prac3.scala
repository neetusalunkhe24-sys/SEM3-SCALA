package Prac3

object SnackExpenseAnalysis {
  def main(args: Array[String]): Unit = {
    val expense = List(60, 85, 70, 90, 75, 65, 80, 95, 55, 78)
    
    val mean = expense.sum.toDouble / expense.length
    
    val variance =
      expense.map(x =>
        math.pow(x - mean, 2)
      ).sum / expense.length
    
    val stdDev = math.sqrt(variance)

    println("Snack Expense Dataset: " + expense)
    println("Mean = " + mean)
    println("Variance = " + variance)
    println("Standard Deviation = " + stdDev)
  }
}

