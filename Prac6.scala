package Prac_6

import breeze.linalg._

object MatrixSliceAnalysis {

  def main(args: Array[String]): Unit = {

    // Create a 4 x 4 matrix
    val dataMatrix = DenseMatrix(
      (1, 2, 3, 4),
      (5, 6, 7, 8),
      (9, 10, 11, 12),
      (13, 14, 15, 16)
    )

    println(s"Complete Matrix:\n$dataMatrix")

    // Extract rows 1 to 2 and columns 1 to 3
    val selectedMatrix = dataMatrix(1 to 2, 1 to 3)

    println(s"\nSelected Sub-Matrix:\n$selectedMatrix")

    // Row-wise sums
    val rowTotal = sum(selectedMatrix(*, ::))

    println(s"\nRow Sums: $rowTotal")

    // Column-wise sums
    val columnTotal = sum(selectedMatrix(::, *))

    println(s"Column Sums: $columnTotal")
  }
}
