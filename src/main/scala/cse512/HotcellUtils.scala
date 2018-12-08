package cse512

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar

object HotcellUtils {
  val coordinateStep = 0.01

  def CalculateCoordinate(inputString: String, coordinateOffset: Int): Int =
  {
    // Configuration variable:
    // Coordinate step is the size of each cell on x and y
    var result = 0
    coordinateOffset match
    {
      case 0 => result = Math.floor((inputString.split(",")(0).replace("(","").toDouble/coordinateStep)).toInt
      case 1 => result = Math.floor(inputString.split(",")(1).replace(")","").toDouble/coordinateStep).toInt
      // We only consider the data from 2009 to 2012 inclusively, 4 years in total. Week 0 Day 0 is 2009-01-01
      case 2 => {
        val timestamp = HotcellUtils.timestampParser(inputString)
        result = HotcellUtils.dayOfMonth(timestamp) // Assume every month has 31 days
      }
    }
    return result
  }

  def timestampParser (timestampString: String): Timestamp =
  {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    val parsedDate = dateFormat.parse(timestampString)
    val timeStamp = new Timestamp(parsedDate.getTime)
    return timeStamp
  }

  def dayOfYear (timestamp: Timestamp): Int =
  {
    val calendar = Calendar.getInstance
    calendar.setTimeInMillis(timestamp.getTime)
    return calendar.get(Calendar.DAY_OF_YEAR)
  }

  def dayOfMonth (timestamp: Timestamp): Int =
  {
    val calendar = Calendar.getInstance
    calendar.setTimeInMillis(timestamp.getTime)
    return calendar.get(Calendar.DAY_OF_MONTH)
  }

  def IsCellInBounds(x:Double, y:Double, z:Int, minX:Double, maxX:Double, minY:Double, maxY:Double, minZ:Int, maxZ:Int): Boolean =
  {
    if ( (x >= minX) && (x <= maxX) && (y >= minY) && (y <= maxY) && (z >= minZ) && (z <= maxZ) ){
      return true
    }
    return false
  }

  def CheckBoundary(point: Int, minVal: Int, maxVal: Int) : Int={

    if (point == minVal || point == maxVal){
      return 1
    }
    return 0
  }

  def GetNeighbourCount(minX:Int, minY:Int, minZ:Int, maxX:Int, maxY:Int, maxZ:Int, Xin:Int, Yin:Int, Zin:Int): Int ={

    // corner, edge, face, inside
    val pointLocationInCube: Map[Int, String] = Map(0->"inside", 1 -> "face", 2-> "edge", 3-> "corner")
    val mapping: Map[String, Int] = Map("inside" -> 26, "face" -> 17, "edge" -> 11, "corner" -> 7)

    var intialState = 0;

    intialState += CheckBoundary(Xin, minX, maxX)
    intialState += CheckBoundary(Yin, minY, maxY)
    intialState += CheckBoundary(Zin, minZ, maxZ)

    var location = pointLocationInCube.get(intialState).get.toString()

    return mapping.get(location).get.toInt
  }

  def GetGScore(x: Int, y: Int, z: Int, numcells: Int, mean:Double, sd: Double, totalNeighbours: Int, sumAllNeighboursPoints: Int): Double ={
    val numerator = (sumAllNeighboursPoints.toDouble - (mean*totalNeighbours.toDouble))
    val denominator = sd * math.sqrt((((numcells.toDouble * totalNeighbours.toDouble) - (totalNeighbours.toDouble * totalNeighbours.toDouble)) / (numcells.toDouble-1.0).toDouble).toDouble).toDouble
    return (numerator/denominator).toDouble
  }
}
