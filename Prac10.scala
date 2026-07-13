package Prac_10

// Filter rows where "IMDb_Rating" > 8

import com.github.tototoshi.csv._
import java.io.File

object Neetu_Prac10 {
  def main(args: Array[String]): Unit = {

    val reader = CSVReader.open(new File("Movie_Ratings.xlsx - Sheet.csv"))
    val data = reader.allWithHeaders()
    reader.close()

    val threshold = 8
    
    val filteredRows = data.filter { row =>
      row.get("IMDb_Rating").exists(value => value.toDoubleOption.exists(_ > threshold))
    }

    println(s"\nTotal Rows with IMDb Rating > $threshold: ${filteredRows.length}\n")
    
    filteredRows.foreach { row =>
      println(row.values.mkString(", "))
    }
  }
}

