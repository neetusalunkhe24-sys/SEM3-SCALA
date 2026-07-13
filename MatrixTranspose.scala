package Prac_5

import breeze.linalg._
import scala.util.Random

object MatrixTranspose {

  def main(args: Array[String]): Unit = {
    
    val rows = 4
    val cols = 4
    
    val randData =
      Array.fill(rows * cols)(
        Random.nextInt(15) + 1
      )

    val randMatrix =
      new DenseMatrix(rows, cols, randData)

    println(s"Generated Matrix:\n$randMatrix")
    
    val transposed =
      randMatrix.t

    println(s"\nTranspose Matrix:\n$transposed")
    
    val doubleMatrix =
      randMatrix.map(_.toDouble)
    
    val determinant =
      det(doubleMatrix)

    println(f"\nDeterminant = $determinant%.2f")
  }
}

