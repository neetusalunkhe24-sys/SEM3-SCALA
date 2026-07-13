package Prac_7

import breeze.linalg._

object MatrixArithmetic {
  def main(args: Array[String]): Unit = {

    val firstMatrix = DenseMatrix((1.0, 2.0), (3.0, 4.0))
    val secondMatrix = DenseMatrix((5.0, 6.0), (7.0, 8.0))

    val sumMatrix = firstMatrix + secondMatrix
    val diffMatrix = firstMatrix - secondMatrix
    val productMatrix = firstMatrix * secondMatrix
    val quotientMatrix = firstMatrix / secondMatrix

    println("First Matrix:")
    println(firstMatrix)

    println("\nSecond Matrix:")
    println(secondMatrix)

    println("\nAddition Result:")
    println(sumMatrix)

    println("\nSubtraction Result:")
    println(diffMatrix)

    println("\nElement-wise Product:")
    println(productMatrix)

    println("\nElement-wise Quotient:")
    println(quotientMatrix)
  }
}