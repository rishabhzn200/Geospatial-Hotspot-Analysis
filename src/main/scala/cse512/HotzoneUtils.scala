package cse512

object HotzoneUtils {

  def ST_Contains(queryRectangle: String, pointString: String ): Boolean = {
    // Return true is the point is inside the rectangle

    val Array(x1, y1, x2, y2) = queryRectangle.split(",").map(x => x.toDouble)
    val xmin = math.min(x1, x2)
    val xmax = math.max(x1, x2)

    val ymin = math.min(y1, y2)
    val ymax = math.max(y1, y2)

    // Get the co-ordinate of the point
    val Array(xp, yp) = pointString.split(',').map(x => x.toDouble)

    // Check if the point is inside the rectangle
    if ( (xp>=xmin) && (xp<=xmax) && (yp>=ymin) && (yp<=ymax) ){
      return true
    }

    return false
  }
}







