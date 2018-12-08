# Geospatial Hotspot Analysis


## Requirement

In this phase, you are required to do spatial hot spot analysis. In particular, you need to complete two different hot spot analysis tasks 


### Hot zone analysis
 A range join operation is performed on a rectangle datasets and a point dataset. For each rectangle, the number of points located within the rectangle areobtained. The hotter rectangle means that it include more points. So this finds the hotness of all the rectangles. 

### Hot cell analysis

#### Description
Spatial statistics is applied to spatio-temporal big data in order to identify statistically significant spatial hot spots using Apache Spark. This is taken from ACM SIGSPATIAL GISCUP 2016.

The Problem Definition page is here: [http://sigspatial2016.sigspatial.org/giscup2016/problem](http://sigspatial2016.sigspatial.org/giscup2016/problem) 

A Spark/Scala program is implemented to calculate the Getis-Ord statistic or "**Hot cell analysis**" of NYC Taxi Trip datasets. 

1. The input is a monthly taxi trip dataset from 2009 - 2012. For example, "yellow\_tripdata\_2009-01\_point.csv", "yellow\_tripdata\_2010-02\_point.csv".
2. Each cell unit size is 0.01 * 0.01 in terms of latitude and longitude degrees.
3. 1 day is used as the Time Step size. The first day of a month is step 1. Every month has 31 days.
4. Only consider Pick-up Locations are considered.


spark-submit parameters example:
```
test/output hotzoneanalysis src/resources/point-hotzone.csv src/resources/zone-hotzone.csv hotcellanalysis src/resources/yellow_trip_sample_100000.csv
```



1. Point data: the input point dataset is the pickup point of New York Taxi trip datasets. The data format of this phase is the original format of NYC taxi trip which is different from the previous phase. But the coding template already parsed it for you. Find the data from our asu google drive shared folder: [https://drive.google.com/drive/folders/1W4GLKNsGlgXp7fHtDlhHEBdLVw_IuAXh?usp=sharing](https://drive.google.com/drive/folders/1W4GLKNsGlgXp7fHtDlhHEBdLVw_IuAXh?usp=sharing)

2. Zone data (only for hot zone analysis): at "src/resources/zone-hotzone" of the template

#### Hot zone analysis
The input point data can be any small subset of NYC taxi dataset.

#### Hot cell analysis
The input point data is a monthly NYC taxi trip dataset (2009-2012) like "yellow\_tripdata\_2009-01\_point.csv"

### Output data format

#### Hot zone analysis
All zones with their count, sorted by "rectangle" string in an ascending order. 

```
"-73.795658,40.743334,-73.753772,40.779114",1
"-73.797297,40.738291,-73.775740,40.770411",1
"-73.832707,40.620010,-73.746541,40.665414",20
```


#### Hot cell analysis
The coordinates of top 50 hotest cells sorted by their G score in a descending order.

```
-7399,4075,15
-7399,4075,29
-7399,4075,22
```