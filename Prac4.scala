package Prac4

import breeze.linalg._

object SparseToDenseBreeze {
  def main(args: Array[String]): Unit = {
    val sparseV = SparseVector(0.0, 5.0, 0.0, 3.0, 8.0)

    val denseV1 = DenseVector(sparseV.toArray)

    val denseV2 = DenseVector(1.0, 2.0, 3.0, 4.0, 5.0)

    println(s"Sparse Vector: $sparseV")
    println(s"Dense Vector 1: $denseV1")
    println(s"Dense Vector 2: $denseV2")

    val sum = breeze.linalg.sum(denseV1)

    val mean = breeze.stats.mean(denseV1)

    val dotProduct = denseV1 dot denseV2

    println(f"Sum = $sum%.2f")
    println(f"Mean = $mean%.2f")
    println(f"Dot Product = $dotProduct%.2f")
  }
}

